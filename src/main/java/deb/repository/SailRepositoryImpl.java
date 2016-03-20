/**
 * 
 */
package deb.repository;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

/**
 * @author debmalyajash
 *
 */
public class SailRepositoryImpl {

	private static Repository rep = null;

	private static Object lock = new Object();

	/*
	 * (non-Javadoc)
	 * 
	 * @see deb.repository.RDFRepository#initialize()
	 */
	public static boolean initialize() throws RepositoryException {
		if (rep == null) {
			synchronized (lock) {
				rep = new SailRepository(new MemoryStore());
				rep.initialize();
				if (rep.isInitialized()) {
					return true;
				}
				return false;
			}
		}
		return true;
	}

}
