package test.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录拦截器，没登录用户不能访问系统
 * @author sfit0867
 *
 */
public class LoginInterceptor extends AbstractInterceptor {  
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
  
        // 取得请求相关的ActionContext实例  
        ActionContext ctx = invocation.getInvocationContext();  
        Map session = ctx.getSession();  
        String user = (String) session.get("LOGIN_USER");  
  
        // 如果没有登陆,都返回重新登陆  
  
        if (user != null && !user.trim().equals("")) {
            return invocation.invoke();  
        }
        ctx.put("tip", "你还没有登录");  
        return Action.LOGIN; 
    }  
  
}  