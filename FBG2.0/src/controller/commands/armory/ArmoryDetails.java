package controller.commands.armory;

import java.util.Observable;

import controller.commands.util.Inventoryable;
import controller.util.Selectable;
import model.director.AvatarInteractionManager;
import model.item.EquipSlot;

public class ArmoryDetails extends Observable implements Selectable, Inventoryable {

	private Node currentTree;
	private AvatarInteractionManager manager = AvatarInteractionManager.getInstance();
	
	public ArmoryDetails() {
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
		
		return head;
	}
	
	
	
	private class Node {
		private Node left;
		private Node right;
		private Node previous;
		private Node next;
		private int currentSelection;
		private EquipSlot slot;
		
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
		
	}



	@Override
	public int getCurrentIndex() {
		return this.currentTree.getCurrentSelection();
	}

	
}
