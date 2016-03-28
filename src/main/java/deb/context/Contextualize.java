/**
 * 
 */
package deb.context;

import java.util.List;

import org.openrdf.model.IRI;
import org.openrdf.model.Statement;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

/**
 * @author debmalyajash
 *
 */
public interface Contextualize {
	/**
	 * To add subject, predicate, object, context (SPOC).
	 * @param namespace to be added
	 * @param subjectIRI 
	 * @param predicateIRI
	 * @param objectIRI
	 * @param context
	 * @param repositoryConnection
	 * @return true if created successfully, false otherwise.
	 */
	public boolean createQuad(final String namespace, final IRI subjectIRI,
			final IRI predicateIRI, final IRI objectIRI, final IRI context,
			RepositoryConnection repositoryConnection);
	
	/**
	 * 
	 * @return Repository result
	 */
	public List<RepositoryResult<Statement>>  getContextStatements(RepositoryConnection repositoryConnection,IRI... contextIRI);
}
