package tree.binarysorttree;

/**
 * 二叉排序树
 * @author wangguoqiang
 * @date 2020/4/4 9:33
 */
public class BinarySortTreeDemo {
    /**
     *            7
     *       3         10
     *    1     5    9    12
     *      2
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int n : arr) {
            tree.add(new Node(n));
        }
        tree.infixOrder();
        System.out.println("测试删除");
        //tree.delNode(2);//删除叶子节点
        //tree.delNode(1);//删除只有一颗子树的节点
        //tree.delNode(10);//删除有两个子树的节点
        tree.delNode(7);//删除根节点
        tree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

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
                if(parent.left == targetNode){//待删除节点是父节点的左子节点
                    parent.left = targetNode.left;
                }else{
                    parent.right = targetNode.left;
                }
            }else{//待删除节点右子树不为空
                if(parent.left == targetNode){//待删除节点是父节点的左子节点
                    parent.left = targetNode.right;
                }else{
                    parent.right = targetNode.right;
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
