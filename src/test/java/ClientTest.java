import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

	
	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
	public void clientsAreTheSame() {
		Client client1 = new Client("Bill", 1);
		Client client2 = new Client("Bill", 1);
		assertTrue(client1.equals(client2));
	}

	@Test
	public void client_checkInstantiatesCorrectly_True() {
		Client newClient = new Client("Bill", 1);
		assertEquals(true, newClient instanceof Client);
	}

	@Test
	public void getName_returnsObjectName_String() {
		Client newClient = new Client("Jason", 1);
		assertEquals("Jason", newClient.getName());
	}

	@Test
	public void all_emptyAtFirst() {
		assertEquals(Client.all().size(), 0);
  }
	@Test
	public void save_assignsIdToObject() {
		Client myClient = new Client("John", 1);
		myClient.save();
		Client savedClient = Client.all().get(0);
		assertEquals(myClient.getId(), savedClient.getId());
	}
	@Test
  	public void save_savesStylistIdIntoDB_true() {
	    Stylist myStylist = new Stylist("Monica");
	    myStylist.save();
	    Client myClient = new Client("Mike", myStylist.getId());
	    myClient.save();
	    Client savedClient = Client.find(myClient.getId());
	    assertEquals(savedClient.getStylistId(), myStylist.getId());
  	}

	@Test
	public void find_findsClientInDatabase_true() {
		Client myClient = new Client("Mary", 1);
		myClient.save();
		Client savedClient = Client.find(myClient.getId());
		assertTrue(myClient.equals(savedClient));
	}
	// @Test
	// public void getStylists_returnsStylist_true() {
	// 	Client myClient = new Client("Beatrice", 1);
	// 	myClient.save();
	// 	Stylist myStylist = new Stylist("Marco");
	// 	myStylist.save();
		
		
	// 	Client[] clients = new Client[] { firstTask, secondTask };
	// 	assertTrue(myClient.getClients().containsAll(Arrays.asList(clients)));
	// }
	
}
