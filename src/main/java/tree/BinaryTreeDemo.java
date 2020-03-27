package tree;

import java.util.Hashtable;

/**
 * 二叉树
 * @author wangguoqiang
 * @date 2020/3/26 20:54
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode heroNode2 = new HeroNode(2,"卢俊义");
        HeroNode heroNode3 = new HeroNode(3,"吴用");
        HeroNode heroNode4 = new HeroNode(4,"林冲");
        HeroNode heroNode5 = new HeroNode(5,"关胜");
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setLeft(heroNode5);
        heroNode3.setRight(heroNode4);
        binaryTree.setRoot(root);
        System.out.println("-----前序遍历----");
        binaryTree.preOrder();
        System.out.println("-----中序遍历----");
        binaryTree.midOrder();
        System.out.println("-----后序遍历----");
        binaryTree.lastOrder();
        System.out.println("-----遍历查找----");
        System.out.println(binaryTree.preOrderSearch(3));
        System.out.println(binaryTree.midOrderSearch(2));
        System.out.println(binaryTree.lastOrderSearch(4));

        System.out.println("----删除前遍历----");
        binaryTree.preOrder();
        //binaryTree.delByNo(5);//叶子节点
        binaryTree.delByNo(3);//非叶子节点
        System.out.println("----删除后遍历----");
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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