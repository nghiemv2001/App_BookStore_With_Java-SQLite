package com.example.quanlithuvien.giaodien.TheLoai;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;

public class CHINHSUA_XOA_THELOAI extends AppCompatActivity {
    EditText theloai_input;
    Button btndelete, btnupdate;
    String matheloai, tentheloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai_update_delete_activity);
        AnhXa();
        getIntentData();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(theloai_input.getText().toString());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(CHINHSUA_XOA_THELOAI.this);
                dbHelper.UpdateTheLoai(matheloai, theloai_input.getText().toString());
                Intent intent = new Intent(CHINHSUA_XOA_THELOAI.this, DanhSachLoaiSach.class);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDaiAlertLog();
            }
        });

    }

    private void AnhXa() {
        theloai_input = findViewById(R.id.edt_theloai_update);
        btnupdate = findViewById(R.id.btn_update_theloai);
        btndelete = findViewById(R.id.btn_delete_theloai);
    }
    void getIntentData(){
        if (getIntent().hasExtra("id_theloai") && getIntent().hasExtra("ten_theloai")){
            matheloai = getIntent().getStringExtra("id_theloai");
            tentheloai = getIntent().getStringExtra("ten_theloai");
            theloai_input.setText(tentheloai);
        }
        else
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    void ConfirmDaiAlertLog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa "+tentheloai+" ?");
        builder.setMessage("Bạn có chắc muốn xóa " + tentheloai + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(CHINHSUA_XOA_THELOAI.this);
                dbHelper.deleteOneRowTheLoai(matheloai);
                Intent intent = new Intent(CHINHSUA_XOA_THELOAI.this, DanhSachLoaiSach.class);
                startActivity(intent);
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