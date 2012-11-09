/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

//---------------------------------------------VUES DES NEWS------------------------------------//

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Xml.Encoding;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewsView extends LinearLayout{
	
	private State state;
	private NewsItem item;
    public enum State {
        CLOSED,
        OPEN,
        OPENING,
    };

    /**
     * Constructs a new NewsView.
     * @param context the context the view is running in, through which it can access the current theme, resources, etc.
     */
	public NewsView(Context context) {
		super(context);
		state = State.CLOSED;
		item = null;
	}
	
	/**
	 * Draws a page containing the news in the following format :
	 * <br><b>Title of the news</b>
	 * <br><i>Horizontal line separator</i>
	 * <br><i>Publication date</i>
	 * <br>TextView of the description or WebView of the content of the news.
	 * <br>Bouton : Fermer
	 * @param canvas the canvas on which the background will be drawn
	 */
	protected void onDraw(Canvas canvas) {
		if (state.equals(State.OPENING) && item != null) {
			setBackgroundColor(Color.rgb(245, 245, 250));
			
			TextView title = new TextView(getContext());
			title.setLayoutParams(new LayoutParams(-1, -2, 0));
			title.setText(item.getTitle());
			title.setTextSize(20);
			title.setTextColor(Color.BLACK);
			title.setTypeface(Typeface.SANS_SERIF, 1);
			title.setGravity(Gravity.CENTER);
			title.setOnLongClickListener(new OnLongClickListener() {
				public boolean onLongClick(View v) {
					if (item.getLink() == null)
						return false;
					
					try {(new URL(item.getLink())).openConnection().connect();
					} catch (MalformedURLException e) {
						Toast.makeText(getContext(),"Erreur : Le lien n'est pas une adresse web.", Toast.LENGTH_SHORT).show();
					} catch (IOException e) {
						Toast.makeText(getContext(),"Erreur : Page internet inaccessible", Toast.LENGTH_SHORT).show();
					}
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
					getContext().startActivity(intent);
					return true;
				}
			});
			addView(title);
			
			View separator = new View(getContext());
			LayoutParams separams = new LayoutParams(-1, -2, 0);
			separams.height = 2;
			separator.setLayoutParams(separams);
			separator.setBackgroundColor(Color.GRAY);
			addView(separator);
			
			TextView date = new TextView(getContext());
			date.setLayoutParams(new LayoutParams(-1, -2, 0));
			date.setText(item.getPubDate()+" ");
			date.setTextSize(10);
			date.setTextColor(Color.GRAY);
			date.setTypeface(Typeface.SANS_SERIF, 0);
			date.setGravity(Gravity.RIGHT);
			addView(date);
			
			String content = item.getContent();
			if (content == null) {
				TextView description = new TextView(getContext());
				description.setLayoutParams(new LayoutParams(-1, -2, 1));
				description.setText(item.getDescription());
				description.setTextSize(16);
				description.setTextColor(Color.DKGRAY);
				addView(description);
			} else {
				WebView contentView = new WebView(getContext());
				contentView.setLayoutParams(new LayoutParams(-1, -2, 1));
				contentView.loadData(content, "text/html", Encoding.UTF_8.name());
				contentView.setBackgroundColor(Color.TRANSPARENT);
				addView(contentView);
			}
			
			Button button = new Button(getContext());
			LayoutParams buttonParams = new LayoutParams(-2, -2, 0);
			buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
			button.setLayoutParams(buttonParams);
			button.setText("   Fermer   ");
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					setState(State.CLOSED);
				}
			});
			addView(button);
			state = State.OPEN;
		}
		super.onDraw(canvas);
	}
	

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		setBackgroundColor(Color.TRANSPARENT);
		removeAllViews();
		invalidate();
	}

	public NewsItem getItem() {
		return item;
	}

	public void setItem(NewsItem item) {
		this.item = item;
	}
}
