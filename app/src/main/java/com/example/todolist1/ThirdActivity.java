package com.example.todolist1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText taskNameEdit;
    private  EditText taskDetailsEdit;
    private String taskName;
    private String taskDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        taskNameEdit = findViewById(R.id.task_name);
        taskDetailsEdit = findViewById(R.id.task_details);

        Button button_save = findViewById(R.id.save_button);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskName = taskNameEdit.getText().toString();
                taskDetails = taskDetailsEdit.getText().toString();
                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", taskName);
                    editor.putString("details" , taskDetails);
                    editor.apply();
                    Toast.makeText(ThirdActivity.this,"Successfully Saved",Toast.LENGTH_SHORT).show();

                    //将事务名称返回给上一个活动
                    Intent intent = new Intent();
                    intent.putExtra("name_return", taskName);
                    setResult(RESULT_OK, intent);
                    finish();

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        taskName = sp.getString("name", "1");
        taskDetails = sp.getString("details", "2");
        taskNameEdit.setText(taskName);
        taskDetailsEdit.setText(taskDetails);
    }
}
