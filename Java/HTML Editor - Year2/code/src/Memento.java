package TeamTwoHTMLEditor;

/**
 * Author:      Grant Kurtz
 */
public class Memento{

	private final String previousState;

	public Memento(String previousState){
		this.previousState = previousState;
	}

	public String getState(){
		return previousState;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Memento))
			return false;
		Memento other = (Memento) obj;
		return other.getState().equals(getState());
	}
}
