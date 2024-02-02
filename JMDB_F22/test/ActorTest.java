import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ActorTest {

	Actor actorInfo = new Actor("Act001", "Markus", "tempImageLink.url", 1950, 2019);
	Actor actorInfo2 = new Actor("Act002", "John", "tempImage.url", 1990);
	Actor actorInfo3 = new Actor("Act003", "Tina", "imageLink.url");
	Actor actorInfo4 = new Actor("Act004", "Sydney");

	@Test
	void testGetActorId() {
		assertEquals("Act001", actorInfo.getActorId());
	}

	@Test
	void testGetName() {
		assertEquals("Markus", actorInfo.getName());
	}

	@Test
	void testGetImageLink() {
		assertEquals("tempImageLink.url", actorInfo.getImageLink());
		assertEquals("", actorInfo4.getImageLink());
	}

	@Test
	void testGetBirthYear() {
		assertEquals("1950", actorInfo.getBirthYear());
		assertEquals("1990", actorInfo2.getBirthYear());
		assertEquals("Unknown", actorInfo3.getBirthYear());
	}

	@Test
	void testGetDeathYear() {
		assertEquals("2019", actorInfo.getDeathYear());
		assertEquals("N/A", actorInfo2.getDeathYear());
	}

	@Test
	void testMovieRole() {
		// add a movie role
		actorInfo.addMovieRole("Shark", "Fitzwallace Humpfrey III");

		// get a movie role
		assertEquals("Fitzwallace Humpfrey III", actorInfo.getMovieRole("Shark"));

		actorInfo.addMovieRole("Shark 2: The Sharkening", "Fitzwallace Humpfrey IV");
		assertEquals("Fitzwallace Humpfrey IV", actorInfo.getMovieRole("Shark 2: The Sharkening"));

		// get all movie roles
		Map<String, String> expected = new HashMap<>();
		expected.put("Shark", "Fitzwallace Humpfrey III");
		expected.put("Shark 2: The Sharkening", "Fitzwallace Humpfrey IV");
		assertEquals(expected, actorInfo.getAllMovieRoles());
	}

}
