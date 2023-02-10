package com.example.quanlithuvien.giaodien.DoanhThu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.quanlithuvien.R;

import java.util.ArrayList;

public class HomeDoanhThu extends AppCompatActivity {


    Spinner spnThang, spnNam;
    ArrayList<String> listarrThang = new ArrayList<String>();
    String strThang;
    ArrayList<String> listarrNam = new ArrayList<String>();
    String strNam;
    Button btn_xemdoanhthu, btn_xemdanhsachhoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doanhthu_home);
        spnThang = findViewById(R.id.spnThang);
        spnNam = findViewById(R.id.spnnam);
        btn_xemdoanhthu = findViewById(R.id.btn_xemdoanhthu);
        btn_xemdanhsachhoadon = findViewById(R.id.btn_xemdanhxachhoadon);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");

        //Spiner thang
        for (int i = 1; i <= 12; i++) {
            listarrThang.add(new String(String.valueOf(i)));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listarrThang);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnThang.setAdapter(adapter);
        spnThang.setOnItemSelectedListener(new ChonPhanTuThang());

        //Spiner nam
        for (int j = 2020; j <= 2050; j++) {
            listarrNam.add(new String(String.valueOf(j)));
        }
        ArrayAdapter<String> adapterNam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listarrNam);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNam.setAdapter(adapterNam);
        spnNam.setOnItemSelectedListener(new ChonPhanTuNam());


        //nut xem doanh thu
        btn_xemdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDoanhThu.this, ThongKeDoanhThu.class);
                intent.putExtra("thang", strThang);
                intent.putExtra("nam", strNam);
                startActivity(intent);
            }
        });

        // nut xem danh sach hoa don
        btn_xemdanhsachhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDoanhThu.this, DanhSachKhachHang.class);
                startActivity(intent);
            }
        });
    }

    private class ChonPhanTuThang implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strThang = listarrThang.get(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            strThang = "1";
        }
    }

    private class ChonPhanTuNam implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strNam = listarrNam.get(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            strNam = "1";
        }
    }
}