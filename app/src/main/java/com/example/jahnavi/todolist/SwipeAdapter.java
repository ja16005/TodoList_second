package com.example.jahnavi.todolist;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jahnavi on 04-11-2016.
 */
public class SwipeAdapter  extends PagerAdapter{

    ArrayList<Task> tasks=new ArrayList<>();


    private Context ctx;
    private LayoutInflater layoutinflater;
    Button delete;
    DBAdapter db_adapter;
    Context context;

    public SwipeAdapter(Context ctx)
    {
        context=ctx;
        this.ctx=ctx;
        db_adapter=new DBAdapter(ctx);
        db_adapter.openDB();
        tasks.clear();
        Cursor c=db_adapter.getAlltasks();
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);
            Task p=new Task(name,pos,id);
            tasks.add(p);
        }
    }

    @Override
    public int getCount() {
        return tasks.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        layoutinflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  item_view=layoutinflater.inflate(R.layout.activity_swipe,container,false);

        Log.d("SwipeAdapter","YAYY");

        TextView tv=(TextView)item_view.findViewById(R.id.profile);
        TextView title=(TextView)item_view.findViewById(R.id.titleEditTxt);
        TextView details=(TextView)item_view.findViewById(R.id.desEditTxt);
        tv.setText("Task");
        title.setText(tasks.get(position).getName());
        details.setText(tasks.get(position).getPosition());
            container.addView(item_view);

        return item_view;
    }


    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        container.removeView((CoordinatorLayout)object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view==((CoordinatorLayout)object));
    }


}
