package com.example.neat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    EditText textBox;
    static final int READ_BLOCK_SIZE = 100;

    private final int ADD_TASK_REQUEST_CODE = 1 ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Task> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataset = new ArrayList<Task>( 2);
        myDataset.add(new Task("Tugas Proyek","3/03/2020"));
        myDataset.add(new Task("Tugas PPB","9/03/2020"));
        myDataset.add(new Task("Tugas Database","29/03/2020"));

        mAdapter = new MyAdapter(myDataset);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        myDataset = new ArrayList<Task>() ;
//        myDataset.add();
        recyclerView.setAdapter(mAdapter);
        textBox = (EditText) findViewById(R.id.editText);
    }




    public void onClick (View view){
        Intent i = new Intent(this,SecondActivity.class);
        i.putExtra("nomorTask", myDataset.size()+1);
        startActivityForResult(i, ADD_TASK_REQUEST_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ADD_TASK_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Task newTask = (Task) data.getSerializableExtra("newTask");
                myDataset.add(newTask);
            }
        }
    }


    public void onClickSave(View view) {
        String str = textBox.getText().toString();
        try {
            FileOutputStream fOut = openFileOutput("textfile.txt",  MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            //---write the string to the file---
            try {
                osw.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            osw.flush();
            osw.close();
            //---display file saved message---
            Toast.makeText(getBaseContext(),
                    "File saved successfully!", Toast.LENGTH_SHORT).show();
            //---clears the EditText---
            textBox.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
