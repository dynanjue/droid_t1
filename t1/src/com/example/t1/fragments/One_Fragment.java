package com.example.t1.fragments;

import com.example.t1.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class One_Fragment extends Fragment {

	private Context mContext;
	@ViewInject(R.id.lst_frament_one_list)
	private ListView mlstList;
	@ResInject(id = R.array.aryStr_main, type = ResType.StringArray)
	private String[] _aryLst;
	private ArrayAdapter<String> adapter;
	private OnItemClickListener mItemClickListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();

		View view = inflater.inflate(R.layout.one_fragment, null);
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		adapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1);
		adapter.addAll(_aryLst);
		mlstList.setAdapter(adapter);
		mlstList.setOnItemClickListener(mItemClickListener);
	}

	public void SetItemSelectedListener(OnItemClickListener listener) {
		mItemClickListener = listener;
	}
}
