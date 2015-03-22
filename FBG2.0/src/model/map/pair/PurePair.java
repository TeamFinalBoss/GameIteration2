package model.map.pair;

public class PurePair <S,T>{
	S first;
	T second;
	
	public PurePair(){}
	public PurePair(S next, T nextest){
		first = next;
		second = nextest;
	}
	public void setFirst(S next){
		first = next;
	}
	public void setSecond(T next){
		second = next;
	}
	public S getFirst(){
		return first;
	}
	public T getSecond(){
		return second;
	}
}
