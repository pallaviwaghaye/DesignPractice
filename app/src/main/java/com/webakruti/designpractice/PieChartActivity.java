package com.webakruti.designpractice;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        PieChart pieChart = findViewById(R.id.piechart);

        //y axis
        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new Entry(945f, 0));
        NoOfEmp.add(new Entry(1040f, 1));
        NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(1695f, 9));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");

        //x axis
        ArrayList year = new ArrayList();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        PieData data = new PieData(year, dataSet);
       // data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(false);
        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        final int[] MY_COLORS = {Color.rgb(192,0,0),
                Color.rgb(220,0,100),
                Color.rgb(127,127,127),
                Color.rgb(255,192,0),
                Color.rgb(146,208,80),
                Color.rgb(0,176,80),
                Color.rgb(20,129,0),
                Color.rgb(100,129,0),
                Color.rgb(0,60,255),
                Color.rgb(127,129,189),
               };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c: MY_COLORS) colors.add(c);
        dataSet.setColors(colors);

        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);


        pieChart.animateXY(3000, 3000);

        //pieChart.setRotationEnabled(false);
        //pieChart.setTouchEnabled(false);

        pieChart.setOnChartValueSelectedListener(this);

        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(2f);
        l.setYEntrySpace(2f);
        l.setFormSize(5f);
        l.setTextSize(0.5f);
        pieChart.getLegend().setEnabled(true);

        pieChart.setDescription("Pie Chart");
        //pieChart.getDescription().setEnabled(false);
        
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Toast.makeText(this, "Value: " + e.getVal() , Toast.LENGTH_SHORT).show();

        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Toast.makeText(this, "nothing selected", Toast.LENGTH_SHORT).show();
    }
}
