package com.ning.myapp.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;  

public class NumPickerDialog extends AlertDialog  implements OnClickListener{  
	
	private NumPicker mNumPicker;  
	private OnNumSetListener mOnNumSetListener; 
	
	 public NumPickerDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		mNumPicker = new NumPicker(context);  
	        setView(mNumPicker);  
	        /* 
	         *实现接口，实现里面的方法 
	         */  
	       
	        setButton("设置", this);  
	        setButton2("取消", (OnClickListener) null);  
		
		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
	/* 
     *接口回調   
     *控件 秒数 
     */  
    public interface OnNumSetListener {  
        void OnValueTimeSet(AlertDialog dialog, long date);  
    }  
    
    /* 
     * 对外公开方法让Activity实现 
     */  
    public void setOnValueSetListener(OnNumSetListener callBack) {  
    	mOnNumSetListener = callBack;  
    }  


	

}  
