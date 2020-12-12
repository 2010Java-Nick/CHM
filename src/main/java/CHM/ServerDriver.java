package CHM;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ServerDriver {
	
	public static void main(String[] args) throws LifecycleException, ServletException {
			
		System.out.println("On");
		
		Tomcat server = new Tomcat();
		server.setPort(9091);
		server.getConnector();
		server.addWebapp("", new File("./").getAbsolutePath());
		
		System.out.println("server started..");
		server.start();
		
		System.out.println("server awaiting..");
		server.getServer().await();
		
		System.out.println(".......");
	}

}
