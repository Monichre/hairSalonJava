import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

	
	@Rule
	public DatabaseRule database = new DatabaseRule();

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

	@Test
	public void getName_returnsObjectName_String() {
		Client newClient = new Client("Jason");
		assertEquals("Jason", newClient.getName());
	}

	@Test
	public void all_emptyAtFirst() {
		assertEquals(Client.all().size(), 0);
  }
	@Test
	public void save_assignsIdToObject() {
		Client myClient = new Client("John");
		myClient.save();
		Client savedClient = Client.all().get(0);
		assertEquals(myClient.getId(), savedClient.getId());
	}

	@Test
	public void find_findsClientInDatabase_true() {
		Client myClient = new Client("Mary");
		myClient.save();
		Client savedClient = Client.find(myClient.getId());
		assertTrue(myClient.equals(savedClient));
	}

	
}
