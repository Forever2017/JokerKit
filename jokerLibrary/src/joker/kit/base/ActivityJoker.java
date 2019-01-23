package joker.kit.base;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
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


    /**
     * 设置全屏，全屏只是会隐藏状态栏，对标题栏无影响。
     */
    public void isFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




    /**
     * 获取存储权限 ## 6.0和6.0以上，需要手动获取权限
     */
    public void initStoragePermissions() {
        //6.0和6.0以上，需要手动获取权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST);
                return;

            }
        }
    }
    private static final int STORAGE_REQUEST = 112;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(this, "应用程序不允许写在你的存储", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
