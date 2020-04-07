package DataStructure.queue;

import java.util.Scanner;

/**
 * 单向队列
 * @author wangguoqiang
 * @date 2020/3/5 11:35
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):推出程序");
            System.out.println("a(push):添加一个数据到队列");
            System.out.println("p(pop):从队列取出一个数据");
            System.out.println("v(peek):查看队列头部数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        arrayQueue.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int v = scanner.nextInt();
                    arrayQueue.push(v);
                    break;
                case 'p':
                    try {
                        int n = arrayQueue.pop();
                        System.out.printf("取出的数据是%d\n",n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'v':
                    try {
                        int n = arrayQueue.peek();
                        System.out.printf("队列头部数据是%d\n",n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr ;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;//指向队列头部数据的前一个位置
        this.rear = -1;//指向队列尾部数据所在的位置
        this.arr = new int[maxSize];
    }

    public boolean isFull(){
        return this.rear == this.maxSize - 1;
    }

    public boolean isEmpty(){
        return this.rear == this.front;
    }

    public void push(int n){
        if(isFull()){
            System.out.println("队列已满，无法增加数据！");
            return;
        }
        arr[++rear] = n;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[++front];
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

    public void show(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
}
