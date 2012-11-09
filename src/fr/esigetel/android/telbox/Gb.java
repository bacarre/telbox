/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

public class Gb extends Activity{

	int WINDOWS_WIDTH;
    int WINDOWS_HEIGHT;
	FrameLayout myGb;
	private static final int STOPAPP = 0;
	private static final long LASTTIME = 2000;
			    private Handler ExitHandler = new Handler() { //fonctionnement similaire au splash screen
			        @Override
			        public void handleMessage(Message msg) {
			            switch (msg.what) {
			                case STOPAPP:
			                    //fini l'activité
			                    
			                    finish();
			                    break;
			            }
			            super.handleMessage(msg);
			        }
			    };	
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WINDOWS_WIDTH = getWindowManager().getDefaultDisplay().getWidth();  //taille de l'écran pour adaptation a la résolution
        WINDOWS_HEIGHT =getWindowManager().getDefaultDisplay().getHeight();


        FrameLayout myGb = new FrameLayout(this);			
        myGb.setLayoutParams(new LayoutParams(-1, -1));
        
        ImageView background = new ImageView(this); // image de fond
        background.setBackgroundColor(Color.argb(250, 0, 0, 0));
        
        background.setLayoutParams(new LayoutParams(-1, -1));
	
        ImageView bugdroid = new ImageView(this); // bandeau de passage d'une vue a l'autre
        bugdroid.setImageResource(R.drawable.bugdroid);
        bugdroid.setLayoutParams(new LayoutParams(WINDOWS_WIDTH/2,WINDOWS_HEIGHT/2, Gravity.CENTER ));
        
        myGb.addView(background);
        myGb.addView(bugdroid);
        setContentView(myGb);
        
        String[] Rd = getResources().getStringArray(R.array.Random_array); // Un tableau contenant les chaines a choisir aléatoirement
        Random randomNumber = new Random();
        int nb = randomNumber.nextInt(Rd.length);
        
        String s = Rd[nb]; // choix d'une chaine au hasard
        
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        Message msg = new Message();
        msg.what = STOPAPP;
        ExitHandler.sendMessageDelayed(msg, LASTTIME);
        	
    }

}
