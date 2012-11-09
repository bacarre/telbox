/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.widget.Toast;

public class NewsListManager {
	
	private Document doc;
	private Node firstChannel;
	private String title;
	private String link;
	private String description;
	private Date lastBuildDate;
	private List<NewsItem> news;
	
	/**
	 * Manage the list of news
	 * @param context the context the view is running in, through which it can access the current theme, resources, etc.
	 */
    public NewsListManager(Context context) {
    	if (HTTPUpdate(context)) {
    		news = new ArrayList<NewsItem>();
    	} else listUpdate();
    }
    
    /**
     * Update current DOM root from Internet
     * @param context the context the view is running in, through which it can access the current theme, resources, etc.s
     * @return true if an error occurred
     */
	private boolean HTTPUpdate(Context context) {
		HttpClient httpclient = new DefaultHttpClient();
        HttpResponse result = null;
		try {
			result = httpclient.execute(new HttpGet("http://esigetel.ruppen.fr/rss/"));
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(result.getEntity().getContent());
	        httpclient.getConnectionManager().shutdown();
		}  catch (ClientProtocolException e) {
			Toast.makeText(context ,"Une erreur est survenue dans le protocole de communication avec le serveur de news.", Toast.LENGTH_LONG).show();
			return true;
		} catch (IOException e) {
			Toast.makeText(context ,"Le serveur de news est inaccessible. Vérifiez votre connection internet.", Toast.LENGTH_LONG).show();
			return true;
		} catch (Exception e) {
			Toast.makeText(context ,"Une erreur est survenue lors de la tentative de récupération des news.", Toast.LENGTH_LONG).show();
			return true;
		}
		return false;
	}
	
	/**
	 * Update current list
	 */
	private void listUpdate() {
    	firstChannel = doc.getElementsByTagName("channel").item(0);
    	title = getNodeValue(getSubnode(firstChannel, "title"));
    	link = getNodeValue(getSubnode(firstChannel, "link"));
    	description = getNodeValue(getSubnode(firstChannel, "description"));
    	lastBuildDate = new Date(getNodeValue(getSubnode(firstChannel, "lastBuildDate")));
    	List<Node> items = getSubnodes(firstChannel, "item");
    	news = new ArrayList<NewsItem>();
    	for (int i = 0, n = items.size(); i < n; i++)
			news.add(new NewsItem(items.get(i)));
	}
	
	/**
	 * Get the subnode corresponding to a name.
	 * @param node the parent node
	 * @param string the name of the subnode
	 * @return the node you wanted
	 */
	private Node getSubnode(Node node, String string) {
		NodeList nodes = node.getChildNodes();
		for(int j = 0, n = nodes.getLength(); j < n; j++)
		{
			node = nodes.item(j);
			if (node.getNodeName().equals(string))
				return node;
		}
		return null;
	}
	
	/**
	 * Get all the subnodes corresponding to a name.
	 * @param node the parent node
	 * @param string the name of the subnodes
	 * @return the list of node corresponding to the name
	 */
	private List<Node> getSubnodes(Node node, String string) {
		List<Node> nodeList = new ArrayList<Node>();
		NodeList nodes = node.getChildNodes();
		for(int j = 0, n = nodes.getLength(); j < n; j++)
		{
			node = nodes.item(j);
			if (node.getNodeName().equals(string))
				nodeList.add(node);
		}
		return nodeList;
	}
	
	/**
	 * Get the string value inside the brackets of a node
	 * @param node the parent node of the string node
	 * @return the value of the string node
	 */
	private String getNodeValue(Node node) {
		if (node==null)
			return "";
		Node subnode = node.getFirstChild();
		if (subnode == null)
			return "";
		String string = subnode.getNodeValue();
		if (string == null)
			return "";
		return string;
	}

	/**
	 * Change the list of items to an array of NewsItem
	 * @return the array of NewItem
	 */
	public NewsItem[] getListedItems() {
		if (news == null)
			return new NewsItem [0];
		NewsItem[] items = new NewsItem [news.size()];
		for(int i = 0, n = news.size(); i < n; i++)
			items[i] = news.get(i);
		return items;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}
	public String getDescription() {
		return description;
	}

	public Date getLastBuildDate() {
		return lastBuildDate;
	}
}