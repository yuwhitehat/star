package com.liuziyu.star.controller.leetcode;

import com.liuziyu.star.entity.leetcode.ListNode;
import com.liuziyu.star.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

/**
 * 合并K个有序链表
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * @author LiuZiyu
 * @date 2022/09/08 18:28
 */
@Slf4j
public class Solution3 {

    public static void main(String[] args) {
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = head1;
        listNodes[1] = head2;
        listNodes[2] = head3;
        log.info("有序链表为：{}", JsonUtil.toString(listNodes));
        ListNode result = mergeKLists(listNodes);
        log.info("合并K个有序链表,结果为，{}", JsonUtil.toString(result));
    }


    /**
     * 1.虚拟头结点
     * 2.使用优先级队列 最小堆 用来找到K个链表中最小的值
     * 3.获取最小节点接在虚拟头结点后面
     * 4.
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val - b.val));
        // 将 k 个链表的头结点加入最小堆
        for (ListNode head : lists) {
            if (head != null)
                pq.add(head);
        }

        while (!pq.isEmpty()) {
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
        return dummy.next;
    }
}
