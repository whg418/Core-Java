package fileChooser;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
/**
 * a file chooser accessory that previews images
 * @author whg
 *
 */
public class ImagePreviewer extends JLabel {
	public ImagePreviewer(JFileChooser chooser)
	{
		setPreferredSize(new Dimension(100, 100));
		setBorder(BorderFactory.createEtchedBorder());
		
		chooser.addPropertyChangeListener(event -> {
			if(event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
			{
				//the user has changed selected file
				File f = (File)event.getNewValue();
				if(f == null)
				{
					setIcon(null);
					return;
				}
				
				//read image into label
				ImageIcon icon = new ImageIcon(f.getName());
				
				//if the image is too large to fit, scale it
				if(icon.getIconWidth() > getWidth())
					icon = new ImageIcon(icon.getImage().getScaledInstance(
							getWidth(), -1, Image.SCALE_DEFAULT));
				
				setIcon(icon);
			}
		});
	}
}
