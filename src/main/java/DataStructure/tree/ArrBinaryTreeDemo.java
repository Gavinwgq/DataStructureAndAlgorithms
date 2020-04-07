package DataStructure.tree;

/**
 * 数组顺序存储二叉树（完全二叉树）
 *
 * @author wangguoqiang
 * @date 2020/3/28 11:23
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        /**
         *             1
         *          2     3
         *       4   5  6   7
         */
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        tree.preOrder(0);//1,2,4,5,3,6,7
        System.out.println();
        System.out.println("中序遍历");
        tree.midOrder(0);//4,2,5,1,6,3,7
        System.out.println();
        System.out.println("后序遍历");
        tree.lastOrder(0);//4,5,2,6,7,3,1
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * 数组下标为n的元素其左子节点下标为2n+1,右子节点下标为2n+2
     *
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能完成遍历");
            return;
        }
        System.out.print(arr[index] + "\t");
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void midOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能完成遍历");
            return;
        }
        if (2 * index + 1 < arr.length) {
            midOrder(2 * index + 1);
        }
        System.out.print(arr[index] + "\t");
        if (2 * index + 2 < arr.length) {
            midOrder(2 * index + 2);
        }
    }

    public void lastOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能完成遍历");
            return;
        }
        if (2 * index + 1 < arr.length) {
            lastOrder(2 * index + 1);
        }

        if (2 * index + 2 < arr.length) {
            lastOrder(2 * index + 2);
        }
        System.out.print(arr[index] + "\t");
    }
}
