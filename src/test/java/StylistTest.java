import org.sql2o.*;
import org.junit.*;
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
	public void all_emptyAtFirst() {
		assertEquals(Stylist.all().size(), 0);
  }
}
