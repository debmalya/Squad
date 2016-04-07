/**
 * 
 */
package deb.camel;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author debmalyajash
 *
 */
public class FileCopierWithCamel {

	private static final Logger LOGGER = Logger
			.getLogger(FileCopierWithCamel.class.getName());

	/**
	 * @param args
	 * @throws Exception
	 *             StreamCaching is not in use. If using streams then its
	 *             recommended to enable stream caching. See more details at
	 *             http://camel.apache.org/stream-caching.html
	 */
	public static void main(String[] args) {
		routeFile("file:src/main/resources/data?noop=true","file:src/main/resources/out");
	}

	/**
	 * 
	 */
	public static void routeFile(final String inputDirectory,final String outputDirectory) {
		CamelContext camelContext = null;
		try {
			camelContext = new DefaultCamelContext();
			camelContext.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					// noop option tells Camel to leave the source file as it
					// is.	
					from(inputDirectory).to(
							outputDirectory);

				}
			});
			camelContext.start();
			Thread.sleep(5000);
		} catch (Throwable th) {
			LOGGER.log(Level.SEVERE, "ERR: while using camel context",
					th.getCause());
		} finally {
			if (camelContext != null) {
				try {
					camelContext.stop();
				} catch (Exception e) {
					LOGGER.log(Level.WARNING,
							"ERR: while stopping camel context", e.getCause());
				}
			}
		}
	}

}
