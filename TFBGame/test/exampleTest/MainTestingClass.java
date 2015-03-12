package exampleTest;

/**
 * @author Kyle Kyrazis
 * 
 * This is a test dependency class
 *
 */

public class MainTestingClass {
	private DependencyInterface face;
	private boolean state;
	
	public MainTestingClass() {
		state = false;
	}
	
	public void setFace(DependencyInterface face) {
		this.face = face;
	}
	
	public boolean getState() {
		return state;
	}
	
	public void forwardARequest() {
		face.doSomething();
	}
	
	public void resetState() {
		state = false;
	}
	
	public void performSomeLogicForState() {
		if(face.returnSomething() == 4) {
			if(face.returnSomething() > 7) {
				state = true;
			}
		} else {
			face.doSomething();
		}
	}
	
	public int thisIsSomeAlgorithm(int value) {
		value *= value;
		value += 1;
		return value;
	}
	
	public void thisWillThrowAnException() {
		throw new IllegalArgumentException("You are dumb");
	}
}
