package TeamTwoHTMLEditor.GUI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 4/6/13 Time: 3:05 PM The list
 * model that implements ListModel.java in order to have a nice display for the
 * JLIST of links in the main editor frame
 */
public class LinksListModel extends AbstractListModel{

	ArrayList<String> linksArray;

	public LinksListModel(ArrayList<String> linksArray){
		this.linksArray = linksArray;
	}

	@Override
	public int getSize(){
		return linksArray.size();  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Object getElementAt(int index){
		return linksArray.get(index);  //To change body of implemented methods use File | Settings | File Templates.
	}
}
