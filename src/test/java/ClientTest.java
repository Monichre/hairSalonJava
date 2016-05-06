import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

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
