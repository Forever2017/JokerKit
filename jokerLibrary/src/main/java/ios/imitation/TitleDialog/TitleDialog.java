package ios.imitation.TitleDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import joker.kit.base.R;


/**
 * 自定义Dialog
 * <p>
 * 作者：
 * 时间：2016-05-16
 */
public class TitleDialog extends Dialog {
    private Context context;
    private TextView title;
    private TextView cancel;
    private TextView sure;
    private View horizontalDivider;
    private View verticalDivider;
    private boolean cancelOutSide;
    private LinearLayout contentView;
    private CstDialogOnClickListener cstDialogOnClickListener;

    public TitleDialog(Context context, CstDialogOnClickListener l) {
        this(context);
        this.cstDialogOnClickListener = l;
        register();
    }


    public TitleDialog(Context context, String leftStr, String rightStr) {
        super(context);
        init(context);
        register();
        if (null != cancel && !TextUtils.isEmpty(leftStr)) {
            cancel.setText(leftStr);
        }

        if (null != sure && !TextUtils.isEmpty(rightStr)) {
            sure.setText(rightStr);
        }
    }

    public TitleDialog(Context context) {
        super(context);
        init(context);
    }

    public TitleDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    protected TitleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        if (null == context) return;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setWindowAnimations(R.style.JokerFadeAnimation);
        View view = LayoutInflater.from(context).inflate(R.layout.joker_ios_dialog_title, null);
        if (null != view) {
            title = (TextView) view.findViewById(R.id.joker_dialog_title);
            cancel = (TextView) view.findViewById(R.id.joker_dialog_cancel);
            sure = (TextView) view.findViewById(R.id.joker_dialog_sure);
            horizontalDivider = view.findViewById(R.id.joker_dialog_horizontal_divider);
            verticalDivider = view.findViewById(R.id.joker_dialog_vertical_divider);
            contentView = (LinearLayout) view.findViewById(R.id.joker_dialog_content_view);
            setContentView(view);
            setCanceledOnTouchOutside(true);
        }
    }

    /**
     * 不显示取消按钮选项
     */
    public void setCancelGone() {
        if (null != cancel) {
            cancel.setVisibility(View.GONE);
        }
        if (null != verticalDivider) {
            verticalDivider.setVisibility(View.GONE);
        }

        if (null != sure) {
            sure.setBackgroundResource(R.drawable.joker_bottom_corners_bg);
        }
    }

    /**
     * 注册点击监听 内部调用
     */
    private void register() {
        setOnClickListener(sure, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != cstDialogOnClickListener) {
                    cstDialogOnClickListener.onClickSure();
                }
                cancel();
            }
        });
        setOnClickListener(cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != cstDialogOnClickListener) {
                    cstDialogOnClickListener.onClickCancel();
                }
                cancel();
            }
        });
    }

    public LinearLayout getContentView() {
        return contentView;
    }


    public void setCstDialogOnClickListener(CstDialogOnClickListener l) {
        this.cstDialogOnClickListener = l;
        register();
    }

    public void setCancelOutSide(boolean cancelOutSide) {
        this.cancelOutSide = cancelOutSide;
        setCanceledOnTouchOutside(cancelOutSide);
    }


    public interface CstDialogOnClickListener {
        void onClickSure();

        void onClickCancel();
    }

    /**
     * 设置分割线颜色
     */
    public void setDividerColor(int color) {
        setBackgroudColor(horizontalDivider, color);
        setBackgroudColor(verticalDivider, color);
    }


    /*内部调用*/
    private void setBackgroudColor(View v, int color) {
        if (null != v) {
            v.setBackgroundColor(color);
        }
    }

    /**
     * 模拟IOS的警告框时调用
     */
    public void setTitleImitateIos(String name, String hint) {
        if (null != name && null != title) {
            if (!TextUtils.isEmpty(name)) {
                if (TextUtils.isEmpty(hint)) {
                    SpannableString spanString = new SpannableString(name);
                    spanString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.darkgray)), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString.setSpan(new AbsoluteSizeSpan(sp2px(context, 16)), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    title.setText(spanString);
                } else {
                    SpannableString spanString = new SpannableString(name + "\n" + hint);
                    spanString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.darkgray)), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString.setSpan(new AbsoluteSizeSpan(sp2px(context, 16)), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString.setSpan(new AbsoluteSizeSpan(sp2px(context, 14)), name.length(), spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    title.setText(spanString);
                }


            } else {
                title.setTextColor(getContext().getResources().getColor(R.color.darkgray));
                title.setGravity(Gravity.LEFT);
                title.setText(hint);
            }
        }
    }


    public void setTitle(String s) {
        setText(title, s);
    }

    public void setTitleTextSize(float s) {
        setTextSize(title, s);
    }

    public void setSure(String s) {
        setText(sure, s);
    }

    public void setCancel(String s) {
        setText(cancel, s);
    }

    public void setTitleColor(int color) {
        setTextColor(title, color);
    }

    public void setSureColor(int color) {
        setTextColor(sure, color);
    }


    public void setCancelColor(int color) {
        setTextColor(cancel, color);
    }


    public void setSureBg(int resId) {
        if (null != sure) {
            sure.setBackgroundResource(resId);
        }
    }

    public void setCancelBg(int resId) {
        if (null != cancel) {
            cancel.setBackgroundResource(resId);
        }
    }

    private void setText(TextView t, String s) {
        if (null != t && null != s) {
            t.setText(s);
        }
    }

    private void setTextSize(TextView t, float s) {
        if (null != t && 0 != s) {
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP, s);
        }
    }

    private void setTextColor(TextView t, int color) {
        if (null != t) {
            t.setTextColor(color);
        }
    }

    private void setOnClickListener(View v, View.OnClickListener l) {
        if (null != v) {
            v.setOnClickListener(l);
        }
    }

    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
