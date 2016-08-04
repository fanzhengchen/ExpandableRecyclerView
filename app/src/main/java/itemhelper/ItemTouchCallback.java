package itemhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.mintcode.expandablerecyclerview.Adapter;

/**
 * Created by fanzhengchen on 8/3/16.
 */
public class ItemTouchCallback extends ItemTouchHelper.Callback {

    private static final float ALPHA_FULL = 1.0f;
    private final ItemTouchHelperAdapter mAdapter;

    public ItemTouchCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = 0;
//        ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        boolean first = (source instanceof Adapter.ParentViewHolder);
        boolean second = (target instanceof Adapter.ParentViewHolder);
        boolean canSwap = false;
        boolean isParent = false;
        if (first && second) {
            canSwap = true;
            isParent = true;
        } else if (!first && !second) {
            Adapter.ChildViewHolder src = (Adapter.ChildViewHolder) source;
            Adapter.ChildViewHolder dest = (Adapter.ChildViewHolder) target;
            if (src.getItemType() == dest.getItemType()) {
                canSwap = true;
            }
        }
        if (canSwap) {
            mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition(), isParent);
        }
        return canSwap;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                ItemTouchHelperViewHolder holder = (ItemTouchHelperViewHolder) viewHolder;
                holder.onItemClear();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }
}
