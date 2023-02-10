package com.example.quanlithuvien.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.TheLoai.CHINHSUA_XOA_THELOAI;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.MyViewHolder> {

    Context context ;
    Activity activity;
    ArrayList theloai_id, theloai_ten;
    String[] strTheLoai;

    public TheLoaiAdapter(Context context, Activity activity, ArrayList theloai_id, ArrayList theloai_ten) {
        this.context = context;
        this.activity = activity;
        this.theloai_id = theloai_id;
        this.theloai_ten = theloai_ten;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_dong_the_loai_sach, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.theloai_id_txt.setText(String.valueOf(theloai_id.get(position)));
        holder.theloai_ten_txt.setText(String.valueOf(theloai_ten.get(position)));
        strTheLoai = new String[getItemCount()];
        strTheLoai[position] = (String) theloai_ten.get(position);
        holder.Layout_ds_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CHINHSUA_XOA_THELOAI.class);
                intent.putExtra("id_theloai", String.valueOf(theloai_id.get(position)));
                intent.putExtra("ten_theloai", String.valueOf(theloai_ten.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return theloai_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView theloai_id_txt, theloai_ten_txt;
        LinearLayout Layout_ds_theloai;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            theloai_id_txt = itemView.findViewById(R.id.theloai_id_txt);
            theloai_ten_txt = itemView.findViewById(R.id.theloai_ten_txt);

            Layout_ds_theloai = itemView.findViewById(R.id.mainLinearlayout_theloai);


        }
    }
}
