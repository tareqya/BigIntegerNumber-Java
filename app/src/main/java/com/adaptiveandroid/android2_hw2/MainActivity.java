package com.adaptiveandroid.android2_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tareqyassin.bignumber.BigNumber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BigNumber b1 = new BigNumber("189_999_999");
        BigNumber b2 = new BigNumber("99_999_999");
        BigNumber b3 = new BigNumber("1_000_000_000_000_000");
        BigNumber res = b1.add(b2);
        BigNumber res2 = b2.sub(b1);
        BigNumber res3 = b2.mul(b1);

        System.out.println(res);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println();
    }
}