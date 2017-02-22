package com.diary.util;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

public class MyGestureListener extends SimpleOnGestureListener {
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if(e1.getX()-e2.getX()>50){
			
		}else if(e2.getX()-e1.getX()>50){
			
		}
		
		return super.onFling(e1, e2, velocityX, velocityY);
	}
}
