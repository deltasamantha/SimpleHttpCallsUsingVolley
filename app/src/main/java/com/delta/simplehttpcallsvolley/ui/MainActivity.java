package com.delta.simplehttpcallsvolley.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.delta.simplehttpcallsvolley.AppFacade;
import com.delta.simplehttpcallsvolley.R;
import com.delta.simplehttpcallsvolley.adapters.UserArrayAdapter;
import com.delta.simplehttpcallsvolley.model.User;
import com.delta.simplehttpcallsvolley.utills.IResponseResult;
import com.delta.simplehttpcallsvolley.utills.ServiceHandler;
import com.delta.simplehttpcallsvolley.utills.UserJsonResponseSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IResponseResult {

    FloatingActionButton fab;
    ListView listView;
    ArrayList<User> userList;
    private static final int K_ITEM = 1;
    UserArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState != null) {
            if (savedInstanceState.getSerializable("userList") != null) {
                userList = (ArrayList<User>) savedInstanceState.getSerializable("userList");
            }
        } else {
            userList = new ArrayList<>();
            getList();
        }

        fab.setOnClickListener(MainActivity.this);

        adapter = new UserArrayAdapter(MainActivity.this, userList);
        listView.setHeaderDividersEnabled(true);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                User user = userList.get(position);
                showMessage("User " + user.getName() + " clicked !!!");
            }
        });

        listView.invalidateViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("userList", userList);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == fab.getId()) {
            getList();
        }
    }

    @Override
    public void response(Object result, int tagNo, boolean success) {
        if (tagNo == K_ITEM) {
            userList.clear();
            adapter.clear();
            if (result != null) {
                if (success) {
                    userList = (ArrayList<User>) result;
                    adapter.addAll(userList);
                    listView.invalidateViews();
                } else {
                    String error = "";
                    try {
                        error = ((JSONObject) result).getString("Message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    showMessage(error);
                    listView.invalidateViews();
                }
            } else {
                showMessage(getString(R.string.ERROR));
                listView.invalidateViews();
            }
        }
    }

    @Override
    public void response(Object result, boolean success) {

    }

    private void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void getList() {
//        ServiceHandler handler = new ServiceHandler(MainActivity.this, getResources().getString(R.string.REF_URL), K_ITEM);
//        handler.setiSerializer(new UserJsonResponseSerializer());
//        handler.makeGatRequest();
        AppFacade.getInstance().initHandler(MainActivity.this, getResources().getString(R.string.REF_URL), K_ITEM,new UserJsonResponseSerializer());
    }
}