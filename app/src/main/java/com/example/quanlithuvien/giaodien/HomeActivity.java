package com.example.quanlithuvien.giaodien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.Adapter.ImageAdapter;
import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.QRcode.QRcodeActivity;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.DoanhThu.HomeDoanhThu;
import com.example.quanlithuvien.giaodien.Sach.DANHSACHSACH;
import com.example.quanlithuvien.giaodien.TheLoai.DanhSachLoaiSach;
import com.example.quanlithuvien.giaodien.dangnhap_dangky.ThongTinNguoiDung;
import com.example.quanlithuvien.giaodien.map.Map_Google;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Integer[] Images= {R.drawable.images_user, R.drawable.image_book , R.drawable.images_books, R.drawable.images_revenue_statistics, R.drawable.icon_map};
    private  String[] Texts = {"Người dùng","Sách","Thể loại","Doanh thu", "Vị trí"};
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DBHelper myDB;
    ArrayList<String> book_id, book_title , book_author, book_pages;
    String tendangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        GridView gdvMenu = findViewById(R.id.gridview);
        ImageAdapter adapter = new ImageAdapter(this, R.layout.item_home_custom_gridview_cell, Images, Texts);
        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener(new ChonCongViec());
        tendangnhap = getIntent().getStringExtra("tendangnhap") ;
    }

    private class ChonCongViec implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(i == 5)
                finish();
            else {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(HomeActivity.this,
                                ThongTinNguoiDung.class);
                        break;
                    case 1:
                        intent = new Intent(HomeActivity.this,
                                DANHSACHSACH.class);
                        break;
                    case 2:
                        intent = new Intent(HomeActivity.this,
                                DanhSachLoaiSach.class);
                        break;
                    case 3:
                        intent = new Intent(HomeActivity.this,
                                HomeDoanhThu.class);
                        break;
                    case 4:
                        intent = new Intent(HomeActivity.this, Map_Google.class);
                        break;
                }
                startActivity(intent);
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_barcode, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.qrcode){
            Intent intent = new Intent(this, QRcodeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}