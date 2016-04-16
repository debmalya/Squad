/**
 * 
 */
package deb.camel;

import java.io.File;
import java.util.Optional;

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
		Optional<String> options = Optional.of("?noop=true");
		
		FileCopierWithCamel.routeFile(FILE_OPTION+inputDir,FILE_OPTION+outputDir,null);		
		checkDirectories(inputDir,outputDir);
		
		FileCopierWithCamel.routeFile(FILE_OPTION+inputDir,FILE_OPTION+outputDir,options);		
		checkDirectories(inputDir,outputDir);
		
		FileCopierWithCamel.routeFile(FILE_OPTION+inputDir,FILE_OPTION+outputDir,Optional.of(""));		
		checkDirectories(inputDir,outputDir);
		
//		options ="?noop=false";
//		FileCopierWithCamel.routeFile(FILE_OPTION+inputDir,FILE_OPTION+outputDir,options);		
//		checkDirectories(inputDir,outputDir);
	}
	
	private void checkDirectories(final String inputDir,final String outputDir) {
		File inputFiles = new File(inputDir);
		File outputFiles = new File(outputDir);
		Assert.assertArrayEquals(inputFiles.list(), outputFiles.list());
	}

}
