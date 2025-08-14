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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lk.inam.glizmo.dialog.AddStockDialog;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author moham
 */
public class StockPanel extends javax.swing.JPanel {

    /**
     * Creates new form StockPanel
     */
    public StockPanel() {
        initComponents();
        init();
        loadStockTable();
        deleteBtn.setVisible(false);

    }

    private void loadStockTable() {

        JTableHeader tableHeader = stockTable.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `view_stock`;");
            DefaultTableModel dtm = (DefaultTableModel) stockTable.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("stock_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("quantity"));
                v.add(rs.getString("price"));
                v.add(rs.getString("date_time"));
                if (rs.getString("product_status").equals("1")) {
                    v.add("Active");
                } else {
                    v.add("Inactive");
                }
                if (rs.getString("quantity").equals("0")) {
                    v.add("Out Of Stock");
                } else {
                    String inStock = "In The stock";
                    v.add(inStock);
                }
                dtm.addRow(v);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            stockTable.setDefaultRenderer(Object.class, centerRenderer);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void init() {
        FlatSVGIcon addStockIcon = new FlatSVGIcon("lk/inam/glizmo/img/plus.svg", 15, 15);
        addStockIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        FlatSVGIcon stockReportIcon = new FlatSVGIcon("lk/inam/glizmo/img/report.svg", 15, 15);
        stockReportIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        stockReport.setIcon(stockReportIcon);
        addStock.setIcon(addStockIcon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addStock = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        stockReport = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        addStock.setBackground(new java.awt.Color(30, 144, 255));
        addStock.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        addStock.setForeground(new java.awt.Color(255, 255, 255));
        addStock.setText("Add Stock");
        addStock.setIconTextGap(8);
        addStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        jLabel3.setText("Stock Management");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        stockTable.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Product Name", "Quantity", "Price", "Date Time", "Status", "Alert"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(stockTable);

        stockReport.setBackground(new java.awt.Color(30, 144, 255));
        stockReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        stockReport.setForeground(new java.awt.Color(255, 255, 255));
        stockReport.setText("Report");
        stockReport.setIconTextGap(8);
        stockReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockReportActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(255, 0, 51));
        deleteBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete Stock");
        deleteBtn.setIconTextGap(8);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(stockReport, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockReport, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
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

    private void addStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddStockDialog dialog = new AddStockDialog(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_addStockActionPerformed

    private void stockReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockReportActionPerformed
        try {
            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/stock_report1.jasper");

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

    }//GEN-LAST:event_stockReportActionPerformed
private String selectedIdColum; // stores numeric stock_id as string

public String getSelectedIdColum() {
    return selectedIdColum;
}

// Delete product method
private synchronized void deleteStock() {
    String id = getSelectedIdColum();
    if (id == null || id.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(null, "No Stock selected.");
        return;
    }

    try {
        // Use PreparedStatement for safety
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        java.sql.Connection conn = MySQL.getConnection(); 
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(id));
        pst.executeUpdate();

        loadStockTable();
        deleteBtn.setVisible(false);

        javax.swing.JOptionPane.showMessageDialog(null, "Stock deleted successfully.");
    } catch (HeadlessException | NumberFormatException | SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Error deleting Stock: " + e.getMessage());
    }
}
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        deleteStock();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void stockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockTableMouseClicked
    if (evt.getClickCount() == 2) {
        int row = stockTable.getSelectedRow();
        // Assuming column 0 contains stock_id (numeric)
        selectedIdColum = String.valueOf(stockTable.getValueAt(row, 0));
        deleteBtn.setVisible(true);
    }
    }//GEN-LAST:event_stockTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStock;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton stockReport;
    private javax.swing.JTable stockTable;
    // End of variables declaration//GEN-END:variables
}
