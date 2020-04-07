package DataStructure.tree.threadBinAryTree;

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
        HeroNode node7 = new HeroNode(17, "dims");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        node2.setParent(root);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);
        node7.setParent(node3);

        ThreadBinAryTree threadBinAryTree = new ThreadBinAryTree();
        threadBinAryTree.setRoot(root);


        //测试一下
        /*threadBinAryTree.threadNodesmid(root);
        HeroNode preNode = node5.getLeft();//no=3
        System.out.println("node5的前驱节点是=" + preNode);
        HeroNode postNode = node5.getRight();//no=1
        System.out.println("node5的后继节点是=" + postNode);
        System.out.println("使用线索化方式进行遍历");
        threadBinAryTree.threadListmid();*/

        /*threadBinAryTree.threadNodespre(root);
        HeroNode preNode = node6.getLeft();//no=6
        System.out.println("node6的前驱节点是=" + preNode);
        HeroNode postNode = node6.getRight();//no=17
        System.out.println("node6的后继节点是=" + postNode);
        System.out.println("使用线索化方式进行遍历");
        threadBinAryTree.threadListpre();*/



        threadBinAryTree.threadNodespost(root);
        HeroNode preNode = node6.getLeft();//no=3
        System.out.println("node6的前驱节点是=" + preNode);
        HeroNode postNode = node6.getRight();//no=17
        System.out.println("node6的后继节点是=" + postNode);
        System.out.println("使用线索化方式进行遍历");
        threadBinAryTree.threadListpost();
    }
}

class ThreadBinAryTree {
    private HeroNode root;
    //指向要线索化节点的前一个节点，便于进行前驱线索
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadListmid() {
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
    public void threadNodesmid(HeroNode node) {
        if (node == null) {
            return;
        }
        threadNodesmid(node.getLeft());
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
        threadNodesmid(node.getRight());

    }

    public void threadListpre() {
        HeroNode node = root;
        while (node != null) {
            System.out.println(node);
            //找到线索化的指针
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            //只要后继节点指针存在，就一直找下去
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            if(node.getLeftType() == 0){
                node = node.getLeft();
            }else if(node.getRightType() == 0){
                node = node.getRight();
            }else{
                break;
            }

        }
    }

    /**
     * 对一个节点进行前序线索化
     *
     * @param node
     */
    public void threadNodespre(HeroNode node) {
        if (node == null) {
            return;
        }
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
        if(node.getLeftType()!=1){
            threadNodespre(node.getLeft());
        }
        if(node.getRightType()!=1){
            threadNodespre(node.getRight());
        }
    }

    public void threadListpost() {
        HeroNode node = root;
        HeroNode preNode = null;
        //找到线索化的指针
        while (node.getLeftType() == 0) {
            node = node.getLeft();
        }
        while (node != null) {
            if(node.getRightType() == 1){
                System.out.println(node);
                preNode = node;
                node = node.getRight();
            }else{
                if(node.getRight() == preNode){
                    System.out.println(node);
                    if(node == root){
                        return;
                    }
                    preNode = node;
                    node = node.getParent();
                }else{
                    node = node.getRight();
                    while (node != null && node.getLeftType()!=1){
                        node = node.getLeft();
                    }
                }
            }
        }
    }


    /**
     * 对一个节点进行后序线索化
     *
     * @param node
     */
    public void threadNodespost(HeroNode node) {
        if (node == null) {
            return;
        }
        threadNodespost(node.getLeft());
        threadNodespost(node.getRight());

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

    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private HeroNode parent;//父节点，后序线索化遍历要用到
    //0=左子树，1=前驱节点
    private int leftType;
    //0=右子树，1=后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
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
