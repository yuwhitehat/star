package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;
import com.liuziyu.star.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 删除链表中倒数第n个节点
 * https://leetcode.cn/problems/SLwz0R/
 *
 * @author LiuZiyu
 * @date 2022/09/19 16:12
 */
@Slf4j
public class Solution5 {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 1; i < 8; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        System.out.println(JsonUtil.toString(head));
        ListNode result = removeNthFromEnd(head, 3);
        System.out.println(JsonUtil.toString(result));
    }


    /**
     * 先找到链表中的倒数第N+1个节点,再进行删除操作，即倒数第N+1个节点的next = 其next的next
     * 注意虚拟头结点的使用,如果链表中只有5个节点，而刚好让删除倒数第5个节点，我们在找倒数第6个节点时就会为null,所以有个虚拟头结点就能够避免这种情况
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = getKthFromEnd(dummy, n + 1);
        // 删除
        node.next = node.next.next;
        return dummy.next;
    }

    /**
     * 找到倒数第N个节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // 让指针P1先走K步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p2;
    }

}
