package Robot;
import EVOLUTION.EVOLUTION;
import Map.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTest {

    @Test
    public void copyGene() {
        Robot r1 = new Robot();
        r1.init_gene();
        Robot r2 = new Robot();
        r2.copyGene(r1.gene);
        r1.printGene();
        System.out.println();
        for(int i=0;i<r1.gene.length;i++) {
            Assert.assertEquals(r1.gene[i], r2.gene[i]);
        }
        r1.gene[0]=8;
        Assert.assertNotEquals(r1.gene[0], r2.gene[0]);

    }

    @Test
    public void testInit_Gene(){
        Robot a = new Robot();
        a.init_gene();
        for(int i=0;i<a.gene.length;i++){
            Assert.assertNotEquals(8,a.gene[i]);
        }
    }

    @Test
    public void testMoveUp(){
        Map a = new Map();
        a.init_map(10,10,0.5);
//        a.showMap();
//        a.showMapInSymbol();

        Robot r = new Robot();
        r.pos[0] = 3;
        r.pos[1] = 3;
        r.moveUp(a.map);
        Assert.assertTrue(r.pos[0]-3 == -1);
    }

    @Test
    public void testClean(){
        Robot r1 = new Robot();
        r1.init_position(EVOLUTION.ROW,EVOLUTION.COL);
        r1.getGenebyString("042032146246003105403412441102101563101002603445040312342340250320221123414154445666660100662660463165645145666660012661066662646345416665303563666321406066055134116044163606111036532050435464042516143462001512253354032456466223346362544135421");
        r1.printGene();
        Map a = new Map();
        a.init_map(EVOLUTION.ROW,EVOLUTION.COL,EVOLUTION.POSSIBILITY);
        a.showMapInSymbol();
        for(int i=0;i<100;i++){
            System.out.println("STEP: " + i + " SOCORE: "+ r1 .score);
            r1.singleAction(a.map);
            a.showMapInSymbol();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}