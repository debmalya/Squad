/**
 * 
 */
package deb.context;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openrdf.model.IRI;
import org.openrdf.model.Statement;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

import deb.repository.NativeRepository;

/**
 * @author debmalyajash
 *
 */
public class ContextualizerTest {

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

			IRI subjectIRI = valueFactory.createIRI(
					"http://www.noggingasia/com", "subject");
			IRI predicateIRI = valueFactory.createIRI(
					"http://www.noggingasia/com", "predicate");
			IRI objectIRI = valueFactory.createIRI(
					"http://www.noggingasia/com", "object");
			IRI contextIRI = valueFactory.createIRI(
					"http://www.noggingasia/com", "context");

			Contextualizer contextulizer = new Contextualizer();
			Assert.assertTrue(contextulizer.createQuad(
					"http://www.nogginasia.com/", subjectIRI, predicateIRI,
					objectIRI, contextIRI, repositoryConnection));
		} catch (Throwable th) {
			Assert.assertFalse("CreateQuad failed :" + th.getMessage(), true);
		}

	}

	@Test
	public final void testCreateRetrieveQuad() {
		try {

			ValueFactory valueFactory = repositoryConnection.getValueFactory();

			IRI contextIRI = valueFactory.createIRI(
					"http://www.noggingasia/com", "context");

			Contextualizer contextulizer = new Contextualizer();
//
			List<RepositoryResult<Statement>> statements = contextulizer
					.getContextStatements(repositoryConnection, contextIRI);

			Assert.assertNotNull(statements);
			
			System.out.println(statements.size());
			
			for  (RepositoryResult<Statement> statement:statements) {
				
				
//				Assert.assertNotNull(statement);
//				while (statement.hasNext()) {
//				System.out.println(statment.+";"+statment.getPredicate()+";"+statment.getObject()+";"+statment.getContext());
//				}
			}
		} catch (Throwable th) {
			Assert.assertFalse("CreateQuad failed :" + th.getMessage(), true);
		}

	}
}
