package com.kittycoder.util;

/**
 * Created by shucheng on 2018/4/29.
 * 在StringBuffer中替换字符串
 * 01 replaceFirst 替换首字符
 * 02 replaceAll 替换所有字符
 *
 * 这两个工具方法的封装过程可以参看test/java/StringBufferTest.java，
 * 封装思路参考了https://bbs.csdn.net/topics/350094135?list=lz
 *
 * StringBufferTest.java位于：MyJavaUtils/src/test/com/kittycoder/util/StringBufferUtilTest.java
 */
public class StringBufferUtil {

    /**
     * 替换首个出现的字符（基于test1和test2进行封装）
     * @param sb
     * @param target
     * @param replacement
     * @return
     */
    public static StringBuffer replaceFirst(StringBuffer sb, String target, String replacement) {
        // 1.查找字符串中是否存在替换目标，不存在为-1
        int index = sb.indexOf(target);
        // 2.如果存在替换目标，则进行替换操作（这里的7是替换目标的字符长度）
        if (index != -1) {
            sb.replace(index, index + target.length(), replacement);
        }
        return sb;
    }

    /**
     * 替换所有出现的字符（基于test3和test4进行封装）
     * @param sb
     * @param target
     * @param replacement
     * @return
     */
    public static StringBuffer replaceAll(StringBuffer sb, String target, String replacement) {
        int index = -1;
        // 1.查找字符串中是否存在替换目标，不存在为-1

        while ((index = sb.indexOf(target)) != -1) {
            // 2.如果存在替换目标，则进行替换操作（这里的7是替换目标的字符长度）
            sb.replace(index, index + target.length(), replacement);
        }
        return sb;
    }
}
