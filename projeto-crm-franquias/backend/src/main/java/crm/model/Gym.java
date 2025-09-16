public class Gym extends Franchise {
    String checkin;

    public Gym(int id, String name, String city, String status, String checkin){
        super(id, name, city, status);
        this.checkin = checkin;
    }

    public String getCheckin(){
        return this.checkin;
    }
    public void setCheckin(String checkin){
        this.checkin = checkin;
    }

}
