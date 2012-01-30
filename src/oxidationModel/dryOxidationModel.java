/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationModel;

import oxidationSession.SiWafer;

/**
 * Dry oxidation model for silicon wafer
 * @author ollivanhoja
 */
public class dryOxidationModel extends SiOxidationModel
{
    public dryOxidationModel(SiWafer wafer)
    {
        set_parameters(wafer);
    }

    private void set_parameters(SiWafer wafer)
    {
        this.wafer = wafer;
        switch(wafer.getOrientation())
        {
            case mi100:
                EaBA = 2.00;
                EaB = 1.23;
                D0BA = 3700000.0;
                D0B = 772.0;
                break;
            case mi111:
                EaBA = 2.00;
                EaB = 1.23;
                D0BA = 6230000.0;
                D0B = 772.0;
                break;
        }
        _Xi = 0.025;
        BA = new SiOxidationModel.ArrheniusRl(D0BA, EaBA);
        B = new SiOxidationModel.ArrheniusRl(D0B, EaB);
    }
}
