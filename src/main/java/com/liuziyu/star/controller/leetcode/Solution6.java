package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;

/**
 * 还是链表....快慢指针技巧超级好用
 *
 * @author LiuZiyu
 * @date 2022/09/19 16:14
 */
public class Solution6 {
    /**
     * 链表的中间节点
     * https://leetcode.cn/problems/middle-of-the-linked-list/
     *
     * 快慢指针法
     * slow指针走一步，fast指针就走两步，当fast到达链表结尾时，slow指针就到中间
     * 如果链表是双数个节点，slow最终指向的是中间两个靠后的那个节点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 加几行代码 可以解 环形链表
     * 判断链表中是否含有环
     *
     * @param head
     * @return
     */
    boolean hasCycle(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，说明含有环
            if (slow == fast) {
                return true;
            }
        }
        // 不包含环
        return false;
    }

    /**
     * 环形链表2--- 返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     * https://leetcode.cn/problems/linked-list-cycle-ii/
     *
     * 1.可以用Set集合
     * 2.下面是双指针的解法 假设慢指针走了K步和快指针相遇，那快指针就多走了K步，所以K就是这个环的整数倍（如何理解）
     *      相遇后，让其中一个指针指向head，然后两个指针一起走，再次相遇时就会
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，说明含有环
            if (slow == fast) {
                break;
            }
        }
        // 如果fast指向null，则说明没有环
        if (fast == null || fast.next == null) {
            return null;
        }
        // 重新让某个指针指向头
        slow = head;
        while (fast != slow) {
            // 同时走，当两个指针再次相遇就是入环的第一个节点
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * https://leetcode.cn/problems/intersection-of-two-linked-lists/
     * 相交链表 --- 找出两个链表的相交的节点，如果没有相交则返回null，不可修改原链表
     *
     * 思路：如果用HashSet先把一个链表的节点记录下来，再和另一个链表对比，这样的话就需要额外的空间
     * 所以思考如何用两个指针来得到相交的节点？
     * 由于两个链表的长度可能不同，节点的位置可能对不上，如果让两个指针同时走，但不一定能同时在相交点相遇
     * 所以关键点在于 通过某种方式，让两个指针在相交点相遇
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            // p1遍历A表，A表遍历结束后开始遍历B表
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            // 同理，p2遍历B表，B表结束后让其开始遍历A表，在逻辑上相当于将两个表连在了一起
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        // 最终,如果两个链表有相交点，p1和p2就能够相遇，如果没有相交点，最终两个指针都会指向null
        return p1;
    }

    /**
     * 相交链表解法2
     * 如果我们能够先计算出两个链表的长度
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        // 计算长度
        for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
            lenA++;
        }
        for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
            lenB++;
        }

        ListNode p1 = headA, p2 = headB;
        // 如果A表的长度大于B表，让p1先走长度差
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                p1 = p1.next;
            }
        } else {
            // 否则，让p2先走
            for (int i = 0; i < lenB - lenA; i++) {
                p2 = p2.next;
            }
        }

        // 然后让两个指针同时开始走，如果两个链表有相交点，则两个指针会同时走到相交点
        // 如果没有相交点，两个指针都会走到null
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

}
