package com.example.quanlithuvien.giaodien.Sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.giaodien.HomeActivity;
import com.example.quanlithuvien.R;

import java.util.ArrayList;

public class THEMSACH extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{
    Spinner spnLoaiSach;
    EditText tensach, tacgia , giasach , sotrang, soluongton;
    Button btn_add;
    String strLoaiSach;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<String> listarr = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_add_activity);
        spnLoaiSach = findViewById(R.id.spn_loaisach);
        tensach = findViewById(R.id.edt_tieude);
        tacgia = findViewById(R.id.edt_tacgia);
        giasach = findViewById(R.id.edt_gia);
        sotrang = findViewById(R.id.edt_sotrang);
        soluongton = findViewById(R.id.edt_soluongton);
        btn_add = findViewById(R.id.btn_add);
        //tao du lieu
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql = "SELECT * FROM theloai_library";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String ten =cursor.getString(1);
            listarr.add(ten);
            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listarr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnLoaiSach.setAdapter(adapter);
        spnLoaiSach.setOnItemSelectedListener(new ChonPhanTu());
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(THEMSACH.this);
                myDB.addBook(strLoaiSach,
                        tensach.getText().toString().trim(),
                        tacgia.getText().toString().trim(),
                        Integer.valueOf(giasach.getText().toString().trim()),
                        Integer.valueOf(sotrang.getText().toString().trim()),
                        Integer.valueOf(soluongton.getText().toString().trim()));
                myDB.close();
                Intent intent = new Intent(THEMSACH.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    private class ChonPhanTu implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strLoaiSach =listarr.get(i);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            strLoaiSach ="";
        }
    }

}