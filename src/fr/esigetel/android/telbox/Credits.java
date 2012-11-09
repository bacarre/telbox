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
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class Credits extends Activity{
	int WINDOWS_WIDTH;
	int WINDOWS_HEIGHT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WINDOWS_WIDTH = getWindowManager().getDefaultDisplay().getWidth();
		WINDOWS_HEIGHT =getWindowManager().getDefaultDisplay().getHeight();
		
		FrameLayout contentView = new FrameLayout(this);			//création du frame layout du menu
		contentView.setLayoutParams(new LayoutParams(-1, -1));
		contentView.setBackgroundColor(Color.TRANSPARENT);
		
		ImageView startBackground0 = new ImageView(this);
		startBackground0.setBackgroundColor(Color.BLACK);
		startBackground0.setLayoutParams(new LayoutParams(-1, -1));
		ImageView startBackground1 = new ImageView(this);
		startBackground1.setBackgroundDrawable(getResources().getDrawable(R.drawable.credit01));
		startBackground1.setLayoutParams(new LayoutParams(-1, -1));
		Animation startAlpha = new AlphaAnimation(0.0f, 1.0f);;
		startAlpha.setDuration(2000);
		startBackground0.startAnimation(startAlpha);
		startBackground1.startAnimation(startAlpha);
		startBackground0.setFocusable(false);
		startBackground1.setFocusable(false);
		contentView.addView(startBackground0);
		contentView.addView(startBackground1);
		TextView pwdb = new TextView(this);
		pwdb.setLines(17);
		pwdb.setTextSize(8);
		pwdb.setHorizontallyScrolling(true);
		pwdb.setTypeface(Typeface.MONOSPACE, 1);
		pwdb.setTextColor(Color.LTGRAY);
		pwdb.setGravity(Gravity.CENTER);
		pwdb.setText(
				" (                                        \n"+
				" )\\ )                               (     \n"+
				"(()/(      (  (      (   (      (   )\\ )  \n"+
				" /(_)) (   )\\))(    ))\\  )(    ))\\ (()/(  \n"+
				"(_))   )\\ ((_)()\\  /((_)(()\\  /((_) ((_)) \n"+
				"| _ \\ ((_)_(()((_)(_))   ((_)(_))   _| |  \n"+
				"|  _// _ \\\\ V  V // -_) | '_|/ -_)/ _` |  \n"+
				"|_|  \\___/ \\_/\\_/ \\___| |_|  \\___|\\__,_|  \n\n"+
				"    )        \n"+
				" ( /(  (     \n"+
				" )\\()) )\\ )  \n"+
				"((_)\\ (()/(  \n"+
				"| |(_) )(_)) \n"+
				"| '_ \\| || | \n"+
				"|_.__/ \\_, | \n"+
				"       |__/  \n");
		pwdb.setLayoutParams(new LayoutParams(-2, -2, Gravity.CENTER_HORIZONTAL|Gravity.TOP));
		Animation pwdbTranslate = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.05f);
		pwdbTranslate.setStartOffset(2000);
		pwdbTranslate.setDuration(3000);
		pwdbTranslate.setFillAfter(true);
		pwdbTranslate.setInterpolator(new OvershootInterpolator());
		pwdb.startAnimation(pwdbTranslate);
		contentView.addView(pwdb);
		
		TextView sexigetel = new TextView(this);
		sexigetel.setLines(16);
		sexigetel.setTextSize(2);
		sexigetel.setHorizontallyScrolling(true);
		sexigetel.setTypeface(Typeface.MONOSPACE, 1);
		sexigetel.setTextColor(Color.WHITE);
		sexigetel.setText(
"     SSSSSSSSSSSSSSS EEEEEEEEEEEEEEEEEEEEEEXXXXXXX       XXXXXXXYYYYYYY       YYYYYYY       GGGGGGGGGGGGGEEEEEEEEEEEEEEEEEEEEEETTTTTTTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEEELLLLLLLLLLL             \n"+
"   SS:::::::::::::::SE::::::::::::::::::::EX:::::X       X:::::XY:::::Y       Y:::::Y    GGG::::::::::::GE::::::::::::::::::::ET:::::::::::::::::::::TE::::::::::::::::::::EL:::::::::L             \n"+
"  S:::::SSSSSS::::::SE::::::::::::::::::::EX:::::X       X:::::XY:::::Y       Y:::::Y  GG:::::::::::::::GE::::::::::::::::::::ET:::::::::::::::::::::TE::::::::::::::::::::EL:::::::::L             \n"+
"  S:::::S     SSSSSSSEE::::::EEEEEEEEE::::EX::::::X     X::::::XY::::::Y     Y::::::Y G:::::GGGGGGGG::::GEE::::::EEEEEEEEE::::ET:::::TT:::::::TT:::::TEE::::::EEEEEEEEE::::ELL:::::::LL             \n"+
"  S:::::S              E:::::E       EEEEEEXXX:::::X   X:::::XXXYYY:::::Y   Y:::::YYYG:::::G       GGGGGG  E:::::E       EEEEEETTTTTT  T:::::T  TTTTTT  E:::::E       EEEEEE  L:::::L               \n"+
"  S:::::S              E:::::E                X:::::X X:::::X      Y:::::Y Y:::::Y  G:::::G                E:::::E                     T:::::T          E:::::E               L:::::L               \n"+
"   S::::SSSS           E::::::EEEEEEEEEE       X:::::X:::::X        Y:::::Y:::::Y   G:::::G                E::::::EEEEEEEEEE           T:::::T          E::::::EEEEEEEEEE     L:::::L               \n"+
"    SS::::::SSSSS      E:::::::::::::::E        X:::::::::X          Y:::::::::Y    G:::::G    GGGGGGGGGG  E:::::::::::::::E           T:::::T          E:::::::::::::::E     L:::::L               \n"+
"      SSS::::::::SS    E:::::::::::::::E        X:::::::::X           Y:::::::Y     G:::::G    G::::::::G  E:::::::::::::::E           T:::::T          E:::::::::::::::E     L:::::L               \n"+
"         SSSSSS::::S   E::::::EEEEEEEEEE       X:::::X:::::X           Y:::::Y      G:::::G    GGGGG::::G  E::::::EEEEEEEEEE           T:::::T          E::::::EEEEEEEEEE     L:::::L               \n"+
"              S:::::S  E:::::E                X:::::X X:::::X          Y:::::Y      G:::::G        G::::G  E:::::E                     T:::::T          E:::::E               L:::::L               \n"+
"              S:::::S  E:::::E       EEEEEEXXX:::::X   X:::::XXX       Y:::::Y       G:::::G       G::::G  E:::::E       EEEEEE        T:::::T          E:::::E       EEEEEE  L:::::L         LLLLLL\n"+
"  SSSSSSS     S:::::SEE::::::EEEEEEEE:::::EX::::::X     X::::::X       Y:::::Y        G:::::GGGGGGGG::::GEE::::::EEEEEEEE:::::E      TT:::::::TT      EE::::::EEEEEEEE:::::ELL:::::::LLLLLLLLL:::::L\n"+
"  S::::::SSSSSS:::::SE::::::::::::::::::::EX:::::X       X:::::X    YYYY:::::YYYY      GG:::::::::::::::GE::::::::::::::::::::E      T:::::::::T      E::::::::::::::::::::EL::::::::::::::::::::::L\n"+
"  S:::::::::::::::SS E::::::::::::::::::::EX:::::X       X:::::X    Y:::::::::::Y        GGG::::::GGG:::GE::::::::::::::::::::E      T:::::::::T      E::::::::::::::::::::EL::::::::::::::::::::::L\n"+
"   SSSSSSSSSSSSSSS   EEEEEEEEEEEEEEEEEEEEEEXXXXXXX       XXXXXXX    YYYYYYYYYYYYY           GGGGGG   GGGGEEEEEEEEEEEEEEEEEEEEEE      TTTTTTTTTTT      EEEEEEEEEEEEEEEEEEEEEELLLLLLLLLLLLLLLLLLLLLLLL");
		sexigetel.setLayoutParams(new LayoutParams(-2, -2, Gravity.CENTER));
		Animation sexigetelTranslate = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		sexigetelTranslate.setStartOffset(5000);
		sexigetelTranslate.setDuration(5000);
		sexigetelTranslate.setFillAfter(true);
		sexigetelTranslate.setInterpolator(new DecelerateInterpolator());
		sexigetel.startAnimation(sexigetelTranslate);
		contentView.addView(sexigetel);
		
		View finalBackground = new View(this);
		finalBackground.setBackgroundColor(Color.BLACK);
		finalBackground.setLayoutParams(new LayoutParams(-1, -1));
		Animation finalAlpha = new AlphaAnimation(0.0f, 1.0f);;
		finalAlpha.setFillBefore(true);
		finalAlpha.setStartOffset(13000);
		finalAlpha.setDuration(2000);
		finalAlpha.setFillAfter(true);
		finalBackground.startAnimation(finalAlpha);
		finalBackground.setFocusable(false);
		contentView.addView(finalBackground);
		
		View animationView = new CreditAnimation(this);
		animationView.setBackgroundColor(Color.TRANSPARENT);
		animationView.setLayoutParams(new LayoutParams(-1,-1));
		animationView.setFocusable(false);
		contentView.addView(animationView);
		
		contentView.setKeepScreenOn(true);
		
		setContentView(contentView);
		
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.credits); // lecture du son
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				finish();
			}
		});
	}
}
