package com.example.t1.custom;

import com.example.t1.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private Paint mPaint;
	private Matrix mMatrix;
	private Bitmap mBitmap;

	public MyView(Context context) {
		this(context, null, 0);
	}

	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		// this(context);
		super(context, attrs, defStyleAttr);
		mPaint = new Paint();
		mPaint.setColor(Color.YELLOW);
		mMatrix = new Matrix();
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.haha);
		// mMatrix.setRotate(-45, 0, 0);
		// mMatrix.postTranslate(200, 200);

		// mBitmap = Bitmap.createBitmap(200, 200, Config.ALPHA_8);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		mPaint.setColor(Color.GREEN);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(10);
		// canvas.drawRoundRect(new RectF(120, 120, 500, 300), 20, 20, mPaint);

		// matrix.set

		canvas.drawBitmap(mBitmap, mMatrix, mPaint);
		// canvas.drawRect(new Rect(100, 100, 200, 200), mPaint);
	}

	public void draw() {
		mMatrix.postScale(2, 2);
		postInvalidate();
	}

}
