package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, dob, contact;
    Button Update,Insert, Delete,View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBhandler DB;

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        Insert = findViewById(R.id.btnInsert);
        Delete = findViewById(R.id.btnDelete);
        Update= findViewById(R.id.btnUpdate);
        View=findViewById(R.id.btnView);
        DB =new DBhandler(this);

        Insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               String nameTXT = name.getText().toString();
               String contactTXT = contact.getText().toString();
               String dobTXT= dob.getText().toString();

               Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
               if(checkinsertdata==true)
                   Toast.makeText(MainActivity.this, "New Entry Inserted ", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, "New Entry not inserted" , Toast.LENGTH_SHORT).show();

            }
        });

        Update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT= dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry updated ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry not updated" , Toast.LENGTH_SHORT).show();

            }
        });


        Delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();


                Boolean checkdeletedata = DB.deleteuserdata(nameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry deleted ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, " Entry not deleted" , Toast.LENGTH_SHORT).show();

            }
        });


        View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
            }
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("Name :" + res.getString(0) + "\n");
                buffer.append("Contact :" + res.getString(1) + "\n");
                buffer.append("Date of Birth:" + res.getString(2) + "\n");
            }

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });
    }
}