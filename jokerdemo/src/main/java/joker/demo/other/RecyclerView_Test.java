package joker.demo.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import joker.demo.R;
import joker.kit.base.ActivityJoker;

public class RecyclerView_Test extends ActivityJoker {

    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerAdapter = new RecyclerAdapter(getData());

        /**
         RecyclerView提供了三种布局管理器：
         LinearLayoutManager         以垂直或者水平列表方式展示Item
         GridLayoutManager           以网格方式展示Item
         StaggeredGridLayoutManager  以瀑布流方式展示Item*/
        //布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        /*mLayoutManager = new GridLayoutManager(this, 3);*/
        /*mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);*/


        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mRecyclerAdapter);
        // 设置Item之间间隔样式   joker.kit.view.recyclerview封装好
   /*     ListItemDecoration itemDecoration = new ListItemDecoration(10);
        mRecyclerView.addItemDecoration(itemDecoration);*/

    }


    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 10; i++) {
            data.add(i + temp);
        }
        return data;
    }
}
