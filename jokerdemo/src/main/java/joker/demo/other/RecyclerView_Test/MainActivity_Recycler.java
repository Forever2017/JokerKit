package joker.demo.other.RecyclerView_Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import joker.demo.R;
import joker.kit.base.ActivityJoker;
import joker.kit.view.recyclerview.ItemTouchHelperJoker;

public class MainActivity_Recycler extends ActivityJoker {

    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    GridLayoutManager mLayoutManager;

    Button but;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_activity);

        mRecyclerView = findViewById(R.id.recyclerView);
        but = findViewById(R.id.but);

        final List<String> mData = getData();
        mRecyclerAdapter = new RecyclerAdapter(mData);


        //布局管理器
//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager = new GridLayoutManager(this, 3);

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //实例化一个ItemTouchHelper，然后关联到RecyclerView就可以实现拖拽
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperJoker(mRecyclerAdapter, mData, false,false));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerAdapter.onLongItemClick(mRecyclerView, new RecyclerAdapter.JokerInterface() {
            @Override
            public void longClick(RecyclerAdapter.ViewHolder vh) {
                //如果item不是最后一个，则执行拖拽
                if (vh.getLayoutPosition() != mData.size() - 1) {
                    //指定拖动Item
                    itemTouchHelper.startDrag(vh);
                }
            }
        });


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "";
                for (String s : mData) {
                    msg = msg + s + " , ";
                }

                Toast(msg);

            }
        });
    }


    private List<String> getData() {
        List<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 10; i++) {
            data.add(i + temp);
        }
        return data;
    }
}
