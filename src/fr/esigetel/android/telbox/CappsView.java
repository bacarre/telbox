/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CappsView extends ImageView{

	Drawable caps;
	float turn;
	Point clicPosition;
	float clicAngle;
	float previousAngle;
	float moveAngle;
	boolean turnedOK;

	public CappsView(Context context, int width, int height) {
		super(context);
		caps = getResources().getDrawable(R.drawable.caps_a_tourner);
		caps.setBounds(0, 0, width, height);
		turn = 0;
		clicAngle = 0;
		previousAngle = 0;
		moveAngle = 0;
		turnedOK = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.rotate(-turn,getWidth()/2,getHeight()/2);
		caps.draw(canvas);
		super.onDraw(canvas);
		canvas.restore();
		if (turn > 300) {
			turn = 0;
		}	
	}

	//------GESTION DU MOUVEMENT DE LA CAPS-------//
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		double l = event.getX() - getWidth()/2;
		double h = getHeight()/2 - event.getY();
		switch(action) { 
		case MotionEvent.ACTION_DOWN:
			if ((h*h+l*l) > 2000) {
				if(l>0 && h>0)
					clicAngle = (float) (Math.atan(h/l)/Math.PI*180);
				else if(l>0 && h<0)
					clicAngle = (float) (Math.atan(h/l)/Math.PI*180) + 360;
				else if (l<0 && h>0)
					clicAngle = (float) (Math.atan(h/l)/Math.PI*180) + 180;
				else if (l<0 && h<0)
					clicAngle = (float) (Math.atan(h/l)/Math.PI*180) + 180;
				moveAngle = previousAngle = clicAngle = modulo(clicAngle);
				turn = 0;
				invalidate();
				return true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if ((h*h+l*l) > 2000) {
				if(l>0 && h>0)
					moveAngle = (float) (Math.atan(h/l)/Math.PI*180);
				else if(l>0 && h<0)
					moveAngle = (float) (Math.atan(h/l)/Math.PI*180) + 360;
				else if (l<0 && h>0)
					moveAngle = (float) (Math.atan(h/l)/Math.PI*180) + 180;
				else if (l<0 && h<0)
					moveAngle = (float) (Math.atan(h/l)/Math.PI*180) + 180;
				moveAngle = modulo(moveAngle);
				if ((modulo(previousAngle) < modulo(moveAngle) && modulo(previousAngle) > modulo(moveAngle)-90) || (modulo(previousAngle)-360 < modulo(moveAngle) && modulo(previousAngle)-360 > modulo(moveAngle)-90))
					turn += moveAngle - previousAngle;
				previousAngle = moveAngle;
				
				if (modulo(turn) > 180 && !turnedOK)
					turnedOK = true;
				invalidate();
				return true;
			}
		case MotionEvent.ACTION_UP:
			turn = 0;
			invalidate();
			if (turnedOK) {
				turnedOK = false;
				Intent intent = new Intent(getContext(), Game.class);
				getContext().startActivity(intent);
			}
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	public float modulo(float f) {
		while (f<0) {
			f += 360;
		}
		while (f > 360) {
			f -= 360;
		}
		return f;
	}
	
}
