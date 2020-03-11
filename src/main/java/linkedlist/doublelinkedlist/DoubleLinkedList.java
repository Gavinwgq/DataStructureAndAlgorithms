package linkedlist.doublelinkedlist;


/**
 * @author wangguoqiang
 * @date 2020/3/11 21:11
 */
public class DoubleLinkedList {
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
        heroNode.pre = temp;
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
            heroNode.pre = temp;
            if(temp.next!=null){
                temp.next.pre = heroNode;
            }
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
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;//找到待删除节点的前一个节点，这里很关键
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            if(temp.next!=null){
                temp.next.pre = temp.pre;
            }
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
