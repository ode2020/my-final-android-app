package com.example.visitrwanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    EditText edt_idno, edt_client_name, edt_contactno;// edt_gender;
    RadioButton rbtn_male, rbtn_female;

    Button btn_send;
    String sidno, sclient_name, scontactno, sgender = "Female";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

         edt_idno = findViewById(R.id.edt_idno);
         edt_client_name = findViewById(R.id.edt_client_name);
         edt_contactno = findViewById(R.id.edt_contactno);
        rbtn_male = findViewById(R.id.rbtn_male);
        rbtn_female = findViewById(R.id.rbtn_female);
         //edt_gender = (EditText)findViewById(R.id.edt_gender);
         btn_send = findViewById(R.id.btn_send);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btn_send.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (edt_idno.getText().toString().isEmpty()
                         || edt_client_name.getText().toString().isEmpty()
                         || edt_contactno.getText().toString().isEmpty()) {

                     Toast.makeText(getApplicationContext(), "Pease fill in all information", Toast.LENGTH_LONG).show();
                 }
                 else
                 {
                     sidno = edt_idno.getText().toString().trim();
                     sclient_name = edt_client_name.getText().toString().trim();
                     scontactno = edt_contactno.getText().toString().trim();
                    // sgender = edt_gender.getText().toString().trim();

                     if (rbtn_female.isChecked())
                     {
                         sgender = "Male";
                     }
                     else
                     {
                         sgender = "Female";
                     }

                     BookingRepository bookingRepository = new BookingRepository(getApplicationContext());
                     Booking booking = new Booking(Integer.parseInt(sidno), sclient_name, scontactno, sgender);
                     BookingRepository.InsertTask(booking);

                     edt_idno.setText("");
                     edt_client_name.setText("");
                     edt_contactno.setText("");
                    // edt_gender.setText("");
                 }
             }

         });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }
}