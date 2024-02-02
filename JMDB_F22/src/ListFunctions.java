import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ListFunctions {

	/**
	 * Create a list of movies containing the search input.
	 * 
	 * @param searchInput input to JTextField
	 * @return list of searched movies
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static List<Movie> createList(String searchInput) throws FileNotFoundException, IOException {
		return APIFunctions.getMovies(searchInput);
	}

	/**
	 * Create a list of the highest rated movies.
	 * 
	 * @return list of highest rated movies
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static List<Movie> createTopMovieList() throws FileNotFoundException, IOException {
		return APIFunctions.getTopMovies();
	}

}
