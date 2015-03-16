package model.map.pair;

/**
 * 
 * @author Michael, ChrisMoscoso
 *
 * @param <T0>
 * @param <T1> will be the coordinate pair
 */
public class Pair <T0, T1 extends CoordinatePair> {
	T0 left;
	T1 right;
	
	public Pair(T0 L, T1 R){
		left = L;
		right = R;
	}
	
	/**
	 * Sets the new left element of the Pair 
	 * 
	 * @author Michael Cohen
	 * @param L the new Left element  
	 */
	public void setObject(T0 L){	left = L; }
	
	/**
	 * Sets the new right element of the Pair
	 * 
	 * @author Michael Cohen
	 * @param R the new Right element
	 */
	public void setCoordPair(T1 R){	right = R; }
	
	/**
	 * Gets the left element of the Pair
	 * 
	 * @author Michael Cohen
	 * @return T0 the left element
	 */
	public T0 getObject(){ return left; }
	
	/**
	 * Gets the right element of the Pair
	 * 
	 * @author Michael Cohen
	 * @return T1 the right element
	 */
	public T1 getCoordPair(){ return right; }
	
}
