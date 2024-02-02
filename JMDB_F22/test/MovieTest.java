import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MovieTest {

	List<Actor> actors = new ArrayList<>();

	Movie movieTest = new Movie("Movie101", "The Greatest Movie", "2018", "DirectorName", actors, "2 hrs 10 min",
			"Action, Adventure", "4/5 Stars", "This is the plot summary!", "link.url");

	@Test
	void testMovieId() {
		assertEquals("Movie101", movieTest.movieId());
	}

	@Test
	void testTitle() {
		assertEquals("The Greatest Movie", movieTest.title());
	}

	@Test
	void testYear() {
		assertEquals("2018", movieTest.year());
	}

	@Test
	void testDirectors() {
		assertEquals("DirectorName", movieTest.directors());
	}

	@Test
	void testActorList() {
		actors.add(new Actor("Act007", "Actor 7 Name"));
		actors.add(new Actor("Act008", "Actor 8 Name"));
		assertEquals(actors, movieTest.actorList());
	}

	@Test
	void testRuntimeStr() {
		assertEquals("2 hrs 10 min", movieTest.runtimeStr());
	}

	@Test
	void testGenres() {
		assertEquals("Action, Adventure", movieTest.genres());
	}

	@Test
	void testRating() {
		assertEquals("4/5 Stars", movieTest.rating());
	}

	@Test
	void testPlotSummary() {
		assertEquals("This is the plot summary!", movieTest.plotSummary());
	}

	@Test
	void testImageLink() {
		assertEquals("link.url", movieTest.imageLink());
	}
}
