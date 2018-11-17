package joker.kit.base;

import android.app.Activity;
import android.widget.Toast;

public class ActivityJoker extends Activity {

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
