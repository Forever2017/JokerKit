package joker.kit.customView.ios;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import joker.kit.base.R;

public class LoadingDialog extends Dialog {

    private TextView JokerIosDialogLoadingText;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.joker_custom_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joker_ios_dialog_loading);
        JokerIosDialogLoadingText = findViewById(R.id.JokerIosDialogLoadingText);


        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
    }

    /**
     * 显示文字.. 默认是不显示文字..
     */
    public void setTitle(String title) {
        JokerIosDialogLoadingText.setVisibility(View.VISIBLE);
        JokerIosDialogLoadingText.setText(title);
    }

}
