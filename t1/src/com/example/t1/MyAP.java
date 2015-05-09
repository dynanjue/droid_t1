package com.example.t1;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

public class MyAP extends ActionProvider {

	public MyAP(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.add("sub1").setIcon(R.drawable.haha);
		subMenu.add("sub2").setIcon(R.drawable.haha);
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setOptionalIconsVisible(boolean a) {

	}
}
