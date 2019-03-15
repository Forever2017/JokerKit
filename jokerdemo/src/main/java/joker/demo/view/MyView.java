package joker.demo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/*
    //计算文字宽度
    paint.measureText(str)
    //获取View高度 构造方法中为0
    getHeight()

*/
@SuppressLint("AppCompatCustomView")
public class MyView extends EditText {
    String title = "姓名：";
    int textHeight;
    int i;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //计算文字宽度
        int textWight = (int) getPaint().measureText(title);


        //计算文字高度
        Rect rect = new Rect();
        getPaint().getTextBounds(title, 0, title.length(), rect);
        textHeight = rect.height();

        //
        i = getPaint().getFontMetricsInt(null);


        //文本框中文字的padding （如果没有设置padding 这里取出来就是 0 ）
        setPadding(textWight, getPaddingTop(), getPaddingRight(), getPaddingBottom());


        setText(getHeight() + "  -  " + textHeight + "  -  " + getTextSize());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取基线高度
        int text_w = i;//文字高度
        //用总高度 - 文字高度 /2   +  文字高度  得出基线点...
        int baseLine = (getHeight() - text_w) / 2 + text_w ;
        //  101    69                69

        bLine(canvas, baseLine);//画线

        int baseLineX = 0;
        int baseLineY = baseLine;


        //写文字
        getPaint().setColor(Color.GREEN);
        getPaint().setTextSize(getTextSize()); //以px为单位
        canvas.drawText(title + "", baseLineX, baseLineY, getPaint());

    }


    /*画基线*/
    public void bLine(Canvas canvas, int xxx) {
        int y = xxx;
        //画基线
        Paint paint = getPaint();
        paint.setColor(Color.RED);
        /*baseLineX, baseLineY 点 到 3000, baseLineY 点*/
        canvas.drawLine(0, y, 3000, y, paint);

    }
}
