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

import java.io.Serializable;

/**
 * Silicon wafer material
 * @author ollivanhoja
 */
public class SiWafer implements Serializable {

    /**
     * Crystal orientation of the wafer
     * @return the crystal orientation
     */
    public eWaferOrientation getOrientation() {
        return orientation;
    }
    /**
     * @param orientation the crystal orientation to set
     */
    public void setOrientation(eWaferOrientation orientation) {
        this.orientation = orientation;
    }  
    /**
     * X_o = oxidation layer thickness on wafer
     */
    public double getXo()
    {
        return Xo;
    }
    /**
     * X_o = oxidation layer thickness on wafer
     */
    public void setXo(double Xo)
    {
        this.Xo = Xo;
    }
    
     /**
     * Get h of wafer
     * @return 
     */
    public double get_h()
    {
        return h + (0.54 * Xo);
    }
    /**
     * Set h of wafer
     * @param h 
     */
    public void set_h(double h)
    {
        this.h = h;
    }
    
    private eWaferOrientation orientation;
    private double h;
    private transient double Xo;
   
    
    /**
     * Silicon wafer
     * @param orientation
     * @param h 
     */
    public SiWafer(eWaferOrientation orientation, double h)
    {
        this.orientation = orientation;
        this.h = h;
        Xo = 0.0;
    }
    
        /**
     * Possible crystal orientations for silicon wafer
     */
    public enum eWaferOrientation
    {
        mi100,
        mi111;
        
        @Override
        public String toString(){
            String[] splitNames = name().toLowerCase().split("_");
            StringBuffer fixedName = new StringBuffer();

            for(int i = 0; i < splitNames.length; i++)
            {
                String firstLetter = splitNames[i].substring(0, 1).toUpperCase(),
                restOfWord = splitNames[i].substring(1),
                spacer = (i == splitNames.length) ? "" : " ";

                fixedName.append(firstLetter).append(restOfWord).append(spacer);
            }

            return fixedName.toString();
        }
    }
}
