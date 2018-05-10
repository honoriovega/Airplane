package com.bignerdranch.android.airplane;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.bignerdranch.android.airplane.Database.AirplaneDatabase;
import com.bignerdranch.android.airplane.Models.User;

import java.util.regex.Pattern;

public class CreateAccountActivity extends AppCompatActivity {


    // Our widget
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button makeAccountButton;

    private String mUsername;
    private String mPassword;

    private AirplaneDatabase mAirplaneDatabase;

    private int mCreationAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Get access to our database singleton
        mAirplaneDatabase = AirplaneDatabase.getInstance(this);

        // bind our widgets
        usernameEditText = (EditText) findViewById(R.id.create_account_username);
        passwordEditText = (EditText) findViewById(R.id.create_account_password);
        makeAccountButton = (Button) findViewById(R.id.create_account);



        makeAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                if(username.equals("admin2")) {

                    Toast toast= Toast.makeText(getApplicationContext(),
                            "admin2 username is reserved", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

                    return;

                }

                if(!hasOneNumberAndThreeLetters(username) || !hasOneNumberAndThreeLetters(password)) {

                    mCreationAttempts = mCreationAttempts + 1;

                    if (mCreationAttempts == 2) {
                        showDialogAndExit("Error", "Maximum account creation attempts. Returning to Main Menu");

                        return;
                    }

                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Username and password must contain at least 1 number and 3 letters.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();



                    return;
                }

                if(mAirplaneDatabase.userDao().getUser(username) != null) {

                    mCreationAttempts = mCreationAttempts + 1;

                    if (mCreationAttempts == 2) {
                        showDialogAndExit("Error", "Maximum account creation attempts. Returning to Main Menu");

                        return;
                    }

                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Sorry that username is taken. Please try again.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

                    return;
                }

                mAirplaneDatabase.userDao().addUser(new User(username,password));
                showDialogAndExit("Success","Account created succesfully. Returning to Main Menu.");
                /*

                (6) The System records the transaction information that includes a transaction type (= new account), the Customer’s username, and current date/time (= hour and minute).
                 */


            }



        });
    }

    private void showDialogAndExit(String title, String message) {
        // Create alert dialog
        AlertDialog.Builder a_builder = new AlertDialog.Builder(CreateAccountActivity.this);
        a_builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                });

        AlertDialog alert = a_builder.create();
        alert.setTitle(title);
        alert.show();
    }

    private boolean hasOneNumberAndThreeLetters(String str) {
        int numberCount = 0, letterCount = 0;
        for (char c : str.toCharArray()) {
            if(Character.isLetter(c) ) {
                letterCount = letterCount + 1;
            }
            if(Character.isDigit(c)) {
                numberCount  = numberCount + 1;
            }
        }

        return numberCount >= 1 && letterCount >= 3;
    }


}
