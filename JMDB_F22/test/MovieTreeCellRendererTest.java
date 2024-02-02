import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.jupiter.api.Test;

class MovieTreeCellRendererTest {

	@Test
	void testMovieTreeCellRenderer() {
		new MovieTreeCellRenderer();
	}

	@Test
	void testGetTreeCellRendererComponent() {
		Movie val = new Movie("tt0307453", "", "", "", null, "", "", "", "", "");
		JTree tempTree = new JTree();
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(val);
		new MovieTreeCellRenderer().getTreeCellRendererComponent(tempTree, node, false, false, false, 0, false);

		Movie val2 = new Movie("", "", "", "", null, "", "", "", "", "");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(val2);
		new MovieTreeCellRenderer().getTreeCellRendererComponent(tempTree, node2, false, false, false, 5, false);
	}

}
