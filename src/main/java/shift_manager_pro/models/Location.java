package shift_manager_pro.models;


public class Location {
    private Long id = null;
    private String name = null;
    private String address = null;
    private String telephone= null;

    public Location(){

    }

    public Location(String name, String address, String telephone) {
        this.address = address;
        this.name = name;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                '}';
    }

}
