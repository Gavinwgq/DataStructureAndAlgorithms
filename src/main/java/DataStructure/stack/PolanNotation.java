package DataStructure.stack;

import com.google.common.collect.Lists;

import java.util.*;

/**
 *
 * @author wangguoqiang
 * @date 2020/3/14 18:18
 */
public class PolanNotation {

    public static void main(String[] args) {
        //后缀表达式（也叫逆波兰表达式） (13+4)*5-16 = 69 (3+4)*5-6 = 29 1+(2+3)*4-5 = 16
        List<String> expressionList = Lists.newArrayList("(13+4)*5-16","(3+4)*5-6","1+(2+3)*4-5");
        for (String expression : expressionList) {
            System.out.printf("%s = %d\n",expression,calcuate(infix2Suffix(expression)));
        }
    }

    //中缀表达式转后缀
    public static List<String>  infix2Suffix(String expression){
        List<String> s2 = Lists.newArrayList();
        Stack<String> s1 = new Stack<>();
        List<String> list = exp2List(expression);
        for (String s : list) {
            if(s.matches("\\d+")){
                s2.add(s);
            }else if("(".equals(s)){
                s1.push(s);
            }else if(")".equals(s)){
                while (!"(".equals(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                while (!s1.isEmpty() && priority(s)<=priority(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将一个表达式拆解成list 如 23+5*4 -> ["23","+","5","*","4"]
     * @param expression
     * @return
     */
    public static List<String> exp2List(String expression){
        List<String> result = Lists.newArrayList();
        char[] chars = expression.toCharArray();
        String keepNum = "";//用于多位数的拼接
        int i = 0;
        do{
            if(Character.isDigit(chars[i])){
                while (i<chars.length && Character.isDigit(chars[i])){
                    keepNum+=chars[i];
                    i++;
                }
                result.add(keepNum);
                keepNum = "";
            }else{
                result.add(String.valueOf(chars[i]));
                i++;
            }
        }while (i < chars.length);
        return result;
    }

    /**
     * 判断运算符的优先级
     * @param oper
     * @return
     */
    public static int priority(String oper){
        if(oper.equals("*")  || "/".equals(oper)){
            return 1;
        }else if(oper.equals("+")  || "-".equals(oper)){
            return 0;
        }else{
            return -1;
        }
    }

    public static int calcuate(List<String> items){
        Stack<String> stack = new Stack<>();
        for (String s : items) {
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
