package joker.demo.other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import joker.demo.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> mData;


    public RecyclerAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.rcText.setText(mData.get(position));

        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (jif != null) {
                    ViewHolder vh = (ViewHolder) mRecyclerView.getChildViewHolder(view);
                    jif.longClick(vh);
                }

                return false;
            }
        });
    }

    private JokerInterface jif;
    private RecyclerView mRecyclerView;

    public interface JokerInterface {
        void longClick(ViewHolder vh);
    }
    public void onLongItemClick(RecyclerView mRecyclerView, JokerInterface jif) {
        this.jif = jif;
        this.mRecyclerView = mRecyclerView;

    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rcText;
        View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            rcText = itemView.findViewById(R.id.rc_text);
        }
    }

}
