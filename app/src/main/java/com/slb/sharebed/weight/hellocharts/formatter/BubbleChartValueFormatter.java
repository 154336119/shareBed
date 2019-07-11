package com.slb.sharebed.weight.hellocharts.formatter;

import com.slb.sharebed.weight.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
