package joker.kit.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ios.imitation.LoadingDialog.LoadingDialog;

public abstract class FragmentJoker extends Fragment {
    private static final String TAG = "FragmentJoker";
    private LoadingDialog mLoadingDialog;//仿IOS菊花等待框..
    public View viewJoker;
    public Activity activityJoker;


    /**
     * 获取布局
     */
    protected abstract int getLayoutId();

    /**
     * 用来做页面操作代码
     */
    protected abstract void init();

    /**
     * 每次重新可见时都会执行，用来做需要刷新数据的方法..
     */
    protected abstract void repeat();


    /**
     * 展示等待
     */
    public void showLoading() {
        if (mLoadingDialog == null)
            mLoadingDialog = new LoadingDialog(getActivity());
        mLoadingDialog.show();
    }

    /**
     * 关闭等待
     */
    public void dismissLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }


    //###################################################################################
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJoker = getActivity();
        viewJoker = activityJoker.getLayoutInflater().inflate(getLayoutId(), null, false);
        Log.e("FragmentJoker", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e("FragmentJoker", "onCreateView");

        // 这句话必须加
        ViewGroup p = (ViewGroup) viewJoker.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        init();
        return viewJoker;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Fragment隐藏时调用
        } else {
            //Fragment显示时调用
            repeat();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        /** 修正Android的BUG，更正点击Home键退出 */
        outState.putString("KEY", "NOT_BUG");
        super.onSaveInstanceState(outState);
    }

    /**
     * Toast代码简洁
     */
    public void Toast(String msg) {
        Toast.makeText(activityJoker, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * findViewById代码简洁
     */
    public View findViewById(int id) {
        return viewJoker.findViewById(id);
    }
}
