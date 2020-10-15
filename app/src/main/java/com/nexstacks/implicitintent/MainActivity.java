package com.nexstacks.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mEtPhoneNumber = findViewById(R.id.et_phone_number);
        final EditText mEtURL = findViewById(R.id.et_url);
        final EditText mEtAddress = findViewById(R.id.et_address);
        final EditText mEtSubject = findViewById(R.id.et_subject);
        final EditText mEtMessage = findViewById(R.id.et_message);

        Button mBtnCall = findViewById(R.id.btn_call);
        Button mBtnBrowser = findViewById(R.id.btn_browser);
        Button mBtnSendMail = findViewById(R.id.btn_send_email);

        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mEtPhoneNumber.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(callIntent);
            }
        });

        mBtnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mEtURL.getText().toString();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        mEtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = mEtAddress.getText().toString();
                String subject = mEtSubject.getText().toString();
                String message = mEtMessage.getText().toString();

                String[] multiaddress = address.split(",");

                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                mailIntent.putExtra(Intent.EXTRA_TEXT, message);
                mailIntent.putExtra(Intent.EXTRA_EMAIL, multiaddress);
                mailIntent.setType("message/rfc822");
                startActivity(mailIntent);
            }
        });
    }
}