package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;

/**
 * 合并两个有序链表
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 * @author LiuZiyu
 * @date 2022/09/08 13:56
 */
public class Solution1 {
    /**
     * 双指针
     * 1.虚拟头节点的使用
     * 2.while循环 注意循环条件
     * 3.注意剩下的那个链表还有个节点
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }
}
