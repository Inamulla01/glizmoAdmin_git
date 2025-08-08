/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package lk.inam.glizmo.dialog;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import connection.MySQL;
import static connection.MySQL.executeSearch;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import lk.inam.glizmo.velidation.Validater;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author moham
 */
public class AddStockDialog extends javax.swing.JDialog {

    private final HashMap<String, Integer> productMap;

    public AddStockDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        this.productMap = new HashMap<>();
        loadProducts();
    }

    private void loadProducts() {
        try {
            ResultSet rs = executeSearch("SELECT * FROM `products`");
            Vector<String> products = new Vector();
            products.add("Select Product");
            productMap.put("Select Product", 0);
            while (rs.next()) {
                String categoryName = rs.getString("product_name");
                productMap.put(categoryName, rs.getInt("product_id"));
                products.add(categoryName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(products);
            productsID.setModel(dcm);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void init() {

        addIcon.setIcon(
                new FlatSVGIcon("lk/inam/glizmo/img/add.svg",
                        50,
                        50)
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        stockPrice = new javax.swing.JTextField();
        addStockBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        addIcon = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        productsID = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        qty = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Glizmo - Add Stock");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jLabel1.setText("Stock Price");

        stockPrice.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        stockPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockPriceActionPerformed(evt);
            }
        });

        addStockBtn.setBackground(new java.awt.Color(0, 102, 255));
        addStockBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addStockBtn.setForeground(new java.awt.Color(255, 255, 255));
        addStockBtn.setText("Add");
        addStockBtn.setToolTipText("");
        addStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 36)); // NOI18N
        jLabel3.setText("Add Stock");

        productsID.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        productsID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jLabel4.setText("Product Name");

        jLabel2.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jLabel2.setText("Quantity");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addStockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(productsID, 0, 340, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(stockPrice)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(addIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(qty)
                    .addComponent(productsID, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addStockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void stockPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockPriceActionPerformed

    private void addStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockBtnActionPerformed
        String selectedProduct = productsID.getSelectedItem().toString();
        int productId = productMap.getOrDefault(selectedProduct, 0);
        String stockPriceText = stockPrice.getText().trim();
        int quantity = (int) qty.getValue();
        Timestamp currentDateTime = new Timestamp(new Date().getTime());

        if (productId == 0) {
            JOptionPane.showMessageDialog(this, "Please select a valid product.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (stockPriceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the stock price.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(stockPriceText);

            String query = "INSERT INTO stock (product_id, quantity, price, date_time, status) "
        + "VALUES (" + productId + ", " + quantity + ", " + price + ", '" + currentDateTime + "', '1')";

            MySQL.executeIUD(query);

            JOptionPane.showMessageDialog(this, "Stock added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            stockPrice.setText("");
            qty.setValue(0);
            productsID.setSelectedIndex(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric stock price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Something went wrong while adding stock.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addStockBtnActionPerformed

 
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddStockDialog dialog = new AddStockDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addStockBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> productsID;
    private javax.swing.JSpinner qty;
    private javax.swing.JTextField stockPrice;
    // End of variables declaration//GEN-END:variables
}
