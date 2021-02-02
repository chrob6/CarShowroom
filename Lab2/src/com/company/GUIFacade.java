package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class GUIFacade implements ActionListener {

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton sort;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    boolean success = false;
    CarShowroomContainer startData;
    JTable tableShowroom;
    JTable tableVehicle;
    JScrollPane scrollShowroom;
    JScrollPane scrollVehicle;
    MyFrame frame;
    JLabel deleted;


    private CarShowroomContainer startData(){
        CarShowroom salon = new CarShowroom("salon", 20);
        salon.insertIntoList();
        CarShowroomContainer container = new CarShowroomContainer();
        container.insertPrevSalon(salon);
        container.addCenter("s2", 10 );

        Vehicle v = new Vehicle("Mercedes-Benz","Class E", ItemConditions.USED, 2017,97000,2.0);
        container.salons.get("s2").addProduct(v);
        container.salons.get("s2").addProduct(v);
        container.salons.get("s2").addProduct(v);

        //System.out.println(container.salons.get("s2").getCurrentCarsAmount());
        container.addCenter("s3", 15 );
        container.addCenter("s4", 72 );

        return container;
    }

    public GUIFacade(){
        startData = startData();
        frame = new MyFrame();

        //deleted = new JLabel("DO NOT EXIST ANYMORE!");
        panel1 = new JPanel();
        panel1.setBackground(new Color(0xb3ccff));
       // panel1.setBounds(0,200,200,200);

        panel2 = new JPanel();
        panel2.setBackground(new Color(0x99bbff));
       // panel1.setBounds(200,200,200,200);

        panel3 = new JPanel();
        panel3.setBackground(new Color(0x80aaff));
        //panel1.setBounds(400,200,200,200);

        panel4 = new JPanel();
        panel4.setBackground(new Color(0x6699ff));
        //panel1.setBounds(600,200,200,200);

        tableShowroom = new JTable();
        tableShowroom.setModel(new TableModelCarshowroom(startData));

        scrollShowroom=new JScrollPane(tableShowroom);
        scrollVehicle=new JScrollPane(tableVehicle);
        //scrollShowroom.setViewportView(tableShowroom);


        tableVehicle = new JTable();


        button1 = new JButton("Add CarShowroom");
        button1.addActionListener(this);

        button2 = new JButton("Delete CarShowroom");
        button2.addActionListener(this);

        button3 = new JButton("Add Vehicle");
        button3.addActionListener(this);

        button4 = new JButton("Delete Vehicle");
        button4.addActionListener(this);

        sort = new JButton("Sort center by current load");
        sort.addActionListener(this);

       frame.add(panel1);

       frame.add(panel2);
       panel2.add(button1);
       panel2.add(button2);
       panel2.add(sort);

        frame.add(panel3);

        frame.add(panel4);
        panel4.add(button3);
        panel4.add(button4);

        panel1.add(tableShowroom);
        panel1.add(scrollShowroom);

        tableShowroom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                try {
                    int row = tableShowroom.rowAtPoint(e.getPoint());
                    int col = tableShowroom.columnAtPoint(e.getPoint());
                    String nameSalon = (String) tableShowroom.getValueAt(row, col);
                    //JOptionPane.showMessageDialog(null, tableShowroom.getValueAt(row,col));

                    tableVehicle.setModel(new TableModelVehicle(nameSalon, startData));
                    panel3.add(tableVehicle);
                   // panel3.remove(deleted);
                    success = true;
                }
                catch(ClassCastException ex) {
                    JOptionPane.showMessageDialog(frame, "Click on the salon name!");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == button1) {
            try {
                //JOptionPane.showMessageDialog(null, "button1");
                String name = JOptionPane.showInputDialog("Salon's name to add: ");
                String S_max = JOptionPane.showInputDialog("Maximum car's in the salon");
                int max = Integer.parseInt(S_max);

                startData.addCenter(name, max);
                tableShowroom.setModel(new TableModelCarshowroom(startData));
                panel1.add(tableShowroom);
            }
            catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "Wrong Input");
                }
            catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong Input");
            }
        } else if (e.getSource() == button2) {
           // panel3.add(deleted);
           if(success) {
               //JOptionPane.showMessageDialog(null, "button2");

               int Selected = tableShowroom.getSelectedRow();
               //JOptionPane.showMessageDialog(null, Selected);
               int i = 0;
               String toRemove = null;
               for (CarShowroom c : startData.salons.values()) {
                   if (i == Selected) toRemove = c.getSalonName();
                   i++;
               }
               startData.removeCenter(toRemove);

               tableShowroom.setModel(new TableModelCarshowroom(startData));
               panel1.add(tableShowroom);
           }

        } else if (e.getSource() == button3) {
            try {
                // JOptionPane.showMessageDialog(null, "button3");
                String brand = JOptionPane.showInputDialog("Vehicle brand: ");
                String model = JOptionPane.showInputDialog("Vehicle model: ");
                String S_condition = JOptionPane.showInputDialog("Vehicle condition: ");
                String S_year = JOptionPane.showInputDialog("Vehicle Production Year: ");
                String S_mileage = JOptionPane.showInputDialog("Vehicle Mileage: ");
                String S_engineSize = JOptionPane.showInputDialog("Vehicle engineSize: ");
                ItemConditions condition = null;

                if (S_condition.equals("NEW")) {
                    condition = ItemConditions.NEW;
                } else if (S_condition.equals("USED")) {
                    condition = ItemConditions.USED;
                } else if (S_condition.equals("DAMAGED")) {
                    condition = ItemConditions.DAMAGED;
                }

                int year = Integer.parseInt(S_year);
                double mileage = Double.parseDouble(S_mileage);
                double engineSize = Double.parseDouble(S_engineSize);

                Vehicle v = new Vehicle(brand, model, condition, year, mileage, engineSize);

                int Selected = tableShowroom.getSelectedRow();
                //JOptionPane.showMessageDialog(null, Selected);
                int i = 0;

                String newSet = null;
                for (CarShowroom c : startData.salons.values()) {
                    if (i == Selected) {
                        c.addProduct(v);
                        newSet = c.getSalonName();
                    }
                    i++;
                }

                tableVehicle.setModel(new TableModelVehicle(newSet, startData));
                panel3.add(tableVehicle);
                tableShowroom.setModel(new TableModelCarshowroom(startData));
                panel1.add(tableShowroom);
            }catch(NullPointerException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong Input");
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong Input");
            }
        } else if (e.getSource() == button4) {
            if(success) {
                //JOptionPane.showMessageDialog(null, "button4");
                int selectedFirst = tableShowroom.getSelectedRow();
                //JOptionPane.showMessageDialog(null, Selected);
                int i = 0;
                CarShowroom toRemove = null;
                String newSet = null;
                for (CarShowroom c : startData.salons.values()) {
                    if (i == selectedFirst) {
                        toRemove = c;
                        newSet = toRemove.getSalonName();
                    }
                    i++;
                }

                int selectedSecond = tableVehicle.getSelectedRow();
                //JOptionPane.showMessageDialog(null, Selected);

                int j = 0;
                for (Vehicle v : toRemove.carList) {
                    if (j == selectedSecond)  toRemove.remove(v);
                    j++;
                }

                tableVehicle.setModel(new TableModelVehicle(newSet, startData));
                panel3.add(tableVehicle);
                tableShowroom.setModel(new TableModelCarshowroom(startData));
                panel1.add(tableShowroom);
            }
        } else if(e.getSource() == sort) {
            java.util.List<Integer> maxCars= new LinkedList<>();
            java.util.List<String> salonNames= new LinkedList<>();
            java.util.List<CarShowroom> salon= new LinkedList<>();

            for(CarShowroom c : startData.salons.values()) {
                 //salonNames.add(c.getSalonName());
                 //maxCars.add(c.getMaxCars());
                salon.add(c);
            }

//            for(CarShowroom c : salon) {
//                System.out.println(c.getMaxCars());
//            }

            SalonByAmountComparator comp = new SalonByAmountComparator();
            Collections.sort(salon, comp);

//            for(CarShowroom c : salon) {
//                System.out.println(c.getMaxCars());
//            }

            for(CarShowroom c : salon) {
                salonNames.add(c.getSalonName());
            }

           // Map<String, CarShowroom> salonsSorted = new TreeMap<>();


            int k = 0;
            for(CarShowroom c : startData.salons.values()) {
                startData.salons.replace(c.getSalonName(), c , salon.get(k));
                k++;
            }

            for(CarShowroom c : startData.salons.values()) {
                System.out.println(c.getMaxCars());
            }
//
//            for(CarShowroom c : salonsSorted.values()) {
//                System.out.println(c.getMaxCars());
//            }

           // startData.salons = salonsSorted;

            tableShowroom.setModel(new TableModelCarshowroom(startData));
            panel1.add(tableShowroom);

          //  Collections.sort(startData.salons.values());
//            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tabShowroom);
//            tableShowroom.setRowSorter(sorter);
//            tableShowroom.setModel(new TableModelCarshowroom(startData));
//            panel1.add(tableShowroom);

//
//
//            for(CarShowroom c : startData.salons.values()) {
//                 //salonNames.add(c.getSalonName());
//                 maxCars.add(c.getMaxCars());
//            }
//
//            Collections.sort(maxCars);
//
//            for(CarShowroom c : startData.salons.values()) {
//
//            }
        }



    }

}
