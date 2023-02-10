package com.example.quanlithuvien.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.DTO.KhachHangDTO;
import com.example.quanlithuvien.DTO.ThanhToanDTO;
import com.example.quanlithuvien.R;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder> {
    private List<KhachHangDTO> listDS_khach_hang;

    public KhachHangAdapter(List<KhachHangDTO> listDS_khach_hang) {
        this.listDS_khach_hang = listDS_khach_hang;
    }

    @NonNull
    @Override
    public KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khachhang, parent, false);
        return new KhachHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangViewHolder holder, int position) {
        KhachHangDTO khachHangDTO = listDS_khach_hang.get(position);
        if(khachHangDTO == null){
            return;
        }
        holder.txt_makhachhang.setText(String.valueOf(position+1));
        holder.txt_tenkhachhang.setText(khachHangDTO.getTenkhachhang());
        holder.txt_sdt_khachhang.setText(khachHangDTO.getSodientgoai());
        holder.txt_ngay.setText(khachHangDTO.getNgay()+"/");
        holder.txt_thang.setText(khachHangDTO.getThang()+"/");
        holder.txtnam.setText(khachHangDTO.getNam());
        holder.txt_gio.setText(khachHangDTO.getGio());
        holder.txt_tongtien.setText(khachHangDTO.getTongtien());
        holder.txttennhanvien.setText(khachHangDTO.getTennhanvien());
    }

    @Override
    public int getItemCount() {
        if(listDS_khach_hang != null){
            return listDS_khach_hang.size();
        }
        return 0;
    }

    public class KhachHangViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_tenkhachhang, txt_sdt_khachhang, txt_makhachhang, txt_ngay,txt_gio, txt_tongtien, txt_thang, txtnam, txttennhanvien;
        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_makhachhang = itemView.findViewById(R.id.khachhang_ma_txt);
            txt_tenkhachhang = itemView.findViewById(R.id.khachhang_ten_txt);
            txt_sdt_khachhang = itemView.findViewById(R.id.khachhang_sdt_txt);
            txt_ngay = itemView.findViewById(R.id.khachhang_ngay_txt);
            txt_gio = itemView.findViewById(R.id.khachhang_gio_txt);
            txt_thang = itemView.findViewById(R.id.khachhang_thang_txt);
            txtnam = itemView.findViewById(R.id.khachhang_nam_txt);
            txt_tongtien = itemView.findViewById(R.id.khachhang_Tongtien_txt);
            txttennhanvien = itemView.findViewById(R.id.khachhang_tennhanvien_txt);
        }
    }
}
