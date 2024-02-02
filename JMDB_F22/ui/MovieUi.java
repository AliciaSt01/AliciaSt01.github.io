import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MovieUi {
	private JFrame frame;
	private JPanel searchPanel;
	private JPanel resultPanel;
	private JPanel homePanel;
	private JScrollPane scrollPane;
	private JTextField searchBar;
	private JButton searchButton;
	private MovieInfoPanel movieInfoPanel;
	private JTree tree;
	private DefaultMutableTreeNode root;
	private MovieTreeCellRenderer renderer;
	private JLabel errorMessage;
	private List<Movie> results;
	private JProgressBar progressBar;
	private Movie selected;

	/**
	 * MovieUi constructor, initialization of values of the core UI
	 */
	public MovieUi() {
		frame = new JFrame();

		searchPanel = new JPanel(new BorderLayout());
		searchBar = new JTextField();
		searchButton = new JButton("Search");

		homePanel = new JPanel(new BorderLayout());
		resultPanel = new JPanel(new BorderLayout());
		scrollPane = new JScrollPane();
		errorMessage = new JLabel("There were no movies that matched your search.");

		movieInfoPanel = new MovieInfoPanel();

		frame.setTitle("JMdB");
		buildGUI();
	}

	/**
	 * Builds the basic panels and adds them to the UI.
	 */
	public void buildGUI() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//    JLabel loadingLabel = new JLabel("Loading...");
//    loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
//    resultPanel.add(loadingLabel, BorderLayout.CENTER);

		searchPanel.add(searchBar, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);
		searchPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		// Retrieves and displays search results for the given search key when search
		// button is clicked.
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// search if the search-bar isn't blank
				homePanel.setVisible(false);
				if (!searchBar.getText().equals("")) {
					search();
				} else {
					errorMessage.setText("Please type a title or kewyord into the search bar in order to search.");
					resultPanel.add(errorMessage, BorderLayout.CENTER);
				}
			}
		});

		buildInfoPanel();

		frame.setUndecorated(false);
		frame.setLayout(new BorderLayout());

		// Add the frame's components to the frame
		frame.add(searchPanel, BorderLayout.NORTH);
		frame.add(resultPanel, BorderLayout.CENTER);

		frame.setVisible(true);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setMinimumSize(new Dimension(frame.getWidth() / 2, frame.getHeight() / 2));
	}

	/**
	 * Displays the top highest rated movies when the program starts running.
	 */
	private void initializeStartingScreen() {		
		refreshResultPanel();
		resultPanel.setSize(resultPanel.getPreferredSize());

		JLabel welcomeLabel = new JLabel("Welcome to JMdB!");
		welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), Font.PLAIN, 25));
		welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		try {
			results = ListFunctions.createTopMovieList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		buildResults();
		root.setUserObject("Highest Rated Movies");
		resultPanel.add(welcomeLabel, BorderLayout.NORTH);
		resultPanel.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * The search function activated by the search button.
	 */
	private void search() {
		resultPanel.removeAll();
		refreshResultPanel();
		homePanel.setVisible(false);
		movieInfoPanel.setVisible(false);

		try {
			results = ListFunctions.createList(searchBar.getText());
			if (results.size() == 0) {
				resultPanel.add(errorMessage, BorderLayout.CENTER);
			} else {
				buildResults();
				resultPanel.add(scrollPane, BorderLayout.CENTER);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Builds the results of the search as a tree.
	 */
	private void buildResults() {
		progressBar = new JProgressBar(0, results.size());
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		
		root = new DefaultMutableTreeNode(String.format("Results for \"%s\"", searchBar.getText()));
		for (int i = 0; i < results.size(); i++) {
			root.add(new DefaultMutableTreeNode(results.get(i)));
		}
		tree = new JTree(new DefaultTreeModel(root));

		// When a movie node is clicked, displays the information for that given movie.
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				selected = (Movie) node.getUserObject();
				try {
					resultPanel.setVisible(false);
					frame.remove(resultPanel);
					frame.add(movieInfoPanel, BorderLayout.CENTER);
					movieInfoPanel.displayInformation(selected, resultPanel.getHeight());
				} catch (MalformedURLException e1) {
					frame.add(movieInfoNotFoundPanel());
				}

				// Allow the user to select and display the same movie multiple times in a row
				try {
					tree.clearSelection();
				} catch (NullPointerException e2) {
					tree.clearSelection();
				}
			}
		});

		renderer = new MovieTreeCellRenderer();
		tree.setCellRenderer(renderer);
		scrollPane = new JScrollPane(tree);
	}

	/**
	 * Sets resultPanel to not visible, removes from and re-adds it to the frame,
	 * and sets it back to visible. In a helper method to avoid duplicate code.
	 */
	private void refreshResultPanel() {
		resultPanel.setVisible(false);
		frame.remove(resultPanel);
		frame.add(resultPanel, BorderLayout.CENTER);
		resultPanel.setVisible(true);
	}

	/**
	 * Creates the info panel that a particular movie's information is displayed on.
	 */
	private void buildInfoPanel() {
		// Close the info panel when the close button is clicked.
		movieInfoPanel.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				movieInfoPanel.setVisible(false);
				frame.remove(movieInfoPanel);
				resultPanel.setVisible(true);
				frame.add(resultPanel);
			}
		});
		movieInfoPanel.gameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RatingGame game = new RatingGame(selected);
				game.run();
			}
			
		});
	}

	/**
	 * In the case of a MalformedURLException, display an error panel.
	 * 
	 * @return error panel as a JFrame
	 */
	private JFrame movieInfoNotFoundPanel() {
		JFrame errorFrame = new JFrame();
		errorFrame.setSize(200, 100);
		JPanel panel = new JPanel(new BorderLayout());
		JLabel error = new JLabel("Error: no report.");
		panel.add(error, BorderLayout.CENTER);

		JButton exit = new JButton("X");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(errorFrame);
				errorFrame.setVisible(false);
				System.exit(0);
			}
		});
		panel.add(exit, BorderLayout.SOUTH);
		errorFrame.add(panel);
		errorFrame.setVisible(true);
		return errorFrame;
	}
	
	

	/**
	 * Runs the UI display.
	 */
	public void run() {
		initializeStartingScreen();
		frame.pack();
	}

	public static void main(String[] args) {
		MovieUi movieUi = new MovieUi();
		movieUi.run();
	}

}
