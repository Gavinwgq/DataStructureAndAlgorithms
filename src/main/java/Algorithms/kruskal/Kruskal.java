package Algorithms.kruskal;

import java.util.Arrays;

/**
 * 布鲁斯卡尔算法
 * @author wangguoqiang
 * @date 2020/4/12 17:10
 */
public class Kruskal {
    private int edgeNums;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//权值为INF时，表示两个顶点不能连通

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}};
        Kruskal kruskal = new Kruskal(vertexs,matrix);
        //kruskal.printMatrix();
        kruskal.kruskal();
    }

    public Kruskal(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        int vlen = vertexs.length;
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(matrix[i][j]!=INF){
                    edgeNums++;
                }
            }
        }
    }

    public void printMatrix(){
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边按照权值进行排序（冒泡）
     * @param edges
     */
    public void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     * 返回对应顶点的下标
     * @param ch
     * @return
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(ch == vertexs[i]){
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNums];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    public void kruskal(){
        int index = 0;//最终结果数组的索引
        int[] ends = new int[edgeNums];//存放顶点的终点
        //结果数组，保存最小生成树
        EData[] results = new EData[vertexs.length-1];
        EData[] edges = getEdges();
        sortEdges(edges);
        for (int i = 0; i < edgeNums; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            int m = getEnd(ends,p1);
            int n = getEnd(ends,p2);
            if(m!=n){//终点不重合 ，可以入选
                ends[m] = n;
                results[index++] = edges[i];
            }
        }
        System.out.println("最小生成树结果："+ Arrays.toString(results));
    }

    /**
     * 获取下标为i的顶点的终点的坐标
     * @param ends 用于记录顶点对应的终点的坐标，在遍历过程中逐步形成的,例如[2,1]中2表示'A'的终点是’C'
     * @param i 顶点的下标，例如 'A'->0,'B'->1
     * @return
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i = ends[i];//比如 A找到B B找到C C的终点为0 则A的终点为C
        }
        return i;
    }
}

/**
 * 边
 */
class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[" + "<" + start + ", " + end + "> = " + weight + ']';
    }
}
