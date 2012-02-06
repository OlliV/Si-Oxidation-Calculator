/* Silicon Wafer Oxidation Layer Calculator
 * Copyright (C) 2012  Olli Vanhoja, olli.vanhoja@gmail.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
