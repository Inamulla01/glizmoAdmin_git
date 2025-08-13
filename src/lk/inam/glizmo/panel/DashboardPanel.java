/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.inam.glizmo.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import connection.MySQL;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author moham
 */
public class DashboardPanel extends javax.swing.JPanel {

    /**
     * Creates new form DashboardPanel
     */
    public DashboardPanel() {
        initComponents();
        init();
        loadTopUserTable();
        loadTopProductTable();
        loadEarningsToday();
        loadEarningsweek();
        loadEarningsmonth();
        todaySoldProduct();
        weekSoldProduct();
        monthSoldProduct();
        totalStock();
        totalProduct();
        totalUser();
        
        
        MonthlyStockChart stockChartPanel = new MonthlyStockChart();
        chart.setLayout(new BorderLayout());
        chart.removeAll();
        chart.add(stockChartPanel, BorderLayout.CENTER);
        chart.revalidate();
        chart.repaint();
        
        MonthlySalesPieChart chart = new MonthlySalesPieChart();
        chart1.setLayout(new BorderLayout());
        chart1.removeAll();
        chart1.add(chart, BorderLayout.CENTER);
        chart1.revalidate();
        chart1.repaint();

    }

    public class MonthlyStockChart extends JPanel {

        public MonthlyStockChart() {
            setLayout(new BorderLayout());

            DefaultPieDataset dataset = new DefaultPieDataset();

            try {
                ResultSet rs = MySQL.executeSearch("SELECT MONTH(date_time) AS month, COUNT(*) AS total FROM stock GROUP BY MONTH(date_time)");
                while (rs.next()) {
                    int month = rs.getInt("month");
                    int total = rs.getInt("total");
                    dataset.setValue(getMonthName(month), total);
                }
            } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Monthly Registered Stocks",
                    dataset,
                    true,
                    true,
                    false
            );

            ChartPanel chartPanel = new ChartPanel(pieChart);
            add(chartPanel, BorderLayout.CENTER);
        }

        private String getMonthName(int month) {
            return switch (month) {
                case 1 ->
                    "Jan";
                case 2 ->
                    "Feb";
                case 3 ->
                    "Mar";
                case 4 ->
                    "Apr";
                case 5 ->
                    "May";
                case 6 ->
                    "Jun";
                case 7 ->
                    "Jul";
                case 8 ->
                    "Aug";
                case 9 ->
                    "Sep";
                case 10 ->
                    "Oct";
                case 11 ->
                    "Nov";
                case 12 ->
                    "Dec";
                default ->
                    "Unknown";
            };
        }
    }

    public class MonthlySalesPieChart extends JPanel {

        public MonthlySalesPieChart() {
            setLayout(new BorderLayout());

            DefaultPieDataset dataset = new DefaultPieDataset();

            try {
                ResultSet rs = MySQL.executeSearch(
                        "SELECT MONTH(order_date_time) AS month, COUNT(*) AS total "
                        + "FROM orders o "
                        + "JOIN order_status os ON o.order_status_id = os.status_id "
                        + "WHERE os.status_type = 'Delivered' "
                        + "GROUP BY MONTH(order_date_time)"
                );

                while (rs.next()) {
                    int month = rs.getInt("month");
                    int total = rs.getInt("total");
                    dataset.setValue(getMonthName(month), total);
                }

            } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            JFreeChart chart = ChartFactory.createPieChart(
                    "Monthly Delivered Orders",
                    dataset,
                    true ,
                    true,
                    false
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            add(chartPanel, BorderLayout.CENTER);
        }

        private String getMonthName(int month) {
            return switch (month) {
                case 1 ->
                    "January";
                case 2 ->
                    "February";
                case 3 ->
                    "March";
                case 4 ->
                    "April";
                case 5 ->
                    "May";
                case 6 ->
                    "June";
                case 7 ->
                    "July";
                case 8 ->
                    "August";
                case 9 ->
                    "September";
                case 10 ->
                    "October";
                case 11 ->
                    "November";
                case 12 ->
                    "December";
                default ->
                    "Unknown";
            };
        }
    }

    private void loadEarningsToday() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT IFNULL(SUM(order_price * order_quantity), 0) AS total_earning_today FROM view_order WHERE DATE(order_date_time) = CURDATE();");

            if (rs.next()) {
                String total = rs.getString("total_earning_today");
                todayErn.setText("Total Earning Today: Rs. " + total + "0");
            } else {
                todayErn.setText("Rs. 00");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadEarningsweek() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT IFNULL(SUM(order_price * order_quantity), 0) AS total_earning_week FROM view_order WHERE YEARWEEK(order_date_time, 1) = YEARWEEK(CURDATE(), 1);");

            if (rs.next()) {
                String total = rs.getString("total_earning_week");
                weekErn.setText("Total Earning This Week: Rs. " + total + "0");
            } else {
                weekErn.setText("Rs. 00");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadEarningsmonth() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT IFNULL(SUM(order_price * order_quantity), 0) AS total_earning_month\n"
                    + "FROM view_order\n"
                    + "WHERE MONTH(order_date_time) = MONTH(CURDATE()) AND YEAR(order_date_time) = YEAR(CURDATE());");

            if (rs.next()) {
                String total = rs.getString("total_earning_month");
                monthErn.setText("Total Earning This Month: Rs. " + total + "0");
            } else {
                monthErn.setText("Rs. 00");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void todaySoldProduct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT IFNULL(SUM(order_quantity), 0) AS sold_today\n"
                    + "FROM view_order\n"
                    + "WHERE DATE(order_date_time) = CURDATE();");

            if (rs.next()) {
                String total = rs.getString("sold_today");
                todaySld.setText("Today Sold Products : " + total + " Products");
            } else {
                todaySld.setText("0");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void weekSoldProduct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT IFNULL(SUM(order_quantity), 0) AS sold_week\n"
                    + "FROM view_order\n"
                    + "WHERE YEARWEEK(order_date_time, 1) = YEARWEEK(CURDATE(), 1);");

            if (rs.next()) {
                String total = rs.getString("sold_week");
                weekSld.setText("This Week Sold Products : " + total + " Products");
            } else {
                weekSld.setText("0");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void monthSoldProduct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT IFNULL(SUM(order_quantity), 0) AS sold_month\n"
                    + "FROM view_order\n"
                    + "WHERE MONTH(order_date_time) = MONTH(CURDATE()) AND YEAR(order_date_time) = YEAR(CURDATE());");

            if (rs.next()) {
                String total = rs.getString("sold_month");
                monthSld.setText("This Month Sold Products : " + total + " Products");
            } else {
                monthSld.setText("0");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void totalStock() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS total_stocks FROM stock WHERE status = '1';");

            if (rs.next()) {
                String total = rs.getString("total_stocks");
                totalStock.setText("Total Stocks : " + total + " Stockes");
            } else {
                totalStock.setText("0");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void totalProduct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS total_products FROM products WHERE status = '1';");

            if (rs.next()) {
                String total = rs.getString("total_products");
                totalProduct.setText("Total Product : " + total + " Products");
            } else {
                totalProduct.setText("0");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void totalUser() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS total_users FROM users WHERE status_id = '1';");

            if (rs.next()) {
                String total = rs.getString("total_users");
                totalUser.setText("Total Users : " + total + " Users");
            } else {
                totalUser.setText("0");
            }

        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTopUserTable() {
        JTableHeader tableHeader = topUser.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT \n"
                    + "    users_id,\n"
                    + "    fname,\n"
                    + "    lname,\n"
                    + "    email,\n"
                    + "    contact,\n"
                    + "    SUM(order_quantity) AS total_items_bought\n"
                    + "FROM view_order\n"
                    + "GROUP BY users_id\n"
                    + "ORDER BY total_items_bought DESC\n"
                    + "LIMIT 10;");
            DefaultTableModel dtm = (DefaultTableModel) topUser.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("users_id"));
                v.add(rs.getString("email"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("total_items_bought"));
                dtm.addRow(v);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            topUser.setDefaultRenderer(Object.class, centerRenderer);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTopProductTable() {
        JTableHeader tableHeader = topProduct.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT \n" +
"    `product_id`,\n" +
"    `product_name`,\n" +
"    SUM(`order_quantity`) AS `total_quantity_sold`,\n" +
"    SUM(`order_price` * `order_quantity`) AS `total_revenue`\n" +
"FROM \n" +
"    `view_order`\n" +
"GROUP BY \n" +
"    `product_id`, `product_name`\n" +
"ORDER BY \n" +
"    `total_quantity_sold` DESC\n" +
"LIMIT 10;");
            DefaultTableModel dtm = (DefaultTableModel) topProduct.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("total_quantity_sold"));
                v.add(rs.getString("total_revenue"));

                dtm.addRow(v);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            topProduct.setDefaultRenderer(Object.class, centerRenderer);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                        "Something Want wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void init() {
        FlatSVGIcon iconMonthSVG = new FlatSVGIcon("lk/inam/glizmo/img/monthly.svg", 50, 50);
        iconMonth.setIcon(iconMonthSVG);

        FlatSVGIcon iconTodaySVG = new FlatSVGIcon("lk/inam/glizmo/img/today.svg", 60, 60);
        iconToday.setIcon(iconTodaySVG);

        FlatSVGIcon iconWeekSVG = new FlatSVGIcon("lk/inam/glizmo/img/weekly.svg", 50, 50);
        iconWeek.setIcon(iconWeekSVG);
        FlatSVGIcon iconMonth1SVG = new FlatSVGIcon("lk/inam/glizmo/img/monthly.svg", 50, 50);
        iconMonth1.setIcon(iconMonth1SVG);

        FlatSVGIcon iconToday1SVG = new FlatSVGIcon("lk/inam/glizmo/img/today.svg", 60, 60);
        iconToday1.setIcon(iconToday1SVG);

        FlatSVGIcon iconWeek1SVG = new FlatSVGIcon("lk/inam/glizmo/img/weekly.svg", 50, 50);
        iconWeek1.setIcon(iconWeek1SVG);

        FlatSVGIcon iconStockCountSVG = new FlatSVGIcon("lk/inam/glizmo/img/stock.svg", 50, 50);
        iconStockCount.setIcon(iconStockCountSVG);

        FlatSVGIcon iconProductCountSVG = new FlatSVGIcon("lk/inam/glizmo/img/product.svg", 60, 60);
        iconProductCount.setIcon(iconProductCountSVG);

        FlatSVGIcon iconUserCountSVG = new FlatSVGIcon("lk/inam/glizmo/img/users-1.svg", 50, 50);
        iconUserCount.setIcon(iconUserCountSVG);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        topUser = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        topProduct = new javax.swing.JTable();
        totalErnToday = new javax.swing.JPanel();
        todayErn = new javax.swing.JLabel();
        iconToday = new javax.swing.JLabel();
        totalErnWeek = new javax.swing.JPanel();
        weekErn = new javax.swing.JLabel();
        iconWeek = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        monthErn = new javax.swing.JLabel();
        iconMonth = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        todaySld = new javax.swing.JLabel();
        iconToday1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        weekSld = new javax.swing.JLabel();
        iconWeek1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        monthSld = new javax.swing.JLabel();
        iconMonth1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        totalStock = new javax.swing.JLabel();
        iconStockCount = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        totalProduct = new javax.swing.JLabel();
        iconProductCount = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        totalUser = new javax.swing.JLabel();
        iconUserCount = new javax.swing.JLabel();
        chart = new javax.swing.JPanel();
        chart1 = new javax.swing.JPanel();
        topProductReport = new javax.swing.JButton();
        topUserReport = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        jLabel1.setText("Dashboard");

        jLabel2.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel2.setText("Top Salling Products");

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel3.setText("Top Buying Users");

        topUser.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        topUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Email", "Full Name", "Contact", "Buyings"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(topUser);

        topProduct.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        topProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Name", "Sold Quantity", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(topProduct);

        totalErnToday.setBackground(new java.awt.Color(255, 255, 255));
        totalErnToday.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        todayErn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        todayErn.setText("Total Earning Today :");
        todayErn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout totalErnTodayLayout = new javax.swing.GroupLayout(totalErnToday);
        totalErnToday.setLayout(totalErnTodayLayout);
        totalErnTodayLayout.setHorizontalGroup(
            totalErnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalErnTodayLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconToday, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(totalErnTodayLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(todayErn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totalErnTodayLayout.setVerticalGroup(
            totalErnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalErnTodayLayout.createSequentialGroup()
                .addComponent(iconToday, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(todayErn)
                .addGap(71, 71, 71))
        );

        totalErnWeek.setBackground(new java.awt.Color(255, 255, 255));
        totalErnWeek.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        weekErn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        weekErn.setText("Total Earning This Week :");
        weekErn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout totalErnWeekLayout = new javax.swing.GroupLayout(totalErnWeek);
        totalErnWeek.setLayout(totalErnWeekLayout);
        totalErnWeekLayout.setHorizontalGroup(
            totalErnWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalErnWeekLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(totalErnWeekLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(weekErn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totalErnWeekLayout.setVerticalGroup(
            totalErnWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalErnWeekLayout.createSequentialGroup()
                .addComponent(iconWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weekErn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        monthErn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        monthErn.setText("Total Earning This Month :");
        monthErn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(monthErn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(iconMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthErn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        todaySld.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        todaySld.setText("Today Sold Products :");
        todaySld.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconToday1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(todaySld)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(iconToday1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(todaySld)
                .addGap(71, 71, 71))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        weekSld.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        weekSld.setText("This Week Sold Products :");
        weekSld.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(weekSld)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(iconWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weekSld)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        monthSld.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        monthSld.setText("This Month Sold Products :");
        monthSld.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(monthSld)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(iconMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthSld)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        totalStock.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        totalStock.setText("Total Stocks :");
        totalStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(totalStock)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconStockCount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(totalStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(iconStockCount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        totalProduct.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        totalProduct.setText("Total Products :");
        totalProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(totalProduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(iconProductCount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(totalProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconProductCount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N

        totalUser.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        totalUser.setText("Total Users :");
        totalUser.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(totalUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconUserCount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconUserCount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        chart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chartLayout = new javax.swing.GroupLayout(chart);
        chart.setLayout(chartLayout);
        chartLayout.setHorizontalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        chartLayout.setVerticalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        chart1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chart1Layout = new javax.swing.GroupLayout(chart1);
        chart1.setLayout(chart1Layout);
        chart1Layout.setHorizontalGroup(
            chart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        chart1Layout.setVerticalGroup(
            chart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        topProductReport.setBackground(new java.awt.Color(30, 144, 255));
        topProductReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        topProductReport.setForeground(new java.awt.Color(255, 255, 255));
        topProductReport.setText("Generate as a report");
        topProductReport.setIconTextGap(8);
        topProductReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topProductReportActionPerformed(evt);
            }
        });

        topUserReport.setBackground(new java.awt.Color(30, 144, 255));
        topUserReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        topUserReport.setForeground(new java.awt.Color(255, 255, 255));
        topUserReport.setText("Generate as a report");
        topUserReport.setIconTextGap(8);
        topUserReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topUserReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalErnToday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalErnWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addComponent(topProductReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(topUserReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalErnWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalErnToday, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topUserReport, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addComponent(topProductReport, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(294, 294, 294))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void topProductReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topProductReportActionPerformed

        try {

            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/order_top.jasper");

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
    }//GEN-LAST:event_topProductReportActionPerformed

    private void topUserReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topUserReportActionPerformed
        
        try {

            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/top_user.jasper");

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
    }//GEN-LAST:event_topUserReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chart;
    private javax.swing.JPanel chart1;
    private javax.swing.JLabel iconMonth;
    private javax.swing.JLabel iconMonth1;
    private javax.swing.JLabel iconProductCount;
    private javax.swing.JLabel iconStockCount;
    private javax.swing.JLabel iconToday;
    private javax.swing.JLabel iconToday1;
    private javax.swing.JLabel iconUserCount;
    private javax.swing.JLabel iconWeek;
    private javax.swing.JLabel iconWeek1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel monthErn;
    private javax.swing.JLabel monthSld;
    private javax.swing.JLabel todayErn;
    private javax.swing.JLabel todaySld;
    private javax.swing.JTable topProduct;
    private javax.swing.JButton topProductReport;
    private javax.swing.JTable topUser;
    private javax.swing.JButton topUserReport;
    private javax.swing.JPanel totalErnToday;
    private javax.swing.JPanel totalErnWeek;
    private javax.swing.JLabel totalProduct;
    private javax.swing.JLabel totalStock;
    private javax.swing.JLabel totalUser;
    private javax.swing.JLabel weekErn;
    private javax.swing.JLabel weekSld;
    // End of variables declaration//GEN-END:variables
}
