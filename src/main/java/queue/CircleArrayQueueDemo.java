package queue;

import java.util.Scanner;

/**
 * 环形队列
 * @author wangguoqiang
 * @date 2020/3/6 21:25
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):推出程序");
            System.out.println("a(push):添加一个数据到队列");
            System.out.println("p(pop):从队列取出一个数据");
            System.out.println("v(peek):查看队列头部数据");
            System.out.println("l(size):查看队列有效数据个数");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        queue.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int v = scanner.nextInt();
                    queue.push(v);
                    break;
                case 'p':
                    try {
                        int n = queue.pop();
                        System.out.printf("取出的数据是%d\n",n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'v':
                    try {
                        int n = queue.peek();
                        System.out.printf("队列头部数据是%d\n",n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'l':
                    System.out.printf("队列有效数据个数是%d\n",queue.size());
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

class CircleArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr ;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = 0;//指向队列头部数据所在个位置
        this.rear = 0;//指向队列尾部数据后一个位置 （为了空出来一个位置）
        this.arr = new int[maxSize];
    }

    public boolean isFull(){
        return (this.rear + 1)%this.maxSize == this.front;
    }

    public boolean isEmpty(){
        return this.rear == this.front;
    }

    public void push(int n){
        if(isFull()){
            System.out.println("队列已满，无法增加数据！");
            return;
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int v = arr[front];
        front = (front+1)%maxSize;
        return v;
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    public void show(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
}
