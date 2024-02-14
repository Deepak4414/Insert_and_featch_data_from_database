package com.example.databasetablelayout;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.databasetablelayout.MyDBHelper;

public class fatchdata extends AppCompatActivity {

    private MyDBHelper dbHelper;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatchdata);

        dbHelper = new MyDBHelper(this);
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Fetch data from the database
        Cursor cursor = dbHelper.getAllContacts();

        // Create table header row
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {MyDBHelper.KEY_REGNO, MyDBHelper.KEY_NAME, MyDBHelper.KEY_COURSE,
                MyDBHelper.KEY_BRANCH, MyDBHelper.KEY_SEM, MyDBHelper.KEY_SECTION};

        for (String header : headers) {
            TextView headerTextView = createTextView(header, true);
            headerRow.addView(headerTextView);
        }

        tableLayout.addView(headerRow);

        // Iterate through the cursor and add rows to the table
        if (cursor != null) {
            while (cursor.moveToNext()) {
                TableRow dataRow = new TableRow(this);
                dataRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                for (String header : headers) {
                    TextView dataTextView = createTextView(cursor.getString(cursor.getColumnIndex(header)), false);
                    dataRow.addView(dataTextView);
                }

                tableLayout.addView(dataRow);
            }
            cursor.close();
        }
    }

    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(30, 30, 30, 30);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(isHeader ? android.R.color.black : android.R.color.darker_gray));
        return textView;
    }
}
