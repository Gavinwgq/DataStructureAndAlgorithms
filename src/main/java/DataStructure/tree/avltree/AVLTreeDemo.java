package DataStructure.tree.avltree;

/**
 * @author wangguoqiang
 * @date 2020/4/6 14:40
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};//单次左旋
        //int[] arr = {10,12,8,9,7,6};//单次右旋
        int[] arr = {10,11,7,6,8,9};//双旋转
        AVLTree avlTree = new AVLTree();
        for (int n : arr) {
            avlTree.add(new Node(n));
        }
        System.out.println("树的高度="+avlTree.getRoot().height());//4
        System.out.println("左子树的高度="+avlTree.getRoot().leftHeight());//1
        System.out.println("右子树的高度="+avlTree.getRoot().rightHeight());//3
    }
}

class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(Node node){
        if (root == null){
            root = node;
        }else{
            root.add(node);
        }
    }
    public void infixOrder(){
        if(root == null){
            System.out.println("树为空！");
        }else{
            root.infixOrder();
        }
    }

    public Node searchNode(int value){
        if(root == null){
            return null;
        }else{
            return root.searchNode(value);
        }
    }

    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    public void delNode(int value){
        if(root == null) return;
        Node targetNode = searchNode(value);
        if(targetNode == null) return;
        if(root.left == null && root.right == null){
            root = null;
            return;
        }
        Node parent = searchParent(value);
        if(targetNode.left == null && targetNode.right == null){//如果要删除的节点是叶子节点
            if(parent.left!=null && parent.left.value == value){//是左子节点
                parent.left = null;
            }else if(parent.right!=null && parent.right.value == value){//是右子节点
                parent.right = null;
            }
        }else if(targetNode.left != null && targetNode.right != null){//如果要删除的节点有左右两个子树
            int minValue = delRightTreeMinValue(targetNode.right);
            targetNode.value = minValue;
        }else{////如果要删除的节点只有一个子树
            if(targetNode.left!=null){//待删除节点左子树不为空
                if(parent!=null){//如果此时要删除的是根节点，且根节点只有一个子树了，那么parent为null,不加判断的话，就会出现异常
                    if(parent.left == targetNode){//待删除节点是父节点的左子节点
                        parent.left = targetNode.left;
                    }else{
                        parent.right = targetNode.left;
                    }
                }else{
                    root = targetNode.left;
                }

            }else{//待删除节点右子树不为空
                if(parent!=null){
                    if(parent.left == targetNode){//待删除节点是父节点的左子节点
                        parent.left = targetNode.right;
                    }else{
                        parent.right = targetNode.right;
                    }
                }else{
                    root = targetNode.right;
                }
            }
        }
    }

    /**
     * 找到node节点的右子树中最小的值，删除最小的节点，并返回该节点的值
     * @param node
     * @return
     */
    public int delRightTreeMinValue(Node node){
        Node targetNode = node;
        while (targetNode.left!=null){
            targetNode = targetNode.left;
        }
        delNode(targetNode.value);
        return targetNode.value;
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void leftRotate(){
        Node newNode = new Node(value);//以当前节点的值，创建新的节点；
        newNode.left = left;//把新的节点的左子树设置为当前节点的左子树
        newNode.right = right.left;//把新的节点的右子树设置为当前节点右子树的左子树
        value = right.value;//把当前节点的值设置右子树的值
        right = right.right;//把当前节点的右子树设置为右子树的右子树
        left = newNode;//把当前节点的左子树设置为新的节点
    }

    public void rightRotate(){
        Node newNode = new Node(value);//以当前节点的值，创建新的节点；
        newNode.right = right;//把新的节点的右子树设置为当前节点的右子树
        newNode.left = left.right;//把新的节点的左子树设置为当前节点左子树的右子树
        value = left.value;//把当前节点的值设置左子树的值
        left = left.left;//把当前节点的左子树设置为左子树的左子树
        right = newNode;//把当前节点的右子树设置为新的节点
    }

    public int leftHeight(){
        return left==null?0:left.height();
    }

    public int rightHeight(){
        return right==null?0:right.height();
    }

    /**
     * 返回以该节点为根的树的高度
     * @return
     */
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public Node searchNode(int value){
        if(this.value == value){
            return this;
        }else if(value<this.value){
            if(this.left!=null){
                return this.left.searchNode(value);
            }else{
                return null;
            }
        }else{
            if(this.right!=null){
                return this.right.searchNode(value);
            }else{
                return null;
            }
        }
    }

    /**
     * 查找待删除节点的父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if((this.left!=null && this.left.value == value) || (this.right!=null && this.right.value == value)){
            return this;
        }else{
            if(value<this.value && this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value && this.right!=null){
                return this.right.searchParent(value);
            }else{
                return null;
            }
        }
    }

    /**
     * 递归的方式添加节点，构建二叉排序树
     * @param node
     */
    public void add(Node node){
        if(node == null){
            return;
        }
        if(node.value<this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
        //当添加完一个节点后，右子树比左子树的高度的差大于1，则进行左旋转
        if(rightHeight()-leftHeight()>1){
            if(right!=null && right.leftHeight()<right.rightHeight()){
                //如果当前节点的右子树的左子树高度比其右子树高度小，则需要先对右子树进行右旋转
                right.rightRotate();
            }
            leftRotate();
        }else if(leftHeight()-rightHeight()>1){
            if(left!=null && left.leftHeight()<left.rightHeight()){
                left.leftRotate();
            }
            rightRotate();
        }
    }

    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}
