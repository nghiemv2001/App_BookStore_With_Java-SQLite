package com.example.quanlithuvien.giaodien.dangnhap_dangky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;

public class DangKy extends AppCompatActivity {
    EditText edt_ma, edt_hoten, edt_tendangnhap, edtsdt, edtmatkhau, edt_xacnhanmatkhau;
    Button btndangky, btncancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nguoidung_dangky);

        AnhXa();

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strma = edt_ma.getText().toString().trim();
                String strhoten = edt_hoten.getText().toString().trim();
                String strtendangnhap = edt_tendangnhap.getText().toString().trim();
                String strsodienthoai = edtsdt.getText().toString().trim();
                String strmatkhau = edtmatkhau.getText().toString().trim();
                String strxacnhanmatkhau = edt_xacnhanmatkhau.getText().toString().trim();
                if (strma.length() <= 3) {
                    edt_ma.setError("Mã nhân viên vui lòng nhập trên 3 kí tự!!!");
                    edt_ma.requestFocus();
                    return;
                }

                if (strhoten.length() == 0) {
                    edt_hoten.setError("Tên nhân viên không được để trống!!!");
                    edt_hoten.requestFocus();
                    return;
                }

                if (strtendangnhap.length() == 0) {
                    edt_tendangnhap.setError("Tên nhân viên không được để trống!!!");
                    edt_tendangnhap.requestFocus();
                    return;
                }
                if (strsodienthoai.length() != 10) {
                    edtsdt.setError("Số điện thoại không đúng 10 chữ số!!!");
                    edtsdt.requestFocus();
                    return;
                }
                if (strmatkhau.length() < 3) {
                    edtmatkhau.setError("Mật khẩu từ 3 kí tự!!!");
                    edtmatkhau.requestFocus();
                    return;
                }

                if (!(strmatkhau.equals(strxacnhanmatkhau))) {
                    edt_xacnhanmatkhau.setError("Mật khẩu xác nhận không chính xác!");
                    edt_xacnhanmatkhau.requestFocus();
                    return;
                }
                DBHelper db = new DBHelper(DangKy.this);
                Boolean checkkeycoe = db.checkkeycode(strma);
                if (checkkeycoe == true) {
                    edt_ma.setError("Mã nhân viên đã được sử dụng!!!");
                    edt_ma.requestFocus();
                    return;
                }
                Boolean checktendangnhap = db.checkUsername(strtendangnhap);
                if (checktendangnhap == true) {
                    edt_tendangnhap.setError("Tên đăng nhập đã được sử dụng!!!");
                    edt_tendangnhap.requestFocus();
                    return;
                } else {
                    db.themnguoidung(edt_ma.getText().toString().trim(),
                            edt_hoten.getText().toString().trim(),
                            edt_tendangnhap.getText().toString().trim(),
                            edtsdt.getText().toString().trim(),
                            edtmatkhau.getText().toString().trim(),String.valueOf(0));
                    db.close();
                    Intent intent = new Intent(DangKy.this, DangNhap.class);
                    startActivity(intent);
                }
            }
        });

        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        edt_ma = findViewById(R.id.edt_ma_input);
        edt_hoten = findViewById(R.id.edt_tendangnhap_input);
        edt_tendangnhap = findViewById(R.id.edt_username_input);
        edtsdt = findViewById(R.id.edt_sdt_input);
        edt_xacnhanmatkhau = findViewById(R.id.edt_xacnhanmatkhau_input);
        edtmatkhau = findViewById(R.id.edt_matkhau_input);
        btndangky = findViewById(R.id.btn_dangky);
        btncancle = findViewById(R.id.btn_cancle);
    }
}