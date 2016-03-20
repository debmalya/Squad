package welcome;
import info.aduna.iteration.Iterations;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
public class HelloNQuad {

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
				URI event = f.createURI("http://www.nogginasia.com/","event");

				conn = rep.getConnection();
				conn.add(john, RDF.TYPE, FOAF.PERSON,event);
				conn.add(john, RDFS.LABEL,
						f.createLiteral("John", XMLSchema.STRING),event);
			
				

				RepositoryResult<Statement> statements = conn.getStatements(
						null, null, null, true);

				Model model = Iterations.addAll(statements,
						new LinkedHashModel());
				
				setNameSpace(namespace, model);
				
				Rio.write(model, new FileOutputStream("Quad"), RDFFormat.NQUADS) ;
				Rio.write(model, System.out, RDFFormat.NQUADS) ;
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RDFHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
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

	private static void setNameSpace(String namespace, Model model) {
		model.setNamespace("rdf",RDF.NAMESPACE);
		model.setNamespace("rdfs", RDFS.NAMESPACE);
		model.setNamespace("xsd",XMLSchema.NAMESPACE);
		model.setNamespace("foaf",FOAF.NAMESPACE);
		model.setNamespace("nga","http://www.nogginasia.com/");
		model.setNamespace("ex",namespace);
	}

//	inspiration : http://rdf4j.org/sesame/tutorials/getting-started.docbook?view
}
