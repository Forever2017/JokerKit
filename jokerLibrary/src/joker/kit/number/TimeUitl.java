package joker.kit.number;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具相关
 */
public class TimeUitl {
    private static SimpleDateFormat df;

    /**
     * int转时间  1500秒转换成 00:00:00
     */
    public static String FormatMiss(int time) {
        String hh = time / 3600 > 9 ? time / 3600 + "" : "0" + time / 3600;
        String mm = (time % 3600) / 60 > 9 ? (time % 3600) / 60 + "" : "0" + (time % 3600) / 60;
        String ss = (time % 3600) % 60 > 9 ? (time % 3600) % 60 + "" : "0" + (time % 3600) % 60;
        return hh + ":" + mm + ":" + ss;
    }

    /**
     * 获取当前时间
     * "yyyy-MM-dd HH:mm:ss"
     */
    public static String getCurrentTimeHMS() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    /**
     * 获取当前时间(精确到毫秒)
     * "yyyy-MM-dd HH:mm:ss.SSS"
     */
    public static String getCurrentTimeHMSS() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置日期格式
        return df.format(new Date());
    }

    //系统默认的13位，long类型时间戳
    public static String TimeLong() {
        return String.valueOf(new Date().getTime()); //String.valueOf() 转换类型
    }

    //long类型时间戳 10位...
    public static String TimeLongTen() {
        return String.valueOf(new Date().getTime() / 1000);
    }

}


