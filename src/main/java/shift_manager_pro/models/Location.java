package shift_manager_pro.models;


public class Location {
    private Long loc_id = null;
    private String name = null;
    private String address = null;
    private int telephone= 0;

    public Location(){

    }

    public Location(String name, String address, int telephone) {
        this.address = address;
        this.name = name;
        this.telephone = telephone;
    }

    public Long getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(Long loc_id) {
        this.loc_id = loc_id;
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

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Location{" +
                "loc_id=" + loc_id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                '}';
    }

}
