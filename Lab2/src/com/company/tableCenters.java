//package com.company;
//import javax.swing.*;
//import javax.swing.table.AbstractTableModel;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class tableCenters extends JPanel {
//    private boolean DEBUG = false;
//    JButton button;
//
//    public tableCenters() {
//        super(new GridLayout(1, 0));
//
//        JTable table = new JTable(new MyTableModel());
//        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//
//        //Create the scroll pane and add the table to it.
//        JScrollPane scrollPane = new JScrollPane(table);
//
////        button = new JButton("Show Car List");
////        button.setBounds(100,100,10,10);
//
////        button.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                for(int i = 0; i<4; i++ ) { // container.salons.size()
////                    Boolean checked = Boolean.valueOf(table.getValueAt(i,0).toString());
////                    String col=table.getValueAt(i, 1).toString();
////
////                    if(checked) {
////                        JOptionPane.showMessageDialog(null, col);
////                    }
////                }
////            }
////        });
//
//      //  JCheckBox[] checkBox = new JCheckBox[4];
//
//        //Add the scroll pane to this panel.add(scrollPane);
//        add(scrollPane);
//      //  add(button);
//
//
//    }
//
//
//
//
//        /**
//         * JTable uses this method to determine the default renderer/ editor for
//         * each cell. If we didn't implement this method, then the last column
//         * would contain text ("true"/"false"), rather than a check box.
//         */
//        public Class getColumnClass(int c) {
//            return getValueAt(0, c).getClass();
//        }
//
//        /**
//         * Don't need to implement this method unless your table's editable.
//         */
//        public boolean isCellEditable(int row, int col) {
//            //Note that the data/cell address is constant,
//            //no matter where the cell appears onscreen.
//            if (col == 3) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        /**
//         * Don't need to implement this method unless your table's data can
//         * change.
//         */
//        public void setValueAt(Object value, int row, int col) {
//            if (DEBUG) {
//                System.out.println("Setting value at " + row + "," + col
//                        + " to " + value + " (an instance of "
//                        + value.getClass() + ")");
//            }
//
//            data[row][col] = value;
//            fireTableCellUpdated(row, col);
//
//            if (DEBUG) {
//                System.out.println("New value of data:");
//                printDebugData();
//            }
//        }
//
//        private void printDebugData() {
//            int numRows = getRowCount();
//            int numCols = getColumnCount();
//
//            for (int i = 0; i < numRows; i++) {
//                System.out.print("    row " + i + ":");
//                for (int j = 0; j < numCols; j++) {
//                    System.out.print("  " + data[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println("--------------------------");
//        }
//    }
//
//    /**
//     * Create the GUI and show it. For thread safety, this method should be
//     * invoked from the event-dispatching thread.
//     */
//    public static void createAndShowGUI() {
//        //Make sure we have nice window decorations.
//        JFrame.setDefaultLookAndFeelDecorated(true);
//
//        //Create and set up the window.
//        JFrame frame = new JFrame("TableDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        tableCenters newContentPane = new tableCenters();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
//
////    public static void main(String[] args) {
////        //Schedule a job for the event-dispatching thread:
////        //creating and showing this application's GUI.
////        javax.swing.SwingUtilities.invokeLater(new Runnable() {
////            public void run() {
////                createAndShowGUI();
////            }
////        });
////    }
//}
