package joker.kit.base;

import android.app.Application;

public class JBaseApplication extends Application {
    /*是否第一次执行*/
    private boolean IS_FIRST = true;

    /**
     * <application
     * <p>
     * android:name="xxx.xxx.JBaseApplication"
     * <p>
     * // android:allowBackup="true"
     * //  android:icon="@drawable/ic_launcher"
     * //  android:label="@string/app_name"
     * //  android:theme="@style/AppTheme" >
     */

    @Override
    public void onCreate() {
        super.onCreate();

        initialize();
    }

    /**
     * 暴露接口，执行一些需要初始化的动作
     */
    public void initialize() {
        if (IS_FIRST) IS_FIRST = false;
        else return;
    }


}
