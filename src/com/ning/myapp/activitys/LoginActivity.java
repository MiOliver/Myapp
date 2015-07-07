package com.ning.myapp.activitys;

import java.io.StringReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ning.myapp.R;
import com.ning.myapp.entitys.User;
import com.ning.myapp.net.BaseResponse;
import com.ning.myapp.utils.AppController;
import com.ning.myapp.utils.Constants;
import com.ning.myapp.utils.ToastUtil;
import com.ning.myapp.utils.Utils;

public class LoginActivity extends Activity implements OnClickListener {
	private static final String TAG = "LoginActivity";
	private Button mbtLogin;
	private Button btback;
	private Button mbtRegister;
	private EditText mtUser;
	private EditText mtUPass;
	
	private String username;
	private String password;
	private Gson gson = new Gson();

	private String tag_json_obj = "json_obj_req";
	private String tag_string_req= "tag_string_req";
	
	private BaseResponse baseRequest= null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		boolean loginstatus=Utils.Preference.getBooleanPref(getApplicationContext(), Constants.Preference.LOGINSTATUS, false);
		if(loginstatus){
			Intent intent = new Intent();
    		intent.setClass(LoginActivity.this,MainTabActivity.class);
    		startActivity(intent);
    		LoginActivity.this.finish();
    		return;
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.usercenter);
		mbtLogin =(Button)findViewById(R.id.btlogin);
		mbtLogin.setOnClickListener(this);
		mbtRegister =(Button)findViewById(R.id.btreg); 
		mbtRegister.setOnClickListener(this);
		mtUser=(EditText)findViewById(R.id.edituser); 
		mtUPass=(EditText)findViewById(R.id.editpass); 
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btuserback:
			finish();
		case R.id.btlogin:
			username=mtUser.getText().toString();
			password=mtUPass.getText().toString();
			String url = Constants.Url.Userinfo.LOGIN+"username="+username+"&"+"password="+password;
			System.out.println(url);
			login(url);
			break;
		case R.id.btreg:
			break;
		default:
			break;
		}
	}

	private void getUserinfo(String url) {
		
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url,"", new Response.Listener<JSONObject>() {
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

		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}
	
	
	private void login(String url) {
		
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url,"", new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
//						baseRequest=gson.fromJson(response.toString(), BaseResponse.class);
						String code;
						String desc;
						try {
							code = response.getJSONObject("header").optString("code");
							desc =response.getJSONObject("header").optString("desc");
							if(code.equals(Constants.ResponseCode.BaseCode.SUCCESS)){
								User user = null;
								JSONObject body= response.getJSONObject("body");
//								JsonReader reader = new JsonReader(new StringReader(body));
//								reader.setLenient(true);
		                    	user = gson.fromJson(body.toString(), User.class);
		                    	System.out.println(user);
		                    	Utils.Preference.setBooleanPref(getApplicationContext(), Constants.Preference.LOGINSTATUS, true);
		                    	Utils.Preference.setStringPref(getApplicationContext(), Constants.Preference.USERID, user.getId());
		                    	Utils.Preference.setStringPref(getApplicationContext(), Constants.Preference.USERID, "user_1433852026322401409");
		                    	Utils.Preference.setStringPref(getApplicationContext(), Constants.Preference.USERNAME, user.getUsername());
		                    	Intent intent = new Intent();
		                		intent.setClass(LoginActivity.this,MainTabActivity.class);
		                		startActivity(intent);
		                		LoginActivity.this.finish();
							}else{
								ToastUtil.show(LoginActivity.this, R.string.loginfail);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Log.i(TAG, error.getMessage());

					}

				});

		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}
	

}
