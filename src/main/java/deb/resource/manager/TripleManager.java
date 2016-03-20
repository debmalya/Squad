/**
 * 
 */
package deb.resource.manager;

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
}
