package com.slb.sharebed.weight.hellocharts.formatter;

import com.slb.sharebed.weight.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
