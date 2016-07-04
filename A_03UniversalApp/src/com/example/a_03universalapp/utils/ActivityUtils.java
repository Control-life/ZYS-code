package com.example.a_03universalapp.utils;

import android.content.Context;
import android.content.res.Configuration;

public class ActivityUtils {

	/**
	 * 判断当前屏幕是不是横屏的
	 * @param context
	 * @return
	 */
	public static boolean isScreenOriention(Context context){
		
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}
}
