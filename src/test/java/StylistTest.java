import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {



	@Before
	public void setUp() {
		DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
	}

	@After
	public void tearDown() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM stylists *;";
			con.createQuery(sql).executeUpdate();
		}
    }

	@Test
	public void stylistsAreTheSame_True() {
		Stylist stylist1 = new Stylist("Sarah");
		Stylist stylist2 = new Stylist("Sarah");
		assertTrue(stylist1.equals(stylist2));
	}

	@Test
	public void Stylist_objectInstantiatesCorrectly_Sarah() {
		Stylist test = new Stylist("Sarah");
		assertEquals(true, test instanceof Stylist);
	}
}
