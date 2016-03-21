/**
 * 
 */
package deb.resource.manager;

import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;

/**
 * @author debmalyajash
 *
 */
public class TripleManager {
	private TripleManager() {

	}

	private static class SingletonHolder {
		private static final TripleManager instance = new TripleManager();
	}
	
	public static TripleManager getInstance() {
		return SingletonHolder.instance;
	}
	
	
	public boolean addTriple(RepositoryConnection repositoryConnection) {
		ValueFactory f = repositoryConnection.getValueFactory();
		return false;
	}
}
