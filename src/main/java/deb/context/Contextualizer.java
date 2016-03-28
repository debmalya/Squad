/**
 * 
 */
package deb.context;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.IRI;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

/**
 * @author debmalyajash
 *
 */
public class Contextualizer implements Contextualize {

	/**
	 * To add subject, predicate, object, context (SPOC).
	 * 
	 * @param namespace
	 *            to be added
	 * @param subjectIRI
	 * @param predicateIRI
	 * @param objectIRI
	 * @param context
	 * @param repositoryConnection
	 * @return true if created successfully, false otherwise.
	 */
	public boolean createQuad(final String namespace, final IRI subjectIRI,
			final IRI predicateIRI, final IRI objectIRI, final IRI context,
			RepositoryConnection repositoryConnection) {
		if (repositoryConnection != null) {
			try {
				repositoryConnection.begin();
				repositoryConnection.add(subjectIRI, predicateIRI, objectIRI,
						context);
				repositoryConnection.commit();
				return true;
			} catch (Throwable th) {
				repositoryConnection.rollback();
				throw new RuntimeException(th.getMessage(), th);
			} finally {
				// repositoryConnection.close();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deb.context.Contextualize#getContextStatements()
	 */
	@Override
	public List<RepositoryResult<Statement>> getContextStatements(
			RepositoryConnection repositoryConnection, IRI... contextIRI) {
		List<RepositoryResult<Statement>> resultList = new ArrayList<>();
		try {

			if (repositoryConnection != null) {
				
				if (contextIRI != null) {
					System.out.println("Here");
					for (IRI each : contextIRI) {
						resultList.add(repositoryConnection.getStatements(null,
								null, null, true, each));
					}
				} else {
					System.out.println("THere");
					resultList.add(repositoryConnection.getStatements(null,
							null, null, true, (Resource)null));
				}
			}
		} catch (Throwable th) {
			throw new RuntimeException("ERR: getContextStatements"
					+ th.getMessage(), th);
		} finally {
//			repositoryConnection.close();
		}
		return resultList;
	}

}
