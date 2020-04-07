package Algorithms.divideandconquer;

/**
 * 汉诺塔
 * @author wangguoqiang
 * @date 2020/4/7 21:18
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(4,'A','B','C');
    }

    /**
     *
     * @param num 盘的个数
     * @param a 起始位置
     * @param b
     * @param c 目标位置
     */
    public static void hanoiTower(int num,char a,char b,char c){
        if(num == 1){
            System.out.println(a+" -> "+c);
        }else{
            //1、把num-1个盘从a移动到b
            hanoiTower(num-1,a,c,b);
            //2、把最下面一个盘从a移动到c
            System.out.println(a+" -> "+c);
            //3、把num-1个盘从b移动到c
            hanoiTower(num-1,b,a,c);
        }
    }
}
