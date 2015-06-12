package com.ning.myapp.activitys;

import com.ning.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class BlogEditActivity extends Activity implements OnClickListener {

	private ImageButton catebutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editblog);
		catebutton = (ImageButton) findViewById(R.id.imb_cate);
		catebutton.setOnClickListener(this);
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
			startActivity(intent);
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

}
