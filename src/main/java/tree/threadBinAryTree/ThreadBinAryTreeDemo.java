package tree.threadBinAryTree;

/**
 * 线索化二叉树
 *
 * @author wangguoqiang
 * @date 2020/3/28 12:22
 */
public class ThreadBinAryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadBinAryTree threadBinAryTree = new ThreadBinAryTree();
        threadBinAryTree.setRoot(root);
        threadBinAryTree.threadNodes(root);

        //测试一下
        HeroNode preNode = node5.getLeft();//no=3
        System.out.println("node5的前驱节点是=" + preNode);
        HeroNode postNode = node5.getRight();//no=1
        System.out.println("node5的后继节点是=" + postNode);

        System.out.println("使用线索化方式进行遍历");
        threadBinAryTree.threadList();
    }
}

class ThreadBinAryTree {
    private HeroNode root;
    //指向要线索化节点的前一个节点，便于进行前驱线索
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadList() {
        HeroNode node = root;
        while (node != null) {
            //找到线索化的指针
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //只要后继节点指针存在，就一直找下去
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 对一个节点进行中序线索化
     *
     * @param node
     */
    public void threadNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadNodes(node.getLeft());
        //处理前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点，实际上是在下次递归时进行处理的
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //这一句非常重要，否则下次递归时就无法完成上面后继节点的处理了
        pre = node;
        threadNodes(node.getRight());

    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //0=左子树，1=前驱节点
    private int leftType;
    //0=右子树，1=后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}
