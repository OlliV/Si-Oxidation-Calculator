/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationModel;

import oxidationSession.SiWafer;

/**
 * Generic oxidation model for silicon wafers
 * @author ollivanhoja
 */
public class genericOxidationModel extends SiOxidationModel
{
    private double Xi;

    public genericOxidationModel(double EaBA, double EaB, double D0BA, double D0B, SiWafer wafer, double Xi)
    {
        this.EaBA = EaBA;
        this.EaB = EaB;
        this.D0BA = D0BA;
        this.D0B = D0B;
        this.wafer = wafer;
        this._Xi = Xi;
        BA = new SiOxidationModel.ArrheniusRl(D0BA, EaBA);
        B = new SiOxidationModel.ArrheniusRl(D0B, EaB);
    }
}
