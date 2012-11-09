/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class Splash extends Activity{

	private static final int STOPSPLASH = 0;
	int WINDOWS_WIDTH;
	int WINDOWS_HEIGHT;

	//-----Handler------//
	private Handler splashHandler = new Handler() { // on gère le lancement différé du menu avec un HANDLER
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case STOPSPLASH:
				//enleve le SplashScreen de la view
				Intent intent = new Intent(Splash.this, TelBoxActivity.class); //Lancement du menu et destruction du splash
				startActivity(intent);
				finish();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WINDOWS_WIDTH = getWindowManager().getDefaultDisplay().getWidth();  //taille de l'écran pour adaptation a la résolution
		WINDOWS_HEIGHT =getWindowManager().getDefaultDisplay().getHeight();

		FrameLayout mySplash = new FrameLayout(this);			//création du frame layout du menu
		mySplash.setLayoutParams(new LayoutParams(-1, -1));
		mySplash.setBackgroundColor(Color.rgb(245, 245, 250));
		
		//----------- VUES STATIQUES----------//

		SplashBackground background = new SplashBackground(Splash.this, WINDOWS_WIDTH, WINDOWS_HEIGHT);
		background.setLayoutParams(new LayoutParams(-1,-1));
		background.setBackgroundColor(Color.TRANSPARENT);

		ImageView titre = new ImageView(this); // image de fond
		titre.setImageResource(R.drawable.telbox);
		LayoutParams myLayout;
		if (WINDOWS_WIDTH < WINDOWS_HEIGHT)
			myLayout = new LayoutParams((int) (WINDOWS_WIDTH*0.6),WINDOWS_HEIGHT/4, Gravity.CENTER_HORIZONTAL);
		else
			myLayout = new LayoutParams((int) (WINDOWS_WIDTH*0.3),WINDOWS_HEIGHT/8, Gravity.CENTER_HORIZONTAL);
		myLayout.topMargin = WINDOWS_WIDTH/4 - 10;
		titre.setLayoutParams(myLayout);

		ImageView sign = new ImageView(this); // image de fond
		sign.setImageResource(R.drawable.pwdb);
		LayoutParams myLayoutxt = new LayoutParams((int) (WINDOWS_WIDTH*0.75), WINDOWS_HEIGHT/4, Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
		if (WINDOWS_WIDTH < WINDOWS_HEIGHT)
			myLayoutxt.bottomMargin = WINDOWS_HEIGHT/2 - WINDOWS_HEIGHT/8;
		else
			myLayoutxt.bottomMargin = WINDOWS_WIDTH/10;
		sign.setLayoutParams(myLayoutxt);
		
		ProgressCircle progc = new ProgressCircle(this);
		LayoutParams myLayoutbar = new LayoutParams(WINDOWS_WIDTH/7,WINDOWS_WIDTH/7,Gravity.RIGHT|Gravity.BOTTOM);
		myLayoutbar.rightMargin = WINDOWS_WIDTH/8;
		myLayoutbar.bottomMargin = WINDOWS_WIDTH/8;
		progc.setLayoutParams(myLayoutbar);
		progc.setBackgroundDrawable(getResources().getDrawable(R.drawable.spinner_red));
		progc.startAnimation(AnimationUtils.loadAnimation(this,R.anim.spinner));
		
		//------AJOUT DES VIEWS-------//
		mySplash.addView(background);
		mySplash.addView(titre);
		mySplash.addView(sign);
		mySplash.addView(progc);
		setContentView(mySplash); // affichage de l'activité

		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.telboxsong); // lecture du son
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				Message msg = new Message();
				msg.what = STOPSPLASH;
				splashHandler.sendMessage(msg); //on envoi le STOPSLASH au Handler pour passer a l'activité suivante après  la fin de musique
			}
		});
	}
}