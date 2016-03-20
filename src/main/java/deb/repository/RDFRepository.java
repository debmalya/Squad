/**
 * 
 */
package deb.repository;

import org.openrdf.repository.RepositoryException;

/**
 * @author debmalyajash
 *
 */
public interface RDFRepository {
	public boolean initialize() throws RepositoryException;
}
