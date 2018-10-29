package joker.kit.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joker.kit.base.R;

/**
 * 自定义标题栏
 */
public class NormalTitleBar extends RelativeLayout {

    protected RelativeLayout mRootView;//整体容器控件
    protected TextView  customTitleText;//标题文字控件
    protected ImageView customLeftBut;//左边按钮控件
    protected Button    customRightBut;//右边按钮控件

    protected String mTitle = "";//标题文字
    protected boolean left_visible, right_visible;//按钮是否隐藏或显示
    protected int titleColor;//整体背景色

    private topbarClickListener mListener;// 映射传入的接口对象，返回点击事件

    public NormalTitleBar(Context context) {
        super(context);
        doInit(context, null);
    }

    public NormalTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        doInit(context, attrs);
    }

    public NormalTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doInit(context, attrs);
    }

    @SuppressLint("ResourceAsColor")
    protected void doInit(Context context, AttributeSet attrs) {
        /**布局文件*/
        LayoutInflater.from(context).inflate(R.layout.custom_title_barl, this, true);
        /**通过这个方法，将你在atts.xml中定义的declare-styleable的所有属性的值存储到TypedArray中*/
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitlStyle);


        /** 从TypedArray中取出对应的值来为要设置的属性赋值*/
        mTitle = attributes.getString(R.styleable.TitlStyle_title);
        left_visible = attributes.getBoolean(R.styleable.TitlStyle_left_visible, true);
        right_visible = attributes.getBoolean(R.styleable.TitlStyle_right_visible, true);
        titleColor = attributes.getColor(R.styleable.TitlStyle_title_background_color, R.color.cornflowerblue);

        /** 获取完TypedArray的值后，一般要调用recyle方法来避免重新创建的时候的错误 */
        recycleAttributeSet(attributes);

        init();
    }

    protected void init() {
        mRootView = findViewById(R.id.mRootView);
        customTitleText = mRootView.findViewById(R.id.CustomTitleBarlTitle);
        customLeftBut =  mRootView.findViewById(R.id.CustomTitleBarlLeft);
        customRightBut = mRootView.findViewById(R.id.CustomTitleBarlRight);

        customTitleText.setText(mTitle);
        customLeftBut.setVisibility(left_visible ? View.VISIBLE : View.GONE);
        customRightBut.setVisibility(right_visible ? View.VISIBLE : View.GONE);
        mRootView.setBackgroundColor(titleColor);//标题栏背景色

        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        customLeftBut.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.leftClick();

            }
        });

        customRightBut.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.rightClick();
            }
        });
    }


    protected void recycleAttributeSet(TypedArray attributes) {
        if (attributes != null) {
            attributes.recycle();
        }
    }
    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    // 接口对象，实现回调机制，在回调方法中
    // 通过映射的接口对象调用接口中的方法
    // 而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();

        // 右按钮点击事件
        void rightClick();
    }


    /**对外的方法，想到了慢慢加..*/
    public void setTitle(String title) {
        mTitle = title;
        customTitleText.setText(mTitle);
    }

    public void setRight(String text) {
        customRightBut.setVisibility(View.VISIBLE);
        customRightBut.setText(text);
    }
}
