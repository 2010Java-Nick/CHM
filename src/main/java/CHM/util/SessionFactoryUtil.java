package CHM.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import CHM.model.Interest;
import CHM.model.Match;
import CHM.model.Message;
import CHM.model.Payment;
import CHM.model.Photo;
import CHM.model.Profile;
import CHM.model.User;

public class SessionFactoryUtil {
	
	private static SessionFactoryUtil sfu;
	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private SessionFactoryUtil() {
		
		if (sessionFactory == null) {
			
			
			Map<String, String> settings = new HashMap<>();
			settings.put("hibernate.connection.url", System.getenv("CHM_URL"));
			settings.put("hibernate.connection.username", System.getenv("CHM_USERNAME"));
			settings.put("hibernate.connection.password", System.getenv("CHM_PASSWORD"));
			settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
			settings.put("hibernate.connection.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			//settings.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//settings.put("hibernate.connection.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
			settings.put("hibernate.show_sql", "true");
			settings.put("hibernate.format_sql", "true");
			//settings.put("hibernate.hbm2ddl.auto", "create");
			
			
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
			
			Metadata metadata = new MetadataSources(standardRegistry)
						.addAnnotatedClass(User.class)
						.addAnnotatedClass(Profile.class)
						.addAnnotatedClass(Payment.class)
						.addAnnotatedClass(Message.class)
						.addAnnotatedClass(Match.class)
						.addAnnotatedClass(Interest.class)
						.addAnnotatedClass(Photo.class)
						.getMetadataBuilder()
						.build();
			
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			
		}
		
	}
	
	public static SessionFactoryUtil getSessionFactoryUtil() {
		if (sfu == null) {
			sfu = new SessionFactoryUtil();
		}
		
		return sfu;
	}

}
