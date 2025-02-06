package pgl.LAW.V4DB.vmap4;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SmpManage extends JFrame implements ActionListener {
    int row;
    JTabbedPane tab = new JTabbedPane();
    JPanel mainPanel = new JPanel();
    JScrollPane viewListScroll;
    JScrollPane viewScroll;
    JPanel updatePanel = new JPanel();
    GetGermplasmInfo smpInfo1 = new GetGermplasmInfo();
    GetGermplasmInfo smpInfo2 = new GetGermplasmInfo();
    JPanel queryPanel = new JPanel();
    JButton dataButton = new JButton("delete");
    JTextField queryTextFile = new JTextField(10);
    Object data[][], data1[][], data2[][];
    Object colName[] = {"GID", "G_repeat", "Bam", "Accession", "Chinese_name", "Taxonomy", "Dataset"};
    JTable smpTable, queryTable, queryList;
    JButton addButton = new JButton("Add");
    JButton modifyButton = new JButton("Modify");
    JButton queryButton = new JButton("Query");
    JButton updateButton = new JButton("Update data");
    String sGID;
    JTextField GIDText = new JTextField(10);
    public SmpManage() {
        super("Germplasm manage system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Add data
     */
    public void addData(){
        JButton addClear = new JButton("Clear");
        mainPanel.add(smpInfo1);
        addButton.addActionListener(this);
        smpInfo1.add(addButton);
        smpInfo1.add(addClear);
        addClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                smpInfo1.GID.setText("");
                smpInfo1.GRepeat.setText("");
                smpInfo1.bam.setText("");
                smpInfo1.accession.setText("");
                smpInfo1.ChineseName.setText("");
                smpInfo1.taxa.setText("");
                smpInfo1.dataSet.setText("");
            }
        });
        tab.add("Add data", smpInfo1);
    }

    /**
     * Modify data
     */
    public void modifyData(){
        JButton updateClear = new JButton("Modify");
        mainPanel.add(smpInfo2);
        smpInfo2.add(modifyButton);
        smpInfo2.add(queryButton);
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                smpInfo2.GID.setEditable(false);
                smpInfo2.GRepeat.setEditable(true);
                smpInfo2.bam.setEditable(true);
                smpInfo2.accession.setEditable(true);
                smpInfo2.ChineseName.setEditable(true);
                smpInfo2.taxa.setEditable(true);
                smpInfo2.dataSet.setEditable(true);
                sGID = smpInfo2.GID.getText();
                if (smpInfo2.GID.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "GID cannot be empty!");
                else try{
                    ResultSet rs;
                    Connection conn = new GetConnection().getConn();
                    Statement stmt = conn.createStatement();
                    String sql = "select * from main_list where GID="+sGID;
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        smpInfo2.GRepeat.setText(rs.getString(2));
                        smpInfo2.bam.setText(rs.getString(3));
                        smpInfo2.accession.setText(rs.getString(4));
                        smpInfo2.ChineseName.setText(rs.getString(5));
                        smpInfo2.taxa.setText(rs.getString(6));
                        smpInfo2.dataSet.setText(rs.getString(7));
                    }
                    queryTable.setVisible(false);
                    queryTable.setVisible(true);
                    rs.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        smpInfo2.add(modifyButton);
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                if (smpInfo2.GID.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "GID cannot be empty!");
                else try {
                    smpInfo2.GRepeat.setEditable(false);
                    smpInfo2.bam.setEditable(false);
                    smpInfo2.accession.setEditable(false);
                    smpInfo2.ChineseName.setEditable(false);
                    smpInfo2.taxa.setEditable(false);
                    smpInfo2.dataSet.setEditable(false);
                    String GID = smpInfo2.GID.getText();
                    String GRepeat = smpInfo2.GRepeat.getText();
                    String bam = smpInfo2.bam.getText();
                    String accession = smpInfo2.accession.getText();
                    String ChineseName = smpInfo2.ChineseName.getText();
                    String taxa = smpInfo2.taxa.getText();
                    String dataSet = smpInfo2.dataSet.getText();
                    Connection conn = new GetConnection().getConn();
                    Statement stmt = conn.createStatement();
                    String sql = "update main_list set " +
                            "GRepeat='"+GRepeat+"'," +
                            "bam='"+bam+"'," +
                            "accession='"+accession+"'," +
                            "ChineseName='"+ChineseName+"'," +
                            "taxa='"+taxa+"'," +
                            "dataSet='"+dataSet+"' where GID='"+GID+"'";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Modify success!");
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    SmpManage add1 = new SmpManage();
                    add1.setLocationRelativeTo(null);
                    add1.setVisible(true);
                    add1.setSize(500, 170);
                    setVisible(false);
                    smpInfo2.GID.setEditable(true);
                }
            }
        });
        smpInfo2.add(updateClear);
        updateClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                smpInfo2.GID.setEditable(true);
                smpInfo2.GRepeat.setEditable(false);
                smpInfo2.bam.setEditable(false);
                smpInfo2.accession.setEditable(false);
                smpInfo2.ChineseName.setEditable(false);
                smpInfo2.taxa.setEditable(false);
                smpInfo2.dataSet.setEditable(false);
                smpInfo2.GID.setText("");
                smpInfo2.GRepeat.setText("");
                smpInfo2.bam.setText("");
                smpInfo2.accession.setText("");
                smpInfo2.ChineseName.setText("");
                smpInfo2.taxa.setText("");
                smpInfo2.dataSet.setText("");
            }
        });
        smpInfo2.GRepeat.setEditable(false);
        smpInfo2.bam.setEditable(false);
        smpInfo2.accession.setEditable(false);
        smpInfo2.ChineseName.setEditable(false);
        smpInfo2.taxa.setEditable(false);
        smpInfo2.dataSet.setEditable(false);
        tab.add("Modify data", smpInfo2);
    }

    /**
     * Delete data
     */
    public void deleteData(){
        JLabel GIDlabel = new JLabel("GID");
        JButton deleteQuery = new JButton("Query");
        JButton deleteClear = new JButton("Clear");
        mainPanel.add(GIDlabel);
        mainPanel.add(GIDText);
        mainPanel.add(deleteQuery);
        mainPanel.add(dataButton);
        mainPanel.add(deleteClear);
        data2 = new Object[1][6];
        queryList = new JTable(data2, colName);
        JScrollPane jsp = new JScrollPane(queryList);
        mainPanel.add(jsp);
        queryList.setVisible(false);
        queryList.setFillsViewportHeight(true);
        deleteQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                sGID = GIDText.getText();
                if (GIDText.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "GID cannot be empty!");
                else try {
                    ResultSet rs1;
                    Connection conn = new GetConnection().getConn();
                    Statement stmt1 = conn.createStatement();
                    String sql = "select * from vmap4 where GID="+sGID;
                    rs1 = stmt1.executeQuery(sql);
                    int i = 0;
                    if (rs1.next()) {
                        data[i][0] = rs1.getString(1);
                        data[i][1] = rs1.getString(2);
                        data[i][2] = rs1.getString(3);
                        data[i][3] = rs1.getString(4);
                        data[i][4] = rs1.getString(5);
                        data[i][5] = rs1.getString(6);
                        data[i][6] = rs1.getString(7);
                        queryList.setVisible(true);
                    } else
                        JOptionPane.showMessageDialog(null, "no record!");
                    queryList.setVisible(false);
                    queryList.setVisible(true);
                    rs1.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                sGID = GIDText.getText();
                if (GIDText.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "GID cannot be empty!");
                else try {
                   Connection conn = new GetConnection().getConn();
                   Statement stmt1 = conn.createStatement();
                   String sql1 = "delete from vmap4 where GID='"+sGID+"'";
                   stmt1.executeUpdate(sql1);
                   queryTable.setVisible(false);
                   queryTable.setVisible(true);
                   conn.close();
                   JOptionPane.showMessageDialog(null, "delete success!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        deleteClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                GIDText.setText("");
            }
        });
        tab.add("delete data", mainPanel);
    }
    /**
     * Query data
     */
    public void queryData() {
        JLabel GIDLabel = new JLabel("GID");
        JButton queryButton = new JButton("Query");
        JButton clearButton = new JButton("Clear");
        queryPanel.add(GIDLabel);
        queryPanel.add(queryTextFile);
        queryPanel.add(queryButton);
        queryPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                queryTextFile.setText("");
            }
        });
        data1 = new Object[1][6];
        queryTable = new JTable(data1, colName);
        viewScroll = new JScrollPane(queryTable);
        queryPanel.add(viewScroll);
        queryPanel.setVisible(false);
        queryTable.setFillsViewportHeight(true);
        tab.add("Query data", queryPanel);
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                sGID = queryTextFile.getText();
                if (queryTextFile.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "GID cannot be empty!");
                else try {
                    ResultSet rs1;
                    Connection conn = new GetConnection().getConn();
                    Statement stmt1 = conn.createStatement();
                    String sql1 = "select * from vmap4 where GID="+sGID;
                    rs1 = stmt1.executeQuery(sql1);
                    int i = 0;
                    if (rs1.next()) {
                        data[i][0] = rs1.getString(1);
                        data[i][1] = rs1.getString(2);
                        data[i][2] = rs1.getString(3);
                        data[i][3] = rs1.getString(4);
                        data[i][4] = rs1.getString(5);
                        data[i][5] = rs1.getString(6);
                        data[i][6] = rs1.getString(7);
                        queryList.setVisible(true);
                    } else
                        JOptionPane.showMessageDialog(null, "no record!");
                    queryTable.setVisible(false);
                    queryTable.setVisible(true);
                    rs1.close();
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    /**
     * Display data with table
     */
    public void displayData() {
        try {
            JPanel content = new JPanel();
            JButton clear = new JButton("Clear");
            ResultSet rs;
            Connection conn = new GetConnection().getConn();
            Statement stmt = conn.createStatement();
            String sql = "select * from vmap4";
            rs = stmt.executeQuery(sql);
            rs.last();
            row = rs.getRow();
            data = new Object[row][6];
            smpTable = new JTable(data, colName);
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            smpTable.setDefaultRenderer(Object.class, r);
            content.setPreferredSize(new Dimension(50, 400));
            content.add(updateButton);
            content.add(clear);
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent d) {
                    SmpManage add1 = new SmpManage();
                    add1.setLocationRelativeTo(null);
                    add1.setVisible(true);
                    add1.setSize(476, 170);
                    setVisible(false);
                }
            });
            content.add(new JScrollPane(smpTable));
            viewListScroll = new JScrollPane(content);
            rs.beforeFirst();
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent d) {
                    for (int i=0; i<smpTable.getRowCount(); i++) {
                        data[i][0]="";
                        data[i][1]="";
                        data[i][2]="";
                        data[i][3]="";
                        data[i][4]="";
                        data[i][5]="";
                        data[i][6]="";
                    }
                }
            });
            int i = 0;
            while (rs.next()) {
                data[i][0]=rs.getString(1);
                data[i][1]=rs.getString(2);
                data[i][2]=rs.getString(3);
                data[i][3]=rs.getString(4);
                data[i][4]=rs.getString(5);
                data[i][5]=rs.getString(6);
                data[i][6]=rs.getString(7);
                i++;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tab.add("View data", viewListScroll);
    }
    /**
     * View data
     */
    public void viewData() {
        displayData();
    }
    /**
     * query if GID exists
     */
    public boolean queryExist(String id) {
        boolean b = false;
        try {
            ResultSet rs;
            Connection conn = new GetConnection().getConn();
            Statement stmt = conn.createStatement();
            String sql = "select * from vmap4 where GID="+id;
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b = true;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return b;
    }
    /**
     * Action performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            Statement stmt = null;
            try {
                String GID = smpInfo2.GID.getText();
                String GRepeat = smpInfo2.GRepeat.getText();
                String bam = smpInfo2.bam.getText();
                String accession = smpInfo2.accession.getText();
                String ChineseName = smpInfo2.ChineseName.getText();
                String taxa = smpInfo2.taxa.getText();
                String dataSet = smpInfo2.dataSet.getText();
                if (queryExist(GID)) {
                    JOptionPane.showMessageDialog(null, "GID already exists");
                } else {
                    Connection conn = new GetConnection().getConn();
                    stmt = conn.createStatement();
                    String sql = "insert into vmap4 (GID, GRepeat, Bam, Accession, Chinese name, Taxonomy, DataSet)" +
                            "values("+"'"+GID+"'"+"'"+GRepeat+"'"+"'"+bam+"'"+"'"+accession+"'"+"'"+ChineseName+"'"+"'"+taxa+"'"+"'"+dataSet+"')";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "add success!");
                }
            } catch (Exception f) {
                f.printStackTrace();
                JOptionPane.showMessageDialog(null,  "add failed");
            } finally {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
