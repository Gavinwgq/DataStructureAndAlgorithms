package stack;

/**
 * @author wangguoqiang
 * @date 2020/3/14 15:19
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.list();
        arrayStack.push(6);
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        arrayStack.list();
        arrayStack.push(6);
        arrayStack.push(7);
        arrayStack.list();
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int num){
        if(isFull()){
            System.out.println("栈已满，不能添加数据了。");
            return;
        }
        stack[++top] = num;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，在没有数据可弹出");
        }
        int val = stack[top];
        top--;
        return val;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈为空，在没有数据可弹出");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
