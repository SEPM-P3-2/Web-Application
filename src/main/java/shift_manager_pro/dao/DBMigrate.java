package shift_manager_pro.dao;

import org.flywaydb.core.Flyway;

/**
 * Utility Class to migrate a DB for users using only IDE.
 */
public class DBMigrate {

  public static void main(String[] args) {
    //Get Flyway instance
    Flyway flyway = Flyway
      .configure()
      .dataSource(DBUtils.DB_DEFAULT_URI, "sa", "")
      .load();
    //Clean testing DB before each test to make sure we have a consistent state
    flyway.clean();
    //Set up db
    flyway.migrate();
  }
}
