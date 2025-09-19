package src.main.java.crm.model;

public class Academia extends Franquia {
    private String checkin;

    public Academia(int id, String nome, String cidade, String status, String checkin) {
        super(id, nome, cidade, status);
        this.checkin = checkin;
    }

    public String getCheckin() {
        return this.checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }
}