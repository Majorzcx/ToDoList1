package com.example.todolist1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> data  ;
    MyAdapter myAdapter  ;
    LinearLayoutManager layoutManager  ;
    EditText edit_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        data = initData();
        myAdapter = new MyAdapter(data);
        layoutManager = new LinearLayoutManager(this);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());

        Button button_add = findViewById(R.id.rv_add_item_btn);
        Button button_delete = findViewById(R.id.rv_del_item_btn);
        button_add.setOnClickListener(this);
        button_delete.setOnClickListener(this);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SecondActivity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rv_add_item_btn) {
            myAdapter.addNewItem();
            layoutManager.scrollToPosition(0);
        } else if (id == R.id.rv_del_item_btn) {
            myAdapter.deleteItem();
            layoutManager.scrollToPosition(0);
        }
    }

    //初始化3个任务
    private ArrayList<String> initData() {
        ArrayList<String> mData = new ArrayList<>();
        for (int i = 3; i > 0; i--) {
            mData.add("Task " + i);
        }
        return mData;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String returnedName = data.getStringExtra("name_return");
                    edit_name = findViewById(R.id.edit_text_name);
                    edit_name.setText(returnedName);
                }
                break;
            default:
        }
    }
}