package fileChooser;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileView;
import javax.swing.filechooser.FileFilter;

public class FileIconViewer extends FileView {
	private FileFilter filter;
	private Icon icon;
	/**
	 * constructs a FileIconViewer
	 * @param filter2 a file filter -- all file that this filter accepts will shown
	 * with the icon
	 * @param icon -- the icon shown with all accepted file
	 */
	public FileIconViewer(FileFilter filter2, Icon icon)
	{
		this.filter = filter2;
		this.icon = icon;
	}
	
	public Icon getIcon(File f)
	{
		if(!f.isDirectory() && filter.accept(f)) return icon;
		else return null;
	}
}
