package welcome;
import info.aduna.iteration.Iterations;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.Rio;
import org.openrdf.sail.memory.MemoryStore;

/**
 * 
 */
/**
 * @author debmalyajash
 *
 */
public class HelloSesame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Repository rep = new SailRepository(new MemoryStore());
		RepositoryConnection conn = null;
		try {
			rep.initialize();
			if (rep.isInitialized()) {
				ValueFactory f = rep.getValueFactory();
				String namespace = "http://example.org/";
				URI john = f.createURI(namespace, "john_doe");

				conn = rep.getConnection();
				conn.add(john, RDF.TYPE, FOAF.PERSON);
				conn.add(john, RDFS.LABEL,
						f.createLiteral("John", XMLSchema.STRING));

				RepositoryResult<Statement> statements = conn.getStatements(
						null, null, null, true);

				Model model = Iterations.addAll(statements,
						new LinkedHashModel());
				
				model.setNamespace("rdf",RDF.NAMESPACE);
				model.setNamespace("rdfs", RDFS.NAMESPACE);
				model.setNamespace("xsd",XMLSchema.NAMESPACE);
				model.setNamespace("foaf",FOAF.NAMESPACE);
				model.setNamespace("ex",namespace);
				
				Rio.write(model, System.out, RDFFormat.TURTLE);
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RDFHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

//	collected from : http://rdf4j.org/sesame/tutorials/getting-started.docbook?view
}
