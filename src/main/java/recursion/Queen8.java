package recursion;

/**
 * 八皇后问题
 * @author wangguoqiang
 * @date 2020/3/18 19:48
 */
public class Queen8 {
    private int max = 8;
    /**
     * arr[i] = val 表示 将皇后放置在第（i+1）行第（val+1）列的位置
     * 比如 arr[2] = 6 将皇后放置在第3行第7列的位置
     */
    private int[] arr = new int[8];
    private int count = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法\n",queen8.count);
    }

    private void check(int n){
        if(n == max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if(judge(n)){
                //如果放置第n个后不冲突，递归放置第n+1个
                check(n+1);
            }
        }
    }

    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            /**
             * arr[i] == arr[n] 检查是否在同一列
             * Math.abs(n-i) == Math.abs(arr[n] - arr[i]) 检查是否在同一斜线
             */
            if(arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

    public void print(){
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }
}
