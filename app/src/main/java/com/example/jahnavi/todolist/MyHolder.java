package com.example.jahnavi.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView taskTitle,taskDes;
    ItemClickListener itemClickListener;


    public MyHolder(View itemView) {
        super(itemView);
        taskTitle= (TextView) itemView.findViewById(R.id.taskTitle);
        taskDes= (TextView) itemView.findViewById(R.id.taskDes);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
