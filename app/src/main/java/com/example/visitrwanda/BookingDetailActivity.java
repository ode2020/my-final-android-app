package com.example.visitrwanda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class BookingDetailActivity extends AppCompatActivity {
    EditText edt_idno, edt_client_name, edt_contactno;
    Button btn_update, btn_delete;

    int sidno;
    String sclient_name ="", scontactno = "", sgender = "";

    String sidno_to_update = "", sclient_name_to_update = "", scontactno_to_update = "", sgender_to_update = "";
    String sidno_to_delete = "", sclient_name_to_delete = "", scontactno_to_delete = "", sgender_to_delete = "";

    RadioButton rbtn_male, rbtn_female;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        //----findBy ids---
        edt_idno = (EditText)findViewById(R.id.edt_idno);
        edt_client_name = (EditText)findViewById(R.id.edt_client_name);
        edt_contactno = (EditText)findViewById(R.id.edt_contactno);
        rbtn_male = (RadioButton)findViewById(R.id.rbtn_male);
        rbtn_female = (RadioButton)findViewById(R.id.rbtn_female);

        btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //end of it---

        //---Getting data from customerAdapter----
        Bundle data = getIntent().getExtras();
        if (data!=null)
        {
            sidno = data.getInt("idno");
            sclient_name = data.getString("client_name");
            scontactno = data.getString("contactno");
            sgender = data.getString("gender");
        }
        //----end of it-----

        //---Set values to UI-----
        edt_idno.setText(sidno+"");
        edt_client_name.setText(sclient_name+"");
        edt_contactno.setText(scontactno+"");

        if(sgender.trim().toLowerCase().equalsIgnoreCase("Male"))
        {
            rbtn_male.setChecked(true);
        }

       else if(sgender.trim().toLowerCase().equalsIgnoreCase("Female"))
        {
            rbtn_female.setChecked(true);
        }
        //---end of it -----


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_client_name.getText().toString().isEmpty()
                || edt_contactno.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Some details are missing", Toast.LENGTH_LONG);
                }
                else
                {
                    sidno_to_update = edt_idno.getText().toString().trim();
                    sclient_name_to_update = edt_client_name.getText().toString().trim();
                    scontactno_to_update = edt_contactno.getText().toString().trim();

                    if (rbtn_male.isChecked())
                    {
                        sgender_to_update = "Male";
                    }

                   else if (rbtn_female.isChecked())
                    {
                        sgender_to_update = "Female";
                    }

                   BookingRepository bookingRepository = new BookingRepository(getApplicationContext());
                   Booking booking = new Booking(Integer.parseInt(sidno_to_update), sclient_name_to_update, scontactno_to_update, sgender_to_update);
                   bookingRepository.UpdateTask(booking);
                   Toast.makeText(getApplicationContext(), "Value Updated!", Toast.LENGTH_LONG).show();
                   finish();

                }

            }
        });

       btn_delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sidno_to_delete = edt_idno.getText().toString().trim();
               sclient_name_to_delete = edt_client_name.getText().toString().trim();
               scontactno_to_delete = edt_contactno.getText().toString().trim();
               if (rbtn_male.isChecked())
               {
                   sgender_to_delete = "Male";
               }

               if (rbtn_female.isChecked())
               {
                   sgender_to_delete = "Female";
               }

               Booking booking_to_delete = new Booking(Integer.parseInt(sidno_to_delete), sclient_name_to_delete, scontactno_to_delete, sgender_to_delete);
               generate_delete_dialog(booking_to_delete);

           }
       });
    }

    //----generate alert dialog for delete operation---
    public void generate_delete_dialog(Booking booking)
    {
        final Booking client_about_to_delete = booking;
        AlertDialog.Builder builder = new AlertDialog.Builder(BookingDetailActivity.this, R.style.AppCompatAlertDialogStyle); // do not write getApplicationContext()
        builder.setTitle("Warning!");
        builder.setMessage("Are you sure you want to delete?\n"+"idno: "+client_about_to_delete.idno+"\n"+"Name: "+client_about_to_delete.client_name);
        builder.setIcon(android.R.drawable.ic_delete);

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // write delete code here---
                BookingRepository bookingRepository = new BookingRepository(getApplicationContext());
                bookingRepository.DeleteTask(client_about_to_delete);
                Toast.makeText(BookingDetailActivity.this, "data deleted", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog deleteDialog = builder.create();
        deleteDialog.show();
    }
    // end this method generation

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }
}