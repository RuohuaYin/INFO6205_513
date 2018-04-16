package Robot;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenerationTest {



    @Test
    public void testGenerationInitilization(){
        Generation g = new Generation(100);
        for(int i=0;i<g.population;i++){
            Assert.assertTrue(g.generation[i].score ==0);
        }
    }

    @Test
    public void testSexualReproduction(){
        Robot r1 = new Robot();
        Robot r2 = new Robot();

        r1.init_gene();
        r2.init_gene();

        Generation g = new Generation(100);
        g.Crossover(r1,r2);
        for(int i=1;i<r1.gene.length;i=i+2){
            Assert.assertTrue(r1.gene[i]==r2.gene[i]);
        }
    }

}