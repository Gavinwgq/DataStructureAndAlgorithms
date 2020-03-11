package linkedlist.singlelinkedlist;

/**
 * @author wangguoqiang
 * @date 2020/3/10 21:17
 */
public class SignleLinkList {
    public static void main(String[] args) {
        Node node1 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node3;


        Node node2 = new Node(1);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node2.next = node4;
        node4.next = node5;

        System.out.println("单链表1");
        print(node1);
        System.out.println("单链表2");
        print(node2);
        System.out.println("合并后的单链表");
        print(merge(node1,node2));

    }

    /**
     * 合并有序单链表
     * @param node1
     * @param node2
     * @return
     */
    public static Node merge(Node node1,Node node2){
        Node cur1 = node1;
        Node cur2 = node2;
        Node temp = new Node(0);
        Node cur = temp;
        while (true){
            if(cur1 == null){
                cur.next = cur2;
                break;
            }
            if(cur2 == null){
                cur.next = cur1;
                break;
            }
            if(cur1.data<cur2.data){
                cur.next = cur1;
                cur = cur1;
                cur1 = cur1.next;
            }else{
                cur.next = cur2;
                cur = cur2;
                cur2 = cur2.next;
            }
        }
        return temp.next;
    }

    /**
     * 打印链表
     * @param head
     */
    public static void print(Node head){
        Node temp = head;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    static class Node{
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
