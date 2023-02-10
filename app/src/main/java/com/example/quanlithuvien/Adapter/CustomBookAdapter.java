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
import com.example.quanlithuvien.giaodien.Sach.CHINHSUA_XOA_SACH;

import java.util.ArrayList;

public class CustomBookAdapter extends RecyclerView.Adapter<CustomBookAdapter.MyViewHolder>{
    Context context ;
    Activity activity;
    ArrayList book_id, book_theloai, book_tensach, book_tacgia, book_giasach, book_sotrang, book_soluongton;


    public CustomBookAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_theloai, ArrayList book_tensach, ArrayList book_tacgia, ArrayList book_giasach, ArrayList book_sotrang, ArrayList book_soluongton) {
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_theloai = book_theloai;
        this.book_tensach = book_tensach;
        this.book_tacgia = book_tacgia;
        this.book_giasach = book_giasach;
        this.book_sotrang = book_sotrang;
        this.book_soluongton = book_soluongton;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book_theloai_txt.setText(String.valueOf(book_theloai.get(position)));
        holder.book_tensach_txt.setText(String.valueOf(book_tensach.get(position)));
        holder.book_tacgia_txt.setText(String.valueOf(book_tacgia.get(position)));
        holder.book_giasach_txt.setText(String.valueOf(book_giasach.get(position)));
        holder.book_sotrang_txt.setText(String.valueOf(book_sotrang.get(position)));
        holder.book_soluongton_txt.setText(String.valueOf(book_soluongton.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CHINHSUA_XOA_SACH.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("loaisach", String.valueOf(book_theloai.get(position)));
                intent.putExtra("tensach", String.valueOf(book_tensach.get(position)));
                intent.putExtra("tacgia", String.valueOf(book_tacgia.get(position)));
                intent.putExtra("giasach", String.valueOf(book_giasach.get(position)));
                intent.putExtra("sotrang", String.valueOf(book_sotrang.get(position)));
                intent.putExtra("soluongton", String.valueOf(book_sotrang.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_theloai_txt, book_tensach_txt, book_tacgia_txt, book_giasach_txt, book_sotrang_txt, book_soluongton_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_theloai_txt = itemView.findViewById(R.id.book_theloai_txt);
            book_tensach_txt = itemView.findViewById(R.id.book_tensach_txt);
            book_tacgia_txt = itemView.findViewById(R.id.book_tacgia_txt);
            book_giasach_txt = itemView.findViewById(R.id.book_giasach_txt);
            book_sotrang_txt = itemView.findViewById(R.id.book_sotrang_txt);
            book_soluongton_txt = itemView.findViewById(R.id.book_soluongton_txt);

            mainLayout = itemView.findViewById(R.id.mainLinearlayout);

        }
    }
}
