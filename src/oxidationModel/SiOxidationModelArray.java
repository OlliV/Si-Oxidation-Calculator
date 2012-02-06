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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ollivanhoja
 */
public class SiOxidationModelArray {
    public SiOxidationModelArray()
    {
        oxidationModels = new ArrayList<SiOxidationModel>();
    }
    
    public SiOxidationModel get(int i)
    {
        return this.oxidationModels.get(i);
    }
    
    public SiOxidationModel get(String name)
    {
        for (Iterator<SiOxidationModel> it = oxidationModels.iterator(); it.hasNext();) {
            SiOxidationModel model = it.next();
            
            if (model.name.equals(name))
                return model;
        }
        
        throw new IndexOutOfBoundsException();
    }
    
    public String getName(int i)
    {
        return this.oxidationModels.get(i).getName();
    }
    
    public String[] getNames()
    {
        int size = oxidationModels.size();
        String[] names = new String[size];
        for (int i=0; i < size; i++)
        {
            names[i] = oxidationModels.get(i).getName();
        }
        
        return names;
    }
    
    public void add(SiOxidationModel model)
    {
        oxidationModels.add(model);
    }
    
    public void add(SiOxidationModel model, String name)
    {
        oxidationModels.add(model);
    }
    
    public void setName(int i, String name)
    {
        this.oxidationModels.get(i).setName(name);
    }
    
    public void setModel(int i, SiOxidationModel model)
    {
        oxidationModels.set(i, model);
    }
    
    public void set(int i, SiOxidationModel model, String name)
    {
        oxidationModels.set(i, model);
        this.oxidationModels.get(i).setName(name);
    }
    
    public void remove(SiOxidationModel model)
    {
        int i = oxidationModels.indexOf(model);
        oxidationModels.remove(model);
    }
    
    public void remove(int i)
    {
        oxidationModels.remove(i);
    }
    
    public void clear()
    {
        oxidationModels.clear();
    }
    
    public int size()
    {
        return oxidationModels.size();
    }
    
    public void trim()
    {
        oxidationModels.trimToSize();
    }
    
    private ArrayList<SiOxidationModel> oxidationModels;
}
