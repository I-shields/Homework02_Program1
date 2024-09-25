package com.example.homework02_program1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{

    Context context;
    ArrayList<ColorInfo> listOfColors;
    public ColorListAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColors = ls;
    }

    @Override
    public int getCount() {
        return listOfColors.size();
    }

    @Override
    public ColorInfo getItem(int i) {
        return listOfColors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        TextView red = view.findViewById(R.id.cc_tv_redInfo);
        TextView green = view.findViewById(R.id.cc_tv_greenInfo);
        TextView blue = view.findViewById(R.id.cc_tv_blueInfo);
        TextView hexValue = view.findViewById(R.id.cc_tv_hexValue);

        ColorInfo ci = listOfColors.get(i);
        red.setText("Red:   " + String.valueOf(ci.getRedValue()));
        green.setText("Green:   " + String.valueOf(ci.getGreenValue()));
        blue.setText("Blue:     " + String.valueOf(ci.getBlueValue()));
        hexValue.setText("Hex value:    " + ci.getHexValue());

        if(colorBrightness(ci.getRedValue(), ci.getGreenValue(), ci.getBlueValue()))
        {
            red.setTextColor(Color.WHITE);
            green.setTextColor(Color.WHITE);
            blue.setTextColor(Color.WHITE);
            hexValue.setTextColor(Color.WHITE);
        }

        view.setBackgroundColor(Color.parseColor(ci.getHexValue()));

        return view;
    }

    private boolean colorBrightness(int r, int g, int b)
    {
        double brightness = (0.299 * r) + (0.587 * g) + (0.114 * b);

        return brightness < 128;
    }
}
