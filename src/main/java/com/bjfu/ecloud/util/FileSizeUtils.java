package com.bjfu.ecloud.util;

import java.text.DecimalFormat;

public class FileSizeUtils {

    private static final long KB = 1024;
    private static final long MB = 1048576;
    private static final long GB = 1073741824;

    public static String formatFileSize(long originSize) {
        String resultSize = String.valueOf(originSize);
        DecimalFormat decimalFormat = new DecimalFormat(".#");

        if (originSize < (double) (MB/2)) {
            double tmp = originSize / KB;
            resultSize = decimalFormat.format(tmp);
            resultSize += "KB";
        } else if (originSize < (double) (GB/2)) {
            double tmp = originSize/MB;
            resultSize = decimalFormat.format(tmp);
            resultSize += "MB";
        }else {
            double tmp = originSize/GB;
            resultSize = decimalFormat.format(tmp);
            resultSize += "GB";
        }

        return resultSize;
    }
}
