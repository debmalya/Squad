/**
 * 
 */
package deb.resource.manager;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

/**
 * This will manage all the triples.
 * Adding triples, retrieving triples.
 * @author debmalyajash
 *
 */
public class TripleManager {
	private TripleManager() {

	}

	/**
	 * Triple Manager Singleton Holder.
	 * @author debmalyajash
	 *
	 */
	private static class SingletonHolder {
		private static final TripleManager instance = new TripleManager();
	}

	/**
	 * 
	 * @return Triple Manager instance.
	 */
	public static TripleManager getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 
	 * @param repositoryConnection
	 * @param namespace
	 * @param subject
	 * @param predicate
	 * @param object
	 * @return true if triple are added successfully.
	 * @throws Exception
	 */
	public boolean addTriple(final RepositoryConnection repositoryConnection,
			final String namespace, final String subject,
			final String predicate, final String object)
			throws Exception {
		try {
		ValueFactory f = repositoryConnection.getValueFactory();
		URI subjectURI = f.createURI(namespace, subject);
		URI predicateURI = RDF.TYPE;
		if (predicate != null) {
			predicateURI = f.createURI(namespace, predicate);
		}

		URI objectURI = f.createURI(namespace,object);
		repositoryConnection.begin();
		RepositoryResult<Statement> statements = repositoryConnection.getStatements(
				null, null, null, true);
		
		repositoryConnection.add(subjectURI, predicateURI, objectURI);

		
		repositoryConnection.commit();
		} catch(Throwable th) {
			repositoryConnection.rollback();
			throw new Exception("addTriple ERR :" + th.getMessage(),th);
			
		}
		return true;
	}
}
