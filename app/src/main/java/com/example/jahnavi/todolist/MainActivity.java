package com.example.jahnavi.todolist;


import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText taskTitle,taskDes;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Task> tasks=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv= (RecyclerView) findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter=new MyAdapter(this,tasks);
        retrieve();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        showDialog();
        return true;
    }

    private void showDialog()
    {
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_layout);
        taskTitle= (EditText) dialog.findViewById(R.id.titleEditTxt);
        taskDes= (EditText) dialog.findViewById(R.id.desEditTxt);
        Button savebtn= (Button) dialog.findViewById(R.id.saveBtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskTitle.getText().toString().isEmpty() ||  taskDes.getText().toString().isEmpty())
                {
                    Message.message(getApplicationContext(),"Please enter all fields ");
                }
                else {

                    save(taskTitle.getText().toString(), taskDes.getText().toString());
                }
            }
        });
        dialog.show();
    }


    private void save(String name,String pos)
    {
        DBAdapter db=new DBAdapter(this);

        db.openDB();
        long result=db.add(name,pos);

        if(result>0)
        {
            taskTitle.setText("");
            taskDes.setText("");
        }else
        {
            Snackbar.make(taskTitle,"Unable To Insert",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
        retrieve();

    }
    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }
    public void retrieve()
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        tasks.clear();
        Cursor c=db.getAlltasks();
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);
            Task p=new Task(name,pos,id);
            tasks.add(p);
        }
        if(!(tasks.size()<1))
        {
            rv.setAdapter(adapter);
        }

    }


}
