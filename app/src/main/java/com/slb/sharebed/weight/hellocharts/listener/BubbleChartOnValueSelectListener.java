package com.slb.sharebed.weight.hellocharts.listener;


import com.slb.sharebed.weight.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int bubbleIndex, BubbleValue value);

}
