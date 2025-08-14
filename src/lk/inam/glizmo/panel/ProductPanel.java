/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.inam.glizmo.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.Connection;

import connection.MySQL;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import lk.inam.glizmo.dialog.AddBrandDialog;
import lk.inam.glizmo.dialog.AddCategoryDialog;
import lk.inam.glizmo.dialog.AddColourDialog;
import lk.inam.glizmo.dialog.AddModelDialog;
import lk.inam.glizmo.dialog.AddProductDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ProductPanel extends javax.swing.JPanel {

    public ProductPanel() {
        initComponents();
        init();
        loadProductTable();

    }

    private void loadProductTable() {
        JTableHeader tableHeader = productTable.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `view_product`");
            DefaultTableModel dtm = (DefaultTableModel) productTable.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("description"));
                v.add(rs.getString("category_name"));
                v.add(rs.getString("brand_name"));
                v.add(rs.getString("model_name"));
                if (rs.getString("product_status").equals("1")) {
                    v.add("Active");
                } else {
                    v.add("Inactive");
                }
                dtm.addRow(v);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            productTable.setDefaultRenderer(Object.class, centerRenderer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        FlatSVGIcon addProductIcon = new FlatSVGIcon("lk/inam/glizmo/img/plus.svg", 15, 15);

        addProductIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        FlatSVGIcon addBrandIcon = new FlatSVGIcon("lk/inam/glizmo/img/plus.svg", 15, 15);
        addBrandIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        FlatSVGIcon addCategoryIcon = new FlatSVGIcon("lk/inam/glizmo/img/plus.svg", 15, 15);
        addCategoryIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        FlatSVGIcon addColourIcon = new FlatSVGIcon("lk/inam/glizmo/img/plus.svg", 15, 15);
        addColourIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        FlatSVGIcon addModerIcon = new FlatSVGIcon("lk/inam/glizmo/img/plus.svg", 15, 15);
        addModerIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        FlatSVGIcon productReportIcon = new FlatSVGIcon("lk/inam/glizmo/img/report.svg", 15, 15);
        productReportIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        addCategory.setIcon(addCategoryIcon);
        addModer.setIcon(addModerIcon);
        addColour.setIcon(addColourIcon);
        productReport.setIcon(productReportIcon);
        addBrand.setIcon(addBrandIcon);
        addProduct.setIcon(addProductIcon);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        addProduct = new javax.swing.JButton();
        addCategory = new javax.swing.JButton();
        addBrand = new javax.swing.JButton();
        addModer = new javax.swing.JButton();
        addColour = new javax.swing.JButton();
        productReport = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        productTable.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Name", "Description", "Category", "Brand", "Model", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setEnabled(false);
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(productTable);

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        jLabel3.setText("Product Management");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        addProduct.setBackground(new java.awt.Color(30, 144, 255));
        addProduct.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addProduct.setForeground(new java.awt.Color(255, 255, 255));
        addProduct.setText("Add Product");
        addProduct.setIconTextGap(8);
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        addCategory.setBackground(new java.awt.Color(102, 102, 102));
        addCategory.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addCategory.setForeground(new java.awt.Color(255, 255, 255));
        addCategory.setText("Add Category");
        addCategory.setIconTextGap(8);
        addCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryActionPerformed(evt);
            }
        });

        addBrand.setBackground(new java.awt.Color(102, 102, 102));
        addBrand.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addBrand.setForeground(new java.awt.Color(255, 255, 255));
        addBrand.setText("Add Brand");
        addBrand.setIconTextGap(8);
        addBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBrandActionPerformed(evt);
            }
        });

        addModer.setBackground(new java.awt.Color(102, 102, 102));
        addModer.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addModer.setForeground(new java.awt.Color(255, 255, 255));
        addModer.setText("Add Model");
        addModer.setIconTextGap(8);
        addModer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModerActionPerformed(evt);
            }
        });

        addColour.setBackground(new java.awt.Color(102, 102, 102));
        addColour.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addColour.setForeground(new java.awt.Color(255, 255, 255));
        addColour.setText("Add Colour");
        addColour.setIconTextGap(8);
        addColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColourActionPerformed(evt);
            }
        });

        productReport.setBackground(new java.awt.Color(30, 144, 255));
        productReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        productReport.setForeground(new java.awt.Color(255, 255, 255));
        productReport.setText("Report");
        productReport.setIconTextGap(8);
        productReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(productReport, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addColour, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addModer, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addModer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addColour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productReport, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(addBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddProductDialog dialog = new AddProductDialog(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_addProductActionPerformed

    private void addCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddCategoryDialog dialog = new AddCategoryDialog(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_addCategoryActionPerformed

    private void addBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBrandActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddBrandDialog dialog = new AddBrandDialog(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_addBrandActionPerformed

    private void addModerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModerActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddModelDialog dialog = new AddModelDialog(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_addModerActionPerformed

    private void addColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColourActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddColourDialog dialog = new AddColourDialog(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_addColourActionPerformed

    private void productReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productReportActionPerformed
        try {

            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/product_report2.jasper");

            if (filePath == null) {
                JOptionPane.showMessageDialog(this, "Report file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HashMap<String, Object> parameters = new HashMap<>();
            Connection connection = MySQL.getConnection();

            JasperPrint fillReport = JasperFillManager.fillReport(filePath, parameters, connection);
            JasperViewer.viewReport(fillReport, false);

        } catch (HeadlessException | JRException e) {
            JOptionPane.showMessageDialog(this, "Error generating report:\n" + e.getMessage(),
                    "Report Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_productReportActionPerformed

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked

    }//GEN-LAST:event_productTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBrand;
    private javax.swing.JButton addCategory;
    private javax.swing.JButton addColour;
    private javax.swing.JButton addModer;
    private javax.swing.JButton addProduct;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton productReport;
    private javax.swing.JTable productTable;
    // End of variables declaration//GEN-END:variables
}
