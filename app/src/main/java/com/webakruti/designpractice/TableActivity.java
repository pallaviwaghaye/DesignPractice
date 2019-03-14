package com.webakruti.designpractice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageViewBack;
    private Toolbar toolbar;
    private Spinner spinnerExam;

    String subject[] = {"Physics", "Maths", "English", "Science", "Biology",
            "Physics", "Maths", "English", "Science", "Biology",
            "Physics", "Maths", "English", "Science", "Biology"};
    String marks[] = {"90", "76", "83", "98", "79",
            "90", "76", "83", "98", "79",
            "90", "76", "83", "98", "79"};

    String grade[] = {"A+", "B+", "C+", "A", "B",
            "A+", "B+", "C+", "A", "B",
            "A+", "B+", "C+", "A", "B"};

    String comment[] = {"Good", "Better", "Best", "Excellent", "Great",
            "Good", "Better", "Best", "Excellent", "Great",
            "Good", "Better", "Best", "Excellent", "Great"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);

        spinnerExam = (Spinner)findViewById(R.id.spinnerExam);
        String[] list1 = getResources().getStringArray(R.array.exams);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list1);
        spinnerExam.setAdapter(adapter1);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addHeaders();
        addData();
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }

    @NonNull
    private LayoutParams getLayoutParams() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * This function add the headers to the table
     **/
    public void addHeaders() {
        TableLayout tl = findViewById(R.id.table);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Subject", Color.WHITE, Typeface.BOLD, Color.GRAY));
        tr.addView(getTextView(0, "Marks", Color.WHITE, Typeface.BOLD, Color.GRAY));
        tr.addView(getTextView(0,"Grade",Color.WHITE,Typeface.BOLD,Color.GRAY));
        tr.addView(getTextView(0,"Comment",Color.WHITE,Typeface.BOLD,Color.GRAY));
        tl.addView(tr, getTblLayoutParams());
    }

    /**
     * This function add the data to the table
     **/
    public void addData() {
        int numSubjects = subject.length;
        TableLayout tl = findViewById(R.id.table);
        for (int i = 0; i < numSubjects; i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getTextView(i + 1, subject[i], Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.faint_blue)));
            tr.addView(getTextView(i + numSubjects, marks[i], Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.gray)));
            tr.addView(getTextView(i + numSubjects + 1, grade[i], Color.BLACK,Typeface.NORMAL, ContextCompat.getColor(this,R.color.faint_blue)));
            tr.addView(getTextView(i + numSubjects + 2, comment[i], Color.BLACK,Typeface.NORMAL, ContextCompat.getColor(this,R.color.gray)));
            tl.addView(tr, getTblLayoutParams());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        TextView tv = findViewById(id);
        if (null != tv) {
            Log.i("onClick", "Clicked on row :: " + id);
            Toast.makeText(this, "Clicked on row :: " + id + ", Text :: " + tv.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
