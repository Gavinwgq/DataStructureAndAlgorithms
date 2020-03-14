package stack;

/**
 * @author wangguoqiang
 * @date 2020/3/14 15:39
 */
public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkedListStack stack = new SingleLinkedListStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.list();
        stack.push(6);
        System.out.println("栈顶数据为"+stack.pop());
        System.out.println("栈顶数据为"+stack.pop());
        stack.push(6);
        stack.list();
        System.out.println("栈顶数据为"+stack.pop());
        System.out.println("栈顶数据为"+stack.pop());
        System.out.println("栈顶数据为"+stack.pop());
        System.out.println("栈顶数据为"+stack.pop());
        System.out.println("栈顶数据为"+stack.pop());
    }
}

class SingleLinkedListStack{
    private int maxSize;
    private int size = 0;
    private Node topNode = null;

    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull(){
        return size == maxSize;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(int val){
        if(isFull()){
            System.out.println("栈已满，不能添加数据了。");
            return;
        }
        Node node = new Node(val);
        node.next = topNode;
        topNode = node;
        size++;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，在没有数据可弹出");
        }
        int val = topNode.data;
        topNode = topNode.next;
        size--;
        return val;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈为空，在没有数据可弹出");
            return;
        }
        Node temp = topNode;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

}

class Node{
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}
