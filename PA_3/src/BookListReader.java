

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookListReader {

	public static List<Book> readBookList(InputStream input) throws IOException {
		List<Book> bookList = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode tree = mapper.readTree(input);

		for (int i = 0; i < tree.size(); i++) {
			String author = tree.get(i).get("author").asText();
			String country = tree.get(i).get("country").asText();
			String imageLink = tree.get(i).get("imageLink").asText();
			String language = tree.get(i).get("language").asText();
			String link = tree.get(i).get("link").asText();
			int pages = tree.get(i).get("pages").asInt();
			String title = tree.get(i).get("title").asText();
			int year = tree.get(i).get("year").asInt();

			Book createBook = new Book(author, country, title, imageLink, link, language, pages, year);

			bookList.add(createBook);
		}

		return bookList;
	}

}
