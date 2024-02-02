import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class LocalCache {
  private static HashMap<String, ImageIcon> ImageCache = new HashMap<>();
  
  public static ImageIcon imageCache(String imageUrl) {
    Image titleImage = null;
    ImageIcon result = null;
    if (ImageCache.containsKey(imageUrl)) {
      result = ImageCache.get(imageUrl);
    } else {
      try {
        titleImage = new ImageIcon(new URL(imageUrl)).getImage();
        titleImage = titleImage.getScaledInstance(-1, 65,
            java.awt.Image.SCALE_SMOOTH);
        result = new ImageIcon(titleImage);
        ImageCache.put(imageUrl, result);
      }
      catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return result;
  }
}