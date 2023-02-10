package com.example.quanlithuvien.giaodien.TheLoai;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.giaodien.HomeActivity;
import com.example.quanlithuvien.R;

public class THEM_THELOAI extends AppCompatActivity {

    EditText edt_tenloai;
    Button btn_add_theloai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai_add_activity);
        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        edt_tenloai = findViewById(R.id.edt_theloai_add);
        btn_add_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(THEM_THELOAI.this);
                db.themloaisach(edt_tenloai.getText().toString().trim());
                db.close();
                Intent intent = new Intent(THEM_THELOAI.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        edt_tenloai = findViewById(R.id.edt_theloai_add);
        btn_add_theloai = findViewById(R.id.btn_add_theloai);
    }
}