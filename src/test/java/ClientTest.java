import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

	@Before
	public void setUp() {
		DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
	}

	@After
	public void tearDown() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM clients *;";
			con.createQuery(sql).executeUpdate();
		}
    }

	@Test
	public void clientsAreTheSame() {
		Client client1 = new Client("Bill");
		Client client2 = new Client("Bill");
		assertTrue(client1.equals(client2));
	}

	@Test
	public void client_checkInstantiatesCorrectly_True() {
		Client newClient = new Client("Bill");
		assertEquals(true, newClient instanceof Client);
	}
}
