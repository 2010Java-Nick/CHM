package CHM.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import CHM.dao.ProfileDao;
import CHM.dao.ProfileDaoHibernate;
import CHM.util.SessionFactoryUtil;

//configuration can also be done in a spring-beans.xml file
@Configuration
@ComponentScan(value = "CHM")
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig implements WebApplicationInitializer {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void onStartup(ServletContext container) {
		
		System.out.println("creating servlet and web cntainer");
		
		//create the 'root' Spring Application Context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		
		//Manage the life-cycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));
		
		//create dispatcher servlet's context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		
		//register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
	
	}
	
	@Bean
	@Scope(value = "singleton")
	public SessionFactory sessionFactory() {
		return SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	}
	
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	
	/*
	 * @Bean
	 * 
	 * @Scope(value = "prototype") public Logger logger(final InjectionPoint ip) {
	 * return LoggerFactory.getLogger(of(ip.getMethodParameter())
	 * .<Class>map(MethodParameter::getContainingClass) .orElseGet( () ->
	 * ofNullable(ip.getField()) .map(Field::getDeclaringClass) .orElseThrow
	 * (IllegalArgumentException::new))); }
	 */
	
	//Bean wiring

//	 @Bean(autowireCandidate = false) 
//	 public ProfileDao profileDaoHibernate() {
//		 ProfileDaoHibernate profileDao = new ProfileDaoHibernate();
//		 profileDao.setSessionFactory(sessionFactory); return profileDao; 
//	}
	 
	
}
