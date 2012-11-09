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
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class SplashBackground extends View{

	Drawable bgTop;
	Drawable bgCentre;
	Drawable bgBottom;
	final int WINDOW_WIDTH;
	final int WINDOW_HEIGHT;
	public SplashBackground(Context context, int width, int height) {
		super(context);
		WINDOW_WIDTH = width;
		WINDOW_HEIGHT = height;
		bgTop = getResources().getDrawable(R.drawable.clipboardtop);
		bgTop.setBounds(0, 0, WINDOW_WIDTH, WINDOW_WIDTH/4);
		bgCentre = getResources().getDrawable(R.drawable.clipboardcentre);
		bgCentre.setBounds(0, WINDOW_WIDTH/4, WINDOW_WIDTH, WINDOW_HEIGHT - WINDOW_WIDTH/4);
		bgBottom = getResources().getDrawable(R.drawable.clipboardbottom);
		bgBottom.setBounds(0, WINDOW_HEIGHT - WINDOW_WIDTH/4, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		bgTop.draw(canvas);
		bgCentre.draw(canvas);
		bgBottom.draw(canvas);
		super.onDraw(canvas);
	}
}
