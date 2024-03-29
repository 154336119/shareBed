package com.slb.sharebed.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {
   
    private boolean haveScrollbar = false;   
   
    public MyListView(Context context) {
        super(context);   
    }   
   
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);   
    }   
   
    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);   
    }   
   
    /**  
     * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true  
     *   
     */
    public void setHaveScrollbar(boolean haveScrollbar) {   
        this.haveScrollbar = haveScrollbar;   
    }   
   
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
        if (haveScrollbar == false) {   
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);   
        } else {   
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);   
        }   
    }   
}   