package com.example.a_03universalapp.ui;

import com.example.a_03universalapp.utils.ActivityUtils;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
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
	private float mFourCircleRadius;
	private Camera mCamera;
	private int mBgColor;
	private int[] mCircleColor;
	private Matrix mMatrix;

	private float mCanvasRotateX = 70f;
	private float mCanvasRotateY = 0f;
	private float mCanvasRotateZ = 0f;

	private int mCenterX;
	private int mCenterY;

	private String bgColor = "#000000";
	private String[] circleColor = { "#151D24", "#172635", "#1A3C4F", "#2E817D", "#225250" };
	private String ovalColor = "#2A9DD1";
	private RectF ovalRectF;

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

		// 确定是横屏

		mMaxRadius = mCenterY / 6 * 5;
		mCenterCircleRadius = mCenterY / 6 * 4;
		mSecondCircleRadius = mCenterY / 6 * 3;
		mThreeCircleRadius = mCenterY / 6 * 2;
		mFourCircleRadius = mCenterY / 6;

		ovalRectF = new RectF(mCenterX - mMaxRadius - 1, mCenterY - mMaxRadius - 1, mCenterX + mMaxRadius + 1,
				mCenterY + mMaxRadius + 1);

		// //如果是横屏
		// if(ActivityUtils.isScreenOriention(getContext())){
		// mMaxRadius = mCenterY / 5 * 5;
		// mCenterCircleRadius = mCenterY / 5 * 4;
		// mSecondCircleRadius = mCenterY / 5 * 3;
		// mThreeCircleRadius = mCenterY / 5 * 2;
		// mFourCircleRadius = mCenterY / 5;
		// }else {
		// mMaxRadius = mCenterX / 5 * 5;
		// mCenterCircleRadius = mCenterX / 5 * 4;
		// mSecondCircleRadius = mCenterX / 5 * 3;
		// mThreeCircleRadius = mCenterX / 5 * 2;
		// mFourCircleRadius = mCenterX / 5;
		// }

		Log.d("CompassView", "mMaxRadius = " + mMaxRadius);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);

		canvas.drawColor(mBgColor);

		 rotateCanvas(canvas);

		// 绘制弧线
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(Color.parseColor(ovalColor));
		mPaint.setStrokeWidth(6);
		canvas.drawArc(ovalRectF, 10, 160, false, mPaint);

		// 画最外层圆
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(mCircleColor[0]);
		mPaint.setStrokeWidth(2);
		canvas.drawCircle(mCenterX, mCenterY, mMaxRadius, mPaint);

		// 绘制内层第一个圆
		mPaint.setColor(mCircleColor[1]);
		canvas.drawCircle(mCenterX, mCenterY, mCenterCircleRadius, mPaint);

		// 绘制内层第二个圆
		mPaint.setColor(mCircleColor[2]);
		canvas.drawCircle(mCenterX, mCenterY, mSecondCircleRadius, mPaint);

		// 绘制内层第三个圆
		mPaint.setColor(mCircleColor[3]);
		canvas.drawCircle(mCenterX, mCenterY, mThreeCircleRadius, mPaint);

		mPaint.setColor(mCircleColor[4]);
		canvas.drawCircle(mCenterX, mCenterY, mFourCircleRadius, mPaint);

		super.onDraw(canvas);

	}

	/**
	 * 旋转画布
	 * 
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
