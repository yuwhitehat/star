package com.liuziyu.star.controller.leetcode;

/**
 * LeetCode 重新排列单词中的空格
 * https://leetcode.cn/problems/rearrange-spaces-between-words/
 *
 * @author LiuZiyu
 * @date 2022/09/07 16:27
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(reorderSpaces("  this   is  a sentence "));
    }

    /**
     * 1.计算空格数
     * 2.计算有几个单词
     * 3.如果只有一个单词，就把全部空格拼接在单词后面
     * 4.一个以上单词，计算平均空格数，将平均值拼接在每个单词后面
     * 5.若有多余的空格，放在最后一个单词后面
     *
     * @param text
     * @return
     */
    public static String reorderSpaces(String text) {
        int length = text.length();
        String[] textArray = text.trim().split("\\s+");
        // 空格的长度
        int spaceLength = length;
        for (int i = 0; i < textArray.length; i++) {
            spaceLength -= textArray[i].length();
        }

        StringBuilder sb = new StringBuilder();
        if (textArray.length == 1) {
            sb.append(textArray[0]);
            for (int i = 0; i < spaceLength; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }

        int avgSpaceLength = spaceLength / (textArray.length - 1);
        int restSpaceLength = spaceLength % (textArray.length - 1);
        for (int i = 0; i < textArray.length; i++) {
            if (i > 0) {
                for (int j = 0; j < avgSpaceLength; j++) {
                    sb.append(" ");
                }
            }
            sb.append(textArray[i]);
        }
        if (restSpaceLength > 0) {
            for (int i = 0; i < restSpaceLength; i++) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
