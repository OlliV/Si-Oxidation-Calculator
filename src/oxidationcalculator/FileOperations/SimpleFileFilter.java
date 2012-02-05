package oxidationcalculator.FileOperations;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.util.ArrayList;

/**
 *
 * @author ollivanhoja
 */
public class SimpleFileFilter extends FileFilter {
    ArrayList<String> fileTypes;
    String fileTypeDescription;
    
    public SimpleFileFilter(String[] fileTypes, String fileTypeDescription)
    {
        this.fileTypes = new ArrayList<String>();
        
        for (String fileType : fileTypes)
            this.fileTypes.add(fileType);
        this.fileTypeDescription = fileTypeDescription;
    }
    
    public SimpleFileFilter(String fileType, String fileTypeDescription)
    {
        this.fileTypes = new ArrayList<String>();
        
        addFileType(fileType);
        this.fileTypeDescription = fileTypeDescription;
    }
    
    final public void addFileType(String fileType)
    {
        fileTypes.add(fileType);
    }
            
    
    @Override
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            for (String fileType : fileTypes)
            {
                if (fileType.equals(extension))
                    return true;
                else
                    return false;
            }
        }

        return false;
    }
    
    public void setDescription(String fileTypeDescription)
    {
        this.fileTypeDescription = fileTypeDescription;
    }
    
    @Override
    public String getDescription()
    {
        return fileTypeDescription;
    }
}
