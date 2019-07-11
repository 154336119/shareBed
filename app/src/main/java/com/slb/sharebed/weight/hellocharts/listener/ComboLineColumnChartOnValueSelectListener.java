package com.slb.sharebed.weight.hellocharts.listener;


import com.slb.sharebed.weight.hellocharts.model.PointValue;
import com.slb.sharebed.weight.hellocharts.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);

}
