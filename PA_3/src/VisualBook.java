import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SuppressWarnings("serial")
public class VisualBook extends JFrame implements ActionListener {

	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode author;
	private DefaultMutableTreeNode book;
	private JLabel selectedLabel;
	private JTree tree;
	private JButton addButton;
	private JButton editButton;
	private String key;
	private FileInputStream bookFile;

	private List<Book> booklist;
	private Map<String, JLabel> booksMap = new HashMap<String, JLabel>();

	private ObjectMapper mapper = new ObjectMapper();
	private ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
	private Book addNewBook;
	private AddBook newBook;

	public VisualBook(String file) throws IOException {
		bookFile = new FileInputStream(file);
		booklist = BookListReader.readBookList(bookFile);

		mapDisplay();
	}

	public void mapDisplay() {

		sort(booklist);

		root = new DefaultMutableTreeNode("Root");
		author = new DefaultMutableTreeNode("Author");
		book = new DefaultMutableTreeNode("Book");

		createMap(root, author, book);

		tree = new JTree(root);

		selectedLabel = new JLabel();
		selectedLabel.setPreferredSize(new Dimension(500, 500));
		addButton = new JButton("Add New Book");
		editButton = new JButton("Edit Book");

		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				if (selectedNode.isLeaf()) {
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();

					key = parent.toString() + " " + selectedNode.toString();
					selectedLabel.setText(booksMap.get(key).getText());
					selectedLabel.setIcon(booksMap.get(key).getIcon());
					add(editButton, BorderLayout.EAST);
				}
			}
		});

		selectedLabel.setHorizontalTextPosition(JLabel.CENTER);
		selectedLabel.setVerticalTextPosition(JLabel.BOTTOM);
		add(selectedLabel, BorderLayout.CENTER);
		add(new JScrollPane(tree), BorderLayout.WEST);
		add(addButton, BorderLayout.SOUTH);
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 600);
		setVisible(true);
	}

	public void sort(List<Book> books) {
		int comparingStrings;

		for (int i = 0; i < books.size() - 1; i++) {
			Book smallest = books.get(i);

			for (int j = i + 1; j < books.size(); j++) {
				String currentComparison = books.get(j).getAuthor();

				comparingStrings = smallest.getAuthor().compareTo(currentComparison);
				if (comparingStrings > 0) {
					smallest = books.get(j);
					Collections.swap(books, i, j);
				}
			}
		}
	}

	public void createMap(DefaultMutableTreeNode root, DefaultMutableTreeNode author, DefaultMutableTreeNode book) {
		String display = "";
		String currentAuthor = booklist.get(0).getAuthor();
		author = new DefaultMutableTreeNode(currentAuthor);
		root.add(author);

		for (int i = 0; i < booklist.size(); i++) {

			String compareAuthors = booklist.get(i).getAuthor();

			if (compareAuthors.compareTo(currentAuthor) == 0) {
				book = new DefaultMutableTreeNode(booklist.get(i).getTitle());
				author.add(book);
			}

			else {
				currentAuthor = booklist.get(i).getAuthor();
				author = new DefaultMutableTreeNode(currentAuthor);
				root.add(author);
				book = new DefaultMutableTreeNode(booklist.get(i).getTitle());
				author.add(book);
			}

			int release = booklist.get(i).getYear();
			String year = "";

			if (release < 0) {
				release = release * (-1);
				year = String.valueOf(release) + " BCE";
			} else
				year = String.valueOf(release);

			ImageIcon image = new ImageIcon(booklist.get(i).getImageLink());

			display = booklist.get(i).getTitle() + " by " + booklist.get(i).getAuthor() + " (" + year + ")";
			selectedLabel = new JLabel(display, image, JLabel.LEFT);

			booksMap.put(booklist.get(i).getAuthor() + " " + booklist.get(i).getTitle(), selectedLabel);

		}
	}

	public static void main(String[] args) throws IOException {
		new VisualBook("books.json");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			newBook = new AddBook(VisualBook.this, "New Book Information");
			newBook.setModal(true);
			newBook.setVisible(true);
		} else if (e.getSource() == editButton) {
			EditBook editBook = new EditBook(VisualBook.this, "Edit Book Information");
			editBook.setModal(true);
			editBook.setVisible(true);
		}

		ImageIcon image = new ImageIcon(newBook.getImageLink());

		String display = newBook.getTitle() + " by " + newBook.getAuthor() + " (" + newBook.getYear() + ")";

		selectedLabel = new JLabel(display, image, JLabel.LEFT);
		booksMap.put(newBook.getAuthor() + " " + newBook.getTitle(), selectedLabel);
		javaToJSON();
	}

	public void javaToJSON() {
		try {
			addNewBook = new Book(newBook.getAuthor(), newBook.getCountry(), newBook.getTitle(), newBook.getImageLink(),
					newBook.getLink(), newBook.getLanguage(), newBook.getPages(), newBook.getYear());

			booklist.add(addNewBook);
			writer.writeValue(new File("books.json"), booklist);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void JSONToJava() {
		try {
			Book editBook = mapper.readValue(new File("books.json"), Book.class);
			System.out.println(editBook);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
