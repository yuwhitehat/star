package com.liuziyu.star.entity.leetcode;

/**
 * 链表
 *
 * @author LiuZiyu
 * @date 2022/09/08 13:59
 */
public class ListNode {
    /**
     * 值
     */
    public int val;

    /**
     * 下一节点
     */
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
