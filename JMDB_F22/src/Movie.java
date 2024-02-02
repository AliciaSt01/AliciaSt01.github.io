import java.util.List;

public class Movie {

	private String movieId;
	private String title;
	private String year;
	private String directors;
	private List<Actor> actors;
	private String runtimeStr;
	private String genres;
	private String rating;
	private String plotSummary;
	private String imageLink;

	public Movie(String movieId, String title, String year, String directors, List<Actor> actors, String runtimeStr,
			String genres, String rating, String plotSummary, String imageLink) {
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.directors = directors;
		this.actors = actors;
		this.runtimeStr = runtimeStr;
		this.genres = genres;
		this.rating = rating;
		this.plotSummary = plotSummary;
		this.imageLink = imageLink;
	}

	public String movieId() {
		return movieId;
	}

	public String title() {
		return title;
	}

	public String year() {
		return year;
	}

	public String directors() {
		return directors;
	}

	public List<Actor> actorList() {
		return actors;
	}

	public String runtimeStr() {
		return runtimeStr;
	}

	public String genres() {
		return genres;
	}

	public String rating() {
		return rating;
	}

	public String plotSummary() {
		return plotSummary;
	}

	public String imageLink() {
		return imageLink;
	}

	public String toString() {
		return String.format("%s (%s)", title, year);
	}
}
