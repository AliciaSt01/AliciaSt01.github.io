import java.util.HashMap;
import java.util.Map;

public class Actor {
	private String actorId;
	private String name;
	private String imageLink;
	private int birthYear;
	private int deathYear;
	private Map<String, String> movieRoles; // NOTE: is this worth having?

	public Actor(String actorId, String name, String imageLink, int birthYear, int deathYear) {
		this.actorId = actorId;
		this.name = name;
		this.imageLink = imageLink;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.movieRoles = new HashMap<String, String>();
	}

	public Actor(String actorId, String name, String imageLink, int birthYear) {
		this(actorId, name, imageLink, birthYear, -1);
	}

	public Actor(String actorId, String name, String imageLink) {
		this(actorId, name, imageLink, -1, -1);
	}

	public Actor(String actorId, String name) {
		this(actorId, name, "", -1, -1);
	}

	public String getActorId() {
		return actorId;
	}

	public String getName() {
		return name;
	}

	public String getImageLink() {
		return imageLink;
	}

	public String getBirthYear() {
		if (birthYear == -1) {
			return "Unknown";
		}
		return String.valueOf(birthYear);
	}

	public String getDeathYear() {
		if (deathYear == -1) {
			return "N/A";
		}
		return String.valueOf(deathYear);
	}

	public Map<String, String> getAllMovieRoles() {
		return movieRoles;
	}

	public String getMovieRole(String movie) {
		return movieRoles.get(movie);
	}

	public void addMovieRole(String movie, String characterName) {
		movieRoles.put(movie, characterName);
	}

}
