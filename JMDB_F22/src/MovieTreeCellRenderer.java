import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MovieTreeCellRenderer extends DefaultTreeCellRenderer {
	private final JLabel label;
	private ImageIcon imageIcon;

	public MovieTreeCellRenderer() {
		label = new JLabel();
		imageIcon = new ImageIcon();
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		Object userObj = ((DefaultMutableTreeNode) value).getUserObject();
		if (userObj instanceof Movie) {
			Movie movie = (Movie) userObj;
			URL imageUrl = null;
			try {
				imageUrl = new URL(movie.imageLink());
			} catch (MalformedURLException e) {
			}
			if (imageUrl != null) {
				imageIcon = LocalCache.imageCache(imageUrl.toString());
				label.setIcon(imageIcon);
			}
			label.setText(movie.toString());
		} else {
			label.setIcon(null);
			label.setText(value.toString());
		}
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		if (row % 2 == 1) {
			Color bg = Color.WHITE;
			label.setBackground(new Color(bg.getRed() - 50, bg.getGreen() - 50, bg.getBlue() - 50));
		}

		return label;
	}

}
