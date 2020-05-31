package leetPack.LinkedList;

import java.math.BigInteger;
import java.util.*;

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

    // 82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        while (head != null){
            if (!set.contains(head.val)){
                deque.addFirst(head.val);
                set.add(head.val);
            }else{
                deque.remove(head.val);
            }
            head = head.next;
        }
        ListNode point = new ListNode(-1);
        ListNode node = point;
        while (!deque.isEmpty()){
            point.next = new ListNode(deque.removeLast());
            point = point.next;
        }
        return node.next;
    }

    // 21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2; if (l2 == null) return l1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while (l1 != null){
            queue.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            queue.add(l2.val);
            l2 = l2.next;
        }
        ListNode l3 = new ListNode(-1);
        ListNode head = l3;
        while (!queue.isEmpty()){
            l3.next = new ListNode(queue.remove());
            l3 = l3.next;
        }
        return head.next;
    }

    // 25. K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode point = head;
        int count = 0;
        while (point != null && count<k){
            point = point.next;
            count ++;
        }
        if (count < k){
            return head;
        }
        ListNode current = null;
        ListNode pointP = head;
        while (point != pointP){
            ListNode temp = pointP.next;
            pointP.next = current;
            current = pointP;
            pointP = temp;
        }
        head.next = reverseKGroup(point, k);
        return current;
    }

    // 147. 对链表进行插入排序
    public ListNode insertionSortList(ListNode head) {
       ListNode dummy = new ListNode(-1);
       ListNode pre;
       dummy.next = head;
       while (head != null && head.next != null){
           if (head.val <= head.next.val){
               head = head.next;
               continue;
           }
           pre = dummy;
           while (pre.next.val < head.next.val){
               pre = pre.next;
           }
           ListNode current = head.next;
           head.next = current.next;
           current.next = pre.next;
           pre.next = current;
       }
       return dummy.next;
    }

    // 148. 排序链表 ***
    // 归并排序法
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }
    private ListNode mergeSort(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode slowPoint = head, fastPoint = head.next.next, left, right;
        while (fastPoint != null && fastPoint.next != null){
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
        }
        right = mergeSort(slowPoint.next);
        slowPoint.next = null;
        left = mergeSort(head);
        return mergeList(left, right);
    }
    private ListNode mergeList(ListNode left, ListNode right){
        ListNode dummyHead = new ListNode(-1);
        ListNode point = dummyHead;
        while (left != null && right != null){
            if (left.val < right.val){
                point.next = left;
                left = left.next;
            }else{
                point.next = right;
                right = right.next;
            }
            point = point.next;
        }
        point.next = left == null ? right : left;
        return dummyHead.next;
    }
    // 快速排序法
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        return quickSort(dummyHead, null);
    }
    private ListNode quickSort(ListNode head, ListNode end){
        if (head == end || head.next == end || head.next.next == end) return head;
        ListNode tempHead = new ListNode(-1);
        ListNode partition = head.next;
        ListNode point = partition;
        ListNode tempPoint = tempHead;
        while (point.next != end){
            if (point.next.val < partition.val){
                tempPoint.next = point.next;
                tempPoint = tempPoint.next;
                point.next = point.next.next;
            }else{
                point = point.next;
            }
        }
        tempPoint.next = head.next;
        head.next = tempHead.next;
        quickSort(head, partition);
        quickSort(partition, end);
        return head.next;
    }
    // 辅助数据结构法
    public ListNode sortList3(ListNode head){
        if (head == null || head.next == null) return head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val -o2.val);
        while (head != null){
            queue.add(head);
            head = head.next;
        }
        ListNode point = new ListNode(-1);
        ListNode sortedHead = point;
        while (!queue.isEmpty()){
            point.next = queue.remove();
            point = point.next;
        }
        point.next = null;
        return sortedHead.next;
    }
}
