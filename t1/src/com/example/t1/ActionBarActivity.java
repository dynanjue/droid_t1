package com.example.t1;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ActionBarActivity extends Activity {

	@SuppressWarnings("unused")
	private Context mContext;
	private ActionBar _mactionBar;
	@ViewInject(R.id.btn_actionbar_ActionBarStatusSwitch)
	private Button mbtnStatusSwitch;

	@OnClick(R.id.btn_actionbar_ActionBarStatusSwitch)
	public void StatusSwitch_onClick(View view) {
		if (_mactionBar.isShowing())
			_mactionBar.hide();
		else
			_mactionBar.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		mContext = this;
		setContentView(R.layout.actionbar_activity);
		ViewUtils.inject(this);

		_mactionBar = getActionBar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// super.onCreateOptionsMenu(menu);

		getMenuInflater().inflate(R.menu.actionbar, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//		startActivity(new Intent(mContext, ActionBarActivity.class));
//		this.finish();
		return true;
	}

}
