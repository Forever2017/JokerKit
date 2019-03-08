package joker.demo.other;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ios.imitation.BottomDialog.BottomDialog;
import joker.demo.R;
import joker.demo.github.SmartRefreshLayoutDeam;
import joker.demo.github.SwitchButtonDemo;
import joker.kit.base.ActivityJoker;
import joker.kit.function.ClipboardManagerJoker;

public class MainActivity extends ActivityJoker {
    ClipboardManagerJoker cmj;

    Button but, test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        but = findViewById(R.id.but);
        test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SmartRefreshLayoutDeam.class));
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog.Builder()
                        .setTitle("标题")
                        .setActivity(MainActivity.this)
                        .setmHeight(40)
                        .setmTextSize(16)
                        .build()
                        .addSheetItem(new BottomDialog.SheetItemBean("上传图片", 16, R.color.green, new BottomDialog.JokerItemOnClick() {
                            @Override
                            public void ItemOnClick(View view) {
                                super.ItemOnClick(view);
                                Toast("点击上传图片！");
                            }
                        }))
                        .addSheetItem(new BottomDialog.SheetItemBean("占个位置", 16, R.color.black, new BottomDialog.JokerItemOnClick() {
                            @Override
                            public void ItemOnClick(View view) {
                                super.ItemOnClick(view);
                                Toast("占位置");
                            }
                        }))
                        .show();
            }
        });




        /*new BottomDialog.Builder()
                .setTitle("标题")
                // .setContext(context)
                .setActivity(this)
                .build()
                .addSheetItem()
                .show();*/


//        cmj = new ClipboardManagerJoker(this);

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

       /* if (cmj.isContent()) {
            Toast("Joker - 有内容，跳转绑定界面！ = " + cmj.getPrimaryClip());

            cmj.clearClip();

            Intent i = new Intent(MainActivity.this, SwitchButtonDemo.class);
            startActivity(i);
        }*/
    }


}