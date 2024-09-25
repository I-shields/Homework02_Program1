package com.example.homework02_program1;

public class ColorInfo
{
    private int redValue;
    private int greenValue;
    private int blueValue;
    private String hexValue;

    public ColorInfo()
    {

    }

    public  ColorInfo(int rv, int gv, int bv, String hv)
    {
        redValue = rv;
        greenValue = gv;
        blueValue = bv;
        hexValue = hv;

    }

    //====================================
    //  GETTERS
    //====================================

    public int getRedValue() {
        return redValue;
    }

    public int getGreenValue() {
        return greenValue;
    }

    public int getBlueValue() {
        return blueValue;
    }

    public String getHexValue() {
        return hexValue;
    }


    //====================================
    //  SETTERS
    //====================================


    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    public void setGreenValue(int greenValue) {
        this.greenValue = greenValue;
    }

    public void setBlueValue(int blueValue) {
        this.blueValue = blueValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }
}
