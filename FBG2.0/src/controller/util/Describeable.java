package controller.util;


/**
 * @author Kyle Kyrazis
 * 
 * This class is used so the view knows what to output. (EG options for a menu)
 *
 */
public interface Describeable {
	/**
	 * Returns a list of strings to be displayed.
	 * @return List<String>
	 */
	public String[] getDescription();

	public int getCurrentIndex();
}
