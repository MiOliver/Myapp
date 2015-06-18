package com.ning.myapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ning.myapp.R;

public class BlogViewFragment extends Fragment {
	
	private TextView textTitle =null;
	private TextView textContent =null;
	private Intent intent=null;
	private String title="";
	private String content="";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 handleIntent();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.blogview, container, false);
		textTitle=(TextView)view.findViewById(R.id.blogview_titleId);
		textContent=(TextView)view.findViewById(R.id.blogview_contentId);
		textTitle.setText(title);
		textContent.setText(content);
		return view;
	}
	
	public void handleIntent(){
		intent=getActivity().getIntent();
		Bundle bundle =intent.getExtras();
		title=bundle.getString("blogTitle", "title");
		content=bundle.getString("blogContent", "content");
	}

}
