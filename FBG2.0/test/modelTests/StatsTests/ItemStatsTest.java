package modelTests.StatsTests;

import static org.junit.Assert.*;
import model.stats.ItemStats;

import org.junit.Test;

public class ItemStatsTest {

	@Test
	public void test() {
		ItemStats s = new ItemStats();
		System.out.println(s.toXML());
	}

}
