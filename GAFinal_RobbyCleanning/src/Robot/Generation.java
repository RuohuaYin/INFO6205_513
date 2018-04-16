package Robot;
import EVOLUTION.EVOLUTION;

import java.util.EventListener;

public class Generation {

    public int population;
    public Robot[] generation;


    public Generation(int population){
        this.population = population;
        generation = new Robot[population];
        for(int i=0;i<population;i++){
            generation[i] = new Robot();
        }
    }

    public void geneStatistic(int genePositon){
        double[] number = {0,0,0,0,0,0,0};
        for(int i=0;i<population;i++){
            if(generation[i].gene[genePositon] == 0)
                number[0] += 1;
            else if(generation[i].gene[genePositon] == 1)
                number[1] += 1;
            else if(generation[i].gene[genePositon] == 2)
                number[2] += 1;
            else if(generation[i].gene[genePositon] == 3)
                number[3] += 1;
            else if(generation[i].gene[genePositon] == 4)
                number[4] += 1;
            else if(generation[i].gene[genePositon] == 5)
                number[5] += 1;
            else if(generation[i].gene[genePositon] == 6)
                number[6] += 1;
        }

        double sum=0;
        for(int i=0;i<number.length;i++){
            sum = sum + number[i];
        }
        System.out.println("gene Position: "+genePositon + " ");
        for(int i=0;i<number.length;i++){
            System.out.print("Ratio of "+ i +": "+ number[i]*100/sum+"%  ");
        }
        System.out.println();
    }

    public double getAverageScore(){
        double sum =0;
        for(int i=0;i<generation.length;i++){
            if(generation[i].score > 0)
                sum += generation[i].score;
            else sum+=0.1;
        }
        return sum/generation.length;
    }


    public int getBestScore(){
        int best = 0;
        for(int i=0;i<generation.length;i++){
            if(generation[i].score>best)
                best = generation[i].score;
        }
        return best;
    }

    public int[] getbestScoreLocation(){
        int best = getBestScore();
        int bestCount = 0;
        int[] resultContainer = new int[EVOLUTION.POPULATION];

        for(int i=0;i<population;i++){
            if(generation[i].score==best){
                resultContainer[bestCount] = generation[i].score;
                bestCount++;
            }
        }

        int[] result = new int[bestCount];
        for(int i=0;i<bestCount;i++){
            result[i] = resultContainer[i];
        }
        return result;
    }

    public int[] getGeneByLocation(int location){
        return generation[location].gene;
    }

    public int getWorstScore(){
        int worst = 0;
        for(int i=0;i<generation.length;i++){
            if(generation[i].score < worst)
                worst = generation[i].score;
        }
        return worst;
    }

    public Generation generateNewGeneration(){

        Generation newGeneration = new Generation(population);

        Selection(newGeneration);

        //newGeneration.printGenerationGene();

        Reproduction(newGeneration);

        Mutation(newGeneration);

        return newGeneration;
    }



    public void Selection(Generation newGeneration){
        double sum = 0;
        int best_score = getBestScore();
        int worst_score = getWorstScore();
        double[] selectionRate = new double[population];
        double[] circleRate = new double[population];

        for(int i=0;i<population;i++){
            if(generation[i].score>0)
                sum += generation[i].score;
            else sum += 0.1;
//System.out.println("sum: "+sum);
        }

        for(int i=0;i<population;i++){
            if(generation[i].score>0)
                selectionRate[i]=generation[i].score/sum;
            else
                selectionRate[i]=0.1/sum;
//System.out.println(selectionRate[i]);
        }
        for(int i=0;i<population;i++){
            if(i==0) circleRate[i] = selectionRate[i];
            else circleRate[i] = circleRate[i-1] + selectionRate[i];
//System.out.println("rate: "+circleRate[i]);
        }

        for(int i=0;i<population;i++){
            double a = Math.random();
            for(int j=0;j<population-1;j++){
                if(a<circleRate[0]){
                    newGeneration.generation[i].copyGene(this.generation[j].gene);
                    break;
                }else if(a>=circleRate[j] && a<= circleRate[j+1]){
                    newGeneration.generation[i].copyGene(this.generation[j+1].gene);
                    break;
                }
            }

        }

    }



    public void Reproduction(Generation newGeneration){
        Generation duplicatGeneration = new Generation(newGeneration.population);
        for(int i=0;i<duplicatGeneration.population;i++){
            duplicatGeneration.generation[i].copyGene(newGeneration.generation[i].gene);
        }
        for(int i=0;i<newGeneration.population;i++){
            double a = Math.random();
            if(a<EVOLUTION.ASEXUALRATE){
                Asexual(newGeneration.generation[i]);
            }else{
                int crossWith = (int)(Math.random()*newGeneration.population);
                while(crossWith == i){
                    crossWith = (int)(Math.random()*newGeneration.population);
                }

//System.out.println(i + " with " + crossWith);
                Crossover(newGeneration.generation[i],duplicatGeneration.generation[crossWith]);
            }
        }
    }

    public void Asexual(Robot R){
        for(int i=0;i<R.gene.length;i++){
            double mutate = Math.random();
            if(mutate < EVOLUTION.MUTATIONRATE){
                int newGene = (int)(Math.random()*7);
                R.gene[i]=newGene;
            }
        }
    }

    public void Crossover(Robot r1,Robot r2){
        for(int i=1;i<r1.gene.length;i=i+2){
            r1.gene[i] = r2.gene[i];
        }
    }


    public void Mutation(Generation newGeneration){
        for(int i=0;i<newGeneration.population;i++){
            for(int j=0;j<newGeneration.generation[i].gene.length;j++){
                double mutate = Math.random();
                if(mutate < EVOLUTION.MUTATIONRATE){
                    int newGene = (int)(Math.random()*7);
                    newGeneration.generation[i].gene[j] = newGene;
                }
            }
        }
    }


    public void printGenerationGene(){
        for(int i=0;i<generation.length;i++){
            generation[i].printGene();
            System.out.println();
        }
    }



}
