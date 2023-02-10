package com.example.quanlithuvien.giaodien.DoanhThu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlithuvien.Adapter.KhachHangAdapter;
import com.example.quanlithuvien.DTO.KhachHangDTO;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKhachHang extends AppCompatActivity {
    RecyclerView recyclerView ;
    FloatingActionButton btn_Home;
    KhachHangAdapter khachHangAdapter;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doanhthu_danhsachkhachhang);
        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        //set recycleview danh sach khach hang
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        khachHangAdapter = new KhachHangAdapter(getListClints());
        recyclerView.setAdapter(khachHangAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachKhachHang.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<KhachHangDTO> getListClints() {
        List<KhachHangDTO> list = new ArrayList<>();
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql = "SELECT * FROM thanhtoan_library";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                String id =cursor.getString(0);
                String ten =cursor.getString(1);
                String sdt =cursor.getString(2);
                String ngay =cursor.getString(3);
                String thang =cursor.getString(4);
                String nam =cursor.getString(5);
                String gio =cursor.getString(6);
                String tongtien =cursor.getString(7);
                String tennhanvien =cursor.getString(8);
                list.add(new KhachHangDTO(ten, sdt, ngay, thang, nam, gio, tongtien,tennhanvien));
            }
        }
        return list;
    }

    private void AnhXa() {
        recyclerView = findViewById(R.id.recyclerView_dskhachhang);
        btn_Home = findViewById(R.id.float_btn_home);
    }
}