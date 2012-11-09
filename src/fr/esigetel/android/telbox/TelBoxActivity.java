/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

//------------------------------------------------ACTIVITE PRINCIPALE-------------------------------//

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.esigetel.android.telbox.NewsView.State;

public class TelBoxActivity extends Activity {
	int WINDOW_WIDTH;
	int WINDOW_HEIGHT;
	int BANDEAU_WIDTH;
	int ENTETE_HEIGHT;
	
	public TelBoxActivity() {
		super();

	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WINDOW_WIDTH = getWindowManager().getDefaultDisplay().getWidth();
		WINDOW_HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
		BANDEAU_WIDTH = WINDOW_WIDTH/10;
		ENTETE_HEIGHT = WINDOW_HEIGHT/6;
        
        FrameLayout newsScreen = new FrameLayout(this);
        newsScreen.setLayoutParams(new LayoutParams(-1, -1));
        newsScreen.setBackgroundColor(Color.rgb(245, 245, 250));
        
      //-------VIEWS--------//
        
        TextView entete = new TextView(this);
        LayoutParams enteteParams = new LayoutParams(ENTETE_HEIGHT*3, ENTETE_HEIGHT);
        enteteParams.rightMargin = (WINDOW_WIDTH - BANDEAU_WIDTH - ENTETE_HEIGHT*3)/2;
        enteteParams.gravity = Gravity.TOP|Gravity.RIGHT;
        entete.setLayoutParams(enteteParams);
        entete.setBackgroundResource(R.drawable.header);
        
        final NewsView newsView = new NewsView(this);
        newsView.setLayoutParams(new LayoutParams(WINDOW_WIDTH - BANDEAU_WIDTH -3 , WINDOW_HEIGHT - ENTETE_HEIGHT, Gravity.BOTTOM|Gravity.RIGHT));
        newsView.setOrientation(LinearLayout.VERTICAL);
        newsView.setBackgroundColor(Color.TRANSPARENT);
        
        NewsListView newsList = new NewsListView(this);
        newsList.setLayoutParams(new LayoutParams(WINDOW_WIDTH - BANDEAU_WIDTH -3 , WINDOW_HEIGHT - ENTETE_HEIGHT, Gravity.BOTTOM|Gravity.RIGHT));
        newsList.setBackgroundColor(Color.TRANSPARENT);
        newsList.setCacheColorHint(Color.TRANSPARENT);
        newsList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				if (newsView.getState().equals(State.CLOSED)) {
					NewsItem item = (NewsItem) adapterView.getItemAtPosition(position);
					newsView.setItem(item);
					newsView.setState(State.OPENING);
				}
			}
        });
        
        final Bandeau bandeau = new Bandeau(this, BANDEAU_WIDTH,  Bandeau.LEFT, WINDOW_WIDTH, WINDOW_HEIGHT);
        bandeau.setBackgroundColor(Color.TRANSPARENT);
		
      //------AJOUT DES VIEWS-------//
        
        newsScreen.addView(entete);
        newsScreen.addView(newsList);
        newsScreen.addView(newsView);
        newsScreen.addView(bandeau);
        
        setContentView(newsScreen);
    }
    
    public void onBackPressed(){ // Lors de l'appui sur la touche BACK
   	 Intent intent = new Intent(TelBoxActivity.this, Gb.class); // On lance le Splash de sortie
   	 startActivity(intent);
   	 finish(); //...et on ferme cette activité
   	 return;
   }
}