package com.example.quanlithuvien.giaodien.Thanhtoan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.Sach.DANHSACHSACH;

public class CHITIETTHANHTOAN extends AppCompatActivity {
    TextView txt_tensanpham, txt_giasanpham;
    EditText edt_soluongsanpham;
    Button btn_themmotsanpham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan_chitietthanhtoan_activity);
        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        String strTen = getIntent().getStringExtra("ten");
        String strGia = getIntent().getStringExtra("gia");
        String strsoluong = getIntent().getStringExtra("soluong");
        int soluongton = Integer.valueOf(strsoluong);
        txt_tensanpham.setText(strTen);
        txt_giasanpham.setText(strGia);
        btn_themmotsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_soluongsanpham.length() ==0  ||  Integer.valueOf(edt_soluongsanpham.getText().toString().trim()) <= 0 || Integer.valueOf(edt_soluongsanpham.getText().toString().trim()) > soluongton){
                    edt_soluongsanpham.setError("Số lượng không hợp lệ!");
                    edt_soluongsanpham.requestFocus();
                    return;
                }else{
                    DBHelper dbHelper = new DBHelper(CHITIETTHANHTOAN.this);
                    dbHelper.themsanpham(txt_tensanpham.getText().toString().trim(), Integer.valueOf(edt_soluongsanpham.getText().toString().trim()), Integer.valueOf(txt_giasanpham.getText().toString().trim()));
                    dbHelper.close();
                    Intent intent = new Intent(CHITIETTHANHTOAN.this, DANHSACHSACH.class);
                    startActivity(intent);
                }

            }
        });

    }

    private void AnhXa() {
        txt_tensanpham = findViewById(R.id.txt_tensanpham);
        txt_giasanpham = findViewById(R.id.txt_giasanpham);
        edt_soluongsanpham = findViewById(R.id.edt_soluongsanpham);
        btn_themmotsanpham = findViewById(R.id.btn_themsanpham);
    }
}