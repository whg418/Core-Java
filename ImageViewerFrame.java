package fileChooser;

import java.awt.Image;
import java.io.File;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;

public class ImageViewerFrame extends JFrame {
	private static final int DEFAULT_HEIGHT = 400;
	private static final int DEFAULT_WIDTH = 300;
	
	private JLabel label;
	private JFileChooser chooser;
	
	public ImageViewerFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//set menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		//set the open item
		JMenuItem openItem = new JMenuItem("open");
		menu.add(openItem);
		openItem.addActionListener(event -> {
			chooser.setCurrentDirectory(new File("."));
			
			//show file chooser dialog
			int result = chooser.showOpenDialog(ImageViewerFrame.this);
			
			//if image file accepted, set it as icon of the label
			if(result == JFileChooser.APPROVE_OPTION)
			{
				String name = chooser.getSelectedFile().getPath();
				label.setIcon(new ImageIcon(name));
				pack();
			}
		});
		//set the exit item
		JMenuItem exitItem = new JMenuItem("exit");
		menu.add(exitItem);
		exitItem.addActionListener(event -> System.exit(0));
		
		//use a label to show the image file selected
		label = new JLabel();
		add(label);
		
		//set up file chooser
		chooser = new JFileChooser();
		//accept all image files ending with .jpg, .jpeg, .gif
		FileFilter filter = new FileNameExtensionFilter("Image file", "jpg", "jpeg", "gif");
		chooser.setFileFilter(filter);
		
		chooser.setAccessory(new ImagePreviewer(chooser));
		
		Image image =  new ImageIcon(
				"C:\\Users\\whg\\Pictures\\矢量图\\调色板.jpg").getImage()
				.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
		chooser.setFileView(new FileIconViewer(filter, new ImageIcon(image)));
	}
}
