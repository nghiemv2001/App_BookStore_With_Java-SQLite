package com.example.quanlithuvien.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.DTO.ThanhToanDTO;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.Thanhtoan.SUA_XOA_THANHTOAN;

import java.util.List;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.ThanhToanViewHolder>{
    private List<ThanhToanDTO> listThanhToan;
    Context context;


    public ThanhToanAdapter(List<ThanhToanDTO> listThanhToan, Context context) {
        this.listThanhToan = listThanhToan;
        this.context = context;
    }

    @NonNull
    @Override
    public ThanhToanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanh_toan, parent, false);
        return new ThanhToanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhToanViewHolder holder, int position) {
        ThanhToanDTO thanhtoan = listThanhToan.get(position);
        if(thanhtoan == null){
            return ;
        }
        holder.txt_id.setText(String.valueOf(position+1));
        holder.txt_ten.setText(thanhtoan.getTen());
        holder.txt_soluong.setText(thanhtoan.getSoluong());
        holder.txt_gia.setText(thanhtoan.getGia());
        holder.thanhtoan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SUA_XOA_THANHTOAN.class);
                intent.putExtra("id", thanhtoan.getId());
                intent.putExtra("ten", thanhtoan.getTen());
                intent.putExtra("soluong", thanhtoan.getSoluong());
                intent.putExtra("gia", thanhtoan.getGia());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listThanhToan != null){
            return listThanhToan.size();
        }
        return 0;

    }

    public class ThanhToanViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_id, txt_ten, txt_soluong , txt_gia;
        LinearLayout thanhtoan_layout;
        public ThanhToanViewHolder(@NonNull View itemView) {
            super(itemView);
            thanhtoan_layout = itemView.findViewById(R.id.Linea_layout_dongthanhtoan);
            txt_id = itemView.findViewById(R.id.thanhtoan_id_txt);
            txt_ten = itemView.findViewById(R.id.thanhtoan_ten_txt);
            txt_soluong = itemView.findViewById(R.id.thanhtoan_soluong_txt);
            txt_gia = itemView.findViewById(R.id.thanhtoan_gia_txt);

        }
    }


}
