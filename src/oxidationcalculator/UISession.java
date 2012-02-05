/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationcalculator;

import oxidationModel.*;
import oxidationSession.SiWafer;
import oxidationSession.oxidationSession;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ollivanhoja
 */
public class UISession
{
    oxidationSession oxs;
    SiOxidationModelArray oxidationModels;
    OxidationSessionTableModel tableModel;

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
        tableModel = new OxidationSessionTableModel();
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
    
    class OxidationSessionTableModel extends AbstractTableModel
    {
        private String[] columnNames = {"Xi",
                                        "Xo",
                                        "T",
                                        "Calculation mode",
                                        "t phase",
                                        "t total",
                                        "Oxidation model"};
        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        @Override
        public int getRowCount()
        {
            return oxs.getSize();
        }

        @Override
        public String getColumnName(int col)
        {
            return columnNames[col];
        }
        
        public String getColumnUnit(int col)
        {
            // TODO return unit
            return "";
        }

        @Override
        public Object getValueAt(int row, int col)
        {
            oxidationSession.oxidationPhase phase;
            phase = oxs.getPhase(row);
            
            switch (col)
            {
                case 0: // Xi
                    return phase.getXi();
                case 1: // Xo
                    return phase.getXoPhase();
                case 2: // T
                    return phase.T;
                case 3: // Calculation
                    return (phase.getCalc_t() == true) ? "Calculate t" : "Calculate Xo";
                case 4: // t
                    return phase.t * 60;
                case 5: // t_tot
                    // TODO return total
                    return 0;
                case 6: // Oxidation model
                    return phase.oModel.getName();
                default: throw new IndexOutOfBoundsException();
            }
        }
        
        @Override
        public void setValueAt(Object aValue, int row, int col)
        {
            oxidationSession.oxidationPhase phase;
            phase = oxs.getPhase(row);
            
            switch (col)
            {
                case 1: // Xo
                    phase.setXoSet((Double)aValue);
                    break;
                case 2: // T
                    phase.T = (Double)aValue;
                    break;
                case 4: // t
                    phase.T = (Double)aValue;
                    break;
            }
        }

        /*
        * JTable uses this method to determine the default renderer/
        * editor for each cell.  If we didn't implement this method,
        * then the last column would contain text ("true"/"false"),
        * rather than a check box.
        */
        @Override
        public Class getColumnClass(int c)
        {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col)
        {
            switch (col)
            {
                case 0:
                    return false;
                case 2:
                    return OxidationCalculator.session.oxs.getPhase(row).getCalc_t();
                case 3:
                    return false;
                case 4:
                    return !(OxidationCalculator.session.oxs.getPhase(row).getCalc_t());
                default:
                    return true;
            }
        }
    }
}
