package com.ning.myapp.fragments;

import com.ning.myapp.R;
import com.ning.myapp.widgets.NumPickerDialog;
import com.ning.myapp.widgets.NumPickerDialog.OnNumSetListener;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Toast;

public class WidgetTestFragment extends Fragment implements Formatter, OnValueChangeListener,OnScrollListener,OnClickListener {
	
	private NumberPicker mNumberPicker;
	private Button mbutton;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.widgettest  ,container, false);
		init(view);
		return view;
	}
	
	private void init(View view) {  
        mNumberPicker = (NumberPicker) view.findViewById(R.id.show_num_picker);  
        mbutton =(Button)view.findViewById(R.id.button);
        mbutton.setOnClickListener(this);
        mNumberPicker.setFormatter(this);  
        mNumberPicker.setOnValueChangedListener(this);  
        mNumberPicker.setOnScrollListener(this);  
        mNumberPicker.setMaxValue(23);  
        mNumberPicker.setMinValue(0);  
        mNumberPicker.setValue(10);  
    }

	@Override
	public void onScrollStateChange(NumberPicker view, int scrollState) {
		// TODO Auto-generated method stub
		
		 switch (scrollState) {  
	        case OnScrollListener.SCROLL_STATE_FLING:  
	            Toast.makeText(this.getActivity(), "scroll state fling", Toast.LENGTH_LONG).show();  
	            break;  
	        case OnScrollListener.SCROLL_STATE_IDLE:  
	            Toast.makeText(this.getActivity(), "scroll state idle", Toast.LENGTH_LONG).show();  
	            break;  
	        case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:  
	            Toast.makeText(this.getActivity(), "scroll state touch scroll", Toast.LENGTH_LONG).show();  
	            break;  
	        }  
	  
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
		 Log.i("tag", "oldValue:" + oldVal + "   ; newValue: " + newVal);  
	     Toast.makeText(this.getActivity(),  "number changed --> oldValue: " + oldVal + " ; newValue: "  + newVal, Toast.LENGTH_SHORT).show();  
		
	}

	@Override
	public String format(int arg0) {
		// TODO Auto-generated method stub
		
		String tmpStr = String.valueOf(arg0);  
        if (arg0 < 10) {  
            tmpStr = "0" + tmpStr;  
        }  
        return tmpStr; 
	}  
	
	 public void showDialog() {  
		 NumPickerDialog dialog = new NumPickerDialog(this.getActivity());  

	        dialog.setOnValueSetListener(new OnNumSetListener() {  
				@Override
				public void OnValueTimeSet(AlertDialog dialog, long date) {
					// TODO Auto-generated method stub
					
				}  
	        });  
	        dialog.show();  
	 }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view.getId()==R.id.button){
			showDialog();
		}
	}

}
