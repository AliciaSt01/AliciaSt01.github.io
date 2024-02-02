import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddBook extends JDialog {

	private String title = "";
	private String author = "";
	private String country = "";
	private String language = "";
	private String link = "";
	private String imageLink = "";
	private int pages = 0;
	private int year = 0;

	private JTextField userTitle = new JTextField(20);
	private JTextField userAuthor = new JTextField(20);
	private JTextField userYear = new JTextField(20);
	private JTextField userCountry = new JTextField(20);
	private JTextField userPages = new JTextField(20);
	private JTextField userLanguage = new JTextField(20);
	private JTextField userImageLink = new JTextField(20);
	private JTextField userLink = new JTextField(20);

	private JButton submit = new JButton("Submit");
	private JButton cancel = new JButton("Cancel");

	public AddBook(Frame owner, String str) {
		super(owner, str);

		setBounds(300, 300, 315, 300);
		Container DialogContent = getContentPane();
		DialogContent.setLayout(new FlowLayout());

		add(new JLabel("Title"));
		add(userTitle);

		add(new JLabel("Author"));
		add(userAuthor);

		add(new JLabel("Year"));
		add(userYear);

		add(new JLabel("Country"));
		add(userCountry);

		add(new JLabel("Pages"));
		add(userPages);

		add(new JLabel("Language"));
		add(userLanguage);
		
		add(new JLabel("Image Link"));
		add(userImageLink);
		
		add(new JLabel("Link"));
		add(userLink);

		add(submit);
		add(cancel);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle(userTitle.getText());
				setAuthor(userAuthor.getText());
				setLanguage(userLanguage.getText());
				setCountry(userCountry.getText());
				setImageLink(userImageLink.getText());
				setLink(userLink.getText());
				setYear(Integer.parseInt(userYear.getText()));
				setPages(Integer.parseInt(userPages.getText()));
				
				AddBook.this.setVisible(false);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
}
