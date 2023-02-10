package com.example.quanlithuvien.giaodien.Sach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.Thanhtoan.CHITIETTHANHTOAN;
import com.example.quanlithuvien.giaodien.Thanhtoan.SUA_XOA_THANHTOAN;
import com.example.quanlithuvien.giaodien.Thanhtoan.Thanhtoan;

import java.util.ArrayList;

public class CHINHSUA_XOA_SACH extends AppCompatActivity {
    Spinner spntheloai;
    Button btn_update, btn_delete;
    ImageButton imgbtnAdd_cart;
    EditText tensach_input, tacgia_input, giasach_input, sotrang_input, soluongton_input;
    String strTheLoai;
    String id , tensach, tacgia, theloai, giasach, sotrang, soluongton;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<String> listarr = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_update_delete_activity);
        spntheloai = findViewById(R.id.spn_loaisach2);
        tensach_input = findViewById(R.id.edt_tieude2);
        tacgia_input = findViewById(R.id.edt_tacgia2);
        giasach_input = findViewById(R.id.edt_gia2);
        sotrang_input = findViewById(R.id.edt_sotrang2);
        soluongton_input = findViewById(R.id.edt_soluongton2);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        imgbtnAdd_cart = findViewById(R.id.imgbtn_add_shopping_cart);
        //Truy van du lieu
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
        spntheloai.setAdapter(adapter);
        spntheloai.setOnItemSelectedListener(new ChonPhanTu());
        getIntentData();
        //set title cho action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(tensach);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(CHINHSUA_XOA_SACH.this);
                myDB.updateData(id, strTheLoai, tensach_input.getText().toString().trim(), tacgia_input.getText().toString().trim(), giasach_input.getText().toString().trim(), sotrang_input.getText().toString().trim(), soluongton_input.getText().toString().trim());
                Intent intent = new Intent(CHINHSUA_XOA_SACH.this, DANHSACHSACH.class);
                startActivity(intent);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


        imgbtnAdd_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CHINHSUA_XOA_SACH.this, CHITIETTHANHTOAN.class);
                intent.putExtra("id", id);
                intent.putExtra("ten", tensach_input.getText().toString());
                intent.putExtra("gia", giasach_input.getText().toString());
                intent.putExtra("soluong", soluongton_input.getText().toString());
                startActivity(intent);
            }
        });

    }
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("loaisach") &&
                getIntent().hasExtra("tensach") && getIntent().hasExtra("tacgia")
                && getIntent().hasExtra("giasach") && getIntent().hasExtra("sotrang")){
            id = getIntent().getStringExtra("id");
            theloai = getIntent().getStringExtra("loaisach");
            tensach = getIntent().getStringExtra("tensach");
            tacgia = getIntent().getStringExtra("tacgia");
            giasach = getIntent().getStringExtra("giasach");
            sotrang = getIntent().getStringExtra("sotrang");
            soluongton = getIntent().getStringExtra("soluongton");

            //setting
            String s = theloai.trim();
           spntheloai.setSelection(getIndex(spntheloai,s));
            tensach_input.setText(tensach);
            tacgia_input.setText(tacgia);
            giasach_input.setText(giasach);
            sotrang_input.setText(sotrang);
            soluongton_input.setText(soluongton);


        }
    }

    private int getIndex(Spinner spntheloai, String s) {
        for ( int i=0 ; i < spntheloai.getCount(); i++){
            if(spntheloai.getItemAtPosition(i).toString().equalsIgnoreCase(s))
                return i;
        }
        return 0;
    }
    private class ChonPhanTu implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strTheLoai =listarr.get(i);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            strTheLoai="";
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa "+ tensach + " ?");
        builder.setMessage("Bạn chắc chắn muốn xóa "+ tensach+ " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper mydb = new DBHelper(CHINHSUA_XOA_SACH.this);
                mydb.deleteOneRow(id);
                Intent intent = new Intent(CHINHSUA_XOA_SACH.this, DANHSACHSACH.class);
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