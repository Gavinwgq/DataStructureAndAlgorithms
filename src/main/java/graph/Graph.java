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
    private boolean[] isVisted;//保存顶点是否被访问过

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

        System.out.println("深度优先遍历");
        graph.dfs();
    }

    public Graph(int n) {
        vertexs = new ArrayList<>();
        edges = new int[n][n];
        edgesnum = 0;
        isVisted = new boolean[n];
    }

    /**
     * 获取顶点的第一个邻接顶点
     * @param index 顶点索引
     * @return 第一个邻接顶点的索引
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexs.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标获取下一个邻接节点
     * @param v1 当前节点下标
     * @param v2 当前节点邻接节点的下标
     * @return 当前节点的下一个邻接节点下标
     */
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexs.size(); i++) {
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    private void dfs(boolean[] isVisted,int i){
        System.out.print(getVertex(i)+"->");//输出该节点
        isVisted[i] = true;//标记该节点被访问过了
        int w = getFirstNeighbor(i);
        while (w!=-1){
            if(!isVisted[w]){
                dfs(isVisted,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        for (int i = 0; i < vertexs.size(); i++) {
            if(!isVisted[i]){
                dfs(isVisted,i);
            }
        }
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
