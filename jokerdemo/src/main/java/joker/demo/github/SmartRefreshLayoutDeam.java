package joker.demo.github;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import joker.demo.R;
import joker.kit.base.ActivityJoker;

public class SmartRefreshLayoutDeam extends ActivityJoker {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deam_activity);
        RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        /*refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000*//*,false*//*);//传入false表示刷新失败
            }
        });*/
        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //
                Log.e("", "下拉刷新");
                refreshLayout.finishRefresh(2000,true);//传入false表示刷新失败
            }
        });
    }
}
