package com.example.quanlithuvien.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlithuvien.R;

public class ImageAdapter extends BaseAdapter {
    private Activity context;
    private int layoutid;
    private Integer[] Images;
    private String[] Text;

    public ImageAdapter(Activity context, int layoutid, Integer[] images, String[] text) {
        this.context = context;
        this.layoutid = layoutid;
        Images = images;
        Text = text;
    }

    public void setDaTaSource(Integer[] images, String[] texts){
        Images = images;
        Text = texts;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public int getLayoutid() {
        return layoutid;
    }

    public void setLayoutid(int layoutid) {
        this.layoutid = layoutid;
    }

    public Integer[] getImages() {
        return Images;
    }

    public void setImages(Integer[] images) {
        Images = images;
    }

    public String[] getText() {
        return Text;
    }

    public void setText(String[] text) {
        Text = text;
    }

    @Override
    public int getCount() {
        return Images.length;
    }

    @Override
    public Object getItem(int i) {
        return Images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder{
        TextView txtText;
        ImageView imgImages;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        view = inflater.inflate(layoutid, viewGroup, false);

        Holder holder = new Holder();

        holder.txtText = view.findViewById(R.id.txt_Text);
        holder.imgImages = view.findViewById(R.id.img_Image);

        viewGroup.setTag(holder);
        holder.txtText.setText(Text[i]);
        holder.imgImages.setImageResource(Images[i]);

        return view;
    }
}
