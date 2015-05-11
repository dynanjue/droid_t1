package com.example.t1;

import java.util.List;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class IndexActivity extends Activity {

	private Context mContext;
	@ViewInject(R.id.lst_main_view)
	private ListView mlst;

	@ResInject(id = R.array.aryStr_main, type = ResType.StringArray)
	private String[] _ary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.custom_title);
		mContext = this;

		ViewUtils.inject(this);
		// mlst = (ListView) findViewById(R.id.lst_main_view);
		// _ary = mContext.getResources().getStringArray(R.array.aryStr_main);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1);
		adapter.addAll(_ary);

		mlst.setAdapter(adapter);
		mlst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();

				switch (arg2) {
				case 0:
					intent.setClass(mContext, LocationActivity.class);
					break;
				case 1:
					intent.setClass(mContext, PhotoActivity.class);
					break;
				case 2:
					intent.setClass(mContext, MIMETypeActivity.class);
					break;
				case 3:
					intent.setClass(mContext, ActionBarActivity.class);
					break;
				case 4:
					intent.setClass(mContext, SurfaceViewActivity.class);
					break;
				case 5:
					intent.setClass(mContext, NavigationDrawerActivity.class);
					break;
				case 6:
					intent.setClass(mContext, Fragment_Activity.class);
					break;
				case 7:
					intent.setClass(mContext, MyView_Activity.class);
					break;
				default:
					return;
				}

				startActivity(intent);
			}
		});
	}

	public class a extends ArrayAdapter<String> {

		public a(Context context, int resource, int textViewResourceId,
				List<String> objects) {
			super(context, resource, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}
	}

	public class sa extends SimpleAdapter {

		public sa(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}
	}
}
