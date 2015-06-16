package com.ning.myapp.activitys;

import org.json.JSONObject;

import android.app.Activity;
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
	private Long blogId=0L;

	private String tag_json_obj = "json_obj_req";
	private String tag_string_req = "tag_string_req";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editblogcontainer);
//		textTitle = (TextView) findViewById(R.id.ed_blog_title);
//		textContent = (TextView) findViewById(R.id.ed_blog_content);
//		catebutton = (ImageButton) findViewById(R.id.imb_cate);
//		catebutton.setOnClickListener(this);
		setUpFragment();
	}
	public void setUpFragment(){
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		EditBlogFragment editfragment = new EditBlogFragment();
		fragmentTransaction.add(R.id.editblogcontainer, editfragment);
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
			saveBlog();
			break;
		case R.id.action_clear:
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	protected void saveBlog() {
		String title = textTitle.getText().toString();
		String content = textContent.getText().toString();
		String userId = Utils.Preference.getStringPref(getApplicationContext(),Constants.Preference.USERID, "");
		blog = new Blog();
		blog.setBlogid(0L);
		blog.setBlogCategoryId(blogId);
		blog.setUserId(userId);
		blog.setBlogTitle(title);
		blog.setContent(content);
		blog.setImageUrl("image");
		blog.setPublic(0);
		blog.setCreatedTime("2015-05-05 12:00:00");
		blog.setTags("tags");

		String jblog = gson.toJson(blog);
		System.out.println(jblog);
		save(Constants.Url.Bloginfo.CREATEBLOG, jblog);
	}

	private void save(String url, String requestBody) {
		JsonObjectRequest jsonReq = new JsonObjectRequest(Method.POST, url,
				requestBody, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						String res = response.toString();
						Log.d(TAG, res);
						int num = Integer.parseInt( res.substring(res.length() - 2, res.length()-1) );
						if (num > 0) {
							ToastUtil.show(BlogEditActivity.this,R.string.add_blog_success);
							BlogEditActivity.this.finish();
						} else {
							ToastUtil.show(BlogEditActivity.this,R.string.add_blog_fail);
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonReq, tag_string_req);
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		switch (requestCode) {
		case 0:
			blogId = intent.getLongExtra("blogcategoryId",0L);  
			System.out.println(blogId);
			break;
		case 2:
			
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
