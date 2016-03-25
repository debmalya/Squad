/**
 * 
 */
package deb.repository;

import java.io.File;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.nativerdf.NativeStore;

/**
 * @author debmalyajash
 *
 */
public class NativeRepository {

	/**
	 * Index based on subject, predicate, object, context. predicate, object,
	 * subject, context. context, object, subject, predicate.
	 */
	private String indexes = "spoc,posc,cosp";

	/**
	 * Native repository
	 */
	private Repository repository = null;

	public NativeRepository(String dataDirectory) {
		if (dataDirectory != null) {
			File dataDir = new File(dataDirectory);
			repository = new SailRepository(new NativeStore(dataDir,indexes));
			try {
				repository.initialize();
			} catch (RepositoryException e) {
				throw new RuntimeException("Exception during repository initialization " + e.getMessage(),e);
			}
		} else {
			throw new RuntimeException("data directory cannot be null");
		}
	}

	/**
	 * @return the repository
	 */
	public Repository getRepository() {
		return repository;
	}

}
