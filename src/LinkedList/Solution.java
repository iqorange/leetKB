package LinkedList;

public class Solution {
//    快乐数
    public boolean isHappy(int n) {
        int t = n;
        while (t != 1 && t != 4 && t!=58){
            String str = Integer.toString(t);
            t = 0;
            for(int i=0;i<str.length();i++){
                int s = Integer.parseInt(String.valueOf(str.charAt(i)));
                t += s*s;
            }
        }
        if(t == 1){
            return true;
        }else{
            return false;
        }
    }

//    203、移除链表元素
    public ListNode removeElements1(ListNode head, int val) {
        // 循环判断头节点是否是要删除的节点
        while (head != null && head.val == val){
            // 删除节点指向头节点
//            LinkedList.ListNode delNode = head;
            // 头节点指向下一节点
//            head = head.next;
            //删除节点
//            delNode = null;
            head = head.next;
        }
        // 判断列表是不是已经空了，空了就直接返回
        if (head == null){
            return null;
        }
        // 判断链表的中间部分
        // （此时的head已经不是要删除的节点了）
        ListNode prev = head;
        // 循环判断下一个节点直到空
        while (prev.next != null){
            // 如果这个节点需要被删除
            if(prev.next.val == val){
                // 开始删除该节点
                // 找到要删除的节点
//                LinkedList.ListNode delNode = prev.next;
                // 绕过delNode节点
//                prev.next = delNode.next;
                // 断开delNode与链表的联系
//                delNode = null;
                // 现在prev的节点已经改变，已经不需要向后移动一节点
                prev.next = prev.next.next;
            }else{
                // 否则的话prev向后移动一节点
                prev = prev.next;
            }
        }
        // 循环完毕之后返回链表
        return head;
    }

//    上一题的虚拟头节点方法
    public ListNode removeElements2(ListNode head, int val){
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

//    上一题的递归方法
    public ListNode removeElements(ListNode head, int val){
        if (head==null)return null;
        head.next = removeElements(head.next, val);
        return head.val==val?head.next:head;
    }
}