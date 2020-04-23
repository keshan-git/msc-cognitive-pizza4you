package msc.pizza4you.gui.components;

import java.awt.Color;

/**
 *
 * @author Keshan De Silva
 */
public final class KeywordLabel extends javax.swing.JPanel
{
    private String keywordText;
    private Color backgroungColor;

    public KeywordLabel()
    {
        initComponents();
    }

    public KeywordLabel(String keywordText)
    {
        this();
        setKeywordText(keywordText);
    }

    public String getKeywordText()
    {
        return keywordText;
    }

    public void setKeywordText(String keywordText)
    {
        this.keywordText = keywordText;
        lblKeyword.setText(keywordText.toUpperCase());
    }

    public Color getBackgroungColor()
    {
        return backgroungColor;
    }

    public void setBackgroungColor(Color backgroungColor)
    {
        this.backgroungColor = backgroungColor;
        lblKeyword.setBackground(backgroungColor);
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

        lblKeyword = new javax.swing.JLabel();

        lblKeyword.setBackground(new java.awt.Color(0, 153, 51));
        lblKeyword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblKeyword.setForeground(new java.awt.Color(255, 255, 255));
        lblKeyword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKeyword.setText("SAMPLE");
        lblKeyword.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKeyword, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblKeyword;
    // End of variables declaration//GEN-END:variables
}
