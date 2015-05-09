package com.example.t1;

import com.example.t1.fragments.One_Fragment;
import com.example.t1.fragments.Two_Fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Activity extends Activity {

	@SuppressWarnings("unused")
	private Context mContext;
	private ActionBar mActionBar;
	private FragmentManager mfm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity);
		mContext = this;
		mActionBar = getActionBar();
		mActionBar.show();
		mActionBar.setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			One_Fragment one_frag = new One_Fragment();
			one_frag.SetItemSelectedListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					mfm.beginTransaction()
							.replace(R.id.fl_fragment_container,
									new Two_Fragment(), "detail")
							.addToBackStack(null).commit();
				}
			});

			mfm = getFragmentManager();
			mfm.beginTransaction()
					.add(R.id.fl_fragment_container, one_frag, "list").commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem searchMenu = menu.findItem(R.id.action_fragment_search);
		// SearchView sv = (SearchView) searchMenu.getActionView();
		searchMenu.setOnActionExpandListener(new OnActionExpandListener() {

			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Toast.makeText(mContext, mfm.getBackStackEntryCount() + "",
		// Toast.LENGTH_SHORT).show();
		if (item.getItemId() == android.R.id.home) {
			if (mfm.getBackStackEntryCount() == 0)
				finish();
			else
				mfm.popBackStackImmediate();
		}

		return super.onOptionsItemSelected(item);
	}

}
