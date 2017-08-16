package test.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvice implements MethodBeforeAdvice {

	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		   System.out.println(arg0);
		   System.out.println(arg1);
		   System.out.println(arg2);
		    System.out.println( " ����BeforeAdvice���before����. " );
	}

}
