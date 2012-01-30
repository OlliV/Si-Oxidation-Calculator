/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationModel;

import java.util.ArrayList;

/**
 *
 * @author ollivanhoja
 */
public class SiOxidationModelArray {
    public SiOxidationModelArray()
    {
        oxidationModels = new ArrayList<SiOxidationModel>();
        names = new ArrayList<String>();
    }
    
    public SiOxidationModel get(int i)
    {
        return this.oxidationModels.get(i);
    }
    
    public String getName(int i)
    {
        return this.names.get(i);
    }
    
    public void add(SiOxidationModel model)
    {
        oxidationModels.add(model);
        names.add(null);
    }
    
    public void add(SiOxidationModel model, String name)
    {
        oxidationModels.add(model);
        names.add(name);
    }
    
    public void setName(int i, String name)
    {
        names.set(i, name);
    }
    
    public void setModel(int i, SiOxidationModel model)
    {
        oxidationModels.set(i, model);
    }
    
    public void set(int i, SiOxidationModel model, String name)
    {
        oxidationModels.set(i, model);
        names.set(i, name);
    }
    
    public void remove(SiOxidationModel model)
    {
        int i = oxidationModels.indexOf(model);
        oxidationModels.remove(model);
        names.remove(i);
    }
    
    public void remove(int i)
    {
        oxidationModels.remove(i);
        names.remove(i);
    }
    
    public void clear()
    {
        oxidationModels.clear();
        names.clear();
    }
    
    public int size()
    {
        return oxidationModels.size();
    }
    
    public void trim()
    {
        oxidationModels.trimToSize();
        names.trimToSize();
    }
    
    private ArrayList<SiOxidationModel> oxidationModels;
    private ArrayList<String> names;
}
