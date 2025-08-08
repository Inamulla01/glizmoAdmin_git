/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.inam.glizmo.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import connection.MySQL;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class OrderPanel extends javax.swing.JPanel {

    public OrderPanel() {
        initComponents();
        init();
        loadOrderTable();
    }

    private void loadOrderTable() {
        JTableHeader tableHeader = orderTable.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `view_order`");
            DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("order_id"));
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("total_amount"));
                v.add(rs.getString("order_quantity"));
                v.add(rs.getString("fname") + "" + rs.getString("lname"));
                v.add(rs.getString("email"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("order_date_time"));
                v.add(rs.getString("order_status"));

                dtm.addRow(v);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            orderTable.setDefaultRenderer(Object.class, centerRenderer);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadOrderTable(String status) {
        JTableHeader tableHeader = orderTable.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));

        try {
            String query;
            if (status == null) {
                query = "SELECT * FROM `view_order`";
            } else {
                query = "SELECT * FROM `view_order` WHERE `order_status` = '" + status + "'";
            }

            ResultSet rs = MySQL.executeSearch(query);
            DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("order_id"));
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("total_amount"));
                v.add(rs.getString("order_quantity"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("email"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("order_date_time"));
                v.add(rs.getString("order_status"));
                dtm.addRow(v);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            orderTable.setDefaultRenderer(Object.class, centerRenderer);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void init() {
        FlatSVGIcon orderReportIcon = new FlatSVGIcon("lk/inam/glizmo/img/report.svg", 15, 15);
        orderReportIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        orderReport.setIcon(orderReportIcon);
        buttonGroup2.add(panding);
        buttonGroup2.add(cancelled);
        buttonGroup2.add(deliverd);
        buttonGroup2.add(shipped);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        orderReport = new javax.swing.JButton();
        panding = new javax.swing.JRadioButton();
        deliverd = new javax.swing.JRadioButton();
        cancelled = new javax.swing.JRadioButton();
        shipped = new javax.swing.JRadioButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        jLabel3.setText(" Orders & Sales");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        orderTable.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Product ID", "Product Name", "Total Amount", "Quantity", "User Name", "User Email", "Contact", "Ordered Date", "Order Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(orderTable);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jLabel1.setText("Search By Status :");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        orderReport.setBackground(new java.awt.Color(30, 144, 255));
        orderReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        orderReport.setForeground(new java.awt.Color(255, 255, 255));
        orderReport.setText("Report");
        orderReport.setIconTextGap(8);
        orderReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderReportActionPerformed(evt);
            }
        });

        panding.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        panding.setText("Pending");
        panding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pandingActionPerformed(evt);
            }
        });

        deliverd.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        deliverd.setText("Deliverd");
        deliverd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliverdActionPerformed(evt);
            }
        });

        cancelled.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        cancelled.setText("Cancelled");
        cancelled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelledActionPerformed(evt);
            }
        });

        shipped.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        shipped.setText("Shipped");
        shipped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shippedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(shipped)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deliverd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelled)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panding)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(orderReport, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderReport, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panding)
                    .addComponent(shipped)
                    .addComponent(deliverd)
                    .addComponent(cancelled))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(47, 47, 47))
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

    private void orderReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderReportActionPerformed
        try {

            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/order_report.jasper");

            if (filePath == null) {
                JOptionPane.showMessageDialog(this, "Report file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HashMap<String, Object> parameters = new HashMap<>();
            Connection connection = MySQL.getConnection();

            JasperPrint fillReport = JasperFillManager.fillReport(filePath, parameters, connection);
            JasperViewer.viewReport(fillReport, false);

        } catch (HeadlessException | JRException e) {
             JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Error generating report:\n" + e.getMessage(),
                    "Report Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_orderReportActionPerformed

    private void shippedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shippedActionPerformed
        loadOrderTable("Shipped");
    }//GEN-LAST:event_shippedActionPerformed

    private void deliverdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliverdActionPerformed
        loadOrderTable("Delivered");
    }//GEN-LAST:event_deliverdActionPerformed

    private void cancelledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelledActionPerformed
        loadOrderTable("Cancelled");
    }//GEN-LAST:event_cancelledActionPerformed

    private void pandingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pandingActionPerformed
        loadOrderTable("Pending");
    }//GEN-LAST:event_pandingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton cancelled;
    private javax.swing.JRadioButton deliverd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton orderReport;
    private javax.swing.JTable orderTable;
    private javax.swing.JRadioButton panding;
    private javax.swing.JRadioButton shipped;
    // End of variables declaration//GEN-END:variables
}
