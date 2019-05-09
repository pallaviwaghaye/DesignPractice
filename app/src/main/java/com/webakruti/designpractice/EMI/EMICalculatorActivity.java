package com.webakruti.designpractice.EMI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.webakruti.designpractice.R;

public class EMICalculatorActivity extends AppCompatActivity {

    Button emiCalcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emicalculator);

        final EditText loanAmount = (EditText)findViewById(R.id.loanAmount); //principal
        final EditText I = (EditText)findViewById(R.id.interest);
        final EditText Y = (EditText)findViewById(R.id.years);
        final EditText TI = (EditText)findViewById(R.id.interest_total);
        final EditText principal = (EditText)findViewById(R.id.principal);
        final EditText TP = (EditText)findViewById(R.id.editTextTotalPayment);
        final EditText result = (EditText)findViewById(R.id.emi) ;


        emiCalcBtn = (Button) findViewById(R.id.btn_calculate2);

        emiCalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st1 = loanAmount.getText().toString();
                String st2 = I.getText().toString();
                String st3 = Y.getText().toString();

                principal.setText(st1);

                if (TextUtils.isEmpty(st1)) {
                    loanAmount.setError("Enter Prncipal Amount");
                    loanAmount.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st2)) {
                    I.setError("Enter Interest Rate");
                    I.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st3)) {
                    Y.setError("Enter Years");
                    Y.requestFocus();
                    return;
                }

                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);

                float Principal = calPric(p);

                float Rate = calInt(i);

                float Months = calMonth(y);

                float Dvdnt = calDvdnt( Rate, Months);

                float FD = calFinalDvdnt (Principal, Rate, Dvdnt);

                float D = calDivider(Dvdnt);

                float emi = calEmi(FD, D);

                float TA = calTa (emi, Months);

                float ti = calTotalInt(TA, Principal);


                result.setText(String.valueOf(emi));

                TI.setText(String.valueOf(ti));

                float totalPayment = addition(Principal,ti);
                TP.setText(String.valueOf(totalPayment));


            }
        });
    }

    public float addition(float principal, float interest) {
        /*float total = principal + emi;
        TP.setText(String.valueOf(answer));*/
        return (float) principal + interest;

    }

    public  float calPric(float p) {
        return (float) (p);
    }

    public  float calInt(float i) {
        return (float) (i/12/100);
    }

    public  float calMonth(float y) {
        return (float) (y * 12);
    }

    public  float calDvdnt(float Rate, float Months) {
        return (float) (Math.pow(1+Rate, Months));
    }

    public  float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {
        return (float) (Principal * Rate * Dvdnt);
    }

    public  float calDivider(float Dvdnt) {
        return (float) (Dvdnt-1);
    }

    public  float calEmi(float FD, Float D) {
        return (float) (FD/D);
    }

    public  float calTa(float emi, Float Months) {
        return (float) (emi*Months);
    }

    public  float calTotalInt(float TA, float Principal) {
        return (float) (TA - Principal);
    }
}
