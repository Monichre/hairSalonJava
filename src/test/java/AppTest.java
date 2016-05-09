import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*; // for DB support
import org.junit.*; // for @Before and @After
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  	public WebDriver webDriver = new HtmlUnitDriver();

	@Override
		public WebDriver getDefaultDriver() {
		return webDriver;
	}

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@ClassRule
  	public static ServerRule server = new ServerRule();


	@Test
	public void rootTest() {
		goTo("http://localhost:4567/");
		assertThat(pageSource()).contains("Avec du Monde");
	}
	@Test
	public void clickBookingsRoute() {
		goTo("http://localhost:4567/");
		click("a", withText("See a list of our Stylists"));
		assertThat(pageSource()).contains("Bookings");
	}

	@Test
	public void homeSubmitRendersSuccessVtl() {
		goTo("http://localhost:4567/");
		fill("#stylistName").with("john");
		fill("#clientName").with("Jessica");
		submit(".btn");
		assertThat(pageSource()).contains("Booked!");
		assertThat(pageSource()).contains("Click here view your booking");
		assertThat(pageSource()).contains("Home");	
	}
	@Test
	public void stylistLinkRendersAddClientVtl() {
		goTo("http://localhost:4567/");
		fill("#stylistName").with("john");
		fill("#clientName").with("Jessica");
		submit(".btn");
		assertThat(pageSource()).contains("Click here view your booking");
		click("a", withText("Click here view your booking"));
		assertThat(pageSource()).contains("Book an appointment with john");
		click("a", withText("Book an appointment with john"));
		assertThat(pageSource()).contains("john");
	}

	@Test
	public void allClientsDisplayOnStylistsPage() {
		Stylist myStylist = new Stylist("Devin");
		myStylist.save();
		Client firstClient = new Client("Eunice", myStylist.getId());
		firstClient.save();
		String bookingsPath = String.format("http://localhost:4567/stylistClients/%d", myStylist.getId());
		goTo(bookingsPath);
		assertThat(pageSource()).contains("Devin");
		assertThat(pageSource()).contains("Eunice");
	}
		
}
