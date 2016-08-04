package com.mintcode.expandablerecyclerview;

/**
 * Created by fanzhengchen on 8/4/16.
 */
public class ChildItem {
    private String name;
    private int parent;

    public ChildItem(String name, int parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
