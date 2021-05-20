package shift_manager_pro.models;

import io.javalin.core.security.Role;


public class User {
    private Long id = null;
    private String email = null;
    private String fullName = null;
    private Role role = null;
    private Long jobId = null;
    private String password;
    private String preferredName = null;
    private String homeAddress = null;

    public User(){

    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public User(String email, String fullName, Role role) {
        this.email = email;
        this.fullName = fullName;
        this.role = role;    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public String getPreferedName(){
        return preferredName;
    }
    public void setPreferedName(String preferredName){
        this.preferredName=preferredName;
    }
    public String getHomeAddress(){
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress){
        this.homeAddress=homeAddress;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", full_name='" + fullName + '\'' +
                ", job_id='" + jobId + '\'' + 
                ", role=" + role + '\'' +
                ", prefered_name='" + preferredName + '\'' +
                ", home_address='" + homeAddress + 
                '}';
    }
}
