package DataStructure.recursion;

/**
 * 迷宫问题
 * @author wangguoqiang
 * @date 2020/3/17 20:28
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("打印地图（1表示为挡板）");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
        findWay(map,1,1);

        System.out.println("打印小球走过后的地图（2表示通路，3表示死路）");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 寻找合适的路线从 [1][1]出发 [6][5]为终点
     * 约定 0 代表未走过的路，1代表挡板，2代表走得通 3 代表该点不通
     * 行走策略 下->右->上->左
     * @param map 地图
     * @param i 从哪个位置开始找
     * @param j 从哪个位置开始找
     * @return
     */
    public static boolean findWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0){//当前点未走过
                map[i][j] = 2;//假定当前点是通的
                if(findWay(map,i+1,j)){//向下走
                    return true;
                }else if(findWay(map,i,j+1)){//向右走
                    return true;
                }else if(findWay(map,i-1,j)){//向上走
                    return true;
                }else if(findWay(map,i,j-1)){//向左走
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
