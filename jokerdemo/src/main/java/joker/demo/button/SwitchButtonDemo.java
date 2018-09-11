package joker.demo.button;

import android.os.Bundle;

import joker.demo.R;
import joker.kit.base.ActivityJoker;
import github.switchbutton.SwitchButton;

public class SwitchButtonDemo extends ActivityJoker {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_button_demo);
        switchButton();
    }


    public void switchButton() {
        final SwitchButton switchButton = findViewById(R.id.switch_button);
//        switchButton.setChecked(true);
//        switchButton.toggle();//切换状态
//        switchButton.isChecked();
//        switchButton.toggle(false);//切换没有动画
//        switchButton.setShadowEffect(true);//禁用阴影效果
//        switchButton.setEnabled(false);//禁用按钮
//        switchButton.setEnableEffect(false);//禁用开关动画
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if (isChecked) Toast("打开");
                else Toast("关闭");
            }
        });
    }
}
