package linkedlist.circlesinglelinkedlist;

/**
 * 约瑟夫问题
 * @author wangguoqiang
 * @date 2020/3/13 21:00
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList cl = new CircleSingleLinkedList();
        cl.addBoys(10);
        //cl.show();
        cl.leaveCircle(2,3,10);
    }
}
class CircleSingleLinkedList{
    private Boy first;

    public void addBoys(int nums){
        if(nums<1){
            System.out.println("小孩数不能小于1");
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                curBoy = boy;
                first.next = boy;
            }else{
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    public void show(){
        if(first == null){
            System.out.println("小孩队伍为空");
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩编号%d\n",curBoy.no);
            if(curBoy.next == first){
                break;
            }
            curBoy = curBoy.next;
        }
    }

    public void leaveCircle(int k,int m,int nums){
        if(first == null || k<1 || m<1 || k>nums){
            System.out.println("参数有误");
        }
        Boy helper = first;
        //辅助指针指首先指向环形链表的最后一个节点
        while (true){
            if(helper.next == first){
                break;
            }
            helper = helper.next;
        }
        //从第k个开始，先移动k-1次
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        while (true){
            if(helper == first){
                break;//只有一个节点了，循环结束
            }
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.printf("小孩%d出圈\n",first.no);
            first = first.next;
            helper.next = first;
        }
        System.out.printf("小孩%d出圈\n",first.no);
    }
}
class Boy{
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
