/**
 * 
 */
package deb.camel;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author debmalyajash
 *
 */
public class FileCopierWithCamelTest {

	/**
	 * 
	 */
	private static final String FILE_OPTION = "file:";

	/**
	 * Test method for {@link deb.camel.FileCopierWithCamel#routeFile(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRouteFile() {
		String inputDir = "src/main/resources/data";
		String outputDir = "src/main/resources/out";
		String options = "?noop=true";
		FileCopierWithCamel.routeFile(FILE_OPTION+inputDir+options,FILE_OPTION+outputDir);
		
		checkDirectories(inputDir,outputDir);
	}
	
	private void checkDirectories(final String inputDir,final String outputDir) {
		File inputFiles = new File(inputDir);
		File outputFiles = new File(outputDir);
		Assert.assertArrayEquals(inputFiles.list(), outputFiles.list());
	}

}
