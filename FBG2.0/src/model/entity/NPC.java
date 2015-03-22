package model.entity;

import java.util.List;

import model.item.Takeable;

public interface NPC {
	public boolean traverseDialogue(int selectin);
	public int checkPayment(int position);
	public Takeable sellItem(int position);
	public int buyItem(Takeable item);
	public void resetDialogue();
	public String getDialogueMessage();
	public List<String> getDialogueOptions();
	public void setLink(int linkNum);
	public int getLink();
}
