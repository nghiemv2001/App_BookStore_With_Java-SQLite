package com.example.quanlithuvien.giaodien.TheLoai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlithuvien.Adapter.TheLoaiAdapter;
import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachLoaiSach extends AppCompatActivity {
    FloatingActionButton btn_add_theloai;
    RecyclerView recyclerview_ds_theloai;
    DBHelper dbHelper;
    ArrayList<String> theloai_ten, theloai_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai_list_activity);

        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        dbHelper = new DBHelper(DanhSachLoaiSach.this);
        theloai_id = new ArrayList<>();
        theloai_ten = new ArrayList<>();
        btn_add_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachLoaiSach.this, THEM_THELOAI.class);
                startActivity(intent);
            }
        });
        storeDataInArrays();

        TheLoaiAdapter customTheLoaiAdapter = new TheLoaiAdapter(DanhSachLoaiSach.this, DanhSachLoaiSach.this, theloai_id, theloai_ten);
        recyclerview_ds_theloai.setAdapter(customTheLoaiAdapter);
        recyclerview_ds_theloai.setLayoutManager(new LinearLayoutManager(DanhSachLoaiSach.this));

    }

    private void AnhXa() {
        recyclerview_ds_theloai = findViewById(R.id.recyclerView_loaisach);
        btn_add_theloai = findViewById(R.id.floatingActionButton_them_loai_sach);
    }

    void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllData1();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                theloai_id.add(cursor.getString(0));
                theloai_ten.add(cursor.getString(1));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            recreate();
        }
    }
}