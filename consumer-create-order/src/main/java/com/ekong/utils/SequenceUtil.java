package com.ekong.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 序列号生成工具
 */
public class SequenceUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static int size = 12;   //补位最大长度

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * example: SequenceUtil.autoGenericSequence(12, 9876) = 20170326000000009876
     *
     * @param seqNum 序号
     * @return
     */
    public static String autoGenericSequence(long seqNum) {
        String dateStr = LocalDate.now().format(formatter);
        return dateStr + String.format("%0" + size + "d", seqNum);
    }

}
