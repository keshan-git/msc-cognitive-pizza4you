package msc.pizza4you.gui;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import msc.pizza4you.gui.components.FlowControlPanel.ButtonType;
import msc.pizza4you.gui.listeners.FlowPanelListener;
import msc.pizza4you.gui.panels.AbstractPanel;
import msc.pizza4you.gui.panels.CheeseSelectionPanel;
import msc.pizza4you.gui.panels.SauceSelectionPanel;
import msc.pizza4you.gui.panels.StartPanel;
import msc.pizza4you.gui.panels.ToppingsSelectionPanel;
import msc.pizza4you.gui.panels.IngredientSelectionPanel;
import msc.pizza4you.core.Engine;
import msc.pizza4you.core.FlowEngine;
import msc.pizza4you.core.FlowEngine.PanelType;
import msc.pizza4you.gui.panels.ResultPanel;

/**
 *
 * @author Keshan De Silva
 */
public class Pizza4YouMainPanel extends javax.swing.JFrame
{
    private final HashMap<PanelType, AbstractPanel> panelMap;
    private final FlowEngine flowManager;
    private ExecutorService executorService = Executors.newFixedThreadPool(3);
        
    public Pizza4YouMainPanel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){}
        
        initComponents();
        setSize(new Dimension(950, 720));
        setResizable(false);
                
        flowManager = FlowEngine.getInstance();
        
        panelMap = new HashMap<>();
        generatePanels();
        
        loadPanel(PanelType.START);
        disableButtons(ButtonType.START, ButtonType.BACK);
        flowManager.setCurrentPanel(PanelType.START);
        
        flowControlPanel.addFlowPanelListener(new FlowPanelListener()
        {
            @Override
            public void onFlowPanelButtonClicked(ButtonType buttonType)
            {
                switch (buttonType)
                {
                    case START : moveToStartOverPanel(); break;
                    case BACK : moveTOBackPanel(); break;
                    case NEXT : moveToNextPanel(); break;
                    case FINISH : moveToFinsh(); break;
                }
            }
        });
    }

    private void generatePanels()
    {
        StartPanel startPanel = new StartPanel();
        IngredientSelectionPanel vegetarianSelectionPanel = new IngredientSelectionPanel();
        CheeseSelectionPanel cheeseSelectionPanel = new CheeseSelectionPanel();
        SauceSelectionPanel sauceSelectionPanel = new SauceSelectionPanel();
        ToppingsSelectionPanel toppingsSelectionPanel = new ToppingsSelectionPanel();
        ResultPanel resultPanel = new ResultPanel();
        
        panelMap.put(PanelType.START, startPanel);
        panelMap.put(PanelType.INGREDIENT, vegetarianSelectionPanel);
        panelMap.put(PanelType.CHEESE, cheeseSelectionPanel);
        panelMap.put(PanelType.SAUCE, sauceSelectionPanel);
        panelMap.put(PanelType.TOPPINGS, toppingsSelectionPanel);
        panelMap.put(PanelType.RESULT, resultPanel);
    }
    
    private void loadPanel(PanelType panelType)
    {
        panelContainer.removeAll();
        AbstractPanel panel = panelMap.get(panelType);
        
        panelContainer.add(panel);
        panelTitleLable.setPanelTitle(panel.getPanelTitle());
        flowManager.setCurrentPanel(panelType);
        
        if (!panelType.equals(PanelType.RESULT))
        {
            panel.updateKeywords();
            flowControlPanel.setButtonEnabled(ButtonType.NEXT, flowManager.hasMorePanels());
            flowControlPanel.setButtonEnabled(ButtonType.FINISH, !flowManager.hasMorePanels());
            flowControlPanel.setButtonEnabled(ButtonType.BACK, flowManager.hasPrevPanels());
        }
        else
        {
            flowControlPanel.setButtonEnabled(ButtonType.START, true);
            flowControlPanel.setButtonEnabled(ButtonType.NEXT, false);
            flowControlPanel.setButtonEnabled(ButtonType.FINISH, false);
            flowControlPanel.setButtonEnabled(ButtonType.BACK, false);
        }
        
        panelContainer.revalidate();
        panelContainer.updateUI();
    }
    
    private void disableButtons(ButtonType... buttonTypes)
    {
        for (ButtonType buttonType : buttonTypes)
        {
            flowControlPanel.setButtonEnabled(buttonType, false);
        }
    }
    
    private void moveToStartOverPanel()
    {
        PanelType panelType = PanelType.START;
        loadPanel(panelType);
    }

    private void moveTOBackPanel()
    {
        PanelType panelType = flowManager.getPrePanelType();
        loadPanel(panelType);
    }

    private void moveToNextPanel()
    {
        AbstractPanel currentPanel = panelMap.get(flowManager.getCurrentPanelType());
        currentPanel.updatePizzaConfigurations();
        
        PanelType panelType = flowManager.getNextPanelType();
        loadPanel(panelType);
    }

    private void moveToFinsh()
    {
        AbstractPanel currentPanel = panelMap.get(flowManager.getCurrentPanelType());
        currentPanel.updatePizzaConfigurations();
        
        executorService.execute(new Runnable()
        {
            @Override
            public void run()
            {
                Engine.getInstance().execute();
                
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ResultPanel resultPanel = (ResultPanel)panelMap.get(PanelType.RESULT);
                        resultPanel.updateResultSet();
                        
                        loadPanel(PanelType.RESULT);
                    }
                });
            }
        });
        
    }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelTitleLable = new msc.pizza4you.gui.components.PanelTitleLabel();
        panelContainer = new javax.swing.JPanel();
        flowControlPanel = new msc.pizza4you.gui.components.FlowControlPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizza4You [Expert System by Keshan De Silva]"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 600));

        panelTitleLable.setPanelTitle("Start");

        panelContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitleLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(flowControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitleLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flowControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Pizza4YouMainPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private msc.pizza4you.gui.components.FlowControlPanel flowControlPanel;
    private javax.swing.JPanel panelContainer;
    private msc.pizza4you.gui.components.PanelTitleLabel panelTitleLable;
    // End of variables declaration//GEN-END:variables
}
