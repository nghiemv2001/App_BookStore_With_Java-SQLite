package com.example.quanlithuvien.giaodien.DoanhThu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlithuvien.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeDoanhThu extends AppCompatActivity{

    BarChart barChart;
    SQLiteDatabase sqLiteDatabase;
    TextView txt_tieude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doanhthu_thongkedoanhthu);
        barChart = findViewById(R.id.bar_chart);
        txt_tieude = findViewById(R.id.txt_thoigian);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        //Nhan du lieu
        Intent intent = getIntent();
        String valuemonth = intent.getStringExtra("thang");
        String valueyear = intent.getStringExtra("nam");
        //
        txt_tieude.setText("Doanh Thu Tháng "+valuemonth+" Năm "+valueyear);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for ( int i = 1; i<=31; i=i+1){
            int tong = 0;
            sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
            String sql = "SELECT * FROM thanhtoan_library";
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            if(cursor.getCount() == 0){
                Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
            }
            else{
                while (cursor.moveToNext()){
                    String ngay = cursor.getString(3);
                    String thang = cursor.getString(4);
                    String nam = cursor.getString(5);
                    String TongTien =cursor.getString(7);
                    if(valueyear.equals(nam)){
                        if((Integer.valueOf(valuemonth) == Integer.valueOf(thang))){
                            if ((Integer.valueOf(ngay) == i)){
                                tong = tong + Integer.valueOf(TongTien);
                            }
                        }
                    }
                }
                barEntries.add(new BarEntry(i,tong));
            }
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Ngày");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(14f);
        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Doanh Thu Theo Tháng");
        barChart.animateY(2000);

    }
}