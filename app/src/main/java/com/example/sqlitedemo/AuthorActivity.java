package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText edtID, edtName, edtAddress, edtEmail;
    Button btnSave, btnSelect;
    GridView gridView_display;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        btnSave = findViewById(R.id.btnSave);
        btnSelect = findViewById(R.id.btnSelect);
        gridView_display = findViewById(R.id.gridview_display);
        dbHelper = new DataBaseHelper(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setId_Author(Integer.parseInt(edtID.getText().toString()));
                author.setName(edtName.getText().toString());
                author.setAddress(edtAddress.getText().toString());
                author.setEmail(edtEmail.getText().toString());
                if (dbHelper.insertAuthor(author) > 0){
                    Toast.makeText(getApplicationContext(), "Đã lưu thành công", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Lưu không thành công", Toast.LENGTH_LONG).show();
                }
                edtID.setText("");
                edtEmail.setText("");
                edtAddress.setText("");
                edtName.setText("");
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Author> listAuthor = new ArrayList<>();
                ArrayList<String> listString = new ArrayList<>();
               if (edtID.getText().toString().equals("")){
                    listAuthor = dbHelper.getAllAuthor();
                } else {
                   listAuthor = dbHelper.getIDAuthor(Integer.parseInt(edtID.getText().toString()));
               }
                for (Author author: listAuthor){
                    listString.add(author.getId_Author()+"");
                    listString.add(author.getName());
                    listString.add(author.getAddress());
                    listString.add(author.getEmail());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthorActivity.this, android.R.layout.simple_list_item_1, listString);
                gridView_display.setAdapter(adapter);
            }
        });
    }
}