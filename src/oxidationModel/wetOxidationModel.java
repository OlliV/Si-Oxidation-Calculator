/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationModel;

import oxidationSession.SiWafer;

/**
 * Wet oxidation model for silicon wafer
 * @author ollivanhoja
 */
public class wetOxidationModel extends SiOxidationModel
{
    public wetOxidationModel(SiWafer wafer)
    {
        set_parameters(wafer);
    }

    private void set_parameters(SiWafer wafer)
    {
        this.wafer = wafer;
        switch(wafer.getOrientation())
        {
            case mi100:
                EaBA = 2.05;
                EaB = 0.78;
                D0BA = 97000000;
                D0B = 386.0;
                break;
            case mi111:
                EaBA = 2.05;
                EaB = 0.78;
                D0BA = 163000000.0;
                D0B = 386.0;
                break;
        }
        _Xi = 0.0;
        BA = new SiOxidationModel.ArrheniusRl(D0BA, EaBA);
        B = new SiOxidationModel.ArrheniusRl(D0B, EaB);
    }
}
