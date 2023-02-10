package com.example.quanlithuvien.giaodien.dangnhap_dangky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.HomeActivity;

public class DangNhap extends AppCompatActivity {
    EditText edt_tendangnhap, edt_matkhau;
    Button btn_dangnhap, btn_dangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nguoidung_dangnhap);
        edt_tendangnhap = findViewById(R.id.edt_username_input_login);
        edt_matkhau = findViewById(R.id.edt_password_input_login);
        btn_dangnhap = findViewById(R.id.btn_login);
        btn_dangky = findViewById(R.id.btn_register_cancle);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(DangNhap.this);
                String strten, strmatkhau;
                strten = edt_tendangnhap.getText().toString().trim();
                strmatkhau = edt_matkhau.getText().toString().trim();
                Boolean checkuser = DB.chechusernamepassword(strten, strmatkhau);
                if(checkuser == true){
                    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
                    String sql = "SELECT * FROM register_library";
                    Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                    while ((cursor.moveToNext())) {
                        if(cursor.getString(2).equals(strten)){
                            String strma = cursor.getString(0);
                            String strhoten = cursor.getString(1);
                            String strtendangnhap = cursor.getString(2);
                            String strsdt = cursor.getString(3);
                            String strpass = cursor.getString(4);
                           DB.suamotnguoidung(strma,strhoten,strtendangnhap,strsdt,strpass, String.valueOf(1));
                        }
                    }
                    Intent intent = new Intent(DangNhap.this, HomeActivity.class);
                    startActivity(intent);

                }
                else{
                    edt_tendangnhap.setError("Tên đăng nhập hoặc mật khẩu sai !!!");
                    edt_tendangnhap.requestFocus();
                    return;
                }
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
}