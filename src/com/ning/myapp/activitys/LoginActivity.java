package com.ning.myapp.activitys;

import org.json.JSONObject;

import android.app.Activity;
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
import com.ning.myapp.R;
import com.ning.myapp.utils.AppController;

public class LoginActivity extends Activity implements OnClickListener {
	private static final String TAG = "LoginActivity";
	private Button mbtLogin;
	private Button btback;
	private Button mbtRegister;
	private EditText mtUser;
	private EditText mtUPass;
	
	private String username;
	private String password;


	private String tag_json_obj = "json_obj_req";
	private String url = "http://192.168.1.111:8080/v1/user/user_1433433953177329398";
	private String loginUrl = "http://192.168.1.111:8080/v1/user/login?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
			String url = loginUrl+"username="+username+"&"+"password="+password;
			System.out.println(url);
			getUserinfo(url);
			break;
		case R.id.btreg:
			break;
		default:
			break;
		}
	}

	private void getUserinfo(String url) {
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

}
