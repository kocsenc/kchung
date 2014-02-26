package TeamTwoHTMLEditor.Links;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts a list of links in Alphanumeric order.
 *
 * Given a list of HTML links, AlphaStrategy will sort them in Alphanumeric
 * order, using the Collections library of Java to handle the sorting. This
 * class makes the assumption that the list passed in can be altered freely,
 * and will not make or return a copy of the list passed in.
 */
public class AlphaStrategy implements LinkStrategy{

	/**
	 * Sorts the given list of HTML links in Alphanumeric order.
	 *
	 * @param links		The links to sort.
	 * @return			The sorted list of links in Alphanumeric Order.
	 */
	@Override
	public ArrayList<String> display(ArrayList<String> links){
		Collections.sort(links);
		return links;
	}
}
