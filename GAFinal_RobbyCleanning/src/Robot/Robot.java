package Robot;

import com.sun.deploy.trace.Trace;

public class Robot {
        public int view = 5;   // A ROBOT with a view of 5 block

        public int[] pos = new int[2];

        public int score;

        public int[][] TraceMap;

        public int[] gene = new int[(int)Math.pow(3,view)];

        //      4
        //   1  0  3        //  how the genePosition is organized
        //      2

        public Robot(){
//            pos[0] = (int)(1+Math.random()*row);
//            pos[1] = (int)(1+Math.random()*col);
            score = 0;
        }

        public void getGenebyString(String s){
            for(int i=0;i<gene.length;i++){
                gene[i] = Character.digit(s.charAt(i), 10);
            }
        }
        public void copyGene(int[] newGene){
            for(int i=0;i<gene.length;i++){
                this.gene[i] = newGene[i];
            }
        }

        public void init_gene(){
            for(int i=0;i<gene.length;i++){
                gene[i] = (int)(Math.random()*7);
                //System.out.print(gene[i]+" ");
            }
        }

        public void init_position(int row,int col){
            pos[0] = (int)(1+Math.random()*row);
            pos[1] = (int)(1+Math.random()*col);
        }

    //      ***** 0-6 to represent Movement *****
    //                      N:4
    //                       ↑
    //       W:1   ←   CO:6,ST:5,RA:0   →   E:3
    //                       ↓
    //                      S:2
    //       ** ***** ***** ***** ***** ***** **


        public int getGeneByPosition(int position){
            return gene[position];
        }

        public void singleAction(int[][] map){

            int[] state = new int[5];
            state[0] = map[pos[0]][pos[1]];          // Current Position
            state[1] = map[pos[0]][pos[1]-1];        // Left
            state[2] = map[pos[0]+1][pos[1]];       // Bottom
            state[3] = map[pos[0]][pos[1]+1];       // Right
            state[4] = map[pos[0]-1][pos[1]];       // Top

            int genePos = (int)(Math.pow(3,4)*state[0]
                                    +Math.pow(3,3)*state[1]
                                    +Math.pow(3,2)*state[2]
                                    +Math.pow(3,1)*state[3]
                                    +Math.pow(3,0)*state[4]);

            int gene = this.getGeneByPosition(genePos);

//            System.out.println(" gene: "+gene);
//            System.out.println(" pos before: "+pos[0]+" "+pos[1]);

            switch (gene){
                case 0:
                    randomMove(map);
                    break;
                case 1:
                    moveLeft(map);
                    break;
                case 2:
                    moveDown(map);
                    break;
                case 3:
                    moveRight(map);
                    break;
                case 4:
                    moveUp(map);
                    break;
                case 5: //STAY in the block
                    break;
                case 6:
                    collectTrash(map);
                    break;
            }

            recordTrace(map);

//            System.out.println(" pos after: "+pos[0]+" "+pos[1]);
//            System.out.println(" score: "+score);
        }

        public void recordTrace(int[][] map){


        }

        public void moveUp(int[][] map){
            if(map[pos[0]-1][pos[1]] == 2)
                score -= 5;
            else pos[0]--;
        }
        public void moveDown(int[][] map){
            if(map[pos[0]+1][pos[1]] == 2)
                score -= 5;
            else pos[0]++;
        }

        public void moveLeft(int[][] map){
            if(map[pos[0]][pos[1]-1] == 2)
                score -= 5;
            else pos[1]--;
        }

        public void moveRight(int[][] map){
            if(map[pos[0]][pos[1]+1] == 2)
                score -= 5;
            else pos[1]++;
        }

        public void randomMove(int[][] map){
            Double a = Math.random();
            if(a<0.25)
                moveUp(map);
            else if(a<0.5)
                moveDown(map);
            else if(a<0.75)
                moveLeft(map);
            else if(a<1)
                moveRight(map);
        }

        public void collectTrash(int[][] map){
            if(map[pos[0]][pos[1]] == 1){
                score +=10;
                map[pos[0]][pos[1]] = 0;
            }else score -= 3;


        }

        public void printGene(){
            for(int i=0;i<gene.length;i++){
                System.out.print(gene[i]);
            }
        }
    //    @Override
    //    public long hashCode(){
    //        StringBuilder a = new StringBuilder();
    //        for(int i=0;i<gene.length;i++){
    //            a.append(gene[i]);
    //        }
    //        //a.append(gene);
    //        System.out.println(a);
    //        return Integer.parseInt(a.toString());
    //    }
}
