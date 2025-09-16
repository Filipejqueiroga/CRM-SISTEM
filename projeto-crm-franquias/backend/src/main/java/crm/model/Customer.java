public class Customer {

    private int id;
    private String name;
    private String phone_number;
    private String plan_type;
    private int franchise_id;

    public Customer(int id, String name, String phone_number, String plan_type, int franchise_id) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.plan_type = plan_type;
        this.franchise_id = franchise_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public int getFranchise_id() {
        return franchise_id;
    }

    public void setFranchise_id(int franchise_id) {
        this.franchise_id = franchise_id;
    }
}