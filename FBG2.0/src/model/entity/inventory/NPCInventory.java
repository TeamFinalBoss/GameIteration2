package model.entity.inventory;

import model.entity.Entity;
import java.util.List;
import model.item.Takeable;

public class NPCInventory extends Inventory {
	Storefront myStore;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	NPCInventory(int size, Entity owner){
		super(size, owner);
		myStore = new Storefront();
	}
	
	/* -------------------- ACCESSORS -------------------- */
	public List<Takeable> shopContents(){
		return myStore.contents();
	}
	
	/* -------------------- MUTATORS -------------------- */
	public int payForItem(int position){
		return myStore.payForItem(position);
	}
	public Takeable buyItem(int position){
		return myStore.buyItem(position);
	}
	public int sellItem(Takeable item){
		return myStore.sellItem(item);
	}
}
