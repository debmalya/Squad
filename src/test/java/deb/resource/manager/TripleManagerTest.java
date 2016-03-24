/**
 * 
 */
package deb.resource.manager;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author debmalyajash
 *
 */
public class TripleManagerTest {

	/**
	 * Test method for {@link deb.resource.manager.TripleManager#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
		Assert.assertNotNull(TripleManager.getInstance());
	}

	/**
	 * Test method for {@link deb.resource.manager.TripleManager#addTriple(org.openrdf.repository.RepositoryConnection, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAddTriple() {
		
	}

}
