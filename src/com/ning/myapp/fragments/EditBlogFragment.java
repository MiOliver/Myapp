package com.ning.myapp.fragments;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ning.myapp.R;
import com.ning.myapp.activitys.BlogEditActivity;
import com.ning.myapp.activitys.NotesActivity;
import com.ning.myapp.entitys.Blog;
import com.ning.myapp.utils.AppController;
import com.ning.myapp.utils.Constants;
import com.ning.myapp.utils.ToastUtil;
import com.ning.myapp.utils.Utils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditBlogFragment extends Fragment implements OnClickListener{
	private static final String TAG="EditBlogFragment";
	
	private ImageButton catebutton;
	private TextView textTitle;
	private TextView textContent;
	private Blog blog;
	private Gson gson = new Gson();
	private static int requestCode = 0;
	private Intent intent = null;
	private String mActon = null;
	private Long blogId = 0L;
	private String tag_json_obj = "json_obj_req";
	private String tag_string_req = "tag_string_req";
	private Fragment blogfragment;

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
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.blogeditfragmenu, menu);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.editblog, container, false);
		textTitle = (TextView) view.findViewById(R.id.ed_blog_title);
		textContent = (TextView) view.findViewById(R.id.ed_blog_content);
		catebutton = (ImageButton) view.findViewById(R.id.imb_cate);
		catebutton.setOnClickListener(this);
		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		switch (id) {
		case R.id.blog_save:
			System.out.println("blog save !");
			if(blogId==0L){
				blogId=Utils.Preference.getLongPref(getActivity().getApplicationContext(),Constants.Preference.DEFAULT_CATE_ID, 0L);
			}
			saveBlog();
			break;
		case R.id.action_clear:
			break;
		default:
			break;
		}
		return true;
		
	}
	
	protected void saveBlog() {
		String title = textTitle.getText().toString();
		String content = textContent.getText().toString();
		String userId = Utils.Preference.getStringPref(getActivity().getApplicationContext(),Constants.Preference.USERID, "");
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
		JsonObjectRequest jsonReq = new JsonObjectRequest(Method.POST, url,requestBody, new Response.Listener<JSONObject>() {
					
					@Override
					public void onResponse(JSONObject response) {
						String res = response.toString();
						Log.d(TAG, res);
						int num = Integer.parseInt(res.substring(res.length() - 2, res.length() - 1));
						if (num > 0) {
							ToastUtil.show(getActivity(),R.string.add_blog_success);
							getActivity().finish();
						} else {
							ToastUtil.show(getActivity(),R.string.add_blog_fail);
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
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode,Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		switch (requestCode) {
		case 0:
			blogId = intent.getLongExtra("blogcategoryId", 0L);
			System.out.println(blogId);
			break;
		case 2:

			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.imb_cate:
			Intent intent = new Intent();
			intent.setClass(getActivity(), NotesActivity.class);
			intent.setAction(Constants.Action.SELECTNOTE);
			requestCode = 0;
			startActivityForResult(intent, requestCode);
			break;
		default:
			break;
		}
		
	}
	
}
