package com.ning.myapp.activitys;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ning.myapp.R;
import com.ning.myapp.entitys.Blog;
import com.ning.myapp.fragments.BlogViewFragment;
import com.ning.myapp.fragments.EditBlogFragment;
import com.ning.myapp.utils.AppController;
import com.ning.myapp.utils.Constants;
import com.ning.myapp.utils.ToastUtil;
import com.ning.myapp.utils.Utils;

public class BlogEditActivity extends Activity implements OnClickListener {


	private static final String TAG = "BlogEditActivity";
	private ImageButton catebutton;
	private TextView textTitle;
	private TextView textContent;
	private Blog blog;
	private Gson gson = new Gson();
	private static int requestCode = 0;
	private Long blogId = 0L;
	private Intent intent = null;
	private String mActon = null;

	private String tag_json_obj = "json_obj_req";
	private String tag_string_req = "tag_string_req";
	private Fragment blogfragment;
//	private BlogViewFragment blogviewfragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editblogcontainer);
		handleIntent();

		if (mActon.equals(Constants.Action.BLOGVIEW)) {
			setUpFragment(R.id.blogviewId);
		} else if (mActon.equals(Constants.Action.BLOGEDIT)) {
			setUpFragment(R.id.editblogId);
		}

	}

	public void handleIntent() {
		intent = getIntent();
		mActon = intent.getAction();
	}

	public void setUpFragment(int fragmentId) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		switch (fragmentId) {
		case R.id.editblogId :
			blogfragment= (EditBlogFragment) fragmentManager.findFragmentById(R.id.editblogId);
			if(blogfragment==null){
				blogfragment = new EditBlogFragment();
			}
			break;
		case R.id.blogviewId:
			blogfragment= (BlogViewFragment) fragmentManager.findFragmentById(R.id.blogviewId);
			if(blogfragment==null){
				blogfragment = new BlogViewFragment();
			}
			break;
		default:
			break;
		}
		fragmentTransaction.replace(R.id.editblogcontainer, blogfragment);
		//fragmentTransaction.add(R.id.editblogcontainer, editfragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blogedit, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.imb_cate:
			Intent intent = new Intent();
			intent.setClass(this, NotesActivity.class);
			intent.setAction(Constants.Action.SELECTNOTE);
			requestCode = 0;
			startActivityForResult(intent, requestCode);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_save:
			break;
		case R.id.action_clear:
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

}
