

import org.hibernate.HibernateException;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //initDatabase();


//        EntityManagerFactory entityManagerFactory;
//        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();



//        Rating r1 = new Rating(5, Date.valueOf("2020-03-12"), "Excellent!");
//        Rating r1_1 = new Rating(4, Date.valueOf("2019-02-02"), "Good enough");
//        Rating r2 = new Rating(2, Date.valueOf("2010-04-01"), "not so good");
//        Rating r3 = new Rating(0, Date.valueOf("2021-01-01"), "worst salon ever!!");
//
//
//        CarShowroom s1 = new CarShowroom("s1", 20);
//        CarShowroom s2 = new CarShowroom("s2", 40);
//        CarShowroom s3 = new CarShowroom("s3", 60);
//
//
//
//        Vehicle v1 = new Vehicle("Mercedes-Benz", "Class E", ItemConditions.USED, 2017, 97000, 2.0, 230000);
//        Vehicle v2 = new Vehicle("Volkswagen", "Golf VI", ItemConditions.NEW, 2010, 99000, 1.4, 33600);
//        Vehicle v3 = new Vehicle("Audi", "A8", ItemConditions.USED, 2018, 83000, 3.0, 248000);
//        Vehicle v4 = new Vehicle("Volkswagen", "Tiguan", ItemConditions.USED, 2016, 79000, 2.0, 75000);
//        Vehicle v5 = new Vehicle("Mercedes-Benz", "Class B", ItemConditions.USED, 2019, 5800, 1.3, 139700);
//        Vehicle v6 = new Vehicle("Volkswagen", "Passat", ItemConditions.NEW, 2013, 229000, 1.9, 20600);
//        Vehicle v7 = new Vehicle("Audi", "A3", ItemConditions.USED, 2012, 123000, 1.2, 8000);
//        Vehicle v8 = new Vehicle("Fiat","126p", ItemConditions.DAMAGED, 1990,504000,0.3, 3200);
//        Vehicle v9 = new Vehicle("Fiat","Punto", ItemConditions.USED, 2005,214000,1.0, 4500);
//        Vehicle v10 = new Vehicle("Seat","Ibiza 4", ItemConditions.NEW, 2014,12,1.4, 13400);
//        Vehicle v11 = new Vehicle("Seat","Leon", ItemConditions.NEW, 2020,5,1.5, 34500);
//        Vehicle v12 = new Vehicle("Opel","Corsa", ItemConditions.USED, 2000,141400,1.1, 7800);
//        Vehicle v13 = new Vehicle("Fiat","Grande Punto", ItemConditions.USED, 2008,114000,1.4, 10000);
//
//        r1.setVehicle(v13);
//        r1_1.setVehicle(v13);
//        r2.setVehicle(v2);
//        r3.setCarShowroom(s1);
//
//        // s1.Rating.add(r3);
////        v2.Rating.add(r2);
////        v13.Rating.add(r3);
////
////        s1.Vehicle.add(v1);
////        s1.Vehicle.add(v2);
////        s1.Vehicle.add(v3);
////
////
////        s2.Vehicle.add(v4);
////        s2.Vehicle.add(v5);
////        s2.Vehicle.add(v6);
////        s2.Vehicle.add(v10);
////
////
////        s3.Vehicle.add(v7);
////        s3.Vehicle.add(v8);
////        s3.Vehicle.add(v9);
////        s3.Vehicle.add(v11);
////        s3.Vehicle.add(v12);
////        s3.Vehicle.add(v13);
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(s1);
//        entityManager.persist(s2);
//        entityManager.persist(s3);
//
//        entityManager.persist(v1);
//        entityManager.persist(v2);
//        entityManager.persist(v3);
//        entityManager.persist(v4);
//        entityManager.persist(v5);
//        entityManager.persist(v6);
//        entityManager.persist(v7);
//        entityManager.persist(v8);
//        entityManager.persist(v9);
//        entityManager.persist(v10);
//        entityManager.persist(v11);
//        entityManager.persist(v12);
//        entityManager.persist(v13);
//
//        entityManager.persist(r1);
//        entityManager.persist(r2);
//        entityManager.persist(r3);
//        entityManager.persist(r1_1);
//
//        entityManager.getTransaction().commit();
//
//        entityManager.refresh(v1);
//        entityManager.refresh(v2);
//        entityManager.refresh(v3);
//        entityManager.refresh(v4);
//        entityManager.refresh(v5);
//        entityManager.refresh(v6);
//        entityManager.refresh(v7);
//        entityManager.refresh(v8);
//        entityManager.refresh(v9);
//        entityManager.refresh(v10);
//        entityManager.refresh(v11);
//        entityManager.refresh(v12);
//        entityManager.refresh(v13);
//
//        entityManager.refresh(s1);
//        entityManager.refresh(s2);
//        entityManager.refresh(s3);
//
//        entityManager.refresh(r1);
//        entityManager.refresh(r2);
//        entityManager.refresh(r3);
//        entityManager.refresh(r1_1);
//
//        entityManager.find(Vehicle.class, 7L).print();
//        System.out.print(entityManager.find(CarShowroom.class, 2L).getSalonName());
//        TypedQuery<CarShowroom> query = entityManager.createQuery("select c from CarShowroom c ", CarShowroom.class);
//        List<CarShowroom> carShowrooms = query.getResultList();
//
//        for(CarShowroom c : carShowrooms){
//            System.out.print(c.getSalonName());
//        }


//        entityManager.close();
//        entityManagerFactory.close();

        new Facade();



    }

    static void initDatabase(){



    }

}
