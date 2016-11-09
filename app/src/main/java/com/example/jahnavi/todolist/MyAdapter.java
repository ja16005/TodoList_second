package com.example.jahnavi.todolist;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by jahnavi on 03/11/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Task> tasks;

    public MyAdapter(Context ctx,ArrayList<Task> tasks)
    {
        this.c=ctx;
        this.tasks=tasks;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        MyHolder holder=new MyHolder(v);
         return holder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //holder.taskDes.setText(tasks.get(position).getPosition());
        holder.taskTitle.setText(tasks.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent i=new Intent(c,SecondActivity.class);
                i.putExtra("position",pos);
                i.putExtra("NAME",tasks.get(pos).getName());
                i.putExtra("POSITION",tasks.get(pos).getPosition());
                i.putExtra("ID",tasks.get(pos).getId());
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
