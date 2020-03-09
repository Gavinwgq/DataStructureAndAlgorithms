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

        System.out.println("修改------------");
        HeroNode newhero1 = new HeroNode(2,"小卢","~玉麒麟~");
        signLinkedList.update(newhero1);

        signLinkedList.list();

        HeroNode newhero2 = new HeroNode(5,"小卢","~玉麒麟~");
        signLinkedList.update(newhero2);
        System.out.println("删除------");
        signLinkedList.delete(2);
        signLinkedList.list();
        signLinkedList.delete(5);

        System.out.println("单链表的长度为size="+size(signLinkedList.getHead()));

        HeroNode res1 = getLastIndexNode(signLinkedList.getHead(),1);
        System.out.println("res1="+res1);
        HeroNode res2 = getLastIndexNode(signLinkedList.getHead(),3);
        System.out.println("res2="+res2);
    }


    /**
     * 获取单链表长度（不包含头节点）
     * @param head 头节点
     * @return
     */
    public static int size(HeroNode head){
        if(head.next == null){
            return 0;
        }
        HeroNode cur = head.next;
        int size = 0;
        while (cur!=null){
            size++;
            cur = cur.next;
        }
        return size;
    }

    /**
     * 获取单链表倒数第k个节点
     * @param head 头节点
     * @param index k
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode head,int index){
        if(head.next == null || index < 0){
            return null;
        }
        int size = size(head);
        if(index>size){
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

}

class SignLinkedList{
    //头节点，不存储具体数据，表示链表起点，位置不会变动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

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

    //根据heronode的no查找更新
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;//到链表尾部了
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.printf("没有找到编号为%d的英雄\n",newHeroNode.no);
        }
    }

    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;//找到待删除节点的前一个节点，这里很关键
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("没有找到编号%d的英雄,无法删除\n",no);
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
