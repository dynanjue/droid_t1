package com.example.t1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class ActionBarActivity extends Activity {

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
		_mactionBar.setDisplayHomeAsUpEnabled(true);
		_mactionBar.setDisplayShowTitleEnabled(false);
		_mactionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		_mactionBar.addTab(_mactionBar.newTab().setText("1")
				.setTabListener(new mytl()));
		_mactionBar.addTab(_mactionBar.newTab().setText("2")
				.setTabListener(new mytl()));
		_mactionBar.addTab(_mactionBar.newTab().setText("3")
				.setTabListener(new mytl()));
		// _mactionBar.setListNavigationCallbacks(new ArrayAdapter<String>(
		// mContext, android.R.layout.simple_spinner_item,
		// R.array.aryStr_main), new OnNavigationListener() {
		//
		// @Override
		// public boolean onNavigationItemSelected(int itemPosition,
		// long itemId) {
		// return true;
		// }
		// });
		// _mactionBar.setDisplayHomeAsUpEnabled(true);
	}

	private Intent getDefaultIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;
	}

	private class mytl implements TabListener {

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// super.onCreateOptionsMenu(menu);

		getMenuInflater().inflate(R.menu.actionbar, menu);

		MenuItem item = menu.findItem(R.id.action_item_myProvider);
		ShareActionProvider m = (ShareActionProvider) item.getActionProvider();
		m.setShareIntent(getDefaultIntent());
		// MyAP a = (MyAP) item.getActionProvider();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(mContext, "Home", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		// setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
		// startActivity(new Intent(mContext, ActionBarActivity.class));
		// this.finish();
		// if (item.getItemId() == R.id.action_help) {
		// startActivity(new Intent(mContext, PhotoActivity.class));
		// }

		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			try {
				Method method = menu.getClass().getDeclaredMethod(
						"setOptionalIconsVisible", Boolean.TYPE);
				method.setAccessible(true);
				method.invoke(menu, true);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

}
