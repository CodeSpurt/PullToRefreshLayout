package com.codespurt.pulltorefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    private int TIMEOUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.list_view);

        arrayList = new ArrayList();
        // add items
        for (int countr = 0; countr < 10; countr++) {
            arrayList.add("Item " + String.valueOf(countr + 1));
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUI();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                swipeRefreshLayout.setRefreshing(true);
                updateUI();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, R.string.ui_updated, Toast.LENGTH_SHORT).show();
            }
        }, TIMEOUT * 1000);
    }
}