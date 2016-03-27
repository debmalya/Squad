/**
 * 
 */
package deb.context;

import org.openrdf.model.IRI;
import org.openrdf.repository.RepositoryConnection;

/**
 * @author debmalyajash
 *
 */
public class Contextualizer implements Contextualize {

	public boolean createQuad(final String namespace, final IRI subjectURI,
			final IRI predicateURI, final IRI objectURI, final IRI context,
			RepositoryConnection repositoryConnection) {
		if (repositoryConnection != null) {
			try {
				repositoryConnection.begin();
				repositoryConnection.add(subjectURI, predicateURI, objectURI,
						context);
				repositoryConnection.commit();
				return true;
			} catch (Throwable th) {
				th.printStackTrace();
				repositoryConnection.rollback();
			} finally {
				repositoryConnection.close();
			}
		}
		return false;
	}

}
