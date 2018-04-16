package EVOLUTION;

import Map.Map;
import Robot.*;

public class EVOLUTION {

    public static int EVOLUTIONGENERATION = 2500;
    public static int ROW = 10;
    public static int COL = 10;
    public static double POSSIBILITY = 0.5;
    public static int POPULATION = 200;
    public static int MOVETIMES = 100;
    public static double ASEXUALRATE = 0.1;
    public static double MUTATIONRATE = 0.001;

    public static void main(String[] args){

        //MAP INITIALIZTION
        Map a = new Map();
        a.init_map(ROW,COL,POSSIBILITY);
        a.showMap();
        a.showMapInSymbol();

        // INITIALIZATION

        //Zero Generation Initialization
        Generation zeroG = new Generation(POPULATION);
        for(int i=0;i<POPULATION;i++){
            zeroG.generation[i].init_position(ROW,COL);
            zeroG.generation[i].init_gene();
            zeroG.generation[i].printGene();
            System.out.println();
        }

        for(int N=0;N<EVOLUTIONGENERATION;N++){// each Generation
//System.out.println("Generation: " + (N+1));
            for(int pop=0;pop<POPULATION;pop++){    //each Robot
                zeroG.generation[pop].init_position(ROW,COL);
                a.init_map(ROW,COL,POSSIBILITY);
                zeroG.generation[pop].initializeTraceMap(a.map);
                for(int move=0;move<MOVETIMES;move++){
                    zeroG.generation[pop].singleAction(a.map);
                }
            }
System.out.println(N + " GENERATION BEST SCORE: "+zeroG.getBestScore() + " AVERAGE SCORE: "+ zeroG.getAverageScore());
            //System.out.println(N+","+zeroG.getBestScore()+","+zeroG.getAverageScore());
            zeroG = zeroG.generateNewGeneration();
        }

        int[] bestSecoreLocation = zeroG.getbestScoreLocation();
        System.out.println("Best Gene:");
        for(int i=0;i<bestSecoreLocation.length;i++){
            zeroG.generation[bestSecoreLocation[i]].printGene();
            System.out.println();
        }

        Robot testRobot = new Robot();
        testRobot.copyGene(zeroG.generation[bestSecoreLocation[0]].gene);
//testRobot.init_position(ROW,COL);
        testRobot.pos[0]=5;
        testRobot.pos[1]=5;
        a.init_map(ROW,COL,POSSIBILITY);
        testRobot.initializeTraceMap(a.map);
       // int[][] match
        for(int i=0;i<MOVETIMES;i++){
            System.out.println("STEP: " + i + " SOCORE: "+ testRobot.score);
            testRobot.singleAction(a.map);
            a.showMapInSymbol();
        }


        zeroG.geneStatistic(0);      // 5 empty
        zeroG.geneStatistic(81);    // middle 1
        zeroG.geneStatistic(3);     // right 1
        zeroG.geneStatistic(30);    // right 1 left 1
        zeroG.geneStatistic(59);    // left up most corner and right 1


    }
}
