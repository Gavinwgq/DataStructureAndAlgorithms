package DataStructure.stack;

/**
 * 计算器（正整数 加减乘除）
 * @author wangguoqiang
 * @date 2020/3/14 17:14
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-50+3";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义辅助变量
        int index = 0;//用于扫描表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于处理多位数
        while (true){
            ch = expression.substring(index,index+1).charAt(0);
            if(isOper(ch)){
                if(!operStack.isEmpty()){
                    if(priority(ch)<= priority(operStack.peek())){
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        oper = operStack.pop();
                        res = handle(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
            }else{
                keepNum+=ch;
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if(isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        while (true){
            if(operStack.isEmpty()){
                break;
            }
            num2 = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            res = handle(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s=%d\n",expression,numStack.pop());
    }

    /**
     * 判断运算符的优先级
     * @param oper
     * @return
     */
    public static int priority(int oper){
        if('*' == oper || '/' == oper){
            return 1;
        }else if('+' == oper || '-' == oper){
            return 0;
        }else{
            throw new RuntimeException("不支持的运算类型");
        }
    }

    public static boolean isOper(int ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static int handle(int num1,int num2,int oper){
        switch (oper){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                break;
        }
        throw new RuntimeException("不支持的运算类型");
    }

}


class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
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
        return stack[top--];
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈为空，在没有数据可弹出");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("DataStructure.stack[%d]=%d\n",i,stack[i]);
        }
    }


    public int peek() {
        return stack[top];
    }
}
