/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

//------------------------------------------AGENCEMENT MENU CAPPS---------------------------//

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

public class CappsMenu extends FrameLayout{
	
	public CappsMenu(Context context, int bandeau_width, int window_width, int window_height) {
		super(context);
		int capsRadius;
		int horizontalCenter;
		LayoutParams capsParams;
		LayoutParams optParams;
		LayoutParams credParams;
		
		if (window_width < window_height) {//portrait mode
			capsRadius = (window_width - bandeau_width)*3/7;
			horizontalCenter = (window_width - bandeau_width)/2;
			capsParams = new LayoutParams(capsRadius*2, capsRadius*2, Gravity.LEFT|Gravity.TOP);
			capsParams.leftMargin = horizontalCenter - capsRadius;
			capsParams.topMargin = horizontalCenter - capsRadius;
			optParams = new LayoutParams(capsRadius, capsRadius/2, Gravity.LEFT|Gravity.BOTTOM);
			optParams.leftMargin = horizontalCenter - capsRadius/2;
			optParams.bottomMargin = (window_height - capsRadius*2)/2;
			credParams = new LayoutParams(capsRadius, capsRadius/2, Gravity.LEFT|Gravity.TOP);
			credParams.leftMargin = horizontalCenter - capsRadius/2;
			credParams.topMargin = window_height - (window_height - capsRadius*2)/2;
		} else {//landscape mode
			capsRadius = window_height*2/5;
			horizontalCenter = (window_width - bandeau_width)/2;
			capsParams = new LayoutParams(capsRadius*2, capsRadius*2, Gravity.LEFT|Gravity.TOP);
			capsParams.leftMargin = horizontalCenter - capsRadius;
			capsParams.topMargin = 0;
			optParams = new LayoutParams(capsRadius*2, capsRadius, Gravity.LEFT|Gravity.TOP);
			optParams.leftMargin = horizontalCenter - capsRadius;
			optParams.topMargin = window_height/2 + capsRadius*3/4;
			credParams = new LayoutParams(capsRadius*2, capsRadius, Gravity.LEFT|Gravity.TOP);
			credParams.leftMargin = horizontalCenter;
			credParams.topMargin = window_height/2 + capsRadius*3/4;
		}
		
		CappsView capsATourner = new CappsView(getContext(), capsRadius*2, capsRadius*2);
		capsATourner.setLayoutParams(capsParams);
		addView(capsATourner);
		
		CappsMenuButton optionsbutt = new CappsMenuButton(getContext(), R.drawable.optbuttoff, R.drawable.optbutton, capsRadius/2);
		optionsbutt.setLayoutParams(optParams);
		addView(optionsbutt);
		
		CappsMenuButton creditsbutt = new CappsMenuButton(getContext(), R.drawable.credbuttoff, R.drawable.credbutton, capsRadius/2);
		creditsbutt.setLayoutParams(credParams);
		creditsbutt.addOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), Credits.class); //Lancement du menu et destruction du splash
				getContext().startActivity(intent);
			}
		});
		addView(creditsbutt);
	}
}
