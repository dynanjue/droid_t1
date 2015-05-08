package com.example.t1;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PhotoActivity extends Activity {

	private static final int REQUEST_CODE_TAKEPHOTO = 1;
	private static final int REQUEST_CODE_FROMALBUM = 2;

	private Context mContext;
	@ViewInject(R.id.btn_photo_choice)
	private Button mbtnChoice;
	@ViewInject(R.id.iv_photo_preview)
	private ImageView mivPreview;
	private AlertDialog mDialog;
	@ViewInject(R.id.iv_customTitle_right)
	private ImageView mivDone;
	@ViewInject(R.id.iv_customTitle_left)
	private ImageView mivBack;
	@ViewInject(R.id.tv_customTitle_title)
	private TextView mtvTitle;

	/**
	 * 点击按钮 选择图片
	 * 
	 * @param view
	 */
	@OnClick(value = R.id.btn_photo_choice)
	public void btnPhoneChoice_onClick(View view) {
		mDialog.show();
	}

	@OnClick(R.id.iv_photo_preview)
	public void ivPreview_onClick(View view) {
		mDialog.show();
	}

	@OnClick(R.id.iv_customTitle_left)
	public void ivBack_onClick(View view) {
		finish();
	}

	@OnClick(R.id.iv_customTitle_right)
	public void ivDone_onClick(View view) {
		Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.photo_activity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);
		mContext = this;
		ViewUtils.inject(this);

		mtvTitle.setText(R.string.str_main_photo);

		initPhotoChoiceDialog();
	}

	/**
	 * 初始化 Photo 选择Dialog
	 */
	private void initPhotoChoiceDialog() {
		mDialog = new AlertDialog.Builder(mContext).setItems(
				R.array.aryStr_photo_choiceItems, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent();
						switch (which) {
						case 0:
							intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
							startActivityForResult(intent,
									REQUEST_CODE_TAKEPHOTO);
							break;
						case 1:
							intent.setType("image/*");
							intent.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(intent,
									REQUEST_CODE_FROMALBUM);
							break;
						}
					}
				}).create();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode != RESULT_OK)
			return;

		switch (requestCode) {
		case REQUEST_CODE_TAKEPHOTO:
			setImgPreview(data);
			break;
		case REQUEST_CODE_FROMALBUM:
			setImgPreview(data);
			break;
		}

	}

	private void setImgPreview(Intent data) {

		if (data == null) {
			Toast.makeText(mContext, "未获取到返回的数据", Toast.LENGTH_SHORT).show();
			return;
		}

		Uri uri;
		if ((uri = data.getData()) != null)
			mivPreview.setImageURI(uri);
		else {
			Bundle b = data.getExtras();
			if (b != null)
				mivPreview.setImageBitmap((Bitmap) b.getParcelable("data"));
		}

	}
}
