/**
 * 
 */
package deb.repository;

import org.junit.Assert;
import org.junit.Test;
import org.openrdf.repository.RepositoryException;

/**
 * @author debmalyajash
 *
 */
public class NativeRepositoryTest {

	/**
	 * Test method for
	 * {@link deb.repository.NativeRepository#NativeRepository(java.lang.String)}
	 * .
	 */
	@Test
	public final void testNativeRepository() {
		NativeRepository repository = null;
		try {
			repository = new NativeRepository("./src/main/resources");
			Assert.assertNotNull(repository.getRepository());
		} catch (RuntimeException re) {
			Assert.assertFalse(re.getMessage(), false);
		} finally {
			if (repository != null) {
				try {
					repository.getRepository().shutDown();
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Test method for {@link deb.repository.NativeRepository#getRepository()}.
	 */
	@Test
	public final void testGetRepository() {

	}

}
