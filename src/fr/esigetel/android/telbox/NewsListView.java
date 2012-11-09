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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsListView extends ListView{
	NewsListManager listManager;

	/**
	 * Construct a new NewsListView with a list of NewsItem from the news server.
	 * @param context the context the view is running in, through which it can access the current theme, resources, etc.
	 */
	public NewsListView(Context context) {
		super(context);
		listManager = new NewsListManager(getContext());
		setAdapter(new ArrayAdapter<NewsItem>(getContext(), R.layout.newsrow, listManager.getListedItems()));
	}
}
