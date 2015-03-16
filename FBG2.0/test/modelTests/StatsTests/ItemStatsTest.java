package modelTests.StatsTests;

import static org.junit.Assert.*;
import model.stats.ItemStats;
import model.stats.PlayerStats;

import org.junit.Test;

public class ItemStatsTest {

	@Test
	public void ItemStatTest() {
		ItemStats s = new ItemStats();
		System.out.println(s.toXML());
	}
	
	

}
