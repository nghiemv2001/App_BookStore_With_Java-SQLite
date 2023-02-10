package com.example.quanlithuvien.giaodien.Thanhtoan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quanlithuvien.Adapter.ThanhToanAdapter;
import com.example.quanlithuvien.DTO.ThanhToanDTO;
import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Thanhtoan extends AppCompatActivity {
    RecyclerView recyclerViewThanhtoan;
    ThanhToanAdapter thanhToanAdapter;
    SQLiteDatabase sqLiteDatabase;
    TextView txtTongtien, txt_date, txt_time, txt_month, txt_year;
    EditText edt_tenkhachhang, editText_sodienthoai;
    Button btn_thanhtoan;
    ImageView imgdate, img_time;
    ArrayList<String> listarr = new ArrayList<String>();
    Spinner spn_tennhanvien;
    String strTenNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan_ds_san_pham_activity);
        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");
        //taospiner
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql1 = "SELECT * FROM register_library";
        Cursor cursor1 = sqLiteDatabase.rawQuery(sql1, null);
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()) {
            String ten = cursor1.getString(1);
            listarr.add(ten);
            cursor1.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listarr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_tennhanvien.setAdapter(adapter);

        spn_tennhanvien.setOnItemSelectedListener(new ChonPhanTu());
        //img_ngay img_gio
        imgdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }

        });


        img_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonGio();
            }
        });

        //Tính tiền
        int tong = 0;
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql = "SELECT * FROM chitietthanhtoan_library";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String tensanpham = cursor.getString(1);
                String soluongsanpham = cursor.getString(2);
                String giasanpham = cursor.getString(3);

                tong = tong + Integer.valueOf(giasanpham) * Integer.valueOf(soluongsanpham);
            }
        }

        txtTongtien.setText(String.valueOf(tong));
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewThanhtoan.setLayoutManager(linearLayoutManager);
        thanhToanAdapter = new ThanhToanAdapter(getListThanhToan(), this);
        recyclerViewThanhtoan.setAdapter(thanhToanAdapter);

        //them mot khach hang vao co so du lieu , dong thoi xoa toan bo chi tiet san pham
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_tenkhachhang.length() == 0) {
                    edt_tenkhachhang.setError("Tên người dùng không được để trống!!!");
                    edt_tenkhachhang.requestFocus();
                    return;
                } else if (editText_sodienthoai.length() != 10) {
                    editText_sodienthoai.setError("Số điện thoại không hợp lệ !!!");
                    return;
                } else {
                    confirmDialog();
                }

            }
        });

    }

    private void ChonGio() {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar.set(0, 0, 0, i, i1);
                txt_time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }

    private void ChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
                txt_date.setText(simpleDateFormat.format(calendar.getTime()));
                txt_month.setText(simpleDateFormat1.format(calendar.getTime()));
                txt_year.setText(simpleDateFormat2.format(calendar.getTime()));

            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private List<ThanhToanDTO> getListThanhToan() {
        // hien thi ra cycleview
        List<ThanhToanDTO> list = new ArrayList<>();
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql = "SELECT * FROM chitietthanhtoan_library";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String tensanpham = cursor.getString(1);
                String soluong = cursor.getString(2);
                String giasanpham = cursor.getString(3);
                list.add(new ThanhToanDTO(id, tensanpham, soluong, giasanpham));
            }
        }
        return list;
    }

    private void AnhXa() {
        recyclerViewThanhtoan = findViewById(R.id.recyclerview_thanhtoan);
        txtTongtien = findViewById(R.id.txt_tongtien);
        txt_date = findViewById(R.id.txt_date);
        txt_month = findViewById(R.id.txt_moth);
        txt_year = findViewById(R.id.txt_year);
        txt_time = findViewById(R.id.txt_time);
        edt_tenkhachhang = findViewById(R.id.edt_tenkhachhang);
        editText_sodienthoai = findViewById(R.id.edt_sodienthoai);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        imgdate = findViewById(R.id.img_date);
        img_time = findViewById(R.id.img_time);
        spn_tennhanvien = findViewById(R.id.spn_tennhanvien);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thanh toán hóa đơn của khách hàng " + edt_tenkhachhang.getText().toString() + " ?");
        builder.setMessage("Xác nhận thanh toán ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //chinh sua so luong ton
                List<ThanhToanDTO> list = new ArrayList<>();
                list = getListThanhToan();
                for (int j = 0; j < list.size(); j = j + 1) {
                    String tenlist = list.get(j).getTen();
                    String soluonglist = list.get(j).getSoluong();
                    String sql1 = "SELECT * FROM book_library";
                    Cursor cursor1 = sqLiteDatabase.rawQuery(sql1, null);
                    while ((cursor1.moveToNext())) {
                        int id = cursor1.getInt(0);
                        String theloai = cursor1.getString(1);
                        String ten = cursor1.getString(2);
                        String tacgia = cursor1.getString(3);
                        String gia = cursor1.getString(4);
                        int sotrang = cursor1.getInt(5);
                        int soluongton = cursor1.getInt(6);
                        if (ten.equals(tenlist)) {
                            int soluongphu = Integer.valueOf(soluonglist);
                            soluongton = soluongton - soluongphu;
                            if (soluongton == 0) {
                                DBHelper dbHelper = new DBHelper(Thanhtoan.this);
                                dbHelper.deleteOneRow(String.valueOf(id));
                            } else {
                                DBHelper dbHelper = new DBHelper(Thanhtoan.this);
                                dbHelper.updateData(String.valueOf(id), theloai, ten, tacgia, gia, String.valueOf(sotrang), String.valueOf(soluongton));
                            }

                        }
                    }
                }
                ////////////////////////////
                DBHelper dbHelper = new DBHelper(Thanhtoan.this);
                dbHelper.themmotkhachhang(edt_tenkhachhang.getText().toString().trim(),
                        editText_sodienthoai.getText().toString().trim(),
                        txt_date.getText().toString().trim(),
                        txt_month.getText().toString().trim(),
                        txt_year.getText().toString().trim(),
                        txt_time.getText().toString().trim(),
                        Integer.valueOf(txtTongtien.getText().toString().trim()), strTenNhanVien);
                dbHelper.close();
                dbHelper.deleteallRowinChiTiecHoaDon();
                Intent intent = new Intent(Thanhtoan.this, HomeActivity.class);
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

    private class ChonPhanTu implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strTenNhanVien = listarr.get(i);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            strTenNhanVien = "";
        }
    }
}