package joker.kit.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joker.kit.base.R;

/**
 * 自定义 底部 导航按钮
 * 上面是图 下面是字儿..
 */
public class NavigationButton extends RelativeLayout {
    protected Context context;
    protected RelativeLayout mRootView;//整体容器控件

    private ImageView JokerNavigationImage;
    private TextView JokerNavigationText;
    private TextView JokerNavigationNumber;


    public NavigationButton(Context context) {
        super(context);
        doInit(context, null);
    }

    public NavigationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        doInit(context, attrs);
    }

    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doInit(context, attrs);
    }

    protected void doInit(Context context, AttributeSet attrs) {
        this.context = context;
        /**布局文件*/
        LayoutInflater.from(context).inflate(R.layout.joker_navigation_button, this, true);
        /**通过这个方法，将你在atts.xml中定义的declare-styleable的所有属性的值存储到TypedArray中*/
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitlStyleJoker);

        init();
    }

    protected void init() {
        mRootView = findViewById(R.id.mRootView);
        JokerNavigationImage = mRootView.findViewById(R.id.JokerNavigationImage);
        JokerNavigationText = mRootView.findViewById(R.id.JokerNavigationText);
        JokerNavigationNumber = mRootView.findViewById(R.id.JokerNavigationNumber);
    }


    /**
     * 显示小红点，设置显示文字
     */
    public void setNumber(String number, int vis) {
        JokerNavigationNumber.setVisibility(vis);
        JokerNavigationNumber.setText(number);
    }

}
