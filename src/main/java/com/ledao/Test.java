package com.ledao;

import org.ansj.splitWord.analysis.ToAnalysis;

/**
 * @author LeDao
 * @company
 * @create 2022-12-20 3:42
 */
public class Test {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String text = "20个左边的卡罗拉倒车镜! ";
        String analysisedText = ToAnalysis.parse(text).toStringWithOutNature();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("精准分词: " + analysisedText + "(" + time + "ms)");
    }
}
