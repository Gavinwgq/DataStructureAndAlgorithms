package hashtab;

import java.util.Scanner;

/**
 * 哈希 散列表
 * @author wangguoqiang
 * @date 2020/3/26 19:42
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:打印雇员");
            System.out.println("find:查找雇员");
            System.out.println("del:删除雇员");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    hashTab.add(new Emp(id,name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的id");
                    int empId = scanner.nextInt();
                    hashTab.findEmpById(empId);
                    break;
                case "del":
                    System.out.println("输入要删除的id");
                    int delId = scanner.nextInt();
                    hashTab.del(delId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab{
    private EmpLinkedList[] empLinkedListArr;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArr = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        int empLinkedListIndex = hashFun(emp.id);
        empLinkedListArr[empLinkedListIndex].add(emp);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    public void findEmpById(int id){
        int empLinkedListIndex = hashFun(id);
        Emp emp = empLinkedListArr[empLinkedListIndex].findEmpById(id);
        if(emp == null){
            System.out.println("未找到该雇员");
        }else{
            System.out.printf("找到雇员:id=%d,name=%s\n",emp.id,emp.name);
        }
    }

    public void del(int id){
        int empLinkedListIndex = hashFun(id);
        empLinkedListArr[empLinkedListIndex].delById(id);
    }

    private int hashFun(int id){
        return id % size;
    }
}

class EmpLinkedList{
    private Emp head;

    public void add(Emp emp){
        if(head == null){
            head = emp;
        }else{
            Emp curEmp = head;
            while (true){
                if(curEmp.next == null){
                    break;
                }
                curEmp = curEmp.next;
            }
            curEmp.next = emp;
        }
    }

    public void list(int no){
        if(head == null){
            System.out.printf("第%d个链表为空\n",no);
            return;
        }
        System.out.printf("第%d个链表雇员信息为：",no);
        Emp curEmp = head;
        while (true){
            System.out.printf("==>id=%d,name=%s\t",curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp findEmpById(int id){
        if(head == null){
            return null;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.id == id){
                return curEmp;
            }
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        return null;
    }

    public void delById(int id){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        if(head.id == id){
            head = head.next;
            return;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.next == null){
                break;
            }
            if(curEmp.next.id == id){
                curEmp.next = curEmp.next.next;
                return;
            }
            curEmp = curEmp.next;
        }
        System.out.println("没有该雇员信息");
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
