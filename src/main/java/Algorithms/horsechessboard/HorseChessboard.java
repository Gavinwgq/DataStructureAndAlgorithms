package Algorithms.horsechessboard;

import java.awt.*;
import java.util.ArrayList;

/**
 * 马踏棋盘（骑士周游问题）
 * @author wangguoqiang
 * @date 2020/4/19 7:28
 */
public class HorseChessboard {
   private static int X;//棋盘列数
   private static int Y;//棋盘行数
    private static boolean finished;
    private static boolean[] isVitised;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        isVitised = new boolean[X*Y];
        int[][] chessboard = new int[X][Y];
        horseChessboard(chessboard,row-1,column -1,1);
        for (int[] arr : chessboard) {
            for (int n : arr) {
                System.out.print(n+"\t");
            }
            System.out.println();
        }

    }

    /**
     * 根据当前位置计算下一步还可以走的坐标点
     * @param curPoint
     * @return
     */
   private static ArrayList<Point> getNextPoints(Point curPoint){
       ArrayList<Point> ps = new ArrayList<>();
       Point p1 = new Point();
       if((p1.x = curPoint.x-2)>=0 && (p1.y = curPoint.y-1) >=0){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x-2)>=0 && (p1.y = curPoint.y+1) <Y){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x+2)<X && (p1.y = curPoint.y-1) >=0){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x+2)<X && (p1.y = curPoint.y+1) <Y){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x-1)>=0 && (p1.y = curPoint.y-2) >=0){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x-1)>=0 && (p1.y = curPoint.y+2) <Y){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x+1)<X && (p1.y = curPoint.y-2) >=0){
           ps.add(new Point(p1));
       }
       if((p1.x = curPoint.x+1)<X && (p1.y = curPoint.y+2) <Y){
           ps.add(new Point(p1));
       }
       return ps;
   }

   public static void horseChessboard(int[][] chessboard,int row,int column,int step){
        isVitised[row*X+column] = true;
        chessboard[row][column] = step;
       ArrayList<Point> nextPoints = getNextPoints(new Point(row, column));
       while (!nextPoints.isEmpty()){
           Point next = nextPoints.remove(0);
           if(!isVitised[next.y*X+next.x]){
               horseChessboard(chessboard,next.y,next.x,step+1);
           }
       }
       if(step<X*Y && !finished){
           isVitised[row*X+column] = false;
           chessboard[row][column] = 0;
       }else {
           finished = true;
       }

   }
}
