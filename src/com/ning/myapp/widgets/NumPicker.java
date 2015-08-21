package com.ning.myapp.widgets;

import com.ning.myapp.R;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;  

public class NumPicker extends FrameLayout {
	
	private NumberPicker mNumberpicker;
	private OnNumChangedListener mOnNumChangedListener;  

	public NumPicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		inflate(context, R.layout.numberpicker_dialog, this); 
		
		mNumberpicker = (NumberPicker) this.findViewById(R.id.np_num);  
		mNumberpicker.setMinValue(0);  
		mNumberpicker.setMaxValue(6);  
        mNumberpicker.setOnValueChangedListener(mOnValueChangedListener);  
        
	}
	
	  /** 
     *  
     * 控件监听器 
     */  
    private NumberPicker.OnValueChangeListener mOnValueChangedListener = new OnValueChangeListener() {  
        @Override  
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {  
            
        }  
    };  
    
    /**
    *接口回调 参数是当前的View 年月日小时分钟 
    */  
   public interface OnNumChangedListener {  
       void onNumChanged(NumPicker view, int year, int month,  
               int day, int hour, int minute);  
   }  
	
    
    public void setOnNumChangedListener(OnNumChangedListener callback) {  
        mOnNumChangedListener = callback;  
    }  
 
	
	

}
