package com.pacosanchez.emergencysystem;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public String[] slide_headings = {

            "Raspberry",
            "Bracelet"
    };

    @Override
    public int getCount(){
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == o;
    }

    /*@Override
    public Object istantiateItem(ViewGroup containter,int position){

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,containter,false);
    }*/
}
