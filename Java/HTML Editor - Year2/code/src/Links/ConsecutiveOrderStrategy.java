package TeamTwoHTMLEditor.Links;

import java.util.ArrayList;

/**
 * Orders a given list of links into the order they are presented in the
 * parsed file.
 *
 * The ordering of the links is preserved in the order that they were parsed.
 * Because of this, this class currently does no actual work in re-ordering
 * the elements of the passed in links.  This class makes the assumption
 * that a new copy of the original list is always passed in each time, and that
 * the original list is not modified structurally.
 */
public class ConsecutiveOrderStrategy implements LinkStrategy{

	/**
	 * The links that are passed in are already maintained in the order that
	 * the file was parsed in.  Therefore, no special ordering is required.
	 *
	 * @param links The links to sort.
	 * @return		The links as sorted in the order they were found in the
	 * 				file.
	 */
	@Override
	public ArrayList<String> display(ArrayList<String> links){
		return links;
	}
}
