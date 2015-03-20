package controller.commands.sack;

import java.util.Observable;

import controller.util.IntegerObserver;
import controller.util.Selectable;

public class SackDetails extends Observable implements IntegerObserver, Selectable {
	private int currentSelection;
	private final int itemsPerRow = 5;
	private int displaySize;
	
	public SackDetails() {
		currentSelection = 0;
		update(50);
	}
	
	public SackDetails(int selection, int displaySize) {
		currentSelection = selection;
		update(displaySize);
	}

	public void next() {
		currentSelection = (currentSelection + 1) > (displaySize - 1) ? currentSelection : currentSelection + 1;
		alert();
	}
	
	public void previous() {
		currentSelection = currentSelection - 1 < 0 ?  currentSelection : currentSelection - 1;
		alert();
	}
	
	public void down() {
		currentSelection = currentSelection + itemsPerRow > (displaySize - 1) ?
				currentSelection : currentSelection + itemsPerRow;
		alert();
	}
	
	public void up() {
		currentSelection = currentSelection - itemsPerRow < 0 ?
				currentSelection : currentSelection - itemsPerRow;
		alert();
	}
	
	public void alert() {
		setChanged();
		notifyObservers();
	}
	
	public void update(int size) {
		int intermediate = size % itemsPerRow;
		if(intermediate != 0) {
			displaySize = itemsPerRow % intermediate;
		} else {
			displaySize = size;
		}
		/*
		if(size != 0) {
			displaySize = itemsPerRow % ( size % itemsPerRow ); 
		} else {
			displaySize = 25;
		}
		*/
	}

	@Override
	public int getCurrentIndex() {
		return this.currentSelection;
	}
}
