package com.ning.myapp.fragments;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ning.myapp.R;
import com.ning.myapp.activitys.LoginActivity;
import com.ning.myapp.activitys.MainTabActivity;
import com.ning.myapp.entitys.Blog;
import com.ning.myapp.listwidget.SimpleFooter;
import com.ning.myapp.listwidget.SimpleHeader;
import com.ning.myapp.listwidget.ZrcListView;
import com.ning.myapp.listwidget.ZrcListView.OnStartListener;
import com.ning.myapp.utils.AppController;
import com.ning.myapp.utils.Constants;
import com.ning.myapp.utils.ToastUtil;
import com.ning.myapp.utils.Utils;

public class Fragment2 extends Fragment {
	private static final String TAG = "Fragment2";

	private ZrcListView listView;
	private Handler handler;
	private ArrayList<Blog> msgs;
	private int pageId = -1;
	private MyAdapter adapter;
	private String tag_json_obj = "json_obj_req";
	private String tag_string_req = "tag_string_req";
	private String tag_json_array = "tag_string_array";
	private Gson gson = new Gson();
	private ArrayList<Blog> blogArray=new ArrayList<Blog>();

	private static final String[][] names = new String[][] {
			{ "加拿大", "瑞典", "澳大利亚", "瑞士", "新西兰", "挪威", "丹麦", "芬兰", "奥地利", "荷兰",
					"德国", "日本", "比利时", "意大利", "英国" },
			{ "德国", "西班牙", "爱尔兰", "法国", "葡萄牙", "新加坡", "希腊", "巴西", "美国", "阿根廷",
					"波兰", "印度", "秘鲁", "阿联酋", "泰国" },
			{ "智利", "波多黎各", "南非", "韩国", "墨西哥", "土耳其", "埃及", "委内瑞拉", "玻利维亚",
					"乌克兰" },
			{ "以色列", "海地", "中国", "沙特阿拉伯", "俄罗斯", "哥伦比亚", "尼日利亚", "巴基斯坦", "伊朗",
					"伊拉克" } };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		ActionBar actionBar = getActivity().getActionBar();  
		actionBar.setTitle("最近笔记");  

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.recentblogs, container, false);
		listView = (ZrcListView) view.findViewById(R.id.zListView);
		handler = new Handler();

		// 设置默认偏移量，主要用于实现透明标题栏功能。（可选）
		float density = getResources().getDisplayMetrics().density;
		listView.setFirstTopOffset((int) (50 * density));

		// 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
		SimpleHeader header = new SimpleHeader(view.getContext());
		header.setTextColor(0xff0066aa);
		header.setCircleColor(0xff33bbee);
		listView.setHeadable(header);

		// 设置加载更多的样式（可选）
		SimpleFooter footer = new SimpleFooter(view.getContext());
		footer.setCircleColor(0xff33bbee);
		listView.setFootable(footer);

		// 设置列表项出现动画（可选）
		listView.setItemAnimForTopIn(R.anim.topitem_in);
		listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

		// 下拉刷新事件回调（可选）
		listView.setOnRefreshStartListener(new OnStartListener() {
			@Override
			public void onStart() {
				refresh();
			}
		});

		// 加载更多事件回调（可选）
		listView.setOnLoadMoreStartListener(new OnStartListener() {
			@Override
			public void onStart() {
				loadMore();
			}
		});

		adapter = new MyAdapter();
		listView.setAdapter(adapter);
		String url = Constants.Url.Blog.ALLBLOG + "user_1433689062285430207";
		getRcBlogs(url);
		listView.refresh(); // 主动下拉刷新

		return view;
	}

	private void refresh() {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
				if (rand == 0 || pageId == -1) {
					pageId = 0;
					msgs = new ArrayList<Blog>();
					for (Blog blog :blogArray) {
						msgs.add(blog);
					}
					adapter.notifyDataSetChanged();
					listView.setRefreshSuccess("加载成功"); // 通知加载成功
					listView.startLoadMore(); // 开启LoadingMore功能
				} else {
					listView.setRefreshFail("加载失败");
				}
			}
		}, 2 * 1000);
	}

	private void loadMore() {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				pageId++;
				if (pageId < 3) {
					for (Blog blog :blogArray) {
						msgs.add(blog);
					}
					adapter.notifyDataSetChanged();
					listView.setLoadMoreSuccess();
				} else {
					listView.stopLoadMore();
				}
			}
		}, 2 * 1000);
	}

	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return msgs == null ? 0 : msgs.size();
		}

		@Override
		public Object getItem(int position) {
			return msgs.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if (convertView == null) {
				textView = (TextView) getActivity().getLayoutInflater()
						.inflate(android.R.layout.simple_list_item_1, null);
			} else {
				textView = (TextView) convertView;
			}
			textView.setText(msgs.get(position).getBlogTitle());
			return textView;
		}
	}

	private void geAllBlogs(String url) {
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url,
				"", new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

					}

				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}

	private void getRecentBlogs(String url) {
		StringRequest strReq = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.d(TAG, response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
	}

	private void getRcBlogs(String url) {
		JsonArrayRequest strReq = new JsonArrayRequest(Method.GET, url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						Blog blog = null;
						for (int i = 0; i < response.length(); i++) {
							 try {
								JSONObject object = (JSONObject) response.getJSONObject(i);
								System.out.println(object);
								blog=gson.fromJson(object.toString(), Blog.class);
								blogArray.add(blog);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

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

}
