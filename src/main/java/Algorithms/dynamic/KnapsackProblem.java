package Algorithms.dynamic;

/**
 * 01背包问题（动态规划）
 * @author wangguoqiang
 * @date 2020/4/8 20:34
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品重量
        int[] val = {1500,3000,2000};//物品价值
        int m = 4;//背包容量
        int n = val.length;//物品个数
        //v[i][j] 表示在前i个商品能够放入容量为j的背包的最大价值
        int[][] v = new int[n+1][m+1];
        //设置第一行及第一列值为0（本程序中也可以步处理，默认就是零）
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //记录商品的存放 path[i][j] = 1 表示第i个商品放入了背包
        int[][] path = new int[n+1][m+1];

        for (int i = 1; i < v.length; i++) {//第一行不处理
            for (int j = 1; j < v[0].length; j++) {//第二行步处理
                if(w[i-1]>j){//商品重量大于背包重量
                    v[i][j] = v[i-1][j];
                }else{
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+"  ");
            }
            System.out.println();
        }

        int i = path.length-1;
        int j = path[0].length-1;
        while (i>0 && j>0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -=w[i-1];
            }
            i--;
        }
    }
}
