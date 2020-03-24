package search;

/**
 * 顺序查找
 * @author wangguoqiang
 * @date 2020/3/24 21:21
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {23,89,343,9,54,62};
        int i = seqSearch(arr,54);
        if(i == -1){
            System.out.println("未找到数据");
        }else{
            System.out.println("数据找到,下标为"+i);
        }

    }

    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
