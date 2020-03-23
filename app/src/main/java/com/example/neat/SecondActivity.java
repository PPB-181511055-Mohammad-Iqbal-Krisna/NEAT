package com.example.neat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText dueDate;
    EditText namaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClick (View view){
        Intent i = new Intent();

        Task newTask = new Task (namaTask.getText().toString(),dueDate.getText().toString());
        i.putExtra("newTask", newTask);

        setResult(RESULT_OK, i);
        finish();
    }
}
