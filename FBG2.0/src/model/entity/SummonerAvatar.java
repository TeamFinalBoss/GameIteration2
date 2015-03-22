package model.entity;

import model.map.pair.CoordinatePair;

public class SummonerAvatar extends SummonerEntity{
	protected String setType(){
		return "avatar";
	}
	public SummonerAvatar(){
		super("The Summoner", "A myserious being from a distant plane", new CoordinatePair(1,1));
	}
}
