package joker.kit.view.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView实现拖拽的封装方法
 * <p>
 * 实例化一个ItemTouchHelper，然后关联到RecyclerView就可以实现拖拽
 * ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new JokerItemTouchHelper(mRecyclerAdapter,mData));
 * itemTouchHelper.attachToRecyclerView(mRecyclerView);
 */
public class ItemTouchHelperJoker extends ItemTouchHelper.Callback {
    private RecyclerView.Adapter mAdapter;
    private List mList;

    private boolean isLongPressDragEnabled;//是否全局拖拽
    private boolean isLastDrag = true; //最后一个Item是否允许拖拽


    //普通拖拽
    public ItemTouchHelperJoker(RecyclerView.Adapter mAdapter, List mList) {
        this.mAdapter = mAdapter;
        this.mList = mList;
        isLongPressDragEnabled = true;//默认支持拖拽
    }
    //普通拖拽 可以设定全局是否可拖拽...一般设置否，然后指定拖拽规则
    public ItemTouchHelperJoker(RecyclerView.Adapter mAdapter, List mList, boolean isLongPressDragEnabled) {
        this.mAdapter = mAdapter;
        this.mList = mList;
        //这里主要是给false,关闭全局拖拽，来做一些限制，例如 最后一项不允许拖拽
        this.isLongPressDragEnabled = isLongPressDragEnabled;
    }
    //普通拖拽 可以设定全局是否可拖拽...一般设置否，然后指定拖拽规则,同时可一直指定最后一个元素永远不能动...
    public ItemTouchHelperJoker(RecyclerView.Adapter mAdapter, List mList, boolean isLongPressDragEnabled,boolean isLastDrag) {
        this.mAdapter = mAdapter;
        this.mList = mList;
        //这里主要是给false,关闭全局拖拽，来做一些限制，例如 最后一项不允许拖拽
        this.isLongPressDragEnabled = isLongPressDragEnabled;
        this.isLastDrag = isLastDrag;
    }







    //用于设置是否处理拖拽事件和滑动事件，以及拖拽和滑动操作的方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //dragFlags 是拖拽标志，swipeFlags是滑动标志
        final int dragFlags;
        final int swipeFlags = 0;

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlags, swipeFlags);

    }

    //拖拽后实现替换
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
        int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position

        //如果不允许拖拽最后一项，就不做后面的替换
        if (!isLastDrag && (toPosition == mList.size() - 1 || fromPosition == mList.size() - 1))
            return true;


        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mList, i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        return true;

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }


    //当长按选中item的时候（拖拽开始的时候）调用  - 这里拖拽的时候给item添加一个背景色
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }


    //当手指松开的时候（拖拽完成的时候）调用   - 这里拖拽完成的时候还原
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }

    //是否需要RecyclerView支持长按拖拽
    @Override
    public boolean isLongPressDragEnabled() {
        //默认返回是ture（即支持）
        /*  return true;*/

        return isLongPressDragEnabled;

        //如果为false，不允许拖拽 需要采用startDrag方法指定可以拖拽的Item
        /* 核心代码：
         --> ItemTouchHelper.startDrag(ViewHolder);//在某个Item长按事件中，需要拖拽的，如此设置
         eg:
         == Activity ==
         mRecyclerAdapter.onLongItemClick(mRecyclerView, new RecyclerAdapter.JokerInterface() {
        @Override
            public void longClick(RecyclerAdapter.ViewHolder vh) {
                //如果item不是最后一个，则执行拖拽
                //这里做更多的逻辑判断
                if (vh.getLayoutPosition() != mData.size() - 1) {
                    itemTouchHelper.startDrag(vh);
                }
            }
        });

         == RecyclerView.Adapter ==
         @Override
         public void onBindViewHolder(ViewHolder holder, int position) {
             ...
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
         */
    }


    /**
     * ##############################################
     */

    /*设置最后一项Item是否允许拖拽*/
    public void isLastDrag(boolean isLastDrag) {
        this.isLastDrag = isLastDrag;
    }
}
