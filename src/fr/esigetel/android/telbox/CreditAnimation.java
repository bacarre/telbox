package fr.esigetel.android.telbox;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TextView;

public class CreditAnimation extends ScrollView{
	TextView text;	
	public CreditAnimation(Context context) {
		super(context);
		String creditsTxt =
				"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"Merci � :\n\n"+
						"l'�quipe de SEXIGETEL\n"+//BB
						"Bastien Carr�\n"+
						"Brice Ruppen\n"+
						"Etienne Miquel\n\n"+
						"Coordinateur d�veloppement\n"+//B
						"Brice Ruppen\n\n"+
						"D�veloppeurs :\n"+//B
						"Bastien Carr�\n"+
						"Brice Ruppen\n"+
						"Etienne Miquel\n\n"+
						"Chef d'orchestre :\n"+//B
						"Bastien Carr�\n\n"+
						"Compositeurs :\n"+//B
						"Bastien Carr�\n"+
						"Etienne Miquel\n\n"+
						"Arrangeurs :\n"+//B
						"Bastien Carr�\n"+
						"Brice Ruppen\n\n"+
						"Images :\n"+//B
						"Brice Ruppen\n\n"+
						"Organisation spatio-temporelle :\n"+//B
						"Etienne Miquel\n\n"+
						"V�rification de la Syntaxe phrasale :\n"+//B
						"Bastien Carr�\n\n"+
						"Responsable Formation Android :\n"+//B
						"Brice Ruppen\n\n"+
						"Responsable Rapport :\n"+//B
						"Etienne Miquel\n\n"+
						"Irresponsable :\n"+//B
						"Bastien Carr�\n\n"+
						"Voix Bugdroid :\n"+//B
						"Etienne Miquel\n\n"+
						"Cam�ra 1 :\n"+//B
						"Bastien Carr�\n\n"+
						"Cascadeur 1 :\n"+//B
						"Brice Ruppen\n\n"+
						"Figurant 1 :\n"+//B
						"Beno�t Kuhn\n\n"+
						"Producteur :\n"+//B
						"Salim Benayoune\n\n\n\n"+
						"Merci �galement �:\n"+//B
						"ESIGETEL\n"+
						"Nacef Berkoukchi\n"+
						"Tania Blanc\n"+
						"Salim Benayoune\n"+
						"Google\n"+
						"HP\n"+
						"DELL\n"+
						"TOSHIBA\n"+
						"Guitar Pro\n"+
						"Microsoft\n"+
						"Wikipedia\n"+
						"HTC\n"+
						"Archos\n"+
						"Christophe Lejeune\n"+
						"La salle 107\n"+
						"Jean-Paul\n"+
						"La K-fet\n"+
						"Le MC\n"+
						"La clim' de Brice\n"+
						"Le videoprojecteur\n"+
						"L'inventeur du Cap's\n"+
						"La Maison des associations\n"+
						"Kronenbourg\n"+
						"Eclipse\n"+
						"Adobe\n"+
						"Jean-Claude Van Damme\n"+
						"La Classe Am�ricaine\n"+
						"Les Shadoks\n"+
						"Laly\n"+
						"La Ville d'Avon\n"+
						"La Ville de Fontainebleau\n"+
						"La Communaut� de Communes\n"+
						"Le babyfoot\n"+
						"Les cigares La Paz\n"+
						"Les barres c�r�ales Sundy\n"+
						"L'eau\n"+
						"Le feu\n"+
						"La terre\n"+
						"L'air\n"+
						"Le Cinqui�me Element\n"+
						"Andr�i\n"+
						"Android\n"+
						"Bugdroid\n"+
						"Le Monopoly\n"+
						"Dan Istrate\n"+
						"gobarbra.com\n"+
						"Lamine Bagheera\n"+
						"Minecraft\n"+
						"Ac6\n"+
						"William\n"+
						"The Witcher 2\n"+
						"Black Ops\n"+
						"Le Chat d'Etienne\n"+
						"Nos parents\n"+
						"..."+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n"+
						"\n\n\n\n\n\n\n\n\n\n";
		text = new TextView(context);
		text.setText(creditsTxt);
		text.setBackgroundColor(Color.TRANSPARENT);
		text.setLayoutParams(new LayoutParams(-2,-1,Gravity.CENTER_HORIZONTAL));
		text.setGravity(Gravity.CENTER);
		text.setFocusable(false);
		addView(text);
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(10000);
					while(true) {
						Thread.sleep(20);
						autoScroll();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	private void autoScroll() {
		final ScrollView sv = (ScrollView) text.getParent();
		sv.post(new Runnable() {
			public void run() {
				if (text.getLineCount() > sv.getScrollY()/text.getLineHeight())
					sv.scrollTo(0, sv.getScrollY()+1);
			}
		});
	}
}
