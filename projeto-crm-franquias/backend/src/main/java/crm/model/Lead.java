public class Lead {

    private String name;
    private String phone_number;
    private String status;

    public Lead(String name, String phone_number, String status) {
        this.name = name;
        this.phone_number = phone_number;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}