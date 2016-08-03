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

    ArrayList<ViewItem> items = new ArrayList<>();

    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int index = 0;
        for (String name : names) {
            ArrayList<String> childNames = new ArrayList<>();
            for (int i = 0; i < 4; ++i) {
                ++index;
                childNames.add(Integer.toHexString(index));
            }
            items.add(new ViewItem(name, childNames));
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
