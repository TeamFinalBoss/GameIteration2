package controller.commands.armory;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.util.Inventoryable;
import controller.util.Selectable;
import model.director.AvatarInteractionManager;
import model.item.EquipSlot;

public class ArmoryDetails extends Observable implements Selectable, Inventoryable, Observer {

	private Node currentTree;
	private Map<EquipSlot, Node> map;
	private AvatarInteractionManager manager = AvatarInteractionManager.getInstance();
	
	public ArmoryDetails() {
		map = new HashMap<>();
		this.currentTree = buildTree();
	}
	
	public void down() {
		if(this.currentTree.getNext() != null) {
			this.currentTree = currentTree.getNext();
			alert();
		}
	}
	
	public void up() {
		if(this.currentTree.getPrevious() != null) {
			this.currentTree = currentTree.getPrevious();
			alert();
		}
		
	}
	
	public void next() {
		if(this.currentTree.getRight() != null) {
			this.currentTree = currentTree.getRight();
			alert();
		}
	}
	
	public void previous() {
		if(this.currentTree.getLeft() != null) {
			this.currentTree = currentTree.getLeft();
			alert();
		}
	}
	
	private void alert() {
		setChanged();
		notifyObservers();
	}

	public void confirm() {
		manager.unequipAtSlot(currentTree.getEquipSlot());
	}
	
	private Node buildTree() {
		Node head = new Node(0, EquipSlot.HEAD);
		Node chest = new Node(1, EquipSlot.TORSO);
		Node mainHand = new Node(2, EquipSlot.MAIN_HAND);
		Node offHand = new Node(3, EquipSlot.OFF_HAND);
		Node legs = new Node(4, EquipSlot.LEGS);
		Node feet = new Node(5, EquipSlot.FEET);
		
		head.setNext(chest);
		
		chest.setNext(legs);
		chest.setPrevious(head);
		chest.setLeft(mainHand);
		chest.setRight(offHand);
		
		mainHand.setRight(chest);
		offHand.setLeft(chest);
		
		legs.setNext(feet);
		legs.setPrevious(chest);
		feet.setPrevious(legs);
		
		map.put(EquipSlot.HEAD, head);
		map.put(EquipSlot.TORSO, chest);
		map.put(EquipSlot.MAIN_HAND, mainHand);
		map.put(EquipSlot.OFF_HAND, offHand);
		map.put(EquipSlot.FEET, feet);
		map.put(EquipSlot.LEGS, legs);
		
		return head;
	}
	
	
	
	private class Node {
		private Node left;
		private Node right;
		private Node previous;
		private Node next;
		private int currentSelection;
		private EquipSlot slot;
		private boolean checkedLeft;
		private boolean checkedRight;
		private boolean checkedNext;
		private boolean checkedPrevious;
		
		public Node(int selection, EquipSlot slot) {
			this.currentSelection = selection;
			this.slot = slot;
		}
		
		public EquipSlot getEquipSlot() {
			return this.slot;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public int getCurrentSelection() {
			return currentSelection;
		}

		public Node getRight() {
			return right;
		}

		public Node getPrevious() {
			return previous;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public Node changeToIndex(int currentIndex) {
			if(this.currentSelection == currentIndex) {
				return this;
			} else if(hasNoChildren()) {
				return null;
			} else {
				Node value = null;
				if(hasNext() && !checkedNext) {
					checkedNext = true;
					value = next.changeToIndex(currentIndex);
					if(value != null) {
						return value;
					}
				}
				
				if(hasRight() && !checkedRight) {
					checkedRight = true;
					value = right.changeToIndex(currentIndex);
					
					if(value != null) {
						return value;
					}
				}
				
				if(hasPrevious() && !checkedPrevious) {
					checkedPrevious = true;
					value = previous.changeToIndex(currentIndex);
					if(value != null) {
						return value;
					}
				}
				if(hasLeft() && !checkedLeft) {
					checkedLeft = true;
					value = left.changeToIndex(currentIndex);
					if(value != null) {
						return value;
					}
				}
				return null;
				
			}
		}
		private boolean hasNext() {
			return next != null;
		}
		
		private boolean hasPrevious() {
			return previous != null;
		}
		
		private boolean hasRight() {
			return right != null;
		}
		
		private boolean hasLeft() {
			return left != null;
		}
		private boolean hasNoChildren() {
			return !hasNext() && !hasRight() && !hasPrevious() && !hasLeft();
		}

		public void reset() {
			checkedLeft = false;
			checkedRight = false;
			checkedNext = false;
			checkedPrevious = false;
		}
		
	}



	@Override
	public int getCurrentIndex() {
		return this.currentTree.getCurrentSelection();
	}

	@Override
	public void update(Observable o, Object arg) {
		Selectable select = (Selectable)o;
		int currentIndex = select.getCurrentIndex();
		currentTree = currentTree.changeToIndex(currentIndex);
		reset();
		
	}

	private void reset() {
		for(Map.Entry<EquipSlot, Node> entry : map.entrySet()) {
			entry.getValue().reset();
		}
	}

	
}
