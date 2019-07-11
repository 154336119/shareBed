package com.slb.sharebed.weight.hellocharts.listener;


import com.slb.sharebed.weight.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int arcIndex, SliceValue value);

}
