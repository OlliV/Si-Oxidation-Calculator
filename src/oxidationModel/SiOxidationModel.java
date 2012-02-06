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

/**
 * Oxidation model for silicon wafers
 * @author ollivanhoja
 */
package oxidationModel;

import java.io.Serializable;

/**
 * Oxidation model for silicon wafers
 * @author ollivanhoja
 */
interface IOxidationModel extends Serializable
{
    /**
     * E_A = Activation energy for linear rate constant
     * @return E_a for B/A
     */
    public double getEaBA();
    /**
     * E_A Activation energy for linear rate constant
     * @param EaBA E_a for B/A
     */
    public void setEaBA(double EaBA);
    
    /**
     * E_A Activation energy for parabolic rate constant
     * @return E_a for B
     */   
    public double getEaB();
    /**
     * E_A Activation energy for parabolic rate constant
     * @param EaB E_a for B
     */
    public void setEaB(double EaB);
    
    /**
     * Diffusivity coefficient for linear rate constant
     * @return D_0 for B/A
     */
    public double getD0BA();
    /**
     * Diffusivity coefficient for linear rate constant
     * @param D0BA D_0 for B/A
     */
    public void setD0BA(double D0BA);
    
    /**
     * Diffusivity constant for parabolic rate constant
     * @return D_0 for B
     */
    public double getD0B();
    /**
     * Diffusivity coefficient for parabolic rate constant
     * @param D0B D_0 for B
     */
    public void setD0B(double D0B);
    
    /**
     * Silicon wafer
     * @return 
     */
    public SiWafer getWafer();
    /**
     * Silicon wafer
     * @param wafer 
     */
    public void setWafer(SiWafer wafer);
    
    /**
     * Get start value (default) X_i for selected oxidation model
     * @return Default X_i in um
     */
    public double getDefault_Xi();
    
    public String getName();
    
    public void setName(String name);
}

/**
 *Oxidation process model for silicon wafer
 * @author ollivanhoja
 */
public class SiOxidationModel implements IOxidationModel
{
    /**
     * E_A = Activation energy
     */
    protected double EaBA;
    protected double EaB;
    /**
     * D_0 = Diffusivity coefficient
     */
    protected double D0BA;
    protected double D0B;
    
    protected transient SiWafer wafer;
    
    protected double _Xi; // Default
    
    protected String name;
    
    @Override
    public double getEaBA()
    {
        return this.EaBA;
    }
    @Override
    public void setEaBA(double EaBA)
    {
        this.EaBA = EaBA;
    }
    
    @Override
    public double getEaB()
    {
        return this.EaB;
    }
    @Override
    public void setEaB(double EaB)
    {
        this.EaB = EaB;
    }
    
    @Override
    public double getD0BA()
    {
        return this.D0BA;
    }
    @Override
    public void setD0BA(double D0BA)
    {
        this.D0BA = D0BA;
    }
    
    @Override
    public double getD0B()
    {
        return this.D0B;
    }
    @Override
    public void setD0B(double D0B)
    {
        this.D0B = D0B;
    }
    
    @Override
    public SiWafer getWafer()
    {
        return this.wafer;
    }
    @Override
    public void setWafer(SiWafer wafer)
    {
        this.wafer = wafer;
    }
    @Override
    public double getDefault_Xi()
    {
        return _Xi;
    }
    public void setDefault_Xi(double Xi)
    {
        this._Xi = Xi;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    public SiOxidationModel()
    {
        BA = new SiOxidationModel.ArrheniusRl(0, 0);
        B = new SiOxidationModel.ArrheniusRl(0, 0);
    }
    
    /**
     * Linear rate constant
     */
    public SiOxidationModel.ArrheniusRl BA;
    /**
     * Parabolic rate constant
     */
    public SiOxidationModel.ArrheniusRl B;
    
    /**
     * Arrhenius relationship
     */
    public class ArrheniusRl implements Serializable
    {
        double D0;
        double Ea;
        /**
         * Boltzmann constant
         */
        final double kB = 0.000086173324;

        public void setParameters(double D0, double Ea)
        {
            this.D0 = D0;
            this.Ea = Ea;
        }
        
        public ArrheniusRl(double D0, double Ea)
        {
            this.D0 = D0;
            this.Ea = Ea;
        }

        public double getD(double T)
        {
            return D0 * Math.exp(-Ea/(kB*T));
        }
    }
}