package com.example.t1;

import com.example.t1.custom.MyView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MyView_Activity extends Activity {

	@ViewInject(R.id.myView1)
	private MyView v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.my_view_activity);
		ViewUtils.inject(this);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				v.draw();
			}
		}, 1000);
	}

}
