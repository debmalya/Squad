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
	 * Test method for {@link deb.camel.FileCopierWithCamel#routeFile(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRouteFile() {
		String inputDir = "src/main/resources/data";
		String outputDir = "src/main/resources/out";
		String options = "?noop=true";
		FileCopierWithCamel.routeFile("file:"+inputDir+options,"file:"+outputDir);
		
		File inputFiles = new File(inputDir);
		File outputFiles = new File(outputDir);
		
		Assert.assertArrayEquals(inputFiles.list(), outputFiles.list());
	}

}
