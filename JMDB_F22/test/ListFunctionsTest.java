import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ListFunctionsTest {


  void testConstructors() {
    new ListFunctions();
    new APIFunctions();
  }

  @Test
  void testCreateList() throws FileNotFoundException, IOException {
    // expected list
    List<Movie> expected = new ArrayList<Movie>();

    // movie IDs gotten directly from the IMdB-API
    expected.add(new Movie("tt0307453", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt12550376", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt1633356", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt3977848", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt21426434", "", "", "", null, "", "", "", "", ""));

    List<Movie> actual = ListFunctions.createList("Shark");

    // Check if these IDs are in the actual results.
    // Testing in this way accounts for any variability in the result order.
    for (int i = 0; i < 5; i++) {
      boolean found = false;
      for (int p = 0; p < actual.size(); p++) {
        if (expected.get(i).movieId().equals(actual.get(p).movieId())) {
          found = true;
        }
      }
      // if the movie ID is nowhere in the actual list, the test will fail
      assertTrue(found);
    }
  }

  @Test
  void testCreateTopMovieList() throws FileNotFoundException, IOException {
    // expected list
    List<Movie> expected = new ArrayList<Movie>();

    // movie IDs gotten directly from the IMdB-API
    expected.add(new Movie("tt0111161", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt0068646", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt0468569", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt0071562", "", "", "", null, "", "", "", "", ""));
    expected.add(new Movie("tt0050083", "", "", "", null, "", "", "", "", ""));

    List<Movie> actual = ListFunctions.createTopMovieList();

    // Check if these IDs are in the actual results.
    // Testing in this way accounts for any variability in the result order.
    for (int i = 0; i < 5; i++) {
      boolean found = false;
      for (int p = 0; p < actual.size(); p++) {
        if (expected.get(i).movieId().equals(actual.get(p).movieId())) {
          found = true;
        }
      }
      // if the movie ID is nowhere in the actual list, the test will fail
      assertTrue(found);
    }
  }
}
