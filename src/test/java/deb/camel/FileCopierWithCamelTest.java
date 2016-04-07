/**
 * 
 */
package deb.camel;

import static org.junit.Assert.*;

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
		FileCopierWithCamel.routeFile("file:"+inputDir+"?noop=true","file:"+outputDir);
	}

}
