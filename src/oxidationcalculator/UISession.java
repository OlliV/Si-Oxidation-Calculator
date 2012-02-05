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
    OxidationSessionTableModel tableModelSession;
    OxidationModelTableModel tableModelOxidationModels;

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
        tableModelSession = new OxidationSessionTableModel();
        tableModelOxidationModels = new OxidationModelTableModel();
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
    
    void setWafer(SiWafer wafer)
    {
        this.wafer = wafer;
        
        // Update standard oxidation models
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
    
    void addConsttPhase(int modelI)
    {
        oxs.addPhase(0, 0, oxidationModels.get(modelI), false);
        noData = false;
    }
    
    void addConstXoPhase(double T, double Xo, int modelI)
    {
        oxs.addPhase(T, Xo, oxidationModels.get(modelI), true);
        noData = false;
    }
    
    void addConstXoPhase(int modelI)
    {
        oxs.addPhase(0, 0, oxidationModels.get(modelI), true);
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
                    if (phase.getCalc_t())
                        return phase.getXoSet();
                    else
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
                    phase.t = ((Double)aValue)/60;
                    break;
            }
        }

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
                case 1:
                    return OxidationCalculator.session.oxs.getPhase(row).getCalc_t();
                case 3:
                    return false;
                case 4:
                    return !(OxidationCalculator.session.oxs.getPhase(row).getCalc_t());
                case 5:
                    return false;
                case 6:
                    return false;
                default:
                    return true;
            }
        }
    }
    
    class OxidationModelTableModel extends AbstractTableModel
    {
        private String[] columnNames = {"Name",
                                        "D_0(BA)",
                                        "D_0(B)",
                                        "E_a(BA)",
                                        "E_a(B)",
                                        "X_I(def)"};
        
        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }
        
        @Override
        public int getRowCount()
        {
            return oxidationModels.size();
        }
        
        @Override
        public String getColumnName(int col)
        {
            return columnNames[col];
        }
        
        @Override
        public Object getValueAt(int row, int col)
        {
            SiOxidationModel model;
            model = oxidationModels.get(row);
            
            switch (col)
            {
                case 0: // Name
                    return model.getName();
                case 1: // D_0(BA)
                    return model.getD0BA();
                case 2: // D_0(B)
                    return model.getD0B();
                case 3: // E_a(BA)
                    return model.getEaBA();
                case 4: // E_a(B)
                    return model.getEaB();
                case 5: // X_I(def)
                    return model.getDefault_Xi();
                default: throw new IndexOutOfBoundsException();
            }
        }
        
        @Override
        public Class getColumnClass(int c)
        {
            return getValueAt(0, c).getClass();
        }
        
        @Override
        public boolean isCellEditable(int row, int col)
        {
            return false;
        }
    }
}
