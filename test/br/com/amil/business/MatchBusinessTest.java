package br.com.amil.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.amil.utils.FileUtil;

public class MatchBusinessTest {

	private MatchBusiness matchBusiness = new MatchBusiness();
	
	@Test
	public void testCreateMatches() {
		matchBusiness.createMatches(generateFileRows());
		assertEquals(2, matchBusiness.getMatches().size());
		assertEquals("11348965", matchBusiness.getMatches().get(0).getNameMatch().toString());
		assertEquals(3, matchBusiness.getMatches().get(0).getPlayers().size());
		assertEquals("123456", matchBusiness.getMatches().get(1).getNameMatch().toString());
		assertEquals(4, matchBusiness.getMatches().get(1).getPlayers().size());
	}
	
	@Test
	public void testCreateMatchesEmptyFile() {
		matchBusiness.createMatches(new ArrayList<String>());
		assertEquals(0, matchBusiness.getMatches().size());
	}
	
	
	private List<String> generateFileRows(){
		return FileUtil.readFileRows("test/br/com/amil/resources/pre-dojo.log");
	}

}
