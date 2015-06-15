package com.ning.myapp.activitys;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.ning.myapp.R;
import com.ning.myapp.entitys.Blog;
import com.ning.myapp.entitys.BlogCategory;
import com.ning.myapp.utils.AppController;
import com.ning.myapp.utils.Constants;
import com.ning.myapp.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NotesActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private static final String TAG = "NotesActivity";
	private ArrayList<BlogCategory> allnotes = new ArrayList<BlogCategory>();
	private Gson gson = new Gson();
	private String tag_json_obj = "json_obj_req";
	private String tag_string_req = "tag_string_req";
	private String tag_json_array = "tag_string_array";

	private ListView listview;
	private MyAdapter adapter = null;
	private String mAction = null;
	private int resultCode = 0;

	// // private static final String
	// url=Constants.Url.Category.ALLCATEGORY+"user_1433852026322401409";
	private static String url = Constants.Url.Category.ALLCATEGORY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorys);
		listview = (ListView) findViewById(R.id.list_category);
		adapter = new MyAdapter();
		listview.setAdapter(adapter);
		String userId = Utils.Preference.getStringPref(getApplicationContext(),
				Constants.Preference.USERID, "");
		url += userId;
		getAllBlogCategory(url);
	}

	private void handleIntent() {
		Intent intent = getIntent();
		mAction = intent.getAction();
		// mType =
		// intent.getStringExtra(Constants.Intent.EXTRA_ORDER_LIST_TYPE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blogedit, menu);
		return true;
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

	private void getAllBlogCategory(String url) {
		JsonArrayRequest strReq = new JsonArrayRequest(Method.GET, url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						BlogCategory blogCategory = null;
						int j = 0;
						int i = 0;
						allnotes.clear();
						for (i = 0; i < response.length(); i++) {
							try {
								JSONObject object = (JSONObject) response
										.getJSONObject(i);
								System.out.println(object);
								blogCategory = gson.fromJson(object.toString(),
										BlogCategory.class);
								allnotes.add(blogCategory);

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						adapter.notifyDataSetChanged();

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(strReq, tag_json_array);
	}

	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return allnotes == null ? 0 : allnotes.size();
		}

		@Override
		public Object getItem(int position) {
			return allnotes.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textTitle;
			TextView textTime;
			ImageView image;
			View view;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.categoryitem, null);
			} else {
				view = convertView;
			}
			image = (ImageView) view.findViewById(R.id.list_cate_image);
			textTitle = (TextView) view.findViewById(R.id.list_cate_title);
			textTime = (TextView) view.findViewById(R.id.list_cate_time);

			textTitle.setText(allnotes.get(position).getTitle());
			String content = allnotes.get(position).getCreatedTime();
			textTime.setText(content);
			Spannable span = new SpannableString(textTime.getText());
			// span.setSpan(new AbsoluteSizeSpan(58), 11, 16,
			// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			span.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 16,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			// span.setSpan(new BackgroundColorSpan(Color.YELLOW), 11, 16,
			// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			textTime.setText(span);
			return view;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View item, int position,
			long arg3) {
		// TODO Auto-generated method stub
		BlogCategory bcate = (BlogCategory) adapter.getItem(position);
		Intent mIntent = new Intent();
		mIntent.putExtra("blogcategoryId", bcate.getId());
		mIntent.putExtra("change02", "2000"); // 设置结果，并进行传送
		this.setResult(resultCode, mIntent);
		this.finish();
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		super.startActivityForResult(intent, requestCode);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
