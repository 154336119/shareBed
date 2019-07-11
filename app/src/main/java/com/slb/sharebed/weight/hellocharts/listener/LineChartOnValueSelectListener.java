package com.slb.sharebed.weight.hellocharts.listener;


import com.slb.sharebed.weight.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
