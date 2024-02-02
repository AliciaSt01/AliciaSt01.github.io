import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class AddBookModel extends JFrame implements ActionListener {

	private JTextField title = new JTextField();
	private JTextField author = new JTextField();
	private JTextField year = new JTextField();
	private JTextField country = new JTextField();
	private JTextField pages = new JTextField();
	private JTextField language = new JTextField();
	private JButton submit = new JButton("Submit");
	private JLabel label;

	ObjectMapper mapper = new ObjectMapper();
	Book addNewBook;

	AddBookModel() {
		this.setLayout(new FlowLayout());
		addBookFrame();
		submit.addActionListener(this);
		javaToJSON();
	}

	public void addBookFrame() {
		label = new JLabel("Title");
		add(label);
		add(title);
		title.setPreferredSize(new Dimension(250, 40));

		label = new JLabel("Author");
		add(label);
		add(author);
		author.setPreferredSize(new Dimension(250, 40));

		label = new JLabel("Year");
		add(label);
		add(year);
		year.setPreferredSize(new Dimension(250, 40));

		label = new JLabel("Country");
		add(label);
		add(country);
		country.setPreferredSize(new Dimension(250, 40));

		label = new JLabel("Pages");
		add(label);
		add(pages);
		pages.setPreferredSize(new Dimension(250, 40));

		label = new JLabel("Language");
		add(label);
		add(language);
		language.setPreferredSize(new Dimension(250, 40));

		add(submit, BorderLayout.SOUTH);
		pack();
		setSize(800, 400);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void javaToJSON() {
		try {
			addNewBook = new Book(author.getText(), country.getText(), title.getText(), null, null, language.getText(),
					Integer.parseInt(pages.getText()), Integer.parseInt(year.getText()));

			mapper.writeValue(new File("books.json"), addNewBook);

			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addNewBook);

			System.out.println(jsonString);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			dispose();
		}

	}

	public static void main(String[] args) throws IOException {
		new AddBookModel();
	}

}
