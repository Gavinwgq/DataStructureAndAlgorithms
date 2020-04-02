package tree.huffmanCode;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 赫夫曼编码
 * @author wangguoqiang
 * @date 2020/4/1 21:21
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        List<Node> nodeList = getNodes(contentBytes);
        System.out.println(nodeList);
        Node root = createHuffmanTree(nodeList);
        preOrder(root);
        getCodes(root,"",stringBuilder);
        System.out.println(huffmanCodes);
        byte[] zip = zip(contentBytes);
        System.out.println(Arrays.toString(zip));
        System.out.println(zip.length);
    }

    private static byte[] zip(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        int len = (builder.length()+7)/8;
        byte[] result = new byte[len];
        int index = 0;
        for (int i = 0; i < builder.length(); i+=8) {
            if(i+8<builder.length()){
                result[index++] = (byte) Integer.parseInt(builder.substring(i,i+8),2);
            }else{
                result[index] = (byte) Integer.parseInt(builder.substring(i),2);
            }
        }
        return result;
    }
    static Map<Byte,String> huffmanCodes = Maps.newHashMap();
    static StringBuilder stringBuilder = new StringBuilder();

    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);
        if(node!=null){
            if(node.data==null){//非叶子节点
                getCodes(node.left,"0",builder);
                getCodes(node.right,"1",builder);
            }else {
                huffmanCodes.put(node.data,builder.toString());
            }
        }
    }

    private static List<Node> getNodes(byte[] bytes){
        Map<Byte,Integer> byteMap = Maps.newHashMap();
        for (byte b : bytes) {
            Integer count = byteMap.get(b);
            if(count == null){
                byteMap.put(b,1);
            }else{
                byteMap.put(b,++count);
            }
        }
        return byteMap.entrySet().stream().map(entry-> new Node(entry.getKey(),entry.getValue())).collect(Collectors.toList());
    }

    private static Node createHuffmanTree(List<Node> nodeList){
        while (nodeList.size()>1){
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parent = new Node(null,leftNode.weight+rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    private static void preOrder(Node root){
        root.preOrder();
    }
}

class Node implements Comparable<Node>{
    Byte data;//存放字符数据对应的值
    int weight;//字符的个数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}
