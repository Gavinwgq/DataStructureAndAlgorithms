package linkedlist.doublelinkedlist;

/**
 * @author wangguoqiang
 * @date 2020/3/11 21:15
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        HeroNode hero5 = new HeroNode(5,"公孙胜","入云龙");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero5);*/

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();

        System.out.println("测试修改");
        HeroNode updateHero5 = new HeroNode(5,"公孙胜","~入云龙~");
        doubleLinkedList.update(updateHero5);
        doubleLinkedList.list();
        System.out.println("测试删除");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
        System.out.println("删除尾节点");
        doubleLinkedList.delete(5);
        doubleLinkedList.list();

    }
}
