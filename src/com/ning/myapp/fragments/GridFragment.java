package com.ning.myapp.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import com.ning.myapp.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GridFragment extends Fragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView(this.getView());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View newsView=inflater.inflate(R.layout.grid_main_layout  ,container, false);
		return newsView;
	}
	
	private void initView(View view){
		GridView gridView=(GridView)view.findViewById(R.id.gridView);
		inintData(gridView);
	}
	
	private void inintData(GridView view) {
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 9; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.card);// 添加图像资源的ID
			map.put("ItemText", "NO." + String.valueOf(i));// 按序号做ItemText
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this.getActivity(), //没什么解释  
                lstImageItem,//数据来源   
                R.layout.grid_item, //night_item的XML实现  
                //动态数组与ImageItem对应的子项          
                new String[] {"ItemImage","ItemText"},   
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
                new int[] {R.id.ItemImage,R.id.ItemText});  
			    //添加并且显示  
				view.setAdapter(saImageItems);  
				//添加消息处理  
				view.setOnItemClickListener(new GridItemClickListener());  
	}  
	
	class GridItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> adapterview, View view, int position,long l) {
			// TODO Auto-generated method stub
			 HashMap<String, Object> item=(HashMap<String, Object>) adapterview.getItemAtPosition(position);  
			    //显示所选Item的ItemText  
			   Toast.makeText(getActivity(), item.get("ItemText").toString(),Toast.LENGTH_SHORT).show();
		}
		
	}

}
