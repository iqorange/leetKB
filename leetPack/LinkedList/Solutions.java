package leetPack.LinkedList;

import java.math.BigInteger;

public class Solutions {

    // 92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode node = new ListNode(0);
        node.next = head;
        // 前一个指针
        ListNode point = node;
        // 移动到M的位置上
        for(int i = 1; i < m; i++){
            point = point.next;
        }
        head = point.next;
        // 反转链表段
        for(int i = m; i < n; i++){
            // 保存当前节点的下一节点
            ListNode next = head.next;
            // 指针后移
            head.next = next.next;
            next.next = point.next;
            point.next = next;
        }
        return node.next;
    }

    // 83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode point = head;
        while (point.next != null){
            if (point.val == point.next.val){
                point.next = point.next.next;
            }else{
                point = point.next;
            }
        }
        return head;
    }

    // 86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode smaller = new ListNode(-1);
        ListNode larger = new ListNode(-1);
        ListNode sHead = smaller;
        ListNode lHead = larger;
        while (head != null){
            if (head.val < x){
                sHead.next = head;
                head = head.next;
                sHead = sHead.next;
                sHead.next = null;
            }else{
                lHead.next = head;
                head = head.next;
                lHead = lHead.next;
                lHead.next = null;
            }
        }
        sHead.next = larger.next;
        return smaller.next;
    }

    // 328. 奇偶链表
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode point1 = new ListNode(-1);
        ListNode point2 = new ListNode(-1);
        boolean odd = true;
        ListNode node1 = point1;
        ListNode node2 = point2;
        while (head != null){
            if (odd){
                point1.next = head;
                head = head.next;
                point1 = point1.next;
                point1.next = null;
            }else{
                point2.next = head;
                head = head.next;
                point2 = point2.next;
                point2.next = null;
            }
            odd = !odd;
        }
        point1.next = node2.next;
        return node1.next;
    }

    // 445. 两数相加 II
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2; if (l2 == null) return l1;
        BigInteger num1 = sortListNum(l1);
        BigInteger num2 = sortListNum(l2);
        ListNode point = new ListNode(-1);
        ListNode head = point;
        for (char e: String.valueOf(num1.add(num2)).toCharArray()){
            point.next = new ListNode(Integer.parseInt(String.valueOf(e)));
            point = point.next;
        }
        return head.next;
    }
    private BigInteger sortListNum(ListNode listNode){
        StringBuilder builder = new StringBuilder();
        while (listNode != null){
            builder.append(listNode.val);
            listNode = listNode.next;
        }
        return new BigInteger(builder.toString());
    }
}
