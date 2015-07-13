package com.ning.myapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ning.myapp.R;
import com.ning.myapp.activitys.BlogEditActivity;
import com.ning.myapp.utils.Constants;
import com.ning.myapp.utils.Utils;

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
		setHasOptionsMenu(true);
		handleIntent();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
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
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.blogviewfragmenu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		switch (id) {
		case R.id.blog_edit:
			System.out.println("blog edit !");
			Intent mIntent= new Intent();
			mIntent.setAction(Constants.Action.BLOGEDIT);
			mIntent.setClass(getActivity(),BlogEditActivity.class);
			mIntent.putExtra("blogTitle", textTitle.getText().toString());
			mIntent.putExtra("blogContent", textContent.getText().toString());
			startActivity(mIntent);
			break;
		case R.id.action_clear:
			break;
		default:
			break;
		}
		return true;
		
	}

}
