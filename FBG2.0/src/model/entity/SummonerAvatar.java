package model.entity;

import model.map.pair.CoordinatePair;

public class SummonerAvatar extends SummonerEntity implements Avatar{
	public SummonerAvatar(String objectName, String description, CoordinatePair location){
		super(objectName, description, location);
	}
}
