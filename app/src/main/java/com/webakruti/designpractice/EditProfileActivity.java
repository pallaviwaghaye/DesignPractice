package com.webakruti.designpractice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextDOB;
    private TextView textView;
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private ImageView imageViewBack;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, FloatingActionButtonActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.profileimg);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView)findViewById(R.id.imageView);
        circularImageView.setImageBitmap(circularBitmap);

        editTextDOB = (EditText)findViewById(R.id.editTextDOB);
        editTextDOB.setOnClickListener(this);
        myCalendar = Calendar.getInstance();
        /*editTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RandomDateOfBirth();
            }
        });*/
    }

    public void RandomDateOfBirth()
    {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        editTextDOB.setText(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
    }
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    /*protected Dialog onCreateDialog(int id) {
        Date d=new Date();
        Calendar cal=Calendar.getInstance();
        cal.set(2000, 1, 1, 0, 0);
        d.setTime(cal.getTimeInMillis());
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);
        switch (id) {
            case DATE_DIALOG_ID:
                // start changes...
                DatePickerDialog dialog = new DatePickerDialog(this,
                        mDateSetListener, cyear, cmonth, cday);
                dialog.getDatePicker().setMaxDate(d.getTime());
                return dialog;
            // end changes...
        }
        return null;
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.editTextDOB :
                Date d=new Date();
                Calendar cal=Calendar.getInstance();
                cal.set(2010, 11, 31, 0, 0);
                d.setTime(cal.getTimeInMillis());
                Calendar c = Calendar.getInstance();
                int cyear = c.get(Calendar.YEAR);
                int cmonth = c.get(Calendar.MONTH);
                int cday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dlg = new DatePickerDialog(EditProfileActivity.this, dateOfBirth, cyear,cmonth,cday);
                dlg.getDatePicker().setMaxDate(d.getTime());
                dlg.show();
                break;
        }
    }
    DatePickerDialog.OnDateSetListener dateOfBirth = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDOBLabel();
        }

        private void updateDOBLabel() {
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
            editTextDOB.setText(sdf.format(myCalendar.getTime()));
        }
    };
}
