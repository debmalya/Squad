/**
 * 
 */
package deb.context;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openrdf.model.IRI;
import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.QueryResults;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

import deb.repository.NativeRepository;

/**
 * @author debmalyajash
 *
 */
public class ContextualizerTest {

	/**
	 * 
	 */
	private static final String NAMESPACE = "http://www.raju.com/";

	private static NativeRepository nativeRepository = new NativeRepository(
			"./src/main/resources");

	private static RepositoryConnection repositoryConnection = nativeRepository
			.getRepository().getConnection();

	/**
	 * Test method for
	 * {@link deb.context.Contextualizer#createQuad(java.lang.String, org.openrdf.model.IRI, org.openrdf.model.IRI, org.openrdf.model.IRI, org.openrdf.model.IRI, org.openrdf.repository.RepositoryConnection)}
	 * .
	 */
	@Test
	public final void testCreateQuad() {
		try {

			ValueFactory valueFactory = repositoryConnection.getValueFactory();

			IRI subjectIRI = valueFactory.createIRI(NAMESPACE,
					"subject");
			IRI predicateIRI = valueFactory.createIRI(NAMESPACE,
					"predicate");
			IRI objectIRI = valueFactory.createIRI(NAMESPACE,
					"object");
			IRI contextIRI = valueFactory.createIRI(NAMESPACE,
					"context");

			Contextualizer contextulizer = new Contextualizer();
			Assert.assertTrue(contextulizer.createQuad(NAMESPACE,
					subjectIRI, predicateIRI, objectIRI, contextIRI,
					repositoryConnection));

			subjectIRI = valueFactory.createIRI(NAMESPACE,
					"subject1");
			predicateIRI = valueFactory.createIRI(NAMESPACE,
					"predicate1");
			objectIRI = valueFactory.createIRI(NAMESPACE,
					"object1");
			contextIRI = valueFactory.createIRI(NAMESPACE,
					"context1");

			subjectIRI = valueFactory.createIRI(NAMESPACE,
					"subject2");
			predicateIRI = valueFactory.createIRI(NAMESPACE,
					"predicate2");
			objectIRI = valueFactory.createIRI(NAMESPACE,
					"object2");
			contextIRI = valueFactory.createIRI(NAMESPACE,
					"context");

			Assert.assertTrue(contextulizer.createQuad(NAMESPACE,
					subjectIRI, predicateIRI, objectIRI, contextIRI,
					repositoryConnection));

		} catch (Throwable th) {
			Assert.assertFalse("CreateQuad failed :" + th.getMessage(), true);
		}

	}

	@Test
	public final void testCreateRetrieveQuad() {
		try {

			ValueFactory valueFactory = repositoryConnection.getValueFactory();

			IRI contextIRI = valueFactory.createIRI(NAMESPACE,
					"context");

			Contextualizer contextulizer = new Contextualizer();
			//
			List<RepositoryResult<Statement>> statements = contextulizer
					.getContextStatements(repositoryConnection, contextIRI);
			checkContextRetrieval(statements);

			contextIRI = valueFactory.createIRI(NAMESPACE,
					"context1");

			statements = contextulizer.getContextStatements(
					repositoryConnection, contextIRI);
			checkContextRetrieval(statements);

		} catch (Throwable th) {
			Assert.assertFalse("CreateQuad failed :" + th.getMessage(), true);
		} finally {
			repositoryConnection.close();
		}

	}

	private void checkContextRetrieval(
			List<RepositoryResult<Statement>> statements) {
		Assert.assertNotNull(statements);

		for (RepositoryResult<Statement> result : statements) {
			Model model = QueryResults.asModel(result);
			System.out.println(model);
			while (result.hasNext()) {
				Statement statement = (Statement) result.next();
				Resource subject = statement.getSubject();
				Assert.assertNotNull(subject);
				System.out.println(subject);

			}
		}
	}
}
