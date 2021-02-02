package com.firm.code;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;

public class Facade implements ActionListener {

    // Map<String, CarShowroom> cart;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton buttonBuy;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    boolean success = false;
    CarShowroomContainer data;
    CarShowroom cart;
    List<String> cartSalonNames;
    JTable tableShowroom;
    JTable tableVehicle;
    JTable tableCart;
    JScrollPane scrollShowroom;
    JScrollPane scrollVehicle;
    JScrollPane scrollCart;
    JFrame frame;
    JMenu menu;
    JMenuItem load, save, export;
    String currentSalon = "";


    public Facade() throws IOException, ClassNotFoundException {
        data = data();
        cart = cart();
        cartSalonNames = names();
        frame = new Frame();

        panel1 = new JPanel();
        panel1.setBackground(new Color(0xb3ccff));
        panel2 = new JPanel();
        panel2.setBackground(new Color(0x99bbff));
        panel3 = new JPanel();
        panel3.setBackground(new Color(0x80aaff));
        panel4 = new JPanel();
        panel4.setBackground(new Color(0x6699ff));
        tableShowroom = new JTable();
        tableShowroom.setModel(new ModelCarshowroom(data));

        //tableCart = new JTable(new ModelCart(cart));


        scrollShowroom = new JScrollPane(tableShowroom);
        scrollVehicle = new JScrollPane(tableVehicle);
        scrollCart = new JScrollPane(tableCart);


        tableVehicle = new JTable();


        button1 = new JButton("Add CarShowroom");
        button1.addActionListener(this);

        button2 = new JButton("Delete CarShowroom");
        button2.addActionListener(this);

        button3 = new JButton("Add Vehicle");
        button3.addActionListener(this);

        button4 = new JButton("Delete Vehicle");
        button4.addActionListener(this);

        button5 = new JButton("Sort by max number");
        button5.addActionListener(this);

        button6 = new JButton("Add to Cart");
        button6.addActionListener(this);

        button7 = new JButton("See your Cart");
        button7.addActionListener(this);

        GridBagLayout layout = new GridBagLayout();
        panel2.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        panel2.add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel2.add(button2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 10;
        panel2.add(button5, gbc);


        panel2.setLayout(layout);
        layout = new GridBagLayout();
        panel4.setLayout(layout);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        panel4.add(button3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        panel4.add(button4, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        panel4.add(button6, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        panel4.add(button7, gbc);

        panel4.setLayout(layout);

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);


        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        export = new JMenuItem("Export");

        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToCSV(tableShowroom, "salons.csv");
                JOptionPane.showMessageDialog(frame, "Salons Exported");
            }
        });


        menu.add(export);

        mb.add(menu);
        frame.setJMenuBar(mb);

        panel1.add(tableShowroom);
        panel1.add(scrollShowroom);


        tableShowroom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    int row = tableShowroom.rowAtPoint(e.getPoint());
                    int col = tableShowroom.columnAtPoint(e.getPoint());
                    String nameSalon = (String) tableShowroom.getValueAt(row, col);

                    currentSalon = nameSalon;
                    tableVehicle.setModel(new ModelVehicle(nameSalon, data));
                    panel3.add(tableVehicle);
                    success = true;
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(frame, "Click on the name!");
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {


                String[] opt = {"YES", "NO"};
                int x = JOptionPane.showOptionDialog(null, "Do you want to save your cart?", "Saving cart",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, opt[0]);

                if (x == 1) {
                    reset();
                } else {
                    try {
                        writeToFileContainer(data);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }


                e.getWindow().dispose();
                System.out.println("Application Closed!");
            }
        });
    }

    public void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame2 = new JFrame("Cart");
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setBounds(100, 100, 700, 300);
                frame2.setLayout(new GridLayout(2, 1));

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel inputPanel = new JPanel();
                JPanel tablePanel = new JPanel();
                tablePanel.setBackground(new Color(0x6FFFFA));
                inputPanel.setBackground(new Color(0x91FFF5));

                try {
                    cart = cart();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                tableCart = new JTable(new ModelCart(cart));
                tableCart.setModel(new ModelCart(cart));
                tablePanel.add(tableCart);
                tablePanel.add(scrollCart);
                inputPanel.setLayout(new FlowLayout());
                tablePanel.setLayout(new FlowLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;


                buttonBuy = new JButton("Buy");
                buttonBuy.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        JOptionPane.showMessageDialog(frame, "Purchased");
                        cart = new CarShowroom("cart", 10);
                        tableCart.setModel(new ModelCart(cart));

                        try {
                            writeToFileContainer(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            readFileContainer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 10, 5, 10);
                inputPanel.add(buttonBuy, gbc);

                JButton buttonReset = new JButton("Reset");
                buttonReset.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
//                        for(String s : cartSalonNames){
//                            System.out.println(s);
//                        }
                        JOptionPane.showMessageDialog(frame, "Reset");
                        reset();
                        tableCart.setModel(new ModelCart(cart));
                    }
                });
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 10, 5, 10);
                inputPanel.add(buttonReset, gbc);


                frame2.add(tablePanel);
                frame2.add(inputPanel);
                frame2.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button1) {
            try {
                String name = JOptionPane.showInputDialog("Name: ");
                int max = Integer.parseInt(JOptionPane.showInputDialog("Max car's: "));

                data.addCenter(name, max);
                tableShowroom.setModel(new ModelCarshowroom(data));
                panel1.add(tableShowroom);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong Input");
            }

        } else if (e.getSource() == button2) {
            if (success) {
                int Selected = tableShowroom.getSelectedRow();
                int i = 0;
                String toRemove = null;
                for (CarShowroom c : data.salons.values()) {
                    if (i == Selected) toRemove = c.getSalonName();
                    i++;
                }
                data.removeCenter(toRemove);
                tableShowroom.setModel(new ModelCarshowroom(data));
                panel1.add(tableShowroom);
            }

        } else if (e.getSource() == button3) {
            try {
                String brand = JOptionPane.showInputDialog("Brand: ");
                String model = JOptionPane.showInputDialog("Model: ");
                String S_condition = JOptionPane.showInputDialog("Condition: ");
                String S_year = JOptionPane.showInputDialog("Production Year: ");
                String S_mileage = JOptionPane.showInputDialog("Mileage: ");
                String S_engineSize = JOptionPane.showInputDialog("EngineSize: ");
                String S_price = JOptionPane.showInputDialog("Price: ");
                ItemConditions condition = null;

                if (S_condition.equals("NEW") || S_condition.equals("new")) {
                    condition = ItemConditions.NEW;
                } else if (S_condition.equals("USED") || S_condition.equals("used")) {
                    condition = ItemConditions.USED;
                } else if (S_condition.equals("DAMAGED") || S_condition.equals("damaged")) {
                    condition = ItemConditions.DAMAGED;
                }

                int year = Integer.parseInt(S_year);
                double mileage = Double.parseDouble(S_mileage);
                double engineSize = Double.parseDouble(S_engineSize);
                double price = Double.parseDouble(S_price);

                Vehicle v = new Vehicle(brand, model, condition, year, mileage, engineSize, price);

                int Selected = tableShowroom.getSelectedRow();
                System.out.print(Selected);
                int i = 0;

                String newSet = null;
                for (CarShowroom c : data.salons.values()) {
                    if (i == Selected) {
                        c.addProduct(v);
                        newSet = c.getSalonName();
                    }
                    i++;
                }

                tableVehicle.setModel(new ModelVehicle(newSet, data));
                panel3.add(tableVehicle);
                tableShowroom.setModel(new ModelCarshowroom(data));
                panel1.add(tableShowroom);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong Input");
            }
        } else if (e.getSource() == button4) {
            if (success) {

                int selectedFirst = tableShowroom.getSelectedRow();

                int i = 0;
                CarShowroom toRemove = null;
                String newSet = null;
                for (CarShowroom c : data.salons.values()) {
                    if (i == selectedFirst) {
                        toRemove = c;
                        newSet = toRemove.getSalonName();
                    }
                    i++;
                }
                try {
                    int selectedSecond = tableVehicle.getSelectedRow();
                    toRemove.removeByRow(selectedSecond);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "Select salon!");
                }

                tableVehicle.setModel(new ModelVehicle(newSet, data));
                panel3.add(tableVehicle);
                tableShowroom.setModel(new ModelCarshowroom(data));
                panel1.add(tableShowroom);
            }

        } else if (e.getSource() == button5) {
            java.util.List<String> salonNames = new LinkedList<>();
            java.util.List<CarShowroom> salon = new LinkedList<>();

            for (CarShowroom c : data.salons.values()) {
                salon.add(c);
            }

            AmountComparator comp = new AmountComparator();
            Collections.sort(salon, comp);

            for (CarShowroom c : salon) {
                salonNames.add(c.getSalonName());
            }

            int k = 0;
            for (CarShowroom c : data.salons.values()) {
                data.salons.replace(c.getSalonName(), c, salon.get(k));
                k++;
            }

            for (CarShowroom c : data.salons.values()) {
                System.out.println(c.getMaxCars());
            }

            tableShowroom.setModel(new ModelCarshowroom(data));
            panel1.add(tableShowroom);

        } else if (e.getSource() == button6) {

            if (success) {
                int selectedFirst = tableShowroom.getSelectedRow();
                int i = 0;
                CarShowroom toRemove = null;
                String newSet = null;
                for (CarShowroom c : data.salons.values()) {
                    if (i == selectedFirst) {
                        toRemove = c;
                        newSet = toRemove.getSalonName();
                    }
                    i++;
                }

                int selectedSecond = tableVehicle.getSelectedRow();
                CarShowroom x = null;
                for (CarShowroom c : data.salons.values()) {

                    if (currentSalon.equals(c.getSalonName())) {
                        x = c;
                    }
                }

                cartSalonNames.add(x.getSalonName());
                cart.addProduct(x.carList.get(selectedSecond));

                try {
                    toRemove.removeByRow(selectedSecond);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "Select salon!");
                }

                tableVehicle.setModel(new ModelVehicle(newSet, data));
                panel3.add(tableVehicle);
                tableShowroom.setModel(new ModelCarshowroom(data));
                panel1.add(tableShowroom);

                try {
                    writeToFileCart(cart);
                    writeToFileNames(cartSalonNames);
                    // System.out.print("dziala?");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        } else if (e.getSource() == button7) {


//            try {
//                writeToFileContainer(data);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }

            try {
                createFrame();
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(frame, "Something went wrong");
            }
        }
    }

    private CarShowroomContainer data() throws IOException, ClassNotFoundException {

        try {
            data = readFileContainer();
        } catch (FileNotFoundException e) {

            CarShowroom salon = new CarShowroom("salon", 20);
            salon.insertIntoList();
            data = new CarShowroomContainer();
            data.insertPrevSalon(salon);
            data.addCenter("s2", 10);

            Vehicle v = new Vehicle("Mercedes-Benz", "Class E", ItemConditions.USED, 2017, 97000, 2.0, 230000);
            Vehicle v2 = new Vehicle("Volkswagen", "Golf VI", ItemConditions.NEW, 2010, 99000, 1.4, 33600);
            Vehicle v3 = new Vehicle("Audi", "A8", ItemConditions.USED, 2018, 83000, 3.0, 248000);
            Vehicle v4 = new Vehicle("Volkswagen", "Tiguan", ItemConditions.USED, 2016, 79000, 2.0, 75000);
            Vehicle v5 = new Vehicle("Mercedes-Benz", "Class B", ItemConditions.USED, 2019, 5800, 1.3, 139700);
            Vehicle v6 = new Vehicle("Volkswagen", "Passat", ItemConditions.NEW, 2013, 229000, 1.9, 20600);
            Vehicle v7 = new Vehicle("Audi", "A3", ItemConditions.USED, 2012, 123000, 1.2, 8000);

            data.salons.get("s2").addProduct(v);
            data.salons.get("s2").addProduct(v6);
            data.salons.get("s2").addProduct(v7);

            //System.out.println(container.salons.get("s2").getCurrentCarsAmount());
            data.addCenter("s3", 15);
            data.salons.get("s3").addProduct(v2);
            data.salons.get("s3").addProduct(v3);
            data.salons.get("s3").addProduct(v4);
            data.salons.get("s3").addProduct(v5);
            //data.addCenter("s4", 72);

            return data;
        }
        return data;
    }

    private CarShowroom cart() throws IOException, ClassNotFoundException {

        try {
            cart = readFileCart();
            // cartSalonNames = readFileNames();
        } catch (FileNotFoundException e) {
            CarShowroom cart = new CarShowroom("Cart", 10);
            List<String> cartSalonNames = new ArrayList<>();
            // Map<String,CarShowroom> cart = new TreeMap<>();
            return cart;
        }
        return cart;
    }

    private List<String> names() throws IOException, ClassNotFoundException {
        try {
            cartSalonNames = readFileNames();
        } catch (FileNotFoundException e) {
            CarShowroom cart = new CarShowroom("Cart", 10);
            List<String> cartSalonNames = new ArrayList<>();
            // Map<String,CarShowroom> cart = new TreeMap<>();
            return cartSalonNames;
        }
        return cartSalonNames;
    }


    public static void writeToFileContainer(CarShowroomContainer carShowroomCont) throws IOException {
        ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("vehicle.bin"));
        oOS.writeObject(carShowroomCont);
    }

    public static CarShowroomContainer readFileContainer() throws IOException, ClassNotFoundException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("vehicle.bin"));
        return (CarShowroomContainer) oIS.readObject();
    }

    public static void writeToFileCart(CarShowroom c) throws IOException {
        ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("cart.bin"));
        oOS.writeObject(c);
    }

    public static CarShowroom readFileCart() throws IOException, ClassNotFoundException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("cart.bin"));
        return (CarShowroom) oIS.readObject();
    }

    public static void writeToFileNames(List<String> s) throws IOException {
        ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("names.bin"));
        oOS.writeObject(s);
    }

    public static List<String> readFileNames() throws IOException, ClassNotFoundException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("names.bin"));
        return (List<String>) oIS.readObject();
    }

    public static boolean exportToCSV(JTable tableToExport,
                                      String pathToExportTo) {

        try {
            TableModel model = tableToExport.getModel();
            FileWriter csv = new FileWriter(new File(pathToExportTo));

            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString() + ",");
                }
                csv.write("\n");
            }

            csv.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    void reset() {
        int i = 0;

        for (Vehicle v : cart.carList) {
            String s_v = cartSalonNames.get(i);
            data.salons.get(s_v).addProduct(v);
            i++;
        }

        tableShowroom.setModel(new ModelCarshowroom(data));
        panel1.add(tableShowroom);

        tableVehicle.setModel(new ModelVehicle(currentSalon, data));
        panel3.add(tableVehicle);
        cart = new CarShowroom("cart", 10);


        int size = cartSalonNames.size();
        for (int j = 0; j < size; j++) {
            cartSalonNames.remove(j);
        }

        if(!cartSalonNames.isEmpty()) {
            System.out.print("LIST IS NOT EMPTY");
        }
    }
}

