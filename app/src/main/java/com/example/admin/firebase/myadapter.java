package com.example.admin.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.firebase.model.Job;

import java.util.ArrayList;

public class myadapter extends BaseAdapter {
    Context c;
    ArrayList<Job> ds = new ArrayList<Job>();
    myadapter(Context c, ArrayList<Job> ds)
    {
        this.c =c;
        this.ds = ds;
    }
    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    static  class  Mot_o
    {
        TextView tv_name,tv_job,tv_address,tv_tel;
        ImageView im_edit, im_thungrac;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = ((JobActivity)c).getLayoutInflater();
        Mot_o mot_o;
        if(convertView==null)
        {
            convertView = inf.inflate(R.layout.one_item_listview,null);
            mot_o = new Mot_o();
            mot_o.tv_name = convertView.findViewById(R.id.tv_ten);
            mot_o.tv_job= convertView.findViewById(R.id.tv_job);
            mot_o.tv_address =convertView.findViewById(R.id.tv_address);
            mot_o.tv_tel = convertView.findViewById(R.id.tv_tel);
            mot_o.im_edit = convertView.findViewById(R.id.im_edit);
            mot_o.im_thungrac= convertView.findViewById(R.id.im_thungrac);
            convertView.setTag(mot_o);

        }
        else
        {
            mot_o= (Mot_o)convertView.getTag();
        }
        mot_o.tv_name.setText(ds.get(position).getUsername());
        mot_o.tv_job.setText(ds.get(position).getjob());
        mot_o.tv_address.setText(ds.get(position).getAddress());
        mot_o.tv_tel.setText(ds.get(position).getPhone_number()+"");



        return convertView;
    }
}
