package sort;

/**
 * 堆排序
 * @author wangguoqiang
 * @date 2020/3/29 20:32
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {3,51,2,4,1,6,9,7,8,45,12,23};
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        //从最后一个非叶子节点开始向上调整
        for (int i = arr.length/2-1; i >=0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int i = arr.length-1; i >0; i--) {
            temp = arr[i];
            arr[i]= arr[0];
            arr[0] = temp;
            //堆顶元素和末尾元素交换后，要重新调整树为大顶堆，
            // 此处只有堆顶和末尾元素右调整，而且末尾元素为最大了，不参与调整了，
            // 因此只需要调整索引0即可
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 将以非叶子节点i为顶的子树调整为大顶堆
     *
     * @param arr    数组
     * @param i      非叶子节点
     * @param length 要调整的数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        //j=2*i+1 表示i节点的左子节点，循环的步长j=2*j+1表示继续调整上一步循环的左子节点
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if(j+1<length && arr[j]<arr[j+1]){
                j++;
            }
            if(arr[j]>temp){
                arr[i] = arr[j];
                //此处是为了让较小的数下沉，避免从上面交换后，使子树不再是大顶堆
                i = j;
            }else{
                break;
            }
        }
        //i = j时进行了标记，
        arr[i] = temp;
    }
}
