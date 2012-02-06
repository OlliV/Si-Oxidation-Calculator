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
