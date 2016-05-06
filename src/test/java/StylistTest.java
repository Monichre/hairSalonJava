import org.sql2o.*;
import org.junit.*;
import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.*;

public class StylistTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();


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

	@Test
	public void getName_returnsObjectName_String() {
		Stylist newStylist = new Stylist("Bruno");
		assertEquals("Bruno", newStylist.getName());
	}

	@Test
	public void all_emptyAtFirst() {
		assertEquals(Stylist.all().size(), 0);
  }
	@Test
	public void find_findsStylistInDatabase_true() {
		Stylist myStylist = new Stylist("Mary");
		myStylist.save();
		Stylist savedStylist = Stylist.find(myStylist.getId());
		assertTrue(myStylist.equals(savedStylist));
	}
	@Test
	public void getClients_returnsAllClientsFromDatabase_True() {
		Stylist myStylist = new Stylist("Beatrice");
		myStylist.save();
		Client firstClient = new Client("Dave", myStylist.getId());
		firstClient.save();
		Client secondClient = new Client("Stacy", myStylist.getId());
		secondClient.save();
		Client[] clients = new Client[] { firstClient, secondClient };
		assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
	}
}
