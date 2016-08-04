package com.mintcode.expandablerecyclerview;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import itemhelper.ItemTouchHelperAdapter;
import itemhelper.ItemTouchHelperViewHolder;
import itemhelper.OnStartDragListener;

/**
 * Created by fanzhengchen on 8/3/16.
 */
public class Adapter extends ExpandableRecyclerAdapter<Adapter.ParentViewHolder, Adapter.ChildViewHolder> implements ItemTouchHelperAdapter {

    private final int CELL_HEIGHT = 100;
    private List<ParentItem> parentItems = null;
    private final OnStartDragListener onStartDragListener;
    private boolean allCollapsed = false;

    public Adapter(@NonNull List<ParentItem> names, OnStartDragListener listener) {
        super(names);
        this.parentItems = names;
        this.onStartDragListener = listener;
    }

    @Override
    public ParentViewHolder onCreateParentViewHolder(ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.parent_view_holder, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, CELL_HEIGHT));
        return new ParentViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup child) {
        View view = View.inflate(child.getContext(), R.layout.child_view_holder, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, CELL_HEIGHT + 30));
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(ParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ParentItem item = (ParentItem) parentListItem;
        parentViewHolder.fillText(item.getName());
        parentViewHolder.setItemType(-1);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int position, Object childListItem) {
        ChildItem childItem = (ChildItem) childListItem;
        childViewHolder.fillText(childItem.getName());
        childViewHolder.setItemType(childItem.getParent());
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition, boolean isParent) {
        if (isParent) {
            Collections.swap(parentItems, fromPosition, toPosition);
            notifyParentItemMoved(fromPosition, toPosition);
        } else {
            Collections.swap(mItemList, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    @Override
    public void onItemDismiss(int position) {

    }

    @Override
    public boolean allCollapsed() {
        return allCollapsed;
    }

    /**
     * Created by fanzhengchen on 8/3/16.
     */
    public class ChildViewHolder extends com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder implements ItemTouchHelperViewHolder {
        @BindView(R.id.child_text)
        TextView textView;

        private View parent;
        private int itemType;

        public ChildViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void fillText(String text) {
            textView.setText(text);
        }

        @OnLongClick(R.id.parent)
        public boolean onLongClick(View view) {
            onStartDragListener.onStartDrag(this);
            return true;
        }

        @Override
        public void onItemSelected() {
            parent.setBackgroundColor(Color.GRAY);
        }

        @Override
        public void onItemClear() {

        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    /**
     * Created by fanzhengchen on 8/3/16.
     */
    public class ParentViewHolder extends com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder {

        @BindView(R.id.holder_text)
        TextView textView;

        private View parent;
        private int itemType;

        public ParentViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void fillText(String text) {
            textView.setText(text);
        }

        @OnLongClick(R.id.parent)
        public boolean onLongClick(View view) {
            allCollapsed = false;
            collapseAllParents();
            allCollapsed = true;
            onStartDragListener.onStartDrag(ParentViewHolder.this);
            return true;
        }


        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public void collapse() {
            collapseView();
        }

        public void expand() {
            expandView();
        }
    }
}
