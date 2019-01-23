package joker.kit.utils;

import joker.kit.file.SdcardUtil;

public class IniData {

    public static String SD_PATH = null;


    public static final String MAIN_FILE = "/Joker";
    public static final String VIDEO_FILE = MAIN_FILE + "/video";
    

    public static void initData(){
        SD_PATH = SdcardUtil.sdFile();

    }



}
