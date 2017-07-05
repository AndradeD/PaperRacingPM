package grupoPM.projetoPaperRacing;

import grupoPM.projetoPaperRacing.Application.RedutorCaminhoPista;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws Exception
	 */
	public void testApp() throws Exception {
		RedutorCaminhoPista redutorFinder = new RedutorCaminhoPista();
		assertNotNull(App.InitializePista("br"));
		assertNotNull(App.InitializePista("be"));
		assertNotNull(App.InitializePista("hu"));
		assertNotNull(App.InitializePista("it"));
		assertNotNull(App.InitializePista("jp"));
		assertNotNull(App.InitializePista("abc"));
		assertNotNull(App.InitializePista("123"));
		assertNotNull(App.InitializePista(null));

		assertNotNull(redutorFinder.findPath(10, 10, 15, 15));
		assertNotNull(redutorFinder.findPath(10, 20, 30, 10));
		assertNotNull(redutorFinder.findPath(18, 30, 50, 60));
		assertNotNull(redutorFinder.findPath(50, 20, 80, 40));
		assertNotNull(redutorFinder.findPath(-20, -10, 25, 35));
		assertNotNull(redutorFinder.findPath(-1, -1, 2, 2));
		assertNotNull(redutorFinder.findPath(2, 2, 2, 2));
		assertNotNull(redutorFinder.findPath(0, 1, -5, -2));
	}
}
