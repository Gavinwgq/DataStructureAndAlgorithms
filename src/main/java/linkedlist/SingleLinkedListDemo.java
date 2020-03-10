package linkedlist;

import java.util.Stack;

/**
 * 单链表
 * @author wangguoqiang
 * @date 2020/3/8 21:20
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");

        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        HeroNode hero5 = new HeroNode(5,"公孙胜","入云龙");

        SignLinkedList signLinkedList1 = new SignLinkedList();
        SignLinkedList signLinkedList2 = new SignLinkedList();
        /*signLinkedList.add(hero1);
        signLinkedList.add(hero2);
        signLinkedList.add(hero3);
        signLinkedList.add(hero4);*/

        //按编号顺序添加
        signLinkedList1.addByOrder(hero1);
        signLinkedList1.addByOrder(hero3);

        signLinkedList2.addByOrder(hero4);
        signLinkedList2.addByOrder(hero2);
        signLinkedList2.addByOrder(hero5);
        //signLinkedList.addByOrder(hero2);

        //signLinkedList1.list();

        System.out.println("修改------------");
        HeroNode newhero1 = new HeroNode(2,"小卢","~玉麒麟~");
        signLinkedList2.update(newhero1);
        signLinkedList2.list();

        HeroNode newhero2 = new HeroNode(5,"小卢","~玉麒麟~");
        signLinkedList1.update(newhero2);
        System.out.println("删除------");
        signLinkedList1.delete(2);
        //signLinkedList1.list();
        //signLinkedList1.delete(5);

        System.out.println("单链表的长度为size="+signLinkedList1.size());

        HeroNode res1 = signLinkedList1.getLastIndexNode(1);
        System.out.println("res1="+res1);
        HeroNode res2 = signLinkedList2.getLastIndexNode(1);
        System.out.println("res2="+res2);

        System.out.println("------测试链表反转-------");
        System.out.println("原来的链表");
        signLinkedList2.list();
        signLinkedList2.reverseList();
        System.out.println("反转后的链表");
        signLinkedList2.list();
        System.out.println("反转后的链表再逆序打印");
        signLinkedList2.reversePrint();
        signLinkedList2.reverseList();

        System.out.println("单链表1");
        signLinkedList1.list();
        System.out.println("单链表2");
        signLinkedList2.list();
        mergeList(signLinkedList1.getHead(),signLinkedList2.getHead());
    }

    /**
     * 合并两个有序的单链表，合并后依然有序
     * @param head1
     * @param head2
     */
    public static void mergeList(HeroNode head1,HeroNode head2){
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode mergehead = new HeroNode(0,"","");
        HeroNode cur = mergehead;
        while (true){
            if(cur1 == null){
                cur.next = cur2;
                break;
            }
            if(cur2 == null){
                cur.next = cur1;
            }
            if(cur1.no<cur2.no){
                cur.next = cur1;
                cur = cur1;
                cur1 = cur1.next;
            }else{
                cur.next = cur2;
                cur = cur2;
                cur2 = cur2.next;
            }
        }

        System.out.println("打印合并后的链表");
        HeroNode temp = mergehead.next;
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

