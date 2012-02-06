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
                name = "Dry oxidation <100>";
                EaBA = 2.00;
                EaB = 1.23;
                D0BA = 3700000.0;
                D0B = 772.0;
                break;
            case mi111:
                name = "Dry oxidation <111>";
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
