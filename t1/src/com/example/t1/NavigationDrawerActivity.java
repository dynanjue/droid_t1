package com.example.t1;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavigationDrawerActivity extends Activity {

	private Context mContext;
	@ViewInject(R.id.drawer_navDrawer)
	private DrawerLayout mdlMain;
	@ViewInject(R.id.lst_navDrawer_menu)
	private ListView mlstMenu;
	@ResInject(id = R.array.aryStr_main, type = ResType.StringArray)
	private String[] _strMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_drawer_activity);
		mContext = this;
		ViewUtils.inject(this);

		mlstMenu.setAdapter(new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, _strMenu));
		mlstMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mlstMenu.setItemChecked(arg2, true);
				setTitle(_strMenu[arg2]);
				mdlMain.closeDrawers();
			}
		});
		mdlMain.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {

			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {

			}

			@Override
			public void onDrawerOpened(View arg0) {

			}

			@Override
			public void onDrawerClosed(View arg0) {

			}
		});
	}
}
