package com.mintcode.expandablerecyclerview;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by fanzhengchen on 8/3/16.
 */
public class ViewItem implements ParentListItem {

    private List<String> childrenItems = null;
    private String name;

    public ViewItem(String name, List<String> childrenItems) {
        this.name = name;
        this.childrenItems = childrenItems;
    }

    @Override
    public List<?> getChildItemList() {
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
