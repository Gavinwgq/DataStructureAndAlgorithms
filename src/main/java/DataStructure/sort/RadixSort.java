package DataStructure.sort;

/**
 * 基数排序（桶排序）
 * @author wangguoqiang
 * @date 2020/3/24 20:26
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {23,67,34,127,3,692,15};
        radixSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
    }

    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }
        //算出最大的数有多少位
        int maxLength = (max+"").length();
        //十个桶
        int[][] bucket = new int[10][arr.length];
        //用于记录每个桶放入了多少个数据，便于遍历的时候取数据
        //bucketElementCounts[0] 代表bucket[0]中的数据个数
        int[] bucketElementCounts = new int[10];
        int index = 0;
        for (int k = 0,n = 1; k < maxLength; k++,n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                int digitOfElement = arr[i] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                //桶中个数++
                bucketElementCounts[digitOfElement]++;
            }
            index = 0;
            //从桶中取出数据放入原数组
            for (int i = 0; i < bucketElementCounts.length; i++) {
                if(bucketElementCounts[i] != 0){
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                //数据取出后 每个桶中的个数清0
                bucketElementCounts[i] = 0;
            }
        }
    }
}
