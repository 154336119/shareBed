package com.slb.sharebed.weight.hellocharts.formatter;


import com.slb.sharebed.weight.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
