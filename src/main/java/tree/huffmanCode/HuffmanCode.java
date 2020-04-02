package tree.huffmanCode;

import com.google.common.collect.Lists;
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
        byte[] zip = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(zip));
        //System.out.println(zip.length);
        byte[] bytes = decode(zip);
        System.out.println("解码后的结果为："+new String(bytes));
    }

    /**
     * 解码
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(byte[] huffmanBytes){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i != huffmanBytes.length-1);
            builder.append(byte2bits(flag,huffmanBytes[i]));
        }
        //System.out.println(builder.toString());
        Map<String, Byte> stringByteMap = huffmanCodes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        List<Byte> byteList = Lists.newArrayList();
        for (int i = 0; i < builder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = builder.substring(i,i+count);//i不动，count++，不停的去匹配，直到找到解码表中对应的字符
                b = stringByteMap.get(key);
                if(b!=null){
                    byteList.add(b);
                    flag = false;
                }else{
                    count++;
                }
            }
            i+=count;
        }
        byte[] result = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            result[i] = byteList.get(i);
        }
        return result;
    }

    /**
     * byte转成对应二进制
     * @param flag 高位是否补0
     * @param b
     * @return
     */
    private static String byte2bits(boolean flag,byte b){
        int temp = b;
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }

    /**
     * 赫夫曼编码压缩
     * @param bytes 原始字符串对应的bytes数组
     * @return 压缩后的byte数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodeList = getNodes(bytes);
        Node root = createHuffmanTree(nodeList);
        getCodes(root,"",stringBuilder);
        return zip(bytes);
    }

    private static byte[] zip(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        //System.out.println("***"+builder.toString());
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
