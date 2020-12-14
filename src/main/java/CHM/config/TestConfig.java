/**
 * 
 */
package CHM.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author Group 3
 *
 */
//@Configuration
public class TestConfig {
	
	//not sure if I need these, not sure how to implement
	private static final String DATABASE_DRIVER = "org.h2.Driver";
	private static final String DATABASE_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DATABASE_USERNAME = "sa";
	private static final String DATABASE_PASSWORD = "";
	private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private static final String HIBERNATE_HBM2DDL_AUTO = "create";
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public DataSource dataSource() {
        
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
            .setType(EmbeddedDatabaseType.H2)
            .build();
        return db;
    }
}

