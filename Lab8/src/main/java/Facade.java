import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.List;

public class Facade implements ActionListener {

    // Map<String, CarShowroom> cart;
    JButton button1;
    boolean controlButton1 = true;
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
    JPanel panel5;
    JTextField lessThan;
    boolean success = false;
    //CarShowroomContainer data;
    Carshowroom cart;
    List<String> cartSalonNames;
    JTable tableShowroom;
    JTable tableVehicle;
    JTable tableCart;
    JTable tableRating;
    JScrollPane scrollShowroom;
    JScrollPane scrollVehicle;
    JScrollPane scrollCart;
    JFrame frame;
    JFrame frame2;
    JMenu menu;
    JMenuItem load, save, export;
    String currentSalon = "";


    public Facade() throws IOException, ClassNotFoundException {

        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Rating r1 = new Rating(5, java.sql.Date.valueOf("2020-03-12"), "Excellent!");
        Rating r1_1 = new Rating(4, java.sql.Date.valueOf("2019-02-02"), "Good enough");
        Rating r2 = new Rating(2, java.sql.Date.valueOf("2010-04-01"), "not so good");
        Rating r3 = new Rating(0, Date.valueOf("2021-01-01"), "worst salon ever!!");
        Rating r3_1 = new Rating(0, Date.valueOf("2021-01-01"), "worst salon ever!!");
        Rating r3_2 = new Rating(5, Date.valueOf("2019-02-12"), "best service!!");
        Rating r3_3 = new Rating(2, Date.valueOf("2018-04-25"), "could be better");

        Rating r_t1 = new Rating(4, Date.valueOf("2018-04-25"), "could be better");
        Rating r_t2 = new Rating(2, Date.valueOf("2018-04-25"), "could be better");
        Rating r_t3 = new Rating(2, Date.valueOf("2018-04-25"), "could be better");

        Carshowroom s1 = new Carshowroom("s1", 20);
        Carshowroom s2 = new Carshowroom("s2", 40);
        Carshowroom s3 = new Carshowroom("s3", 60);



        Vehicle v1 = new Vehicle("Mercedes-Benz", "Class E", ItemConditions.USED, 2017, 97000, 2.0, 230000);
        Vehicle v2 = new Vehicle("Volkswagen", "Golf VI", ItemConditions.NEW, 2010, 99000, 1.4, 33600);
        Vehicle v3 = new Vehicle("Audi", "A8", ItemConditions.USED, 2018, 83000, 3.0, 248000);
        Vehicle v4 = new Vehicle("Volkswagen", "Tiguan", ItemConditions.USED, 2016, 79000, 2.0, 75000);
        Vehicle v5 = new Vehicle("Mercedes-Benz", "Class B", ItemConditions.USED, 2019, 5800, 1.3, 139700);
        Vehicle v6 = new Vehicle("Volkswagen", "Passat", ItemConditions.NEW, 2013, 229000, 1.9, 20600);
        Vehicle v7 = new Vehicle("Audi", "A3", ItemConditions.USED, 2012, 123000, 1.2, 8000);
        Vehicle v8 = new Vehicle("Fiat","126p", ItemConditions.DAMAGED, 1990,504000,0.3, 3200);
        Vehicle v9 = new Vehicle("Fiat","Punto", ItemConditions.USED, 2005,214000,1.0, 4500);
        Vehicle v10 = new Vehicle("Seat","Ibiza 4", ItemConditions.NEW, 2014,12,1.4, 13400);
        Vehicle v11 = new Vehicle("Seat","Leon", ItemConditions.NEW, 2020,5,1.5, 34500);
        Vehicle v12 = new Vehicle("Opel","Corsa", ItemConditions.USED, 2000,141400,1.1, 7800);
        Vehicle v13 = new Vehicle("Fiat","Grande Punto", ItemConditions.USED, 2008,114000,1.4, 10000);

        r1.setVehicle(v13);
        r1_1.setVehicle(v13);
        r2.setVehicle(v2);
        r3.setCarshowroom(s1);
        r3_1.setCarshowroom(s1);
        r3_2.setCarshowroom(s1);
        r3_3.setCarshowroom(s1);
        r_t1.setCarshowroom(s2);
        r_t2.setCarshowroom(s2);
        r_t3.setCarshowroom(s2);

        v1.setCarshowroom(s1);
        v2.setCarshowroom(s1);
        v3.setCarshowroom(s1);
        v4.setCarshowroom(s1);
        v5.setCarshowroom(s1);
        v6.setCarshowroom(s1);
        v7.setCarshowroom(s2);
        v8.setCarshowroom(s2);
        v9.setCarshowroom(s2);
        v10.setCarshowroom(s2);
        v11.setCarshowroom(s3);
        v12.setCarshowroom(s3);
        v13.setCarshowroom(s3);



        entityManager.getTransaction().begin();
        entityManager.persist(s1);
        entityManager.persist(s2);
        entityManager.persist(s3);

        entityManager.persist(v1);
        entityManager.persist(v2);
        entityManager.persist(v3);
        entityManager.persist(v4);
        entityManager.persist(v5);
        entityManager.persist(v6);
        entityManager.persist(v7);
        entityManager.persist(v8);
        entityManager.persist(v9);
        entityManager.persist(v10);
        entityManager.persist(v11);
        entityManager.persist(v12);
        entityManager.persist(v13);

        entityManager.persist(r1);
        entityManager.persist(r2);
        entityManager.persist(r3);
        entityManager.persist(r3_1);
        entityManager.persist(r3_2);
        entityManager.persist(r3_3);
        entityManager.persist(r1_1);
        entityManager.persist(r_t2);
        entityManager.persist(r_t3);
        entityManager.persist(r_t1);

        entityManager.getTransaction().commit();

        entityManager.refresh(v1);
        entityManager.refresh(v2);
        entityManager.refresh(v3);
        entityManager.refresh(v4);
        entityManager.refresh(v5);
        entityManager.refresh(v6);
        entityManager.refresh(v7);
        entityManager.refresh(v8);
        entityManager.refresh(v9);
        entityManager.refresh(v10);
        entityManager.refresh(v11);
        entityManager.refresh(v12);
        entityManager.refresh(v13);

        entityManager.refresh(s1);
        entityManager.refresh(s2);
        entityManager.refresh(s3);

        entityManager.refresh(r1);
        entityManager.refresh(r2);
        entityManager.refresh(r3);
        entityManager.refresh(r1_1);

        s1.Rating.add(r3_2);
        s1.Rating.add(r3_3);
        s2.Rating.add(r_t1);
        s2.Rating.add(r_t2);
        s2.Rating.add(r_t3);

        v2.Rating.add(r2);
        v13.Rating.add(r3);

        s1.Vehicle.add(v1);
        s1.Vehicle.add(v2);
        s1.Vehicle.add(v3);


        s2.Vehicle.add(v4);
        s2.Vehicle.add(v5);
        s2.Vehicle.add(v6);
        s2.Vehicle.add(v10);


        s3.Vehicle.add(v7);
        s3.Vehicle.add(v8);
        s3.Vehicle.add(v9);
        s3.Vehicle.add(v11);
        s3.Vehicle.add(v12);
        s3.Vehicle.add(v13);



        entityManager.find(Vehicle.class, 7L).print();



        frame =  new Frame();
        panel1 = new JPanel();
        panel1.setBackground(new Color(0xb3ccff));
        panel2 = new JPanel();
        panel2.setBackground(new Color(0x99bbff));
        panel3 = new JPanel();
        panel3.setBackground(new Color(0x80aaff));
        panel4 = new JPanel();
        panel4.setBackground(new Color(0x6699ff));
        panel5 = new JPanel();
        panel5.setBackground(new Color(0x3271F8));


//        List<CarShowroom> carShowrooms = carShowroomsList();
//        List<Object []> resultRating = carShowroomQuery();
//        List<Object []> resultVehicle = vehiclesAmountQuery();
        //<property name="hibernate.hbm2ddl.auto" value="create" />
        entityManager.close();
        entityManagerFactory.close();


       tableShowroom = new JTable();
     //  tableShowroom.setModel(new ModelCarshowroom(carShowrooms, resultRating, resultVehicle));

        scrollShowroom = new JScrollPane(tableShowroom);
        scrollVehicle = new JScrollPane(tableVehicle);
        scrollCart = new JScrollPane(tableCart);


        tableVehicle = new JTable();
        tableRating = new JTable();

        lessThan = new JTextField("LessThan");
        lessThan.setBounds(100,100,50,10);

        button1 = new JButton("The Best");
        button1.addActionListener(this);

        button2 = new JButton("Sort by brand");
        button2.addActionListener(this);

        button3 = new JButton("Add Vehicle");
        button3.addActionListener(this);

        button4 = new JButton("Delete Vehicle");
        button4.addActionListener(this);

        button5 = new JButton("Low budget");
        button5.addActionListener(this);

        button6 = new JButton("Rate salon"); //("Add to Cart");
        button6.addActionListener(this);

        button7 = new JButton("Rate vehicle"); //("Add to Cart");
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


        panel2.add(lessThan);


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


        panel1.add(tableShowroom);
        panel1.add(scrollShowroom);


        tableShowroom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                EntityManagerFactory entityManagerFactory;
                entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                try {

                    int row = tableShowroom.rowAtPoint(e.getPoint());
                    int col = tableShowroom.columnAtPoint(e.getPoint());
                    if(col == 0) {
                        String nameSalon = (String) tableShowroom.getValueAt(row, col);
                        // System.out.print(row + " " + col);
                        // System.out.print(nameSalon);
                        TypedQuery<Carshowroom> query = entityManager.createQuery("select c from CarShowroom c where c.salonName = :salonName", Carshowroom.class);
                        query.setParameter("salonName", nameSalon);
                        Carshowroom id_carshowroom = query.getSingleResult();


                        List<Vehicle> Vehicles = vehicleList(id_carshowroom);
                        List<Object []> resultRatingV = vehiclesQuery();


                        int i = 0;
                        for(Object o : resultRatingV) {
                            Vehicle v = (Vehicle) resultRatingV.get(i)[2];
                            System.out.println(resultRatingV.get(i)[0] + " " + resultRatingV.get(i)[1] + " " + v.getId());
                            i++;
                        }

                        tableVehicle.setModel(new ModelVehicle(Vehicles, resultRatingV));
                        panel3.add(tableVehicle);
                        success = true;
                    }
                    else if(col == 4) {
                        String nameSalon = (String) tableShowroom.getValueAt(row, 0);
                        // System.out.print(row + " " + col);
                        // System.out.print(nameSalon);
                        TypedQuery<Carshowroom> query = entityManager.createQuery("select c from CarShowroom c where c.salonName = :salonName", Carshowroom.class);
                        query.setParameter("salonName", nameSalon);
                        Carshowroom id_carshowroom = query.getSingleResult();


                        TypedQuery<Rating> query2 = entityManager.createQuery("select r from Rating r where r.carShowroom = :id_carShowroom", Rating.class);
                        query2.setParameter("id_carShowroom", id_carshowroom);
                        List<Rating> Ratings = query2.getResultList();
                        for(Rating r : Ratings){
                            System.out.print(r.getGrade() + " ");
                        }

                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                frame2 = new JFrame("Rating");
                                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                frame2.setBounds(100, 100, 700, 300);

                                try {
                                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                JPanel tablePanel = new JPanel();
                                tablePanel.setBackground(new Color(0x6FFFFA));

                                tableRating = new JTable();
                                tableRating.setModel(new ModelRating(Ratings));

                                tablePanel.add(scrollCart);
                                tablePanel.setLayout(new FlowLayout());

                                tablePanel.add(tableRating);
                                frame2.add(tablePanel);


                                frame2.setVisible(true);
                            }
                        });

                       // tableRating.setModel(new ModelRating(Ratings));
                        //panel3.add(tableRating);
                        success = true;
                    }
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(frame, "Click on the name!");
                }

                entityManager.close();
                entityManagerFactory.close();
            }
        });

        tableVehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                EntityManagerFactory entityManagerFactory;
                entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                try {
                    int row = tableVehicle.rowAtPoint(e.getPoint());
                    int col = tableVehicle.columnAtPoint(e.getPoint());
                    if(col == 8) {
                        String model = (String) tableVehicle.getValueAt(row, 1);
                        String brand = (String) tableVehicle.getValueAt(row, 0);
                        // System.out.print(row + " " + col);
                        // System.out.print(nameSalon);
                        TypedQuery<Vehicle> query = entityManager.createQuery("select v from Vehicle v where v.brand = :brand AND v.model = :model", Vehicle.class);
                        query.setParameter("brand", brand);
                        query.setParameter("model", model);
                        Vehicle vehicle_id = query.getSingleResult();

                        TypedQuery<Rating> query2 = entityManager.createQuery("select r from Rating r where r.Vehicle = :id_vehicle", Rating.class);
                        query2.setParameter("id_vehicle", vehicle_id);
                        List<Rating> Ratings = query2.getResultList();
                        for(Rating r : Ratings){
                            System.out.print(r.getGrade() + " ");
                        }

                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                frame2 = new JFrame("Rating");
                                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                frame2.setBounds(100, 100, 700, 300);

                                try {
                                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                JPanel tablePanel = new JPanel();
                                tablePanel.setBackground(new Color(0x6FFFFA));

                                tableRating = new JTable();
                                tableRating.setModel(new ModelRating(Ratings));

                                tablePanel.add(scrollCart);
                                tablePanel.setLayout(new FlowLayout());

                                tablePanel.add(tableRating);
                                frame2.add(tablePanel);


                                frame2.setVisible(true);
                            }
                        });

                        // tableRating.setModel(new ModelRating(Ratings));
                        //panel3.add(tableRating);
                        success = true;
                    }
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(frame, "Click on the name!");
                }

                entityManager.close();
                entityManagerFactory.close();
            }
        });

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {


//                String[] opt = {"YES", "NO"};
//                int x = JOptionPane.showOptionDialog(null, "Do you want to save your cart?", "Saving cart",
//                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, opt[0]);
//
//                if (x == 1) {
//
//                }
//

                e.getWindow().dispose();
                System.out.println("Application Closed!");
            }
        });


    }

    public void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame2 = new JFrame("Cart");
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


                //tableCart = new JTable(new ModelCart(cart));
                //tableCart.setModel(new ModelCart(cart));
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
                        cart = new Carshowroom("cart", 10);
                        //tableCart.setModel(new ModelCart(cart));

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
                       // reset();
                        //tableCart.setModel(new ModelCart(cart));

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

           List<Object []> best = BestVehicle();
           Vehicle v = (Vehicle) best.get(0)[0];

           List<Vehicle> bestV = new LinkedList<>();
           bestV.add(0, v);

           tableVehicle.setModel(new ModelVehicle(bestV, vehiclesQuery()));
           panel3.add(tableVehicle);

        } else if (e.getSource() == button2) {

            EntityManagerFactory entityManagerFactory;
            entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            int Selected = tableShowroom.getSelectedRow();
            int i = 0;
            List<Carshowroom> Carshowrooms = carShowroomsList();
            Carshowroom selectedCarshowroom = null;

            for (Carshowroom c : Carshowrooms) {
                if (i == Selected) {
                    selectedCarshowroom = c;
                }
                i++;
            }

            List<Vehicle> VehiclesNEW = vehicleListBrandSorted(selectedCarshowroom);
            tableVehicle.setModel(new ModelVehicle(VehiclesNEW, vehiclesQuery()));
            panel3.add(tableVehicle);


            entityManager.close();
            entityManagerFactory.close();

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

                
                List<Carshowroom> Carshowrooms = carShowroomsList();
                Carshowroom selectedCarshowroom = null;

                for (Carshowroom c : Carshowrooms) {
                    if (i == Selected) {
                        selectedCarshowroom = c;
                    }
                    i++;
                }

                addVehicle(selectedCarshowroom,v);

                List<Vehicle> VehiclesNEW = vehicleList(selectedCarshowroom);
                tableVehicle.setModel(new ModelVehicle(VehiclesNEW, vehiclesQuery()));
                panel3.add(tableVehicle);

                List<Carshowroom> carShowroomsNEW = carShowroomsList();
                List<Object []> resultRatingNEW = carShowroomQuery();
                List<Object []> resultVehicleNEW = vehiclesAmountQuery();

                tableShowroom.setModel(new ModelCarshowroom(carShowroomsNEW, resultRatingNEW, resultVehicleNEW));
                panel1.add(tableShowroom);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(frame, "Wrong Input");
            }
        } else if (e.getSource() == button4) {
            if (success) {

                int selectedFirst = tableShowroom.getSelectedRow();

                int i = 0;
                List<Carshowroom> Carshowrooms = carShowroomsList();
                Carshowroom toRemove = null;
                String newSet = null;
                for (Carshowroom c : Carshowrooms) {
                    if (i == selectedFirst) {
                        toRemove = c;
                    }
                    i++;
                }

                int selectedSecond = tableVehicle.getSelectedRow();
                List<Vehicle> VehiclesNEW = vehicleList(toRemove);
                Vehicle removeVehicle = VehiclesNEW.get(selectedSecond);

                removeVehicle(removeVehicle.getId());

                VehiclesNEW = vehicleList(toRemove);
                tableVehicle.setModel(new ModelVehicle(VehiclesNEW, vehiclesQuery()));
                panel3.add(tableVehicle);

                List<Carshowroom> carShowroomsNEW = carShowroomsList();
                List<Object []> resultRatingNEW = carShowroomQuery();
                List<Object []> resultVehicleNEW = vehiclesAmountQuery();

                tableShowroom.setModel(new ModelCarshowroom(carShowroomsNEW, resultRatingNEW, resultVehicleNEW));
                panel1.add(tableShowroom);
            }

        } else if (e.getSource() == button5) {

            String price = lessThan.getText();
            Double price_  = Double.parseDouble(price);
            vehicleListLowBudget(price_);

            tableVehicle.setModel(new ModelVehicle(vehicleListLowBudget(price_), vehiclesQuery()));
            panel3.add(tableVehicle);


        } else if (e.getSource() == button6) {



                if (success) {
                    String grade = JOptionPane.showInputDialog("Grade ");
                    String description = JOptionPane.showInputDialog("Description ");
                    int grade_ = Integer.parseInt(grade);


                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    Rating r = new Rating(grade_,date,description);

                    int Selected = tableShowroom.getSelectedRow();
                    System.out.print(Selected);
                    int i = 0;

                    List<Carshowroom> Carshowrooms = carShowroomsList();
                    Carshowroom selectedCarshowroom = null;

                    for (Carshowroom c : Carshowrooms) {
                        if (i == Selected) {
                            selectedCarshowroom = c;
                        }
                        i++;
                    }

                    addRatingCarShowroom(selectedCarshowroom, r);


                    List<Carshowroom> carShowroomsNEW = carShowroomsList();
                    List<Object []> resultRatingNEW = carShowroomQuery();
                    List<Object []> resultVehicleNEW = vehiclesAmountQuery();

                    tableShowroom.setModel(new ModelCarshowroom(carShowroomsNEW, resultRatingNEW, resultVehicleNEW));
                    panel1.add(tableShowroom);
            }

        } else if (e.getSource() == button7) {

            if (success) {
                String grade = JOptionPane.showInputDialog("Grade ");
                String description = JOptionPane.showInputDialog("Description ");
                int grade_ = Integer.parseInt(grade);


                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Rating r = new Rating(grade_,date,description);

                int Selected = tableShowroom.getSelectedRow();
                System.out.print(Selected);
                int i = 0;

                List<Carshowroom> Carshowrooms = carShowroomsList();
                Carshowroom selectedCarshowroom = null;

                for (Carshowroom c : Carshowrooms) {
                    if (i == Selected) {
                        selectedCarshowroom = c;
                    }
                    i++;
                }

                int selectedSecond = tableVehicle.getSelectedRow();
                List<Vehicle> VehiclesNEW = vehicleList(selectedCarshowroom);
                Vehicle Vehicle = VehiclesNEW.get(selectedSecond);
                addRatingVehicle(Vehicle, r);

                VehiclesNEW  = vehicleList(selectedCarshowroom);
                List<Object []>  ratings = vehiclesQuery();



                tableVehicle.setModel(new ModelVehicle(VehiclesNEW,ratings));
                panel3.add(tableVehicle);

            }



        }
    }


    List<Object []> vehiclesQuery() {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder3 = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery3 = builder3.createQuery(Object[].class);
        Root<Rating> root3 = criteriaQuery3.from(Rating.class);
        criteriaQuery3.multiselect(builder3.avg(root3.get("grade")), builder3.count(root3.get("grade")), root3.get("Vehicle"));
        criteriaQuery3.groupBy(root3.get("Vehicle"));
        //criteriaQuery.having(builder.isNotNull("carShowroom"));
        Query q = entityManager.createQuery(criteriaQuery3);
        List<Object []> resultRatingV = q.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultRatingV;
    }

    List<Object []> carShowroomQuery() {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Rating> root = criteriaQuery.from(Rating.class);
        criteriaQuery.multiselect(builder.avg(root.get("grade")), builder.count(root.get("grade")), root.get("carShowroom"));
        criteriaQuery.groupBy(root.get("carShowroom"));
        //criteriaQuery.having(builder.isNotNull("carShowroom"));
        Query q = entityManager.createQuery(criteriaQuery);
        List<Object []> resultRating = q.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultRating;
    }

    List<Object []> vehiclesAmountQuery() {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder2 = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery2 = builder2.createQuery(Object[].class);
        Root<Vehicle> root2 = criteriaQuery2.from(Vehicle.class);
        criteriaQuery2.multiselect(builder2.count(root2.get("id_vehicle")),  root2.get("carShowroom"));
        criteriaQuery2.groupBy(root2.get("carShowroom"));
        //criteriaQuery.having(builder.isNotNull("carShowroom"));
        Query q2 = entityManager.createQuery(criteriaQuery2);
        List<Object []> resultVehicle = q2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultVehicle;
    }

    List<Carshowroom> carShowroomsList () {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Carshowroom> query = entityManager.createQuery("select c from CarShowroom c ", Carshowroom.class);
        List<Carshowroom> Carshowrooms = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Carshowrooms;
}

    void addVehicle(Carshowroom carShowroom, Vehicle vehicle){
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vehicle.setCarshowroom(carShowroom);

        entityManager.getTransaction().begin();
        entityManager.persist(vehicle);
        entityManager.getTransaction().commit();

        entityManager.refresh(vehicle);

        entityManager.close();
        entityManagerFactory.close();

    }


    void removeVehicle( long vehicleId){
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Vehicle deleteFinallyDamnIt = entityManager.find(Vehicle.class, vehicleId);
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.remove(deleteFinallyDamnIt);
        t.commit();

       // entityManager.refresh(vehicle);

        entityManager.close();
        entityManagerFactory.close();

    }

    List<Vehicle> vehicleList (Carshowroom id_carshowroom) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Vehicle> query2 = entityManager.createQuery("select v from Vehicle v where v.carShowroom = :id_carShowroom", Vehicle.class);
        query2.setParameter("id_carShowroom", id_carshowroom);
        List<Vehicle> Vehicles = query2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Vehicles;
    }

    void addRatingCarShowroom(Carshowroom carShowroom, Rating rating){
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        rating.setCarshowroom(carShowroom);

        entityManager.getTransaction().begin();
        entityManager.persist(rating);
        entityManager.getTransaction().commit();

        entityManager.refresh(rating);

        entityManager.close();
        entityManagerFactory.close();

    }

    void addRatingVehicle(Vehicle vehicle, Rating rating){
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        rating.setVehicle(vehicle);

        entityManager.getTransaction().begin();
        entityManager.persist(rating);
        entityManager.getTransaction().commit();

        entityManager.refresh(rating);

        entityManager.close();
        entityManagerFactory.close();

    }

    List<Vehicle> vehicleListBrandSorted (Carshowroom id_carshowroom) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Vehicle> query2 = entityManager.createQuery("select v from Vehicle v where v.carShowroom = :id_carShowroom order by v.brand ", Vehicle.class);
        query2.setParameter("id_carShowroom", id_carshowroom);
        List<Vehicle> Vehicles = query2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Vehicles;
    }

    List<Vehicle> vehicleListLowBudget (Double price) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Vehicle> query2 = entityManager.createQuery("select v from Vehicle v where v.price < :price ", Vehicle.class);
        query2.setParameter("price", price);
        List<Vehicle> Vehicles = query2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Vehicles;
    }

    List<Object []> BestVehicle () {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Object []> query2 = entityManager.createQuery("select r.Vehicle, avg(r.grade) from Rating r group by r.Vehicle order by avg(r.grade) DESC ", Object[].class);
        List<Object []> Vehicles = query2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Vehicles;
    }



}

