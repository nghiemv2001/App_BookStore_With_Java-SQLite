package com.example.quanlithuvien.giaodien;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.dangnhap_dangky.DangNhap;

public class Welcome extends AppCompatActivity {
    TextView txt_welcome, txt_booksote;

    private static int splash_timoute = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        txt_welcome = findViewById(R.id.txt_welcome);
        txt_booksote = findViewById(R.id.txt_bookstore);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this, DangNhap.class);
                startActivity(intent);
            }
        },splash_timoute);
        Animation animation1 = AnimationUtils.loadAnimation(Welcome.this, R.anim.animation);
        txt_welcome.startAnimation(animation1);
        Animation animation2 = AnimationUtils.loadAnimation(Welcome.this, R.anim.animation2);
        txt_welcome.startAnimation(animation2);
    }
}