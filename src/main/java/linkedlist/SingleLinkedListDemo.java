package linkedlist;

/**
 * 单链表
 * @author wangguoqiang
 * @date 2020/3/8 21:20
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SignLinkedList signLinkedList = new SignLinkedList();
        /*signLinkedList.add(hero1);
        signLinkedList.add(hero2);
        signLinkedList.add(hero3);
        signLinkedList.add(hero4);*/

        //按编号顺序添加
        signLinkedList.addByOrder(hero1);
        signLinkedList.addByOrder(hero3);
        signLinkedList.addByOrder(hero4);
        signLinkedList.addByOrder(hero2);
        //signLinkedList.addByOrder(hero2);

        signLinkedList.list();
    }

}

class SignLinkedList{
    //头节点，不存储具体数据，表示链表起点，位置不会变动
    private HeroNode head = new HeroNode(0,"","");

    //不考虑编号顺序，直接加到链表尾部
    public void add(HeroNode heroNode){
        //使用一个临时变量辅助遍历
        HeroNode temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //当遍历结束时temp指向最后一个节点
        temp.next = heroNode;
    }

    //按照heronode的no属性大小进行添加，如果编号已存在提示添加失败
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next ==null){
                break;//已到链表尾部
            }
            if(temp.next.no>heroNode.no){
                break;//位置找到
            }
            if(temp.next.no == heroNode.no){
                flag = true;
                break;//编号已存在
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("英雄编号%d已经存在，不能加入了\n",heroNode.no);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //遍历输出
    public void list(){
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            //打印节点
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
