/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.inam.glizmo.dialog.panel;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.ImageIcon;

/**
 *
 * @author moham
 */
public class ProductImg extends javax.swing.JPanel {

    public ProductImg() {
        initComponents();
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        imgLable.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

    public boolean setProductImage(ImageIcon icon) {
        if (icon != null) {
            imgLable.setIcon(icon);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productImgPanel = new javax.swing.JPanel();
        imgLable = new javax.swing.JLabel();

        javax.swing.GroupLayout productImgPanelLayout = new javax.swing.GroupLayout(productImgPanel);
        productImgPanel.setLayout(productImgPanelLayout);
        productImgPanelLayout.setHorizontalGroup(
            productImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLable, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );
        productImgPanelLayout.setVerticalGroup(
            productImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLable, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLable;
    private javax.swing.JPanel productImgPanel;
    // End of variables declaration//GEN-END:variables
}
