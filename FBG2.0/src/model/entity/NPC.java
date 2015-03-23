package model.entity;

import java.util.List;

import model.item.Takeable;

public interface NPC{
	public boolean traverseDialogue(int selectin);
	public int checkPayment(int position);
	public Takeable sellItem(int position, Entity purchaser);
	public void buyItem(Takeable item, Entity purchaser);
	public void resetDialogue();
	public String getDialogueMessage();
	public List<String> getDialogueOptions();
	public void setLink(int linkNum);
	public int getLink();
	public List<Takeable> getStoreContents();
	public List<Takeable> getFullStoreContents();
	public void buyItem(Takeable item);
	public boolean getFriendly();
	public void setFriendly(boolean next);

}
