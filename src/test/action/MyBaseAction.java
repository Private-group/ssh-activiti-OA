package test.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有antion继承此类
 * @author sfit0867
 *
 */
public class MyBaseAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String LOGIN_USER="LOGIN_USER";
	
	public String getCurrentUserName(){
		return (String) ActionContext.getContext().getSession().get(LOGIN_USER);
	}
	
   public void setCurrentUserName(String userName){
	ActionContext.getContext().getSession().put(LOGIN_USER, userName); 
	}
   
   public void removeCurrentUserName(){
	   ActionContext.getContext().getSession().remove(LOGIN_USER);
   }
   
	// 获取Parameter
	@SuppressWarnings({ "rawtypes", "static-access" })
	public String getParameter(String paramName) {
		 Map request =   (Map) ActionContext.getContext().getContext().get("request");  
		return  (String) request.get(paramName);
	}
	
	// 获取Parameter 返回map
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public Map getParameters(String [] paramNames) {
		 Map request =   (Map) ActionContext.getContext().getContext().get("request");  
		 Map map=new HashMap();
		 if(paramNames!=null&&paramNames.length!=0){
			 for (String paramName : paramNames) {
				 map.put(paramName, request.get(paramName));
			}
		 }
		return  map ;
	}

}
