/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationcalculator;

import oxidationModel.*;
import oxidationSession.SiWafer;
import oxidationSession.oxidationSession;

/**
 *
 * @author ollivanhoja
 */
public class UISession
{
    oxidationSession oxs;
    SiOxidationModelArray oxidationModels;

    public SiWafer getWafer()
    {
        return this.wafer;
    }
    
    public boolean getNoData()
    {
        return this.noData;
    }

    private SiWafer wafer;
    private boolean noData;

    public UISession()
    {
        clear();
    }
    public final void clear()
    {
        noData = true;
        wafer = null;
        oxidationModels = new SiOxidationModelArray();
        oxs = new oxidationSession();
    }

    void setWafer(SiWafer.eWaferOrientation orientation, double h)
    {
        clear();
        wafer = new SiWafer(orientation, h);
        // Add standard oxidation models
        dryOxidationModel procDry = new dryOxidationModel(wafer);
        wetOxidationModel procWet = new wetOxidationModel(wafer);
        oxidationModels.add(procDry, procDry.toString());
        oxidationModels.add(procWet, procWet.toString());
    }

    void addConsttPhase(double T, double t, int modelI)
    {
        oxs.addPhase(T, t, oxidationModels.get(modelI), false);
        noData = false;
    }
    
    void addConstXoPhase(double T, double Xo, int modelI)
    {
        oxs.addPhase(T, Xo, oxidationModels.get(modelI), true);
        noData = false;
    }
}
