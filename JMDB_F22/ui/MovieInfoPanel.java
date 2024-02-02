import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieInfoPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JPanel closeButtonPanel;
  public JButton closeButton;
  public JButton gameButton;
  private JPanel basicInfoPanel;
  private JLabel image;
  private JLabel title;
  private JPanel actorPanel = new JPanel(new BorderLayout(100,100));

  private JPanel descriptionPanel;
  private JTextArea description = new JTextArea(10, 10);
  private JScrollPane descriptionScrollPane;
  private JTree tree;
  private DefaultMutableTreeNode root;

  public MovieInfoPanel() {
    super(new BorderLayout());
    buildComponents();

    setVisible(false);
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    
    add(basicInfoPanel, BorderLayout.NORTH);
    add(descriptionPanel, BorderLayout.WEST);
    add(new JScrollPane(actorPanel), BorderLayout.EAST);
  }

  /**
   * Initialize each of the MovieInfoPanel's components
   */
  private void buildComponents() {
    basicInfoPanel = new JPanel(new BorderLayout());
    image = new JLabel();
    title = new JLabel();

    // close button
    closeButtonPanel = new JPanel();
    closeButton = new JButton("X");
    closeButtonPanel.add(closeButton, BorderLayout.NORTH);
    closeButtonPanel.setSize(closeButton.getSize());
    gameButton = new JButton("+/-");
    closeButtonPanel.add(gameButton, BorderLayout.SOUTH);

    // basic movie information (poster, title, year)
    basicInfoPanel.add(closeButtonPanel, BorderLayout.EAST);
    basicInfoPanel.add(image, BorderLayout.WEST);
    basicInfoPanel.add(title, BorderLayout.SOUTH);

    // movie description
    descriptionPanel = new JPanel(new BorderLayout());
    description = new JTextArea();
    description.setEditable(false);
    descriptionScrollPane = new JScrollPane(description);
    descriptionPanel.add(descriptionScrollPane, BorderLayout.WEST);
    descriptionPanel.setSize(new Dimension(10,10));
    
    actorPanel.setSize(10,10);

  }

  /**
   * Display the information for a selected movie.
   *
   * @param selected    movie selected
   * @param panelHeight height of panel to use for scaling the movie poster
   * @throws MalformedURLException if the movie poster URL link is malformed
   */
  public void displayInformation(Movie selected, int panelHeight) throws MalformedURLException {
    /*
     * basicInfoPanel.setSize(resultPanel.getWidth(), resultPanel.getHeight() / 3 *
     * 2);
     */

    // scale movie poster while maintaining aspect ratio
    Image poster = new ImageIcon(new URL(selected.imageLink())).getImage();
    poster = poster.getScaledInstance(-1, (int) (panelHeight / 2), java.awt.Image.SCALE_SMOOTH);
    ImageIcon posterIcon = new ImageIcon(poster);

    image.setIcon(posterIcon);
    image.setSize(basicInfoPanel.getWidth() / 3, basicInfoPanel.getHeight());

    title.setText(selected.toString());
    description.setText(selected.plotSummary());
    updateActorScrollPane(selected);
    
    //rating.setText(selected.rating());

    gameButton.removeAll();
    gameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new RatingGame(selected).run();;
      }
    });


    setVisible(true);
  }

  /**
   * Initializes scroll pane for the actor field on the movie information panel.
   */
  private void updateActorScrollPane(Movie selected) throws MalformedURLException {
    
    root = new DefaultMutableTreeNode();
    actorPanel.removeAll();
    
    
    if (this.tree != null) {
      tree.setVisible(false);
      TreeModel model = tree.getModel();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
      root.removeAllChildren();
      tree.setVisible(true);
    }
    

    for (Actor actor : selected.actorList()) {

      // actorPanel.add(new JLabel(new ImageIcon(new URL(actor.getImageLink()))));
      root.add(new DefaultMutableTreeNode(actor.getName()));
      // actorScroll.add(new JLabel(actor.getName().toString()));
      // actorPanel.add(new JLabel(actor.getName(),new ImageIcon(new URL(actor.getImageLink())), SwingConstants.TRAILING));
    }

    
    tree = new JTree(new DefaultTreeModel(root));
    tree.setRootVisible(false);
    actorPanel.add(tree);
  }
}
