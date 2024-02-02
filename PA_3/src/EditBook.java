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
public class EditBook extends JDialog {

	private String title = "";
	private String author = "";
	private String image = "";
	private int year = 0;

	private JTextField userTitle = new JTextField(20);
	private JTextField userImage = new JTextField(20);
	private JTextField userAuthor = new JTextField(20);
	private JTextField userYear = new JTextField(20);

	private JButton submit = new JButton("Submit");
	private JButton cancel = new JButton("Cancel");

	public EditBook(Frame owner, String str) {
		super(owner, str);

		setBounds(300, 300, 310, 300);
		Container DialogContent = getContentPane();
		DialogContent.setLayout(new FlowLayout());

		add(new JLabel("Title"));
		add(userTitle);

		add(new JLabel("Author"));
		add(userAuthor);
		
		add(new JLabel("Image"));
		add(userImage);

		add(new JLabel("Year"));
		add(userYear);

		add(submit);
		add(cancel);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle(userTitle.getText());
				setAuthor(userAuthor.getText());
				setImage(userImage.getText());
				setYear(Integer.parseInt(userYear.getText()));
				
				EditBook.this.setVisible(false);
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
