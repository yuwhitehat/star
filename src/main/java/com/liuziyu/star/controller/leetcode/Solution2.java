package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;

/**
 * 分割链表
 * https://leetcode.cn/problems/partition-list/
 *
 * @author LiuZiyu
 * @date 2022/09/08 17:20
 */
public class Solution2 {

    /**
     * 双指针
     * 1.虚拟头结点的使用
     * 2.对链表遍历 分成大于X和小于X的两个链表
     * 3.将两个链表链接在一起
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        // dummy1是小于X的链表的虚拟头结点
        ListNode dummy1 = new ListNode(-1);
        // dummy2是大于X的链表的虚拟头结点
        ListNode dummy2 = new ListNode(-1);

        // p1,p2两个指针负责生成结果链表
        ListNode p1 = dummy1;
        ListNode p2 = dummy2;

        // p负责遍历原链表
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }

            // 关键步骤
            // 断开原链表中的每个节点的 next 指针，否则会死循环
            // 原因：如果倒数第二个大于x，倒数第一个小于x，这时候倒数第二个还连着倒数第一个，然后再头尾一连就形成循环；如果最后一个是大于x就不会出现这种情况
            ListNode tmp = p.next;
            p.next = null;
            p = tmp;
        }
        // 或者直接在这里将大于x的链表的next置为null，也可解决上述死循环的问题
        // p2.next = null;

        // 连接两个链表
        p1.next = dummy2.next;
        return dummy1.next;
    }
}
