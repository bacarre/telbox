/**
** Programme : Tel'Box
**
** Auteur : Sexigetel (Etienne Miquel, Brice Ruppen, Bastien Carré)
** Date : 2011/06/28
** Date MAJ : 2011/06/28
** Version : 1.0
**/

package fr.esigetel.android.telbox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NewsItem {
	
	private String title;
	private String link;
	private String pubDate;
	private String description;
	private String content;
	
	@Deprecated
	/**
	 * Constructs a new NewsItem using empty values. Its use is not recommended.
	 */
	public NewsItem() {
		title = "Empty news";
		link = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
		pubDate = dateFormat.format(new Date());;
		description = "There is no description here";
		content = null;
	}
	
	/**
	 * Constructs a new NewsItem using an item node to get its values.
	 * @param node from witch the values are sent
	 */
	public NewsItem(Node node) { // mise en forme de l'article 
		title = getNodeValue(getSubnode(node, "title"));
		link = getNullNodeValue(getSubnode(node, "link"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
		Date date = new Date(getNodeValue(getSubnode(node, "pubDate")));
		pubDate = dateFormat.format(date);
		description = getNodeValue(getSubnode(node, "description"));
		content = getNullNodeValue(getSubnode(node, "content:encoded"));
	}
	
	/**
	 * Get the from subnode corresponding to a name.
	 * @param node the parent node
	 * @param string the name of the subnode
	 * @return the node you wanted
	 */
	private Node getSubnode(Node node, String string) {
		NodeList nodes = node.getChildNodes();
		for(int j = 0, n = nodes.getLength(); j < n; j++) {
			node = nodes.item(j);
			if (node.getNodeName().equals(string))
				return node;
		}
		return null;
	}
	
	/**
	 * Get the string value inside the brackets of a node
	 * @param node the parent node of the string node
	 * @return the value of the string node or "" if no string is found
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
	 * Get the string value inside the brackets of a node
	 * @param node the parent node of the string node
	 * @return the value of the string node or null if no string is found
	 */
	private String getNullNodeValue(Node node) {
		if (node==null)
			return null;
		Node subnode = node.getFirstChild();
		if (subnode == null)
			return null;
		String string = subnode.getNodeValue();
		if (string == null)
			return null;
		return string;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getPubDate() {
		return pubDate;
	}
	public String getDescription() {
		return description;
	}
	public String getContent() {
		return content;
	}
}
