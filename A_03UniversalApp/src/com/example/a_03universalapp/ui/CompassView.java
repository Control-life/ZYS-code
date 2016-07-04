package com.example.a_03universalapp.ui;

import com.example.a_03universalapp.utils.ActivityUtils;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CompassView extends View {

	private Paint mPaint;
	private float mMaxRadius;
	private float mCenterCircleRadius;
	private float mSecondCircleRadius;
	private float mThreeCircleRadius;
	private Camera mCamera;
	private int mBgColor;
	private int[] mCircleColor;
	private Matrix mMatrix;
	
	private float mCanvasRotateX = 70f;
	private float mCanvasRotateY = 0f;
	private float mCanvasRotateZ = 0f;
	
	private int mCenterX;
	private int mCenterY;

//	private String bgColor = "#142152";
	private String bgColor = "#654321";
	private String[] circleColor = { "#3352CC", "#5C75D6", "#5CD675", "#F1F141", "#070B1B" };

	public CompassView(Context context) {
		this(context, null);
	}

	public CompassView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.FILL);

		mMatrix = new Matrix();
		mBgColor = Color.parseColor(bgColor);
		
		mCircleColor = new int[circleColor.length];
		for (int i = 0; i < circleColor.length; i++) {
			mCircleColor[i] = Color.parseColor(circleColor[i]);
		}
		
		mCamera = new Camera();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		mCenterX = getWidth() / 2;
		mCenterY = getHeight() / 2;
		
		//如果是横屏
		if(ActivityUtils.isScreenOriention(getContext())){
			mMaxRadius = mCenterY / 5 * 4;
			mCenterCircleRadius = mCenterY / 5 * 3;
			mSecondCircleRadius = mCenterY / 5 * 2;
			mThreeCircleRadius = mCenterY / 5;
		}else {
			mMaxRadius = mCenterX / 5 * 4;
			mCenterCircleRadius = mCenterX / 5 * 3;
			mSecondCircleRadius = mCenterX / 5 * 2;
			mThreeCircleRadius = mCenterX / 5;
		}
		
		
		Log.d("CompassView", "mMaxRadius = " + mMaxRadius);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
		
		canvas.drawColor(mBgColor);
		
		rotateCanvas(canvas);
		
		//画最外层圆
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(mCircleColor[0]);
		mPaint.setStrokeWidth(2);
		canvas.drawCircle(mCenterX, mCenterY, mMaxRadius, mPaint);
		
		//绘制内层第一个圆
		mPaint.setColor(mCircleColor[1]);
		canvas.drawCircle(mCenterX, mCenterY, mCenterCircleRadius, mPaint);
		
		//绘制内层第二个圆
		mPaint.setColor(mCircleColor[2]);
		canvas.drawCircle(mCenterX, mCenterY, mSecondCircleRadius, mPaint);
		
		//绘制内层第三个圆
		mPaint.setColor(mCircleColor[3]);
		canvas.drawCircle(mCenterX, mCenterY, mThreeCircleRadius, mPaint);
		
		super.onDraw(canvas);

	}

	/**
	 * 旋转画布
	 * @param canvas
	 */
	private void rotateCanvas(Canvas canvas) {
		
		mMatrix.reset();
		mCamera.save();
		mCamera.rotateX(mCanvasRotateX);
		mCamera.rotateY(mCanvasRotateY);
		mCamera.rotateZ(mCanvasRotateZ);
		mCamera.getMatrix(mMatrix);
		mCamera.restore();
		mMatrix.preTranslate(-mCenterX, -mCenterY);
		mMatrix.postTranslate(mCenterX, mCenterY);
		
		canvas.concat(mMatrix);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
