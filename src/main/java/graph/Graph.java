package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图(使用邻接矩阵表示)
 * @author wangguoqiang
 * @date 2020/4/6 17:01
 */
public class Graph {
    private List<String> vertexs;//顶点集合
    private int[][] edges ;//邻接矩阵，边
    private int edgesnum;//边的个数

    public static void main(String[] args) {
        int n = 5;
        String values[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String vertex : values) {
            graph.addVertex(vertex);
        }
        graph.addEdge(0,1,1);//A-B
        graph.addEdge(0,2,1);//A-C
        graph.addEdge(1,2,1);//B-C
        graph.addEdge(1,3,1);//B-D
        graph.addEdge(1,4,1);//B-E

        graph.showGraph();
    }

    public Graph(int n) {
        vertexs = new ArrayList<>();
        edges = new int[n][n];
        edgesnum = 0;
    }

    /**
     * 添加一个顶点
     * @param str
     */
    public void addVertex(String str){
        vertexs.add(str);
    }

    /**
     * 添加一条边
     * @param v1 顶点一的索引
     * @param v2 顶点二的索引
     * @param weight 权重
     */
    public void addEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgesnum++;
    }

    /**
     * 获取顶点个数
     * @return
     */
    public int getNumOfVertex(){
        return vertexs.size();
    }

    /**
     * 获取边的个数
     * @return
     */
    public int getEdgesnum(){
        return edgesnum;
    }

    public String getVertex(int index){
        return vertexs.get(index);
    }

    /**
     * 获取两个顶点间的权值
     * @param v1 顶点1
     * @param v2 顶点2
     * @return
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * 显示一个图
     */
    public void showGraph(){
        for (int[] n : edges) {
            System.out.println(Arrays.toString(n));
        }
    }
}
