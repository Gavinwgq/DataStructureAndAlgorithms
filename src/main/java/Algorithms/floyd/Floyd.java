package Algorithms.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 * @author wangguoqiang
 * @date 2020/4/15 20:39
 */
public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可以连接
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};
        Graph graph = new Graph(vertex,matrix);
        graph.floyd();
        graph.showDis();
    }
}
class Graph{
    private char[] vertex;//顶点数组
    private int[][] dis;//保存各个顶点到其他顶点的最短距离，初始为邻接矩阵
    private int[][] pre;//前驱节点
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i],i);
        }
    }
    public void showDis(){
        System.out.println("------show dis------");
        for (int[] link : dis) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void showPre(){
        System.out.println("------show pre------");
        for (int[] link : pre) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void floyd(){
        int len = 0;
        //遍历中间顶点
        for (int k = 0; k < dis.length; k++) {
            //遍历出发顶点
            for (int i = 0; i < dis.length; i++) {
                //遍历终点
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];//计算顶点i经过中间顶点k到达终点j的距离
                    if(len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
