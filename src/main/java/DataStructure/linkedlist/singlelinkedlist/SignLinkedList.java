package DataStructure.linkedlist.singlelinkedlist;

import java.util.Stack;

public class SignLinkedList{
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

    //反转单链表（头插法）
    public  void reverseList(){
        if(head.next == null || head.next.next == null){
            return;//链表为空或只有一个元素，无需反转
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        while (cur!=null){
            next = cur.next;//保存当前节点的下一个节点，后面有用
            cur.next = reverseHead.next;//将cur的下一个节点指向新链表的最前端
            reverseHead.next = cur;//将cur链接到新链表上
            cur = next;//cur 后移
        }
        head.next = reverseHead.next;
    }

    //单链表逆序打印，不改变链表结构
    public  void reversePrint(){
        if(head.next == null){
            return;
        }
        HeroNode cur = head.next;
        //利用栈的后进先出的特性
        Stack<HeroNode> stack = new Stack<>();
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 获取单链表倒数第k个节点
     * @param index k
     * @return
     */
    public HeroNode getLastIndexNode(int index){
        if(head.next == null || index < 0){
            return null;
        }
        int size = size();
        if(index>size){
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 获取单链表长度（不包含头节点）
     * @return
     */
    public  int size(){
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
}

