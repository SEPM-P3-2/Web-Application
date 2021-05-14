package shift_manager_pro.models;

public class Job {

  private Long id = null;
  private String name = null;

  public Job() {}

  public Job(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Job [id=" + id + ", name=" + name + "]";
  }
}
