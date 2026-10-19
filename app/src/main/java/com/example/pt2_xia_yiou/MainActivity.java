package com.example.pt2_xia_yiou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTemp = findViewById(R.id.btn_temp);
        Button btnLength = findViewById(R.id.btn_length);
        Button btnWeight = findViewById(R.id.btn_weight);

        // Cada botón abre su conversor.
        btnTemp.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Temp.class)));

        btnLength.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Length.class)));

        btnWeight.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Weight.class)));
    }
}
