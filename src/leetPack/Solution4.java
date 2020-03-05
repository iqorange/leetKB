package leetPack;

import java.util.*;

public class Solution4 {
    // 876. 链表的中间结点
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode car = head;
        ListNode point = head;
        while (car.next != null){
            if (car.next.next != null){
                car = car.next.next;
                point = point.next;
            }else{
                car = car.next;
                point = point.next;
            }

        }
        return point;
    }
}
