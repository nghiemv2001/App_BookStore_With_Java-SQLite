package com.example.quanlithuvien.giaodien.Sach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.quanlithuvien.DTO.Book;
import com.example.quanlithuvien.Adapter.BookAdapter;
import com.example.quanlithuvien.Database.DBHelper;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.Thanhtoan.Thanhtoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;


public class DANHSACHSACH extends AppCompatActivity {
    DBHelper myDB;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    SQLiteDatabase sqLiteDatabase;
    BookAdapter bookAdapter;

    private  SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_activity);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.floatingActionButton);

        //set title cho action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhà Sách");


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DANHSACHSACH.this, THEMSACH.class);
                startActivity(intent);
            }
        });
        //do du lieu ra recycle view

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        bookAdapter = new BookAdapter(this, getListBook());
        recyclerView.setAdapter(bookAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private List<Book> getListBook() {
        List<Book> list = new ArrayList<>();
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);
        String sql = "SELECT * FROM book_library";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                String id = cursor.getString(0);
                String theloai =cursor.getString(1);
                String ten =cursor.getString(2);
                String tacgia =cursor.getString(3);
                String gia =cursor.getString(4);
                String sotrang =cursor.getString(5);
                String soluongton =cursor.getString(6);
                list.add(new Book(id, theloai, ten, tacgia, gia, sotrang, soluongton));
            }
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            recreate();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_shoppingcart, menu);
        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ShoppingCart){
            Intent intent = new Intent(DANHSACHSACH.this, Thanhtoan.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}