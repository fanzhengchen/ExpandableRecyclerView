package com.mintcode.expandablerecyclerview;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by fanzhengchen on 8/3/16.
 */
public class ParentItem implements ParentListItem {

    private List<ChildItem> childrenItems = null;
    private String name;
    private int position;

    public ParentItem(String name, List<ChildItem> childrenItems) {
        this.name = name;
        this.childrenItems = childrenItems;
    }

    @Override
    public List<ChildItem> getChildItemList() {
        return childrenItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getName() {
        return name;
    }
}
