package br.com.amil.business;

import static org.junit.Assert.*;

import org.junit.Test;

public class RankingBusinessTest {

	private static String LOG_PATH = "src/br/com/amil/resources/pre-dojo.log";
	private RankingBusiness rankingBusiness = new RankingBusiness();
	
	@Test
	public void testGenerateRanking() {
		rankingBusiness.generateRanking(LOG_PATH);
		assertTrue(true);
	}

}
