package com.lab9;

import com.lab9.model.Carshowroom;
import com.lab9.model.Rating;
import com.lab9.model.Vehicle;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Database {

    public List<Vehicle> vehicleList (long id_carshowroom) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Carshowroom> temp_list = carShowroomsList();
        Carshowroom toFind = null;
        for(Carshowroom c : temp_list ){
            if( c.getId_carshowroom() == id_carshowroom) {
                toFind = c;
            }
        }


        TypedQuery<Vehicle> query2 = entityManager.createQuery("select v from Vehicle v where v.carshowroom = :id_carshowroom", Vehicle.class);
        query2.setParameter("id_carshowroom", toFind);
        List<Vehicle> Vehicles = query2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Vehicles;
    }

    public List<Vehicle> vehicleList () {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Vehicle> query2 = entityManager.createQuery("select v from Vehicle v", Vehicle.class);
        List<Vehicle> Vehicles = query2.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Vehicles;
    }

    public List<Carshowroom> carShowroomsList () {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Carshowroom> query = entityManager.createQuery("select c from Carshowroom c ", Carshowroom.class);
        List<Carshowroom> Carshowrooms = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return Carshowrooms;
    }

    public int getMaxCars (long id) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Integer> query = entityManager.createQuery("select c.maxcars from Carshowroom c where id_carshowroom = :id ", Integer.class);
        query.setParameter("id", id);

        int maxCars = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return maxCars;
    }

    public List<Object []> vehiclesQuery() {
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

    public List<Object []> carShowroomQuery() {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Rating> root = criteriaQuery.from(Rating.class);
        criteriaQuery.multiselect(builder.avg(root.get("grade")), builder.count(root.get("grade")), root.get("carshowroom"));
        criteriaQuery.groupBy(root.get("carshowroom"));
        //criteriaQuery.having(builder.isNotNull("carShowroom"));
        Query q = entityManager.createQuery(criteriaQuery);
        List<Object []> resultRating = q.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultRating;
    }

    public Double BestVehicle (long id) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Vehicle> temp_list = vehicleList();
        Vehicle toFind = null;
        for(Vehicle v : temp_list ){
            if(v.getId_vehicle() == id) {
                toFind = v;
            }
        }

        TypedQuery<Double> query2 = entityManager.createQuery("select avg(r.grade) from Rating r where r.Vehicle = :id ", Double.class);
        query2.setParameter("id", toFind);

        Double avg = query2.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return avg;
    }

    public Double BestSalon (long id) {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Carshowroom> temp_list = carShowroomsList();
        Carshowroom toFind = null;
        for(Carshowroom c : temp_list ){
            if( c.getId_carshowroom() == id) {
                toFind = c;
            }
        }

        TypedQuery<Double> query2 = entityManager.createQuery("select avg(r.grade) from Rating r where r.carshowroom = :id ", Double.class);
        query2.setParameter("id", toFind);

        Double avg = query2.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return avg;
    }


    public void ExportVehicles () {
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT v INTO OUTFILE 'file.csv'\n" +
                "    FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'\n" +
                "    LINES TERMINATED BY '\\n'\n" +
                "    FROM Vehicle v;");

        entityManager.close();
        entityManagerFactory.close();

    }
}
