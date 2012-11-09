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
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
	
public class GameView extends View{
		Drawable logo; 
		boolean moveable;
		int width;
		int height;
		int logoWidth;
		int logoHeight;
		TextView gameText;
		MediaPlayer mp;
		
		public GameView(Context cont, int WINDOW_WIDTH, int WINDOW_HEIGHT) {
			super(cont);
			Toast.makeText(getContext(),"Jeu de Capps en travaux. Amusez-vous avec le logo en attendant.", Toast.LENGTH_LONG).show();
			width = WINDOW_WIDTH;
			height = WINDOW_HEIGHT;
			logo = getResources().getDrawable(R.drawable.logonoir);
			mp = MediaPlayer.create(getContext(), R.raw.gameover);
			logoWidth = width/2;
			logoHeight = height/10;
			logo.setBounds(width/2-logoWidth/2, height/3-logoHeight/2, width/2+ logoWidth/2, height/3+logoHeight/2);
			moveable = false;
		}
		
		public void onDraw(Canvas canvass) {
			logo.draw(canvass);
			setBackgroundColor (Color.rgb(255, 255, 255));
			super.onDraw(canvass);
			//gameText = new TextView(getContext());
			//gameText.setText("Cette Application est en travaux, nous sommes donc dans l'incapacité de vous fournir le jeu pour le moment. Merci de votre compréhension");
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			Point position = new Point((int) event.getX(), (int) event.getY());
			
			int action = event.getAction();
			
			switch(action) {
			case MotionEvent.ACTION_DOWN:
				if(position.x > logo.getBounds().left && position.x < logo.getBounds().right && position.y > logo.getBounds().top && position.y < logo.getBounds().bottom) {
					
					moveable = true;
					mp.start();
				}	
			break;
			case MotionEvent.ACTION_MOVE:	
				if(position.x > logoWidth/2 && position.x < width - logoWidth/2 && position.y > logoHeight/2 && position.y < height - logoHeight/2 && moveable == true)
					logo.setBounds(position.x - logoWidth/2, position.y - logoHeight/2, position.x + logoWidth/2, position.y + logoHeight/2);		
			break;
			case MotionEvent.ACTION_UP:
				moveable = false;
				mp.pause();
			}
			invalidate();
			return true;
		}	
	}

