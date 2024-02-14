package com.example.databasetablelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextRegNo, editTextName, editTextCourse, editTextBranch, editTextSem, editTextSection;
    private Button buttonAddContact,buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextRegNo = findViewById(R.id.Regno);
        editTextName = findViewById(R.id.name);
        editTextCourse = findViewById(R.id.course);
        editTextBranch = findViewById(R.id.branch);
        editTextSem = findViewById(R.id.sem);
        editTextSection = findViewById(R.id.section);

        buttonAddContact = findViewById(R.id.signupbtn);
        buttonView = findViewById(R.id.view);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = editTextRegNo.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String course = editTextCourse.getText().toString().trim();
                String branch = editTextBranch.getText().toString().trim();
                String sem = editTextSem.getText().toString().trim();
                String section = editTextSection.getText().toString().trim();

                if (name.isEmpty() || reg.isEmpty() || course.isEmpty() || branch.isEmpty() || sem.isEmpty() || section.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    MyDBHelper dbHelper = new MyDBHelper(MainActivity.this);
                    dbHelper.addContact(reg, name, course, branch, sem, section);
                    Toast.makeText(MainActivity.this, "Contact added successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to FetchDataActivity
                Intent intent = new Intent(MainActivity.this, fatchdata.class);
                startActivity(intent);
            }
        });
    }

    private void clearFields() {
        editTextRegNo.setText("");
        editTextName.setText("");
        editTextCourse.setText("");
        editTextBranch.setText("");
        editTextSem.setText("");
        editTextSection.setText("");
    }

}
