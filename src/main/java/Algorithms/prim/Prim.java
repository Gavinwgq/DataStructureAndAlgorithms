package Algorithms.prim;

import java.util.Arrays;

/**
 * 普利姆算法（最小生成树）
 * @author wangguoqiang
 * @date 2020/4/12 14:55
 */
public class Prim {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //10000表示两个顶点之间不直接连通
        int[][] weight = {{10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph,0);
    }

}

class MinTree{

    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){
        for (int i = 0; i < graph.weight.length; i++) {
            System.out.println(Arrays.toString(graph.weight[i]));
        }
    }

    /**
     * 普利姆算法
     * @param graph 图
     * @param v 起始顶点  'A'->0,..
     */
    public void prim(MGraph graph,int v){
        //标记是否访问过，默认值0表示未访问过，使用其他语言实现可能需要进行初始化
        int[] visited = new int[graph.verxs];
        visited[v] = 1;//把起始顶点标记为已访问
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {//最后的结果是graph.verxs个顶点，grap.verxs-1个边
            for (int i = 0; i < graph.verxs; i++) {
                if(visited[i] != 1){
                    continue;
                }
                for (int j = 0; j < graph.verxs; j++) {
                    if(visited[j] != 1 && graph.weight[i][j] < minWeight ){
                        //寻找以访问过的节点和未未访问过的节点之间的权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">,weight="+minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph{
    int verxs;//表示图的节点个数
    char[] data;// 存放节点数据
    int[][] weight;//存放邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
