package sparseArray;

/**
 * 稀疏数组
 * @author wangguoqiang
 * @date 2020/3/4 19:36
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0：代表没有棋子  1代表黑色棋子 2代表蓝色棋子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        //输出打印看一下
        System.out.println("打印原始数组");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转换为稀疏数组
        //1、遍历二维数组，确定有效数据个数（不为0）
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if(chessArray[i][j]!=0){
                    sum++;
                }
            }
        }
        //2、创建稀疏数组
        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;
        //3、为稀疏数组赋值
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if(chessArray[i][j]!=0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("打印稀疏数组");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }

        //将稀疏数组还原成原始数组
        int[][] newChessArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            newChessArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("打印还原后的原始数组");
        for (int[] row : newChessArray) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
