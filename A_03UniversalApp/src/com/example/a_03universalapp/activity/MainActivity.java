package com.example.a_03universalapp.activity;

import com.example.a_03universalapp.R;
import com.example.a_03universalapp.R.layout;
import com.example.a_03universalapp.ui.carousel.Carousel;
import com.example.a_03universalapp.ui.carousel.CarouselImageAdapter;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Carousel carousel = (Carousel)findViewById(R.id.carousel);
        TypedArray images = getResources().obtainTypedArray(R.array.points);
        
        CarouselImageAdapter imageAdapter=new CarouselImageAdapter(this, images);
        carousel.setCarouselAdapter(imageAdapter);
        images.recycle();
        
		
	}

}
