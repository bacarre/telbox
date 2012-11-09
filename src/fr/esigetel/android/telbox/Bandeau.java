/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

//----------------------------------GESTION DU BANDEAU POUR ACCEDER AU MENU CAPS--------------------------//

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Bandeau extends FrameLayout
{
	private FrameLayout mHandle; // le bandeau
	private FrameLayout mBackground; // Le fond du visuel du menu du jeu
	private CappsMenu mContent; // Le contenu du menu caps : les boutons...
	private boolean open;
	public static final int TOP = 0;
	public static final int BOTTOM = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	private int mWidth;
	private final int WINDOW_WIDTH;
	private final int WINDOW_HEIGHT;

	private boolean moveable;
	public Bandeau(Context context, int width, int position, int wWidth, int wHeight)
	{
		super(context);
		mWidth = width;
		WINDOW_WIDTH = wWidth;
		WINDOW_HEIGHT = wHeight;
		open = false;
		mHandle = new FrameLayout(getContext());
		mHandle.setLayoutParams(new android.widget.FrameLayout.LayoutParams(width, wHeight, Gravity.LEFT));
		mHandle.setBackgroundColor(Color.rgb(100, 100, 100));
		ImageView bandeau_capsView = new ImageView(getContext());
		bandeau_capsView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(width, width*5, Gravity.CENTER));
		bandeau_capsView.setBackgroundResource(R.drawable.bandeau_capps);
		mHandle.addView(bandeau_capsView);
		
		mBackground = new FrameLayout(getContext());
		mBackground.setLayoutParams(new android.widget.FrameLayout.LayoutParams(0, -1, Gravity.LEFT));
		mBackground.setBackgroundColor(Color.argb(230, 0, 0, 0));

		mContent = new CappsMenu(getContext(), mWidth, WINDOW_WIDTH, WINDOW_HEIGHT);
		mContent.setLayoutParams(new LayoutParams(WINDOW_WIDTH, -1));
		mContent.setBackgroundColor(Color.TRANSPARENT);
		
		addView(mBackground, 0);
		addView(mContent);
		addView(mHandle);
		mContent.setVisibility(INVISIBLE);
	}

	//-------------------------GETTERS------------------//
	
	public View getHandle()
	{
		return mHandle;
	}

	public View getContent()
	{
		return mContent;
	}

	//---------------AFFICHAGE DU BANDEAU SELON QU'il SOIT OUVERT OU NON------------------//
	
	public void setOpen(boolean open)
	{
		removeView(mBackground); //On enlève les vues et on les affiche de mannière correcte en fonction du contexte (bandeau ouvert/fermé)
		removeView(mHandle);
		if (open)
		{
			mHandle.removeAllViews();
			ImageView bandeau_capsView = new ImageView(getContext());
			bandeau_capsView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(mWidth, mWidth*4, Gravity.CENTER));
			bandeau_capsView.setBackgroundResource(R.drawable.bandeau_news);
			mHandle.addView(bandeau_capsView);
			mHandle.setLayoutParams(new android.widget.FrameLayout.LayoutParams(mWidth, -1, Gravity.RIGHT));
			mBackground.setLayoutParams(new android.widget.FrameLayout.LayoutParams(WINDOW_WIDTH - mWidth, -1, Gravity.LEFT));
			addView(mBackground, 0);
			addView(mHandle);
			this.open = true;
		} else {
			mHandle.removeAllViews();
			ImageView bandeau_capsView = new ImageView(getContext());
			bandeau_capsView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(mWidth, mWidth*5, Gravity.CENTER));
			bandeau_capsView.setBackgroundResource(R.drawable.bandeau_capps);
			mHandle.addView(bandeau_capsView);
			mHandle.setLayoutParams(new android.widget.FrameLayout.LayoutParams(mWidth, -1, Gravity.LEFT));
			mBackground.setLayoutParams(new android.widget.FrameLayout.LayoutParams(0, -1, Gravity.LEFT));
			addView(mBackground, 0);
			addView(mHandle);
			mContent.setVisibility(INVISIBLE);
			this.open = false;
		}
	}

	//--------------------------GESTION TACTILE----------------------------//
	
	public boolean isOpen()
	{
		return open;
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch(action) {
		case MotionEvent.ACTION_DOWN:
			if (mHandle.getLeft() < event.getX() && mHandle.getRight() > event.getX()) { //si le doigt est posé sur le bandeau ,on peut le déplacer (moveable = true)
				moveable = true;
				 if (!isOpen()) {
						mContent.layout(mWidth - WINDOW_WIDTH, getTop(), mWidth, getBottom());
					
					} else if (isOpen()) {  //on positionne la fenêtre qui contient le menu caps en fonction qu'elle soit ouverte ou non
						mContent.layout(0, getTop(), WINDOW_WIDTH, getBottom());	
					}
				return true;
			}
		break;
		
		case MotionEvent.ACTION_MOVE:
			if (moveable) {
				mHandle.layout((int)event.getX() - mWidth/2, mHandle.getTop(), (int)event.getX() + mWidth/2, mHandle.getBottom());
				mBackground.layout(0, mHandle.getTop(), mHandle.getRight(), mHandle.getBottom());
				mContent.layout(mHandle.getRight() - WINDOW_WIDTH, getTop(), mHandle.getRight(), getBottom());
				if (mContent.getVisibility() != VISIBLE && event.getX() > WINDOW_WIDTH/6)
					mContent.setVisibility(VISIBLE); // On rend le contenu  visible
				return true;
			}
		break;
		
		case MotionEvent.ACTION_UP:
			if (moveable) {
				if (event.getX() > WINDOW_WIDTH - (WINDOW_WIDTH/4) && !isOpen()) {
					mContent.layout(0, getTop(), WINDOW_WIDTH, getBottom());
					setOpen(true);
				} else if (event.getX() < (WINDOW_WIDTH/4) && isOpen()) {
					mContent.layout(mWidth - WINDOW_WIDTH, getTop(), mWidth, getBottom());
					setOpen(false);
				} else if (!isOpen()) {
					mHandle.layout(0, mHandle.getTop(), mWidth, mHandle.getBottom());
					mBackground.layout(0, getTop(), mWidth, getBottom());
					mContent.layout(mWidth - WINDOW_WIDTH, getTop(), mWidth, getBottom());
				} else if (isOpen()) {
					mHandle.layout(WINDOW_WIDTH - mWidth, mHandle.getTop(), WINDOW_WIDTH, mHandle.getBottom());
					mBackground.layout(0, mHandle.getTop(), mHandle.getRight(), mHandle.getBottom());
					mContent.layout(0, getTop(), WINDOW_WIDTH, getBottom());
				}
				moveable = false;
				return true;
			}
		}
		invalidate();
		if (isOpen())
			return true;
		
		return false;
		}

}