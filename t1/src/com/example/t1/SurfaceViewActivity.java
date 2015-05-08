package com.example.t1;

import java.io.IOException;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SurfaceViewActivity extends Activity {

	private Context mContext;
	@ViewInject(value = R.id.sv_surfaceView_view)
	private SurfaceView msvView;
	private SurfaceHolder msh;
	private Camera mCamera;

	// private MediaRecorder mMidiaRecorder;// ÊÓÆµÂ¼ÖÆ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.surfaceview_activity);
		mContext = this;
		ViewUtils.inject(this);

		msvView.setClickable(true);
		msvView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(
						mContext,
						"Clicked: Camera num is " + Camera.getNumberOfCameras(),
						Toast.LENGTH_SHORT).show();
				mCamera.takePicture(null, null, new PictureCallback() {
					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						MediaStore.Images.Media.insertImage(
								getContentResolver(), BitmapFactory
										.decodeByteArray(data, 0, data.length),
								"title_" + System.currentTimeMillis(), "des_"
										+ System.currentTimeMillis());
						mCamera.startPreview();
					}
				});
			}
		});
		msh = msvView.getHolder();
		// msh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		msh.addCallback(new Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				mCamera.stopPreview();
				mCamera.release();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					mCamera = Camera.open(1);
					mCamera.setDisplayOrientation(90);
					mCamera.setPreviewDisplay(msh);
					mCamera.startPreview();
				} catch (IOException e) {
					Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				mCamera.startPreview();
			}
		});
	}

	@Override
	public boolean isDestroyed() {
		mCamera.stopPreview();
		mCamera.release();
		return super.isDestroyed();
	}
}
