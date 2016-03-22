/**
 * 
 */
package deb.resource.manager;

import info.aduna.iteration.Iterations;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

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
			throw new Exception("addTriple ERR :" + th.getMessage(),th);
			
		}
		return true;
	}
}
