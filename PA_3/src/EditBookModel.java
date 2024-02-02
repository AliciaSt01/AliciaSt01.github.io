import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class EditBookModel extends JFrame implements ActionListener {

	private JTextField title = new JTextField("Title");
	private JTextField author = new JTextField();
	private JTextField year = new JTextField();
	private JButton submit = new JButton("Submit");
	private JLabel label;

	ObjectMapper mapper = new ObjectMapper();
	Book editBook;

	EditBookModel() {
		this.setLayout(new FlowLayout());
		editBookFrame();;
	}

	public void editBookFrame() {
		label = new JLabel();
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

		add(submit, BorderLayout.SOUTH);
		setSize(500, 300);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		submit.addActionListener(this);
	}


	public static void main(String[] args) {
		new EditBookModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
