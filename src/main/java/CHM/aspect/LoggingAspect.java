package CHM.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("within(CHM..*Profile*)")
	public void pointcutForProfileMethods() {
	}
	
	//Types of Advice - Before, After, Around, AfterThrowing, AfterReturning
	@AfterReturning(value = "pointcutForProfileMethods()", returning = "returnValue")
	public void logAfterReturningFromProfileMethod(JoinPoint jp, Object returnValue) {
		System.out.println("Returned from Profile Method: " + jp.getSignature());
		if (returnValue != null) {
			System.out.println("Returning value: " + returnValue.toString());
		}
		log.info("Returned from Profile Method");
	}
	
	@AfterThrowing(value = "pointcutForProfileMethods()", throwing = "exception")
	public void logAfterThrowingFromProfileMethod(JoinPoint jp, Throwable exception) {
		System.out.println("Exception " + exception + "thrown from " + jp.getSignature());
		exception.printStackTrace();
		log.warn("Exception " + exception + "thrown from " + jp.getSignature(), exception);
		
	}
}
