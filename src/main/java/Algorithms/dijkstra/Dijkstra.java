package Algorithms.dijkstra;

import java.util.Arrays;

/**
 * Dijkstra算法 最短路径
 * @author wangguoqiang
 * @date 2020/4/14 20:39
 */
public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可以连接
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex,matrix);
        //graph.showGraph();
        graph.dijkstra(6);
        graph.showDis();
    }
}

class VisitedVertex{
    int[] already_arr;//记录顶点是否访问过 1=访问过 0=未访问
    int[] pre_visited;//每个下标顶点对应的前驱顶点，会动态更新
    int[] dis;//从出发顶点到各个顶点的距离

    /**
     *
     * @param length 顶点个数
     * @param index 出发顶点下标
     */
    public VisitedVertex(int length,int index) {
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];
        Arrays.fill(dis,65535);//初始化距离为最大值65535
        dis[index] = 0;//出发顶点到自身的距离为0
        already_arr[index] = 1;
    }

    /**
     * 判断下标为index的顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到下标为index的顶点的距离为len
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新下标为now的顶点的前驱顶点为下标为index的顶点
     * @param now
     * @param index
     */
    public void updatePre(int now,int index){
        pre_visited[now] = index;
    }

    public int getDis(int index){
        return dis[index];
    }

    public int updateArr(){
        int min = 65535,index =0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i]<min){
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println(Arrays.toString(already_arr));
        System.out.println(Arrays.toString(pre_visited));
        System.out.println(Arrays.toString(dis));
    }
}

class Graph{
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra(int index){
        visitedVertex = new VisitedVertex(vertex.length,index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = visitedVertex.updateArr();//获取下一个访问顶点
            update(index);
        }
    }

    private void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            //出发顶点到index的距离+index到i的距离
            len = visitedVertex.getDis(index)+matrix[index][i];
            //i点未访问过，且len小于出发顶点到i的距离，则更新
            if(!visitedVertex.in(i) && len < visitedVertex.getDis(i)){
                visitedVertex.updatePre(i,index);
                visitedVertex.updateDis(i,len);
            }
        }
    }

    public void showDis(){
        visitedVertex.show();
    }
}
