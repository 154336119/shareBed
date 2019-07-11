package com.slb.sharebed.weight.hellocharts.formatter;

import com.slb.sharebed.weight.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
