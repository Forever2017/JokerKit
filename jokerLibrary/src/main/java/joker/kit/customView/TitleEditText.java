package joker.kit.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import joker.kit.base.R;

@SuppressLint("AppCompatCustomView")
public class TitleEditText extends EditText {
    private String fixedText;
    private int leftPadding;
    protected Context context;
    protected String left_text;

    public TitleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        doInit(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(fixedText)) {
            canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
//            通过下面的代码，可以查看出文字的基线，以及view的中线
//            Paint p = new Paint();
//            p.setStrokeWidth(1);
//            p.setColor(Color.parseColor("#ff0000"));
//            canvas.drawLine(0, getBaseline(), getMeasuredWidth(), getBaseline(), p);
//            canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, p);
        }
    }

    @SuppressLint("ResourceAsColor")
    protected void doInit(Context context, AttributeSet attrs) {
        this.context = context;
        /**通过这个方法，将你在atts.xml中定义的declare-styleable的所有属性的值存储到TypedArray中*/
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitlStyleJoker);

        /** 从TypedArray中取出对应的值来为要设置的属性赋值*/
        left_text = attributes.getString(R.styleable.TitlStyleJoker_left_text);

        /** 获取完TypedArray的值后，一般要调用recyle方法来避免重新创建的时候的错误 */
        recycleAttributeSet(attributes);

        if (left_text != null && !left_text.equals("") && !left_text.equals("null"))
            setFixedText(left_text);
    }

    //获取完TypedArray的值后，一般要调用recyle方法来避免重新创建的时候的错误
    protected void recycleAttributeSet(TypedArray attributes) {
        if (attributes != null) {
            attributes.recycle();
        }
    }


    public void setFixedText(String text) {
        fixedText = text;
        leftPadding = getPaddingLeft();
        int left = (int) getPaint().measureText(fixedText) + leftPadding;
        setPadding(left, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }


}
