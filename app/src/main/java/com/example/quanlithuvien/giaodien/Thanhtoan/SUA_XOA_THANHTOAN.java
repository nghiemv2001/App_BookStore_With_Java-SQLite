package com.example.quanlithuvien.giaodien.Thanhtoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;

public class SUA_XOA_THANHTOAN extends AppCompatActivity {
    TextView txtten, txtgia;
    EditText edtSoluong;
    String id, ten, soluong, gia;
    Button btn_update, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan_update_delete_activity);
        AnhXa();
        getIntenData();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(SUA_XOA_THANHTOAN.this);
                dbHelper.updateDatachitietsanpham(id, ten, edtSoluong.getText().toString().trim(), gia);
                Intent intent = new Intent(SUA_XOA_THANHTOAN.this, Thanhtoan.class);
                startActivity(intent);

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(SUA_XOA_THANHTOAN.this);
                dbHelper.xoamotitemtrongdsthanhtoan(id);
                Intent intent = new Intent(SUA_XOA_THANHTOAN.this, Thanhtoan.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        txtten = findViewById(R.id.txt_tensanpham_update);
        edtSoluong = findViewById(R.id.edt_soluongsanpham_update);
        txtgia = findViewById(R.id.txt_giasanpham_update);
        btn_update = findViewById(R.id.btn_chitietsanpham_update);
        btn_delete = findViewById(R.id.btn_chitietsanpham_delete);
    }

    void getIntenData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("ten") && getIntent().hasExtra("soluong") && getIntent().hasExtra("gia")) {
            id = getIntent().getStringExtra("id");
            ten = getIntent().getStringExtra("ten");
            soluong = getIntent().getStringExtra("soluong");
            gia = getIntent().getStringExtra("gia");

            // nhan du lieu
            txtten.setText(ten);
            edtSoluong.setText(soluong);
            txtgia.setText(gia);


        } else {
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa " + ten + " ?");
        builder.setMessage("Bạn chắc chắn muốn xóa " + ten + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(SUA_XOA_THANHTOAN.this);
                dbHelper.xoamotitemtrongdsthanhtoan(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}