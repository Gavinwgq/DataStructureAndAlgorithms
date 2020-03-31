package tree;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 赫夫曼树的构建
 * @author wangguoqiang
 * @date 2020/3/31 21:29
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        printTree(root);
    }

    /**
     * 传入一个数组，返回一个赫夫曼树的根节点
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int[] arr){
        List<Node> nodeList = Arrays.stream(arr).boxed().map(n->{
            Node node = new Node(n);
            return node;
        }).collect(Collectors.toList());
        while (nodeList.size()>1){
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    public static void printTree(Node root){
        root.preOrder();
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
