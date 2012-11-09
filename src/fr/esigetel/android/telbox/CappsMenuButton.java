/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class CappsMenuButton extends View{
	final int SIZE;
	Drawable off;
	Drawable on;
	boolean activeState;
	private List<OnClickListener> onClickListeners;

	public CappsMenuButton(Context context, int residOFF, int residON, int size) {
		super(context);
		SIZE = size;
		off = getResources().getDrawable(residOFF);
		off.setBounds(0, 0, size*2, size);
		on = getResources().getDrawable(residON);
		on.setBounds(0, 0, size*2, size);
		activeState = false;
		onClickListeners = new ArrayList<View.OnClickListener>();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (activeState)
			on.draw(canvas);
		else
			off.draw(canvas);
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			activeState = true;
			invalidate();
			return true;
		case MotionEvent.ACTION_MOVE:
			if (activeState) {
				if (event.getX() < 0 || event.getX() > SIZE*2 || event.getY() < 0 || event.getY() > SIZE) {
					activeState = false;
					invalidate();
					return true;
				}
			} else {
				if (event.getX() > 0 && event.getX() < SIZE*2 && event.getY() > 0 && event.getY() < SIZE) {
					activeState = true;
					invalidate();
					return true;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (activeState) {
				activeState = false;
				invalidate();
				setClicked();
			}
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	public void setClicked() {
		for (int i = 0, n = onClickListeners.size(); i < n; i++)
			onClickListeners.get(i).onClick(this);
	}
	
	public void addOnClickListener(OnClickListener onClickListener) {
		onClickListeners.add(onClickListener);
	}
}
