package joker.kit.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import joker.kit.utils.ActivityManager;
import joker.kit.utils.LogUtils;

public class ActivityJoker extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**将当前Activity推入栈中*/
        ActivityManager.getInstance().pushActivity(this);
    }

    /**
     * Activity被摧毁时调用...
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**从栈中删除Activity*/
        ActivityManager.getInstance().popActivity(this);
    }


    /**
     * Toast代码简洁
     */
    public void Toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * Activity从后台重新回到前台时被调用(创建时不调用)
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Activity创建或者从后台重新回到前台时被调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        /*与onResume有些类似，onStart是activity用户可见，包括有一个activity在他上面，
            但没有将它完全覆盖，用户可以看到部分activity但不能与它交互*/
    }


}
