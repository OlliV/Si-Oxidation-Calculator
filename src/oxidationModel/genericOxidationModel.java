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
        name = "";
    }
}
