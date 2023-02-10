package com.example.quanlithuvien.giaodien.dangnhap_dangky;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;

public class ThongTinNguoiDung extends AppCompatActivity {
    TextView txtma, txtten, txtsdt;
    SQLiteDatabase sqLiteDatabase;
    Button btn_dangxuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nguoidung_thongtinnguoidung);
        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        String tendangnhap = getIntent().getStringExtra("tendangnhap");
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql = "SELECT * FROM register_library";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while ((cursor.moveToNext())) {
            if(cursor.getString(5).equals("1")){
                String strma = cursor.getString(0);
                String strten = cursor.getString(1);
                String strsdt = cursor.getString(3);
                txtma.setText(strma);
                txtsdt.setText(strsdt);
                txtten.setText(strten);
            }
        }

        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(ThongTinNguoiDung.this);
                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
                String sql = "SELECT * FROM register_library";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while ((cursor.moveToNext())) {
                    if(cursor.getString(5).equals("1")){
                        String strma = cursor.getString(0);
                        String strhoten = cursor.getString(1);
                        String strtendangnhap = cursor.getString(2);
                        String strsdt = cursor.getString(3);
                        String strpass = cursor.getString(4);
                        dbHelper.suamotnguoidung(strma,strhoten,strtendangnhap,strsdt,strpass, String.valueOf(0));
                    }
                }

                Intent intent = new Intent(ThongTinNguoiDung.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        txtma = findViewById(R.id.txt_ma);
        txtten = findViewById(R.id.txt_ten);
        txtsdt = findViewById(R.id.txt_sdt);
        btn_dangxuat = findViewById(R.id.btn_dangxuat);
    }
}