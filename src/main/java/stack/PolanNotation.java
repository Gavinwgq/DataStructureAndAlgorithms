package stack;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 逆波兰表达式（后缀表达式）的计算
 * @author wangguoqiang
 * @date 2020/3/14 18:18
 */
public class PolanNotation {

    public static void main(String[] args) {
        //后缀表达式（也叫逆波兰表达式） (3+4)*5-6 = 29
        String expression = "3 4 + 5 * 6 -";

        System.out.println("表达式结果="+calcuate(expression));
    }

    public static int calcuate(String expression){
        String[] strings = expression.split(" ");
        Stack<String> stack = new Stack<>();
        for (String s : strings) {
            if(s.matches("\\d+")){
                stack.push(s);
            }else{
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(s.equals("+")){
                    res = num1 + num2;
                }else if(s.equals("-")){
                    res = num1 - num2;
                }else if(s.equals("*")){
                    res = num1 * num2;
                }else if(s.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误。");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
