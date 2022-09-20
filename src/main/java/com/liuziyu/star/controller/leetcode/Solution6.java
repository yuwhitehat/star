package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;

/**
 * 链表的中间节点
 * https://leetcode.cn/problems/middle-of-the-linked-list/
 *
 * @author LiuZiyu
 * @date 2022/09/19 16:14
 */
public class Solution6 {
    /**
     * 快慢指针
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

}
