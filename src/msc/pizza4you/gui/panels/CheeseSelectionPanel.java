package msc.pizza4you.gui.panels;

import javax.swing.JPanel;
import msc.pizza4you.data.PizzaConfiguration;
import msc.pizza4you.core.Engine;
import msc.pizza4you.data.CheeseDetails;
import msc.pizza4you.data.LikeLevelEnum;
        
/**
 *
 * @author Keshan De Silva
 */
public class CheeseSelectionPanel extends AbstractPanel
{
    public CheeseSelectionPanel()
    {
        initComponents();
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

        btnGroupSouce = new javax.swing.ButtonGroup();
        panelBorder = new javax.swing.JPanel();
        radioLike = new javax.swing.JRadioButton();
        radioDontLike = new javax.swing.JRadioButton();
        radioNoMatter = new javax.swing.JRadioButton();
        radioLove = new javax.swing.JRadioButton();
        panelKeywords = new javax.swing.JPanel();

        panelBorder.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cheese Selection", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnGroupSouce.add(radioLike);
        radioLike.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioLike.setSelected(true);
        radioLike.setText("I Like Cheese");
        radioLike.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioLikeActionPerformed(evt);
            }
        });

        btnGroupSouce.add(radioDontLike);
        radioDontLike.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioDontLike.setText("I Don't Like Cheese");
        radioDontLike.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioDontLikeActionPerformed(evt);
            }
        });

        btnGroupSouce.add(radioNoMatter);
        radioNoMatter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioNoMatter.setText("Cheese, Doesn't Matter");
        radioNoMatter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioNoMatterActionPerformed(evt);
            }
        });

        btnGroupSouce.add(radioLove);
        radioLove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioLove.setText("I Love Cheese");
        radioLove.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioLoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioDontLike)
                    .addComponent(radioNoMatter)
                    .addComponent(radioLike)
                    .addComponent(radioLove))
                .addContainerGap(195, Short.MAX_VALUE))
        );

        panelBorderLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {radioDontLike, radioLike, radioLove, radioNoMatter});

        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(radioLove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioLike)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDontLike)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioNoMatter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBorderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {radioDontLike, radioLike, radioLove, radioNoMatter});

        panelKeywords.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cheese Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelKeywords.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radioLoveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioLoveActionPerformed
    {//GEN-HEADEREND:event_radioLoveActionPerformed
        updateKeywords();
    }//GEN-LAST:event_radioLoveActionPerformed

    private void radioLikeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioLikeActionPerformed
    {//GEN-HEADEREND:event_radioLikeActionPerformed
        updateKeywords();
    }//GEN-LAST:event_radioLikeActionPerformed

    private void radioDontLikeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioDontLikeActionPerformed
    {//GEN-HEADEREND:event_radioDontLikeActionPerformed
        updateKeywords();
    }//GEN-LAST:event_radioDontLikeActionPerformed

    private void radioNoMatterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioNoMatterActionPerformed
    {//GEN-HEADEREND:event_radioNoMatterActionPerformed
        updateKeywords();
    }//GEN-LAST:event_radioNoMatterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupSouce;
    private javax.swing.JPanel panelBorder;
    private javax.swing.JPanel panelKeywords;
    private javax.swing.JRadioButton radioDontLike;
    private javax.swing.JRadioButton radioLike;
    private javax.swing.JRadioButton radioLove;
    private javax.swing.JRadioButton radioNoMatter;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getPanelTitle()
    {
        return "Cheese Selection";
    }

    @Override
    public PizzaConfiguration updatePizzaConfigurations()
    {
        Engine engine = Engine.getInstance();
        PizzaConfiguration pizzaConfiguration = engine.getPizzaConfiguration();
        CheeseDetails cheeseDetails = new CheeseDetails();
        
        if (radioLove.isSelected())
        {
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.LOVE);
        }
        else if (radioLike.isSelected())
        {
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.LIKE);
        }
        else if (radioDontLike.isSelected())
        {
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.DONT_LIKE);
        }
        else if (radioNoMatter.isSelected())
        {
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.NO_MATTER);
        }
        
        pizzaConfiguration.setCheeseDetails(cheeseDetails);
        return pizzaConfiguration;
    }

    @Override
    public JPanel getKeywordPanel()
    {
        return panelKeywords;
    }
}
