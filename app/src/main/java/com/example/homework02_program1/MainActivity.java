//============================================================
// Author: Isaac Shields
// Date  : 9-25-2024
// Desc  : Program that changes the app's background
//         based on the RGB values you select with the sliders
//============================================================



package com.example.homework02_program1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.FileObserver;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;



//1: get the variables set up
public class MainActivity extends AppCompatActivity {

    SeekBar sb_ma_red;
    SeekBar sb_ma_green;
    SeekBar sb_ma_blue;
    TextView tv_ma_redNum;
    TextView tv_ma_greenNum;
    TextView tv_ma_blueNum;
    TextView tv_ma_hexNum;
    View mainLayout;
    Button saveBtn;
    ListView ma_lv_colorList;

    ArrayList<ColorInfo> listOfColors;
    ColorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listOfColors = new ArrayList<ColorInfo>();

        sb_ma_red = findViewById(R.id.sb_am_red);
        sb_ma_green = findViewById(R.id.sb_am_green);
        sb_ma_blue = findViewById(R.id.sb_am_blue);
        ma_lv_colorList = findViewById(R.id.am_lv_colorList);
        tv_ma_redNum = findViewById(R.id.am_tv_redNum);
        tv_ma_greenNum = findViewById(R.id.am_tv_greenNum);
        tv_ma_blueNum = findViewById(R.id.am_tv_blueNum);
        tv_ma_hexNum = findViewById(R.id.am_tv_hexNum);
        mainLayout = findViewById(R.id.main);
        saveBtn = findViewById(R.id.btn_am_saveColor);

        updateValue();
        fillList();
        clickListItem();

        //2: Setup listeners for the seekbars
        sb_ma_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateValue();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_ma_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateValue();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_ma_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateValue();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //3: setup save button listener
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addColorToList();
                resetBackground();
                adapter.notifyDataSetChanged();
            }
        });

    }


    //4: Listener for changing color to saved color
    private void clickListItem()
    {
        ma_lv_colorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                ColorInfo obj = adapter.getItem(i);

                fillInfoFromList(obj.getRedValue(), obj.getGreenValue(), obj.getBlueValue());

            }
        });
    }

    //5: Update values when needed
    private void updateValue()
    {
        tv_ma_redNum.setText(String.valueOf(sb_ma_red.getProgress()));
        tv_ma_greenNum.setText(String.valueOf(sb_ma_green.getProgress()));
        tv_ma_blueNum.setText(String.valueOf(sb_ma_blue.getProgress()));
        String hexNum = String.format("#%02X%02X%02X", sb_ma_red.getProgress(), sb_ma_green.getProgress(), sb_ma_blue.getProgress());
        tv_ma_hexNum.setText(hexNum);
        changeBackColor(hexNum);
    }


    //6: change color to the RGB value provided
    private void fillInfoFromList(int r, int g, int b)
    {
        sb_ma_red.setProgress(r);
        sb_ma_green.setProgress(g);
        sb_ma_blue.setProgress(b);
        updateValue();
    }

    //7: Change color based on Hex value
    private void changeBackColor(String hexValue)
    {
        mainLayout.setBackgroundColor(Color.parseColor(hexValue));
    }

    //8: reset background color to white when a color is saved
    private void resetBackground()
    {
        sb_ma_red.setProgress(sb_ma_red.getMax());
        sb_ma_green.setProgress(sb_ma_green.getMax());
        sb_ma_blue.setProgress(sb_ma_blue.getMax());
        updateValue();
    }


    //9: add color to list
    private void addColorToList()
    {
        //String.format("#%02X%02X%02X") found online
        String hexVal = String.format("#%02X%02X%02X", sb_ma_red.getProgress(), sb_ma_green.getProgress(), sb_ma_blue.getProgress());
        ColorInfo ci = new ColorInfo(sb_ma_red.getProgress(), sb_ma_green.getProgress(), sb_ma_blue.getProgress(), hexVal);
        listOfColors.add(ci);
        adapter.notifyDataSetChanged();
    }

    //10: Fill list with saved colors
    private void fillList()
    {
        adapter = new ColorListAdapter(this, listOfColors);
        ma_lv_colorList.setAdapter(adapter);

    }

}