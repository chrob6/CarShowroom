import javax.persistence.*;
import java.sql.Date;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id_rating;

    private int grade;
    private java.sql.Date date;
    String description;

    @ManyToOne
    @JoinColumn(name = "id_vehicle")
    private Vehicle Vehicle;

    @ManyToOne
    @JoinColumn(name = "id_carshowroom")
    //@JoinColumn(name = "carShowroom_id")
    private Carshowroom carshowroom;

    public Carshowroom getCarshowroom() {
        return carshowroom;
    }

    public Rating(int _grade, java.sql.Date _date, String _description ) {
        if(_grade < 0 || _grade > 5) {
            System.out.print("WRONG GRADE");
        }
        this.grade = _grade;
        this.date = _date;
        this.description = _description;
    }

    public Rating() {

    }

    public void setCarshowroom(Carshowroom carshowroom) {
        this.carshowroom = carshowroom;
    }

    public long getId_rating() {
        return id_rating;
    }

    public int getGrade() {
        return grade;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Vehicle getVehicle() {
        return Vehicle;
    }


    public void setId_rating(long id_rating) {
        id_rating = id_rating;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVehicle(Vehicle vehicle) {
        Vehicle = vehicle;
    }

    public void setId_carsShowroom(int id_carsShowroom) {
        id_carsShowroom = id_carsShowroom;
    }
}
