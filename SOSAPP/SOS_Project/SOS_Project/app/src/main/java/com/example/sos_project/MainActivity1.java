package com.example.sos_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity1 extends AppCompatActivity {

    LineChart lineChart;
    ArrayList<JacketStatusVO> dataset1;
    Double waterpressure1 = 0.0;
    int waterpressure2 = 0;
    int watertemperature1 = 0;
    int waterdetect1 = 0;
    MemberJacketAdapter adapter;
    ArrayList<JacketStatusVO> dataset;
    RequestQueue requestQueue;
    StringRequest request;
    ListView listview;
    ArrayList<tbl_user> user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        listview = findViewById(R.id.line_chart_list);
        dataset = new ArrayList<>();
        adapter = new MemberJacketAdapter(getApplicationContext(), R.layout.member_status_list, dataset);
        requestQueue = Volley.newRequestQueue(MainActivity1.this);
        AllJacketList();

        //String pres1 = getIntent().getStringExtra("??????");
        //String temp1 = getIntent().getStringExtra("??????");
        //String dete1 = getIntent().getStringExtra("??????");
        //waterpressure1 = Double.parseDouble(pres1);
        //Log.d(waterpressure1+"" , "waterpressure");
        //watertemperature1 = Integer.parseInt(temp1);
        //waterdetect1 = Integer.parseInt(dete1);
        //= getIntent().getSerializableExtra("dataset");
        Intent receive = getIntent();
        ArrayList<JacketStatusVO> dataset1 = (ArrayList<JacketStatusVO>)receive.getSerializableExtra("dataset");
        ArrayList<JacketStatusVO> dataset2 = (ArrayList<JacketStatusVO>)receive.getSerializableExtra("user_info");
        double[] data3 = new double[830];
        double[] data4 = new double[830];
        double[] data5 = new double[830];
        int[] data33 = new int[830];
        int[] data44 = new int[830];
        int[] data55 = new int[830];
        String[] data333 = new String[830];
        String[] data444 = new String[830];
        String[] data555 = new String[830];
//        for(int i = 0; i<830;i++) {
//            data3[i] = dataset2.get(0).getWaterDetect();
//            data333[i] = String.valueOf(data3[i]);
//            data333[i] = data333[i].replace(".", "");
//            data33[i] = Integer.parseInt(data333[i]);
//        }
//        for(int i = 0; i<830;i++) {
//            data4[i] = dataset2.get(0).getWaterPressure();
//            data444[i] = String.valueOf(data4[i]);
//            data444[i] = data444[i].replace(".", "");
//            data44[i] = Integer.parseInt(data444[i]);
//        }
//        for(int i = 0; i<830;i++) {
//            data5[i] = dataset2.get(0).getWaterTemperature();
//            data555[i] = String.valueOf(data5[i]);
//            data555[i] = data555[i].replace(".", "");
//            data55[i] = Integer.parseInt(data555[i]);
//        }

        Intent user1 = getIntent();
        user_info = (ArrayList<tbl_user>) user1.getSerializableExtra("user_info");
        // ?????????
        lineChart = findViewById(R.id.line_chart);


        // 1. ??????????????? ????????? ??????
        LineDataSet lineDataSet1 = new LineDataSet(data1(data33), "Data Set1");
        LineDataSet lineDataSet2 = new LineDataSet(data2(data44), "Data Set2");
        LineDataSet lineDataSet3 = new LineDataSet(data3(data55), "Data Set3");

        // 2. ???????????? ???????????? ??????
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet3);

        // ?????? ?????????

        //?????? ??????
        lineDataSet1.setLineWidth(4);
        lineDataSet2.setLineWidth(4);
        lineDataSet3.setLineWidth(4);
        // ?????? ??????
        // bgr ?????????

        lineDataSet1.setColor(Color.BLUE);
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet3.setColor(Color.RED);

        // ????????? ??? ?????? ??????
        lineDataSet1.setDrawCircles(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet3.setDrawCircles(true);

        // ????????? ??? ?????? ??? ????????? ?????? (????????? true)
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet3.setDrawCircleHole(false);

        // ????????? ??? ??????
        lineDataSet1.setCircleColor(Color.BLUE);
        lineDataSet2.setCircleColor(Color.GREEN);
        lineDataSet3.setCircleColor(Color.RED);

        //????????? ??? ??? ??????
        lineDataSet1.setCircleHoleColor(Color.BLUE);
        lineDataSet2.setCircleHoleColor(Color.GREEN);
        lineDataSet3.setCircleHoleColor(Color.RED);

        // ????????? ??? ?????????
        lineDataSet1.setCircleRadius(10);
        lineDataSet2.setCircleRadius(10);
        lineDataSet3.setCircleRadius(10);

        // ????????? ??? ??? ?????????
        lineDataSet1.setCircleHoleRadius(5);

        // ????????? ?????? ??????
        lineDataSet1.setValueTextSize(10);
        lineDataSet2.setValueTextSize(10);
        lineDataSet3.setValueTextSize(10);

        // ????????? ?????? ??????
        lineDataSet1.setValueTextColor(Color.BLUE);
        lineDataSet2.setValueTextColor(Color.GREEN);
        lineDataSet3.setValueTextColor(Color.RED);

        // ?????? ?????? ?????????(????????????) ????????????, ????????????, ??????  ??? ???????????????
        //lineDataSet1.enableDashedLine(5, 10, 0);

        // ?????? ??? ?????? ??????
        //int colorArray1[] = {R.color.color1, R.color.color2, R.color.color3};

        //lineDataSet1.setColors(colorArray1, MainActivity1.this);

        // ?????? ?????????
        // ?????? ?????? ??????
        lineChart.setBackgroundColor(Color.WHITE);

        // ?????? ????????? ?????? ??????
        lineChart.setNoDataText("No Data");

        // ?????? ????????? ?????? ????????? ??????    // ??? ?????? ????????????
        // lineChart.setNoDataTextColor(Color.BLUE);

        // ?????? ????????? ??????
        //lineChart.setDrawGridBackground(true);

        // ?????? ????????? ?????????
        //lineChart.setDrawBorders(false);

        // ?????? ????????? ??????
        //lineChart.setBorderColor(Color.RED);

        // ?????? ????????? ??????
        // lineChart.setBorderWidth(5);

        // ??????(??????)
        Legend legend = lineChart.getLegend();

        // ?????? ??????(????????? true)
        legend.setEnabled(true);

        // ?????? ????????? ??????
        legend.setTextColor(Color.RED);

        // ?????? ????????? ?????????
        legend.setTextSize(15);

        // ?????? ????????? ??????
        legend.setForm(Legend.LegendForm.CIRCLE); // line

        //?????? ????????? ?????? ??????
        legend.setFormSize(10);

        // ?????? ??? ??????
        legend.setXEntrySpace(15);

        //?????? ???????????? ?????? ????????? ??????
        legend.setFormToTextSpace(10);

        // ????????? ?????? ????????? ??????
        LegendEntry[] legendEntries = new LegendEntry[3];

        // ??????
        int[] colorArray = {Color.BLUE, Color.GREEN, Color.RED};

        // ?????????
        String[] legendName = {"??????", "??????", "??????"};

        for(int i = 0; i<legendEntries.length;i++) {

            LegendEntry entry = new LegendEntry();

            // ??????
            entry.formColor = colorArray[i];

            // ?????????
            entry.label = legendName[i];

            // ??????
            legendEntries[i] = entry;
        }

        // ??????
        legend.setCustom(legendEntries);


        // ??????
        Description description = new Description();
        description.setText("??????"); // ??????
        description.setTextSize(20); // ?????? ????????? ??????
        description.setTextColor(Color.BLUE);

        //lineChart.setDescription(description);


        // 3. ?????????????????? ????????? ??????
        LineData data = new LineData(dataSets);

        // 4. ????????? ??????????????? ??????
        lineChart.setData(data);

        // 5. ?????? ?????????
        lineChart.invalidate();
    } // onCreate

    private ArrayList<Entry> data1(int[] data33) {

        ArrayList<Entry> dataList = new ArrayList<>();
//        for(int i =0; i<data33.length;i++) {
//            dataList.add(new Entry(i, data33[i]));
//        }
        dataList.add(new Entry(0, 0));
        dataList.add(new Entry(1, 10));
        dataList.add(new Entry(2, 20));
        dataList.add(new Entry(3, 40));
        dataList.add(new Entry(4, 30));
        dataList.add(new Entry(5, 50));
        dataList.add(new Entry(6, 70));
        dataList.add(new Entry(7, 20));
        dataList.add(new Entry(8, 40));
        dataList.add(new Entry(9, 60));
        dataList.add(new Entry(10, 55));
        dataList.add(new Entry(11, 45));
        dataList.add(new Entry(12, 30));
        dataList.add(new Entry(13, 40));
        dataList.add(new Entry(14, 30));
        dataList.add(new Entry(15, 40));
        return dataList;
    }

    private ArrayList<Entry> data2(int[] data44) {

        ArrayList<Entry> dataList = new ArrayList<>();

        dataList.add(new Entry(0, 0));
        dataList.add(new Entry(1, 40));
        dataList.add(new Entry(2, 50));
        dataList.add(new Entry(3, 60));
        dataList.add(new Entry(4, 70));
        dataList.add(new Entry(5, 90));
        dataList.add(new Entry(6, 110));
        dataList.add(new Entry(7, 100));
        dataList.add(new Entry(8, 90));
        dataList.add(new Entry(9, 110));
        dataList.add(new Entry(10, 70));
        dataList.add(new Entry(11, 90));
        dataList.add(new Entry(12, 80));
        dataList.add(new Entry(13, 50));
        dataList.add(new Entry(14, 60));
        dataList.add(new Entry(15, 70));

        return dataList;
    }

    private ArrayList<Entry> data3(int[] data55) {

        ArrayList<Entry> dataList = new ArrayList<>();


        dataList.add(new Entry(0, 0));
        dataList.add(new Entry(1, -1));
        dataList.add(new Entry(2, -8));
        dataList.add(new Entry(3, -5));
        dataList.add(new Entry(4, -4));
        dataList.add(new Entry(5, -3));
        dataList.add(new Entry(6, -7));
        dataList.add(new Entry(7, -9));
        dataList.add(new Entry(8, -5));
        dataList.add(new Entry(9, 0));
        dataList.add(new Entry(10, 5));
        dataList.add(new Entry(11, 3));
        dataList.add(new Entry(12, 2));
        dataList.add(new Entry(13, 4));
        dataList.add(new Entry(14, 5));
        dataList.add(new Entry(15, 7));

        return dataList;
    }
    public void AllJacketList() {
        Intent receive = getIntent();
        ArrayList<JacketStatusVO> jacket = (ArrayList<JacketStatusVO>) receive.getSerializableExtra("jacketdetailinfo");
        //Log.d("jacket", jacket.get(0).getBatteryStatus());
        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/getchartInfo.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("jacketres", response.toString());
                            JSONObject json = new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("jacketdetailinfo");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject jacket = (JSONObject) list.get(i);
                                String batteryStatus = jacket.getString("batteryStatus");
                                String connectStatus = jacket.getString("connectStatus");
                                //String product_id = jacket.getString("product_id");
                                Double waterPressure = Double.parseDouble(jacket.getString("water_pressure"));
                                Double waterTemperature = Double.parseDouble(jacket.getString("water_temperature"));
                                Double waterDetect = Double.parseDouble(jacket.getString("water_detect"));
                                dataset.add(new JacketStatusVO(batteryStatus, connectStatus, waterPressure, waterTemperature, waterDetect));

                            }
                            listview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_info.get(0).getUser_id());
                return params;
            }
        };
        requestQueue.add(request);
    }
}// mainactivity