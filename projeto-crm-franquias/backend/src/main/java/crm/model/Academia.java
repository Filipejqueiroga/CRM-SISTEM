package crm.model;

public class Academia extends Franquia {
    private String checkin;

    public Academia(int id, String nome, String cidade, String status, String tipo_negocio, String checkin) {
        super(id, nome, cidade, status, tipo_negocio);
        this.checkin = checkin;
    }

    public String getCheckin() {
        return this.checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }
}