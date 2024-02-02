import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class APIFunctions {
  public final static int COUNT_MAX_TOP_MOVIES = 25;
  public final static String firstPartOfURL = "https://imdb-api.com/en/API/";
  public final static String apiKey = "k_mcx0w8kk";
  public final static String SEARCH_TYPE_MOVIE = "SearchMovie";
  public final static String SEARCH_TYPE_ADVANCED = "AdvancedSearch";
  public final static String ADVANCED_LAST_PART = "&title_type=feature,tv_movie,video,short";
  public final static String GET_TYPE_TITLE = "Title";
  public final static String GET_TYPE_TOP_MOVIES = "Top250Movies";

  /**
   * Returns the list of movies whose titles contain the given search key.
   * 
   * @param searchKey Search key
   * @return List of movies whose titles contain the given search key
   * @throws IOException
   */
  public static List<Movie> getMovies(String searchKey) throws IOException {
    File searchFile = new File("src/json-files/" + searchKey + ".json");

    // If a first time search, call the API to get the search results
    if (searchFile.createNewFile()) {
      URL url = new URL(String.format("%s%s/%s?title=%s%s", firstPartOfURL, SEARCH_TYPE_ADVANCED,
          apiKey, searchKey, ADVANCED_LAST_PART));
      List<String> movieIds = getMovieIds(url.openStream(), "results");
      return getMovies(movieIds, searchFile);
    }
    // If NOT a first time search, read values from the locally stored search results
    return getMovies(searchFile);
  }

  /**
   * Returns a list of the most highly rated movies.
   * 
   * @return List of the most highly rated movies
   * @throws IOException
   */
  public static List<Movie> getTopMovies() throws IOException {
    File searchFile = new File("src/json-files/topMovies.json");

    // If a first time search, call the API to get the search results
    if (searchFile.createNewFile()) {
      URL url = new URL(String.format("%s%s/%s", firstPartOfURL, GET_TYPE_TOP_MOVIES, apiKey));
      List<String> movieIds = getMovieIds(url.openStream(), "items");

      // Get a sub-set of the highest rated movie results (the API automatically gives 250 results)
      return getMovies(movieIds.subList(0, COUNT_MAX_TOP_MOVIES), searchFile);
    }
    // If NOT a first time search, read values from the locally stored search results
    return getMovies(searchFile);
  }

  /**
   * Returns the list of movie IDs from a JSON-formatted list of movies.
   * 
   * @param json JSON-formatted list of movies
   * @param getType The field-name used by the search result; either "items" for top movie search or
   *          "results" for other searches
   * @return List of movie IDs
   * @throws IOException
   */
  private static List<String> getMovieIds(InputStream json, String getType) throws IOException {
    List<String> movieIds = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(json).get(getType);

    for (JsonNode obj : tree) {
      movieIds.add(obj.get("id").asText());
    }
    json.close();
    return movieIds;
  }

  /**
   * Given a list of movie IDs and the search results file in which to store them, returns the list
   * of movies associated with those IDs. This method is called when the specified search key has
   * never been searched before.
   * 
   * @param movieIds List of movie IDs
   * @param searchFile File in which to store all of the results of the specific search
   * @return List of movie objects corresponding to the given movie IDs
   */
  private static List<Movie> getMovies(List<String> movieIds, File searchFile) {
    List<Movie> movies = new ArrayList<>();
    URL url;

    // Get the details for each movie searching by individual movie ID
    for (String id : movieIds) {
      try {
        url = new URL(
            String.format("%s%s/%s/%s/%s", firstPartOfURL, GET_TYPE_TITLE, apiKey, id, "Trailer,"));
        movies.add(getMovieDetails(url.openStream(), searchFile));
      } catch (IOException e) {
        continue; // If the URL can't be accessed, skip the movie
      } catch (NoSuchFieldError e) {
        continue; // If the movie's fields are null, skip the movie
      }
    }

    return movies;
  }

  /**
   * Given a file containing the search results for a specified search key, returns the
   * corresponding list of movies. This method is called when the search key already has locally
   * cached search results.
   * 
   * @param searchFile File in which to store all of the results of the specific search
   * @return List of movie objects corresponding to the given movie IDs
   * @throws IOException
   */
  private static List<Movie> getMovies(File searchFile) throws IOException {
    List<Movie> movies = new ArrayList<>();

    JsonNode jsonSearchResults = new ObjectMapper().readTree(new FileInputStream(searchFile));
    for (JsonNode jsonMovie : jsonSearchResults) {
      movies.add(getMovieDetails(jsonMovie));
    }

    return movies;
  }

  /**
   * Given a JSON-formatted movie, and the search results file in which to store it, adds the
   * movie's JSON to the file and returns the corresponding Movie object. This method is called when
   * the specified search key has never been searched before.
   * 
   * @param json The details of a specific movie as a JSON-formatted InputStream
   * @param searchFile File in which to store all of the results of the specific search
   * @return Corresponding Movie object
   * @throws IOException If an I/O error occurs
   * @throws NoSuchFieldError If the movie's fields are null; the movie will be skipped in the event
   *           this error is thrown
   */
  private static Movie getMovieDetails(InputStream json, File searchFile)
      throws IOException, NoSuchFieldError {
    JsonNode jsonMovie = new ObjectMapper().readTree(json);

    // If the movie's title is null, don't include the movie in the list
    if (jsonMovie.get("title").asText().equals("null")) {
      throw new NoSuchFieldError();
    }

    Movie movie = getMovieDetails(jsonMovie);

    // Add movie json to results file
    addMovieToResultFile(jsonMovie, searchFile);

    json.close();
    return movie;
  }

  /**
   * Given a JSON-formatted movie, returns a corresponding Movie object.
   * 
   * @param jsonMovie The details of a specific movie as a JsonNode object
   * @return Corresponding Movie object
   */
  private static Movie getMovieDetails(JsonNode jsonMovie) {
    Movie movie = null;

    // Get the list of actors in the movie
    List<Actor> actors = new ArrayList<>();
    for (JsonNode obj : jsonMovie.get("actorList")) {
      actors.add(
          new Actor(obj.get("id").asText(), obj.get("name").asText(), obj.get("image").asText()));
    }

    // Build the movie
    movie = new Movie(jsonMovie.get("id").asText(), jsonMovie.get("title").asText(),
        jsonMovie.get("year").asText(), jsonMovie.get("directors").asText(), actors,
        jsonMovie.get("runtimeStr").asText(), jsonMovie.get("genres").asText(),
        jsonMovie.get("imDbRating").asText(), jsonMovie.get("plot").asText(),
        jsonMovie.get("image").asText());
    return movie;
  }

  /**
   * Adds the given JSON-formatted movie to its corresponding search result file.
   * 
   * @param jsonMovie The details of a specific movie as a JsonNode object
   * @param searchFile File in which to store all of the results of the specific search
   * @throws IOException If searchFile cannot be found
   */
  private static void addMovieToResultFile(JsonNode jsonMovie, File searchFile) throws IOException {
    ObjectMapper jsonMapper = JsonMapper.builder().build();
    JsonNode node = null;
    if (searchFile.length() > 0) {
      node = jsonMapper.readTree(searchFile);
    } else {
      node = jsonMapper.createArrayNode();
    }

    ArrayNode array = (ArrayNode) node;
    array.addPOJO(jsonMovie);

    ObjectWriter writer = jsonMapper.writer(new DefaultPrettyPrinter());
    writer.writeValue(searchFile, node);
  }

}
