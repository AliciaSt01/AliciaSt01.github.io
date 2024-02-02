public class Book {

	String author, country, title, imageLink, link, language;
	int pages, year;

	public Book(String author, String country, String title, String imageLink, String link, String language, int pages,
			int year) {
		this.author = author;
		this.country = country;
		this.title = title;
		this.imageLink = imageLink;
		this.link = link;
		this.language = language;
		this.pages = pages;
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public String getCountry() {
		return country;
	}

	public String getTitle() {
		return title;
	}

	public String getImageLink() {
		return imageLink;
	}

	public String getLink() {
		return link;
	}

	public String getLanguage() {
		return language;
	}

	public int getPages() {
		return pages;
	}

	public int getYear() {
		return year;
	}
}
