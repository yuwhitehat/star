package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;
import com.liuziyu.star.util.JsonUtil;

import java.awt.event.FocusEvent;

/**
 * 链表中倒数第K个节点
 * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 *
 * @author LiuZiyu
 * @date 2022/09/19 16:11
 */
public class Solution4 {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 1; i < 8; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        System.out.println(JsonUtil.toString(head));
        ListNode result = getKthFromEnd(head, 3);
        System.out.println(JsonUtil.toString(result));
    }

    /**
     * 1.如果链表的长度为n,则链表中的倒数第K个节点是链表的第（n - k + 1）个节点，可以先得出链表的长度，然后找到第（n - k + 1）个节点，但这样就要遍历两次
     * 2.只遍历一次该怎么做？
     * 使用双指针。指针P1走了k步时，就会剩下（n - k）步，
     * 这时让指针P2指向头结点，然后两个指针同时走，
     * 当指针P1继续走了（n - k）步时，也就是走到最后指向null时，指针P2也走了（n - k）步，此时指针P2指向的就是第（n - k + 1） 个节点
     *
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
