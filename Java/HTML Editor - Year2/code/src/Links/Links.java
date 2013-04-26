package TeamTwoHTMLEditor.Links;

import TeamTwoHTMLEditor.GUI.LinksListModel;
import TeamTwoHTMLEditor.HTMLFile;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Maintains a list of HTML links parsed from the HTMLFile this class
 * represents.
 */
public class Links {

    private final HTMLFile file;
    private final ArrayList<String> links;

    public Links(HTMLFile f) {
        file = f;
        links = new ArrayList<String>();
        this.refresh();
    }

    /**
     * Re-parses the entire file for all HTML links.
     */
    public void refresh() {
        String contents;

        contents = file.getFileContents();
        links.clear();
        if (contents != "") {
            parseContents(contents);
        }
    }

    /**
     * @param c - whatever contents that you want parsed
     */
    public void refresh(String c) {
        String contents = c;
        links.clear();
        if (contents != "") {
            parseContents(contents);
        }
    }

    /**
     * Handles processing the HTMLFile for HTML links contained within.
     *
     * @param contents - the contents of the file that you want to find the
     *                 links of
     */
    private void parseContents(String contents) {
        Pattern p = Pattern.compile("href=\"(.*?)\"");
        Matcher m = p.matcher(contents);
        String url = null;
        while (m.find()) {
            links.add(m.group(1));
        }
    }

    /**
     * Produces a list of HTML links ordered according to a LinkStrategy. Note,
     * that a copy of the original list is used to preserve the original
     * ordering of the HTML links.
     *
     * @param s The LinkStrategy to sort by.
     * @return The links as sorted by the specified LinkStrategy.
     */
    public ArrayList<String> display(LinkStrategy s) {
        return s.display((ArrayList<String>) links.clone());
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public LinksListModel getLinksModel() {
        return new LinksListModel(links);
    }

}
