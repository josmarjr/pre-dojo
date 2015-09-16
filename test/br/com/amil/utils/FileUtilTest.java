package br.com.amil.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.amil.utils.FileUtil;

public class FileUtilTest {

	@Test
	public void testReadFileRowsWithFileFound() {
		String path = "test/br/com/amil/resources/pre-dojo.log";
		List<String> rows = FileUtil.readFileRows(path);
		assertNotNull(rows);
		assertEquals(rows.size(), 4);
	}
	
	@Test(expected = RuntimeException.class)
	public void testReadFileRowsWithFileNotFound() {
		String path = "test/br/com/amil/resources/pre-dojo-1.log";
		FileUtil.readFileRows(path);
	}

}
