package com.example.quanlithuvien.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private  Context context;
    public static final  String DBNAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME ="book_library";
    private static final String TABLE_THELOAI ="theloai_library";
    private static final String TABLE_THANHTOAN ="thanhtoan_library";
    private static final String TABLE_CHITIETTHANHTOAN= "chitietthanhtoan_library";
    private static final String TABLE_REGISTER ="register_library";
    // bang sach

    private static final String COLUMN_ID="_id";
    private static final String COLUMN_LOAISACH="book_loaisach";
    private static final String COLUMN_TIEUDE ="book_tieude";
    private static final String COLUMN_TACGIA = "book_tacgia";
    private static final String COLUMN_GIASACH = "book_giasach";
    private static final String COLUMN_SOTRANG= "boook_sotrang";
    private static final String COLUMN_SOLUONGTON= "boook_soluongton";

    // bang the loai
    private static final String COLUMN_MATHELOAI="book_matheloai";
    private static final String COLUMN_TENTHELOAI="book_tentheloai";

    //Bang thanh toan

    private static  final String COLUMN_MAHOADON="thanhtoan_mahoadon";
    private static final String COLUMN_TENKHACHHANG="thanhtoan_tenkhachhang";
    private static final String COLUMN_SDT = "thanhtoan_sdt";
    private static final String COLUMN_NGAY = "thanhtoan_ngay";
    private static final String COLUMN_THANG = "thanhtoan_thang";
    private static final String COLUMN_NAM = "thanhtoan_nam";
    private static final String COLUMN_GIO = "thanhtoan_gio";
    private static final String COLUMN_TONGTIEN ="thanhtoan_tongtien";
    private static final String COLUMN_TENNHANVIEN ="thanhtoan_tennhanvien";


    //Bang chi tiet thanh toan
    private static final String COLUMN_ID_SANPHAM="id";
    private static final String COLUMN_TENSANPHAM= "CTTT_tensanpham";
    private static final String COLUMN_SOLUONGSANPHAM= "CTTT_soluongsanpham";
    private static final String COLUMN_GIASANPHAM= "CTTT_giasanpham";

    //Bang Nguoi dung
    private static final String COLUMN_MA="ma";
    private static final String COLUMN_HOTEN="hoten";
    private static final String COLUMN_TENDANGNHAP ="tendangnhap";
    private static final String COLUMN_SODIENTHOAI = "sodienthoai";
    private static final String COLUMN_MATKHAU = "password";
    private static final String COLUMN_TRANGTHAI = "trangthai";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String bang_sach = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_LOAISACH + " TEXT, " +
                COLUMN_TIEUDE + " TEXT, " +
                COLUMN_TACGIA + " TEXT, " +
                COLUMN_GIASACH + " TEXT, " +
                COLUMN_SOTRANG + " INTEGER, " +
                COLUMN_SOLUONGTON + " INTEGER);";

        String bang_theloai = "CREATE TABLE " + TABLE_THELOAI +
                " (" + COLUMN_MATHELOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TENTHELOAI + " TEXT);";

        String bang_thanhtoan = "CREATE TABLE " + TABLE_THANHTOAN +
                " (" + COLUMN_MAHOADON + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TENKHACHHANG + " TEXT, " +
                COLUMN_SDT + " TEXT, " +
                COLUMN_NGAY + " TEXT, " +
                COLUMN_THANG + " TEXT, " +
                COLUMN_NAM + " TEXT, " +
                COLUMN_GIO + " TEXT, " +
                COLUMN_TONGTIEN + " TEXT, " +
                COLUMN_TENNHANVIEN + " TEXT);";

        String bang_chitiethoadon = "CREATE TABLE " + TABLE_CHITIETTHANHTOAN +
                " (" + COLUMN_ID_SANPHAM + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TENSANPHAM + " TEXT, " +
                COLUMN_SOLUONGSANPHAM + " TEXT, " +
                COLUMN_GIASANPHAM + " TEXT);";

        String bang_nguoidung = "CREATE TABLE " + TABLE_REGISTER +
                " (" + COLUMN_MA + " TEXT PRIMARY KEY, "+
                COLUMN_HOTEN + " TEXT, " +
                COLUMN_TENDANGNHAP + " TEXT, " +
                COLUMN_SODIENTHOAI + " TEXT, " +
                COLUMN_MATKHAU + " TEXT, " +
                COLUMN_TRANGTHAI + " INTEGER);";


        sqLiteDatabase.execSQL(bang_sach);
        sqLiteDatabase.execSQL(bang_theloai);
        sqLiteDatabase.execSQL(bang_thanhtoan);
        sqLiteDatabase.execSQL(bang_chitiethoadon);
        sqLiteDatabase.execSQL(bang_nguoidung);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_THELOAI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_THANHTOAN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CHITIETTHANHTOAN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_REGISTER);
        onCreate(sqLiteDatabase);
    }


    //Truy vấn cơ sỡ dữ liệu bên sách
    //the mot sach
    public void addBook(String loaisach, String tieude, String tacgia, int giasach, int sotrang, int soluongton){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LOAISACH, loaisach);
        cv.put(COLUMN_TIEUDE, tieude);
        cv.put(COLUMN_TACGIA, tacgia);
        cv.put(COLUMN_GIASACH, giasach);
        cv.put(COLUMN_SOTRANG, sotrang);
        cv.put(COLUMN_SOLUONGTON, soluongton);
        long result = db.insert(TABLE_NAME, null, cv);
        if ( result == -1 ){
            Toast.makeText(context, "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
        }
    }

    //sua mot sach
    public void updateData(String row_id, String loaisach, String tensach , String tacgia, String giasach, String sotrang, String soluongton){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LOAISACH, loaisach);
        cv.put(COLUMN_TIEUDE, tensach);
        cv.put(COLUMN_TACGIA, tacgia);
        cv.put(COLUMN_GIASACH, giasach);
        cv.put(COLUMN_SOTRANG, sotrang);
        cv.put(COLUMN_SOLUONGTON, soluongton);
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Chỉnh sửa thông tin sách thất bại", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Chỉnh sửa thông tin sách thành công", Toast.LENGTH_SHORT).show();
        }
    }

    //xoa mot sach
    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Đã xóa thành công 1 quyển sách", Toast.LENGTH_SHORT).show();
        }
    }
    //xoa toan bo sach
    public void deleteAllRow(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
    }
    //truy van du lieu
    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    // Truy vấn cơ sở dữ liệu bên Thể Loại
    //them mot the loai
    public void themloaisach( String tenloai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENTHELOAI, tenloai );
        long result = db.insert(TABLE_THELOAI, null, cv);
        if ( result == -1 ){
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Thành công thêm một loại sách", Toast.LENGTH_SHORT).show();
        }
    }

    //sua mot the loai
    public void UpdateTheLoai(String row_id, String ten_theloai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENTHELOAI, ten_theloai);
        long result = db.update(TABLE_THELOAI, cv, "book_matheloai=?", new String[]{row_id});
        if(result == -1)
            Toast.makeText(context, "Cập nhật thể loại thất bại", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Cập nhật thể loại thành công", Toast.LENGTH_SHORT).show();
    }

    //xoa mot the loai
    public void deleteOneRowTheLoai(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_THELOAI, "book_matheloai=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Xóa thành công một loại sách", Toast.LENGTH_SHORT).show();
        }
    }


    //truy van du lieu trong bang the loai
    public Cursor readAllData1(){
        String query = "SELECT * FROM " + TABLE_THELOAI;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



    //Truy vấn cơ sở dữ liệu bên người dùng
    public boolean insertData(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("password", password);

        long result = db.insert("users", null, cv);
        if(result==-1)
           return false;
        else
            return true;
    }

    //Them mot san pham trong don hang
    public void themsanpham(String ten, int soluong , int gia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENSANPHAM, ten);
        cv.put(COLUMN_SOLUONGSANPHAM, soluong);
        cv.put(COLUMN_GIASANPHAM, gia);
        long result = db.insert(TABLE_CHITIETTHANHTOAN, null, cv);
        if ( result == -1 ){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Thêm thành công một sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor docdulieucuasanpham(){
        String query = "SELECT * FROM " + TABLE_CHITIETTHANHTOAN;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateDatachitietsanpham(String id, String ten, String soluong , String gia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENSANPHAM, ten);
        cv.put(COLUMN_SOLUONGSANPHAM, soluong);
        cv.put(COLUMN_GIASANPHAM, gia);
        long result = db.update(TABLE_CHITIETTHANHTOAN, cv, "id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
        }
    }
    //xoa mot san pham trong list hoa don
    public void xoamotitemtrongdsthanhtoan(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CHITIETTHANHTOAN, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    //Thanh toan hoa don
    public void deleteallRowinChiTiecHoaDon(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_CHITIETTHANHTOAN);
    }

    ///////////////////////////////////////////////////
    public void themmotkhachhang(String ten, String sdt , String ngay, String thang, String nam, String gio, int tongtien, String tennhanvien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENKHACHHANG, ten);
        cv.put(COLUMN_SDT, sdt);
        cv.put(COLUMN_NGAY, ngay);
        cv.put(COLUMN_THANG, thang);
        cv.put(COLUMN_NAM, nam);
        cv.put(COLUMN_GIO, gio);
        cv.put(COLUMN_TONGTIEN, tongtien);
        cv.put(COLUMN_TENNHANVIEN, tennhanvien);
        long result = db.insert(TABLE_THANHTOAN, null, cv);
        if ( result == -1 ){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Thêm thành công một khách hàng", Toast.LENGTH_SHORT).show();
        }
    }



    ///HAM XU LI NGUOI DUNG
    public  void themnguoidung(String ma, String hoten, String tendangnhap, String sdt, String matkhau, String trangthai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MA, ma);
        cv.put(COLUMN_HOTEN, hoten);
        cv.put(COLUMN_TENDANGNHAP, tendangnhap);
        cv.put(COLUMN_SODIENTHOAI, sdt);
        cv.put(COLUMN_MATKHAU, matkhau);
        cv.put(COLUMN_TRANGTHAI, trangthai);
        long result = db.insert(TABLE_REGISTER, null, cv);
        if ( result == -1 ){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Thêm thành công một người dùng", Toast.LENGTH_SHORT).show();
        }
    }

    public  void suamotnguoidung(String ma, String hoten, String tendangnhap, String sdt, String matkhau, String trangthai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MA, ma);
        cv.put(COLUMN_HOTEN, hoten);
        cv.put(COLUMN_TENDANGNHAP, tendangnhap);
        cv.put(COLUMN_SODIENTHOAI, sdt);
        cv.put(COLUMN_MATKHAU, matkhau);
        cv.put(COLUMN_TRANGTHAI, trangthai);
        long result = db.update(TABLE_REGISTER, cv, "ma=?", new String[]{ma});
        if ( result == -1 ){
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkkeycode(String ma){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from register_library where ma=?", new String[]{ma});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public boolean checkUsername(String tendangnhap){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from register_library where tendangnhap=?", new String[]{tendangnhap});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean chechusernamepassword(String tendangnhap, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from register_library where tendangnhap=? and password=?", new String[]{tendangnhap,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}