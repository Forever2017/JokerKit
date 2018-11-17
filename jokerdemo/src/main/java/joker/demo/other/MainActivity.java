package joker.demo.other;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import joker.demo.R;
import joker.demo.github.SwitchButtonDemo;
import joker.kit.base.ActivityJoker;
import joker.kit.function.ClipboardManagerJoker;

public class MainActivity extends ActivityJoker {
    ClipboardManagerJoker cmj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        cmj = new ClipboardManagerJoker(this);

        /*// 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        // 添加剪贴板数据改变监听器
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                // 剪贴板中的数据被改变，此方法将被回调

                Toast("Joker - 剪贴板数据变化！");

                Intent i = new Intent(MainActivity.this,SwitchButtonDemo.class);
                startActivity(i);
            }
        });*/
    }

    /*与onResume有些类似，onStart是activity用户可见，包括有一个activity在他上面，
    但没有将它完全覆盖，用户可以看到部分activity但不能与它交互*/
    @Override
    protected void onStart() {
        super.onStart();

        if (cmj.isContent()) {
            Toast("Joker - 有内容，跳转绑定界面！ = " + cmj.getPrimaryClip());

            cmj.clearClip();

            Intent i = new Intent(MainActivity.this, SwitchButtonDemo.class);
            startActivity(i);
        }
    }


}