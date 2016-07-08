package com.example.a_03universalapp.ui.carousel;


import com.example.a_03universalapp.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CarouselItem extends FrameLayout
	implements Comparable<CarouselItem> {

	private ImageView mIconImage;
	private ImageView mNameImage;

	private int index;
	private float currentAngle;
	private float itemX;
	private float itemY;
	private float itemZ;
	private boolean drawn;

	// It's needed to find screen coordinates
	private Matrix mCIMatrix;

	public CarouselItem(Context context) {

		super(context);

		FrameLayout.LayoutParams params =
				new FrameLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);

		this.setLayoutParams(params);

	  	LayoutInflater inflater = LayoutInflater.from(context);
		View itemTemplate = inflater.inflate(R.layout.carousel_item, this, true);

		mIconImage = (ImageView)itemTemplate.findViewById(R.id.item_icon);
		mNameImage=(ImageView)itemTemplate.findViewById(R.id.item_name);

	}


	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}


	public void setCurrentAngle(float currentAngle) {

		if(index == 0 && currentAngle > 5){
			Log.d("", "");
		}

		this.currentAngle = currentAngle;
	}

	public float getCurrentAngle() {
		return currentAngle;
	}

	public int compareTo(CarouselItem another) {
		//System.out.println("compareTo:" + another.itemZ+","+this.itemZ);
		return (int)(another.itemZ - this.itemZ);
	}

	public void setItemX(float x) {
		this.itemX = x;
	}

	public float getItemX() {
		return itemX;
	}

	public void setItemY(float y) {
		this.itemY = y;
	}

	public float getItemY() {
		return itemY;
	}

	public void setItemZ(float z) {
		this.itemZ = z;
	}

	public float getItemZ() {
		return itemZ;
	}

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}

	public boolean isDrawn() {
		return drawn;
	}

	public void setImageBitmap(Bitmap bitmap){
		mIconImage.setImageBitmap(bitmap);

	}

	public ImageView getImageView() {
		return this.mIconImage;
	}
	
	public void setNameBitmap(Bitmap bitmap){
		mNameImage.setImageBitmap(bitmap);
	}
	
	public ImageView getNameImageView(){
		return this.mNameImage;
	}

	Matrix getCIMatrix() {
		return mCIMatrix;
	}

	void setCIMatrix(Matrix mMatrix) {
		this.mCIMatrix = mMatrix;
	}

}
