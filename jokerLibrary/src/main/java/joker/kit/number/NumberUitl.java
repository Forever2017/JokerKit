package joker.kit.number;

import android.text.format.Formatter;

public class NumberUitl {
    /**
     * 字节 转换为B MB GB
     * @param size 字节大小
     * @return
     */
    public static String getPrintSize(long size){
        long rest = 0;
        if(size < 1024){
            return String.valueOf(size) + "B";
        }else{
            size /= 1024;
        }

        if(size < 1024){
            return String.valueOf(size) + "KB";
        }else{
            rest = size % 1024;
            size /= 1024;
        }

        if(size < 1024){
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((rest * 100 / 1024 % 100)) + "MB";
        }else{
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    public static void main(String[] args) {
        System.out.println(getPrintSize(3774575));
    }
}
