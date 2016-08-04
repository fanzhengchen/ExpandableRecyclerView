package com.mintcode.expandablerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import itemhelper.ItemTouchCallback;
import itemhelper.OnStartDragListener;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    Adapter adapter;

    @BindArray(R.array.string_array)
    String[] names;

    ArrayList<ParentItem> items = new ArrayList<>();

    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int index = 0;
        for (int i = 0; i < names.length; ++i) {
            String name = names[i];
            ArrayList<ChildItem> childItems = new ArrayList<>();
            for (int j = 0; j < 5; ++j) {
                childItems.add(new ChildItem(name, i));
            }
            items.add(new ParentItem(name, childItems));
        }
        adapter = new Adapter(items, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        ItemTouchHelper.Callback callback = new ItemTouchCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itemTouchHelper.startDrag(holder);
    }
}
