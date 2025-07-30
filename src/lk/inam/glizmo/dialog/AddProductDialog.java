/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package lk.inam.glizmo.dialog;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import static connection.MySQL.executeIUD;
import static connection.MySQL.executeSearch;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import lk.inam.glizmo.dialog.panel.ProductImgPanel;
import lk.inam.glizmo.dialog.panel.ProductInfo;
import lk.inam.glizmo.velidation.Validater;

/**
 *
 * @author moham
 */
public class AddProductDialog extends javax.swing.JDialog {

    private ProductInfo productInfoPanel;
    private ProductImgPanel productImgPanel;
    private CardLayout contentPanelLayout;

    public AddProductDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        loadPanels();
    }

    private void init() {

        addIcon.setIcon(
                new FlatSVGIcon("lk/inam/glizmo/img/add.svg",
                        50,
                        50)
        );
    }

    private void loadPanels() {
        if (contentPanelLayout == null && productPanel.getLayout() instanceof CardLayout) {
            contentPanelLayout = (CardLayout) productPanel.getLayout();
        }
        productInfoPanel = new ProductInfo();
        productImgPanel = new ProductImgPanel();

        productPanel.add(productInfoPanel, "product_info");
        productPanel.add(productImgPanel, "product_image");

        contentPanelLayout.show(productPanel, "product_info");
        SwingUtilities.updateComponentTreeUI(productPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        addIcon = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        backBtn = new javax.swing.JButton();
        controlBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        productPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Glizmo - Add Product");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 36)); // NOI18N
        jLabel3.setText("Add Product");

        backBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        controlBtn.setBackground(new java.awt.Color(0, 102, 255));
        controlBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        controlBtn.setForeground(new java.awt.Color(255, 255, 255));
        controlBtn.setText("Next");
        controlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlBtnActionPerformed(evt);
            }
        });

        productPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(productPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(controlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 522, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        contentPanelLayout.previous(productPanel);
        controlBtn.setText("Next");
        backBtn.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    private void controlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlBtnActionPerformed
        if (controlBtn.getText().equalsIgnoreCase("next")) {
            contentPanelLayout.next(productPanel);
            controlBtn.setText("Submit");
            backBtn.setVisible(true);
        } else {
            insertProductData();
        }
    }//GEN-LAST:event_controlBtnActionPerformed

    private synchronized void insertProductData() {
        String productName = productInfoPanel.getPrNameInput().getText();
        String description = productInfoPanel.getPrDescriptionInput().getText();

        int categorySelectedIndex = productInfoPanel.getPrCategoryCombo().getSelectedIndex();
        int brandSelectedIndex = productInfoPanel.getPrBrandCombo().getSelectedIndex();
        int modelSelectedIndex = productInfoPanel.getPrModelCombo().getSelectedIndex();
        int colourSelectedIndex = productInfoPanel.getPrColourCombo().getSelectedIndex();

        String imagePath = productImgPanel.getPrImgPathInput().getText();

        if (!Validater.isInputFieldValid(productName)) {
            return;
        } else if (!Validater.isInputFieldValid(description)) {
            return;
        } else if (!Validater.isSelectedItemValid(categorySelectedIndex)) {
            return;
        } else if (!Validater.isSelectedItemValid(brandSelectedIndex)) {
            return;
        } else if (!Validater.isSelectedItemValid(modelSelectedIndex)) {
            return;
        } else if (!Validater.isSelectedItemValid(colourSelectedIndex)) {
            return;
        } else if (!Validater.isInputFieldValid(imagePath)) {
            return;
        }
        HashMap<String, Integer> categoriesMap = productInfoPanel.getCategoriesMap();
        HashMap<String, Integer> brandsMap = productInfoPanel.getBrandsMap();
        HashMap<String, Integer> modelMap = productInfoPanel.getModelMap();
        HashMap<String, Integer> colourMap = productInfoPanel.getColourMap();

        String category = productInfoPanel.getPrCategoryCombo().getItemAt(categorySelectedIndex);
        String brand = productInfoPanel.getPrBrandCombo().getItemAt(brandSelectedIndex);
                String model = productInfoPanel.getPrModelCombo().getItemAt(modelSelectedIndex);
        String colour = productInfoPanel.getPrColourCombo().getItemAt(colourSelectedIndex);

        int categoryId = categoriesMap.get(category);
        int brandId = brandsMap.get(brand);
        int modelId = modelMap.get(model);
        int colourId = colourMap.get(colour);

        try {

            ResultSet rs = executeSearch("SELECT `product_id` FROM `products` WHERE `products`.`product_name` = '" + productName + "' "
                    + "AND `products`.`category_id`='" + categoryId + "' "
                    + "AND `products`.`brand_id` = '" + brandId + "'"
                    + "AND `products`.`model_id` = '" + modelId + "'"
                    + "AND `products`.`colour_id` = '" + colourId + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "This product is already exists!",
                        "Product Information Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                executeIUD("INSERT INTO `products`(`product_name`,`warranty_period`,`description`,`category_id`,`brand_id`,`model_id`,`colour_id`, `status`) "
                        + "VALUES('" + productName + "','6', '" + description + "','" + categoryId + "','" + brandId + "','" + modelId + "','" + colourId + "', '1')");
                ResultSet rs1 = executeSearch("SELECT LAST_INSERT_ID()");
                if (rs1.next()) {
                    int lastInsertId = rs1.getInt(1);
                    executeIUD("INSERT INTO `image`(`image_path`,`product_id`) VALUES('" + imagePath + "','" + lastInsertId + "')");
                }
                JOptionPane.showMessageDialog(null,
                        "New product added successfully!",
                        "Product Information Dialog",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddProductDialog dialog = new AddProductDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addIcon;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton controlBtn;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel productPanel;
    // End of variables declaration//GEN-END:variables
}
