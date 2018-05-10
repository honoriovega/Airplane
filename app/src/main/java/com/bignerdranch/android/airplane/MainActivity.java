package com.bignerdranch.android.airplane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button makeAccount;
    private Button reserveSeatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeAccount = (Button) findViewById(R.id.create_account);
        reserveSeatButton = (Button) findViewById(R.id.reserve_seat);

        makeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);

            }
        });

        reserveSeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ReserveSeatActivity.class);
                startActivity(i);

            }
        });
    }
}
