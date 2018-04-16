package Map;

public class Map {

    public int[][] map;
    public int ROW;
    public int COL;


    public void init_map(int row, int col, double poss){
        ROW = row;
        COL = col;
        map = new int[row+2][col+2];    // the reason to plus 2 is to set up the wall on the top,bottom,left,right

        int i,j;
        for(i=0;i<row+2;i++){
            for(j=0;j<row+2;j++){
                map[i][j]=0;
            }
        }

// set up for walls
        for(i=0;i<row+2;i++){
            map[0][i] = 2;  //the top wall
            map[i][0] = 2;  //the left wall
            map[row+1][i] = 2; // the right wall
            map[i][col+1] = 2;
        }

// place trash
        for(i=1;i<row+1;i++){
            for(j=1;j<col+1;j++){
                double a = Math.random();
                if(Math.random()<poss)
                    map[i][j] = 1;
            }
        }

    }

    public void showMap(){
        int i,j;
        for(i=0;i<ROW+2;i++){
            for(j=0;j<ROW+2;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void showMapInSymbol(){
        int i,j;
        for(i=0;i<ROW+2;i++){
            for(j=0;j<ROW+2;j++){
                if(map[i][j]==2)
                    System.out.print("=");
                if(map[i][j]== 1)
                    System.out.print("*");
                if(map[i][j]== 0)
                    System.out.print(" ");
                System.out.print(" ");
            }
            System.out.println();
        }
    }



}
