package shift_manager_pro.models;

import io.javalin.core.security.Role;


public class User {
    private Long id = null;
    private String email = null;
    private String name = null;
    private Role role = null;
    private Integer standard_working_hour = 0;
    private Long job_id = null;
    private String preferred_name = null;
    private String home_address = null;
    private String phone_number = null;
    private Double current_working_hour = 0.0;
    private String password;

    public User(String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;   
    }

    public User(){ 
    }
    public Double getCurrent_working_hour() {
        return current_working_hour;
    }

    public void setCurrent_working_hour(Double current_working_hour) {
        this.current_working_hour = current_working_hour;
    }
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public Integer getStandard_working_hour() {
        return standard_working_hour;
    }

    public void setStandard_working_hour(Integer standard_working_hour) {
        this.standard_working_hour = standard_working_hour;
    }

    public String getPreferred_name() {
        return preferred_name;
    }

    public void setPreferred_name(String preferred_name) {
        this.preferred_name = preferred_name;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }


    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", job_id='" + job_id + '\'' + 
                ", role=" + role + '\'' +
                ", prefered_name='" + preferred_name + '\'' +
                ", home_address='" + home_address + '\'' +
                ", standard_working_hour='" + standard_working_hour + '\'' +
                ", current_working_hour='" + current_working_hour +
                '}';
    }
}
