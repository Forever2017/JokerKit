package ios.imitation.BottomDialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import joker.kit.base.R;

public class _Test {

    public void test(Context context) {
        Activity temp = null;

        new BottomDialog.Builder()
                .setTitle("标题")
                .setActivity(temp)
                .setmHeight(40)
                .setmTextSize(16)
                .build()
                .addSheetItem(
                        new BottomDialog.SheetItemBean("上传图片", 16,
                                R.color.green, new BottomDialog.JokerItemOnClick() {
                            @Override
                            public void ItemOnClick(View view) {
                                super.ItemOnClick(view);
                                //Toast("点击上传图片！");
                            }
                        }))
                .show();
    }
}
