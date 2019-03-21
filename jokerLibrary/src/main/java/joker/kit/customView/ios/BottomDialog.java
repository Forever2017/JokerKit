package joker.kit.customView.ios;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import joker.kit.base.R;
import joker.kit.number.Transform;

/*
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
        .show();
*/
public class BottomDialog {
    //    private Context context;
    private String title;//标题
    private int mHeight;//按钮高度
    private int mTextSize;//字体大小
    private Activity mActivity;
    private List<SheetItemBean> mList;

    public BottomDialog(Builder builder) {
        this.title = builder.getTitle();
        this.mActivity = builder.getActivity();
        this.mHeight = builder.getmHeight();
        this.mTextSize = builder.getmTextSize();
        mList = new ArrayList<SheetItemBean>();
    }

    public static class Builder {
        private Activity activity;
        private String title;//标题
        private int mHeight;//按钮高度
        private int mTextSize;//字体大小

        /*赋值完后调用，整理参数，得到类对象，直接调用方法..*/
        public BottomDialog build() {
            //设置默认值
            if (mHeight == 0) mHeight = 40;
            if (mTextSize == 0) mTextSize = 16;

            return new BottomDialog(this);

        }

        //添加标题
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder setmHeight(int mHeight) {
            this.mHeight = mHeight;
            return this;
        }

        public Builder setmTextSize(int mTextSize) {
            this.mTextSize = mTextSize;
            return this;
        }

        /**
         * ===    ====== ===    ======  ======
         */
        public String getTitle() {
            return title;
        }

        public Activity getActivity() {
            return activity;
        }

        public int getmHeight() {
            return mHeight;
        }

        public int getmTextSize() {
            return mTextSize;
        }


    }

    //添加选项
    public BottomDialog addSheetItem(SheetItemBean mSheet) {
        mList.add(mSheet);
        return this;
    }

    public static abstract class JokerItemOnClick {

        public void ItemOnClick(View view) {
        }

    }


    private PopupWindow mPopupWindow;
    private LinearLayout JokerBottomWindowLinear;
    private TextView JokerBottomWindowClose;

    public void show() {
        View contentView = mActivity.getLayoutInflater().inflate(R.layout.joker_bottom_window, null);

        mPopupWindow = new PopupWindow(contentView, mActivity.getWindowManager().getDefaultDisplay().getWidth(), mActivity.getWindowManager().getDefaultDisplay().getHeight(), true);
        //设置popupwindow弹出动画
        mPopupWindow.setAnimationStyle(R.style.popupwindow_anim_style);
        //设置popupwindow背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //
        JokerBottomWindowClose = contentView.findViewById(R.id.JokerBottomWindowClose);
        JokerBottomWindowLinear = contentView.findViewById(R.id.JokerBottomWindowLinear);
        configView(JokerBottomWindowClose);
        //
        for (final SheetItemBean mSheet : mList) {

            TextView temp = new TextView(mActivity);

            configView(temp, mSheet);

            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSheet != null)
                        mSheet.mJItem.ItemOnClick(view);
                }
            });

            JokerBottomWindowLinear.addView(temp);


            /*View line = new View(mActivity);
            line.setMinimumHeight(Transform.dip2px(mActivity, 1));
            line.setBackgroundColor(mActivity.getResources().getColor(R.color.gainsboro));

            JokerBottomWindowLinear.addView(line);*/
        }

        JokerBottomWindowClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

    }


    public static class SheetItemBean {
        String title;//内容
        int mTextSize;//字体大小
        int mTextColor;//字体颜色
        JokerItemOnClick mJItem;//点击回调

        public SheetItemBean(String title, int mTextSize, int mTextColor) {
            this.title = title;
            this.mTextSize = mTextSize;
            this.mTextColor = mTextColor;
        }

        public SheetItemBean(String title, int mTextSize, int mTextColor, JokerItemOnClick mJItem) {
            this.title = title;
            this.mTextSize = mTextSize;
            this.mTextColor = mTextColor;
            this.mJItem = mJItem;
        }
    }

    //设置控件样式
    private void configView(TextView temp) {
        //文字居中
        temp.setGravity(Gravity.CENTER);
        //字体颜色
        temp.setTextColor(mActivity.getResources().getColor(R.color.red));
        //字体大小
        temp.setTextSize(mTextSize);
        //控件高度
        temp.setHeight(Transform.dip2px(mActivity, mHeight));
    }

    //设置控件样式
    private void configView(TextView temp, SheetItemBean mSheet) {
        //显示文本
        temp.setText(mSheet.title);
        //文字居中
        temp.setGravity(Gravity.CENTER);
        //字体颜色
        temp.setTextColor(mActivity.getResources().getColor(mSheet.mTextColor));
        //字体大小
        temp.setTextSize(mSheet.mTextSize);
        //控件高度
        temp.setHeight(Transform.dip2px(mActivity, mHeight));
    }


    public void dismiss() {
        mPopupWindow.dismiss();
    }
}
