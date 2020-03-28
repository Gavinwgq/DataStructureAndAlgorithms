package tree.threadBinAryTree;

/**
 * 线索化二叉树
 * @author wangguoqiang
 * @date 2020/3/28 12:22
 */
public class ThreadBinAryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

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
        System.out.println("node5的前驱节点是="+preNode);
        HeroNode postNode = node5.getRight();//no=1
        System.out.println("node5的后继节点是="+postNode);
    }
}

class ThreadBinAryTree{
    private HeroNode root;
    //指向要线索化节点的前一个节点，便于进行前驱线索
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 对一个节点进行中序线索化
     * @param node
     */
    public void threadNodes(HeroNode node){
        if(node == null){
            return;
        }
        threadNodes(node.getLeft());
        //处理前驱节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点，实际上是在下次递归时进行处理的
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //这一句非常重要，否则下次递归时就无法完成上面后继节点的处理了
        pre = node;
        threadNodes(node.getRight());

    }
    //前序遍历
    public void preOrder(){
        if(root != null){
            root.preOrder();
        }
    }
    //中序遍历
    public void midOrder(){
        if(root != null){
            root.midOrder();
        }
    }

    //后序遍历
    public void lastOrder(){
        if(root != null){
            root.lastOrder();
        }
    }

    /**
     * 前序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode preOrderSearch(int no){
        if(this.root != null){
            return this.root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    /**
     * 中序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode midOrderSearch(int no){
        if(this.root != null){
            return this.root.midOrderSearch(no);
        }else{
            return null;
        }
    }

    /**
     * 后序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode lastOrderSearch(int no){
        if(this.root != null){
            return this.root.lastOrderSearch(no);
        }else{
            return null;
        }
    }

    public void delByNo(int no){
        if(this.root != null){
            if(this.root.getNo() == no){
                this.root = null;
            }else{
                this.root.delByNo(no);
            }
        }else{
            System.out.println("树为空");
        }
    }
}

class HeroNode{
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

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void midOrder(){
        if(this.left!=null){
            this.left.midOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.midOrder();
        }
    }

    //后序遍历
    public void lastOrder(){
        if(this.left!=null){
            this.left.lastOrder();
        }
        if(this.right!=null){
            this.right.lastOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode preOrderSearch(int no){
        if(this.no == no){
            return this;
        }
        HeroNode res = null;
        if(this.left != null){
            res = this.left.preOrderSearch(no);
        }
        if(res != null){
            return res;
        }
        if(this.right != null){
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    /**
     * 中序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode midOrderSearch(int no){
        HeroNode res = null;
        if(this.left != null){
            res = this.left.midOrderSearch(no);
        }
        if(res != null){
            return res;
        }
        if(this.no == no){
            return this;
        }
        if(this.right != null){
            res = this.right.midOrderSearch(no);
        }
        return res;
    }

    /**
     * 后序遍历查找
     * @param no 要查找的编号
     * @return
     */
    public HeroNode lastOrderSearch(int no){
        HeroNode res = null;
        if(this.left != null){
            res = this.left.lastOrderSearch(no);
        }
        if(res != null){
            return res;
        }
        if(this.right != null){
            res = this.right.lastOrderSearch(no);
        }
        if(res != null){
            return res;
        }
        if(this.no == no){
            return this;
        }
        return res;
    }

    /**
     * 删除操作
     * 规定：1、如果时叶子节点删除该节点，如果不是叶子节点，删除该子树
     * @param no
     */
    public void delByNo(int no){
        if(this.left!=null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right!=null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left!=null){
            this.left.delByNo(no);
        }
        if(this.right!=null){
            this.right.delByNo(no);
        }
    }
}
