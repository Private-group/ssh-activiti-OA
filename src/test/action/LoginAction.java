package test.action;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;

import test.service.WorkFlowService;

public   class LoginAction  extends MyBaseAction  {
 
	private static final long serialVersionUID = 1L;
	private String name; 
	
	private WorkFlowService workFlowService;
	
//      private void hehe(){
//    	    ActionContext context = ActionContext.getContext();   
//    	    Map request = (Map)context.get("request");  
//    	    Map session = context.getSession();  
//    	    Map application = context.getApplication();  
//    	    request.put("greeting", "hehe");
//    	    session.put("user", "userObj");//
//    	    application.put("counter", 11); 
//    	    /**  jsp 中取值 ${sessionScope.user.username}  ${requestScope.greeting}  ${applicationScope.counter}
//    	     */
//      }
  
	public String login() throws Exception {
	    
	    String userName =this.getCurrentUserName();
	    
	    //防止用户登录后在主页刷新页面，导致name字段没值
	    if(!(userName!=null&&!userName.trim().equals(""))){
//	    	User user = workFlowService.getUserById(name);
	    	this.setCurrentUserName(name);
	    }
	    userName =this.getCurrentUserName();
	    if(userName==null||userName.trim().equals("")){
	    	return INPUT;
	    }
	    System.out.println("登录成功！！！");
		return SUCCESS;
	}
	 
	public String  logout() throws Exception {
		    this.removeCurrentUserName();
		    System.out.println("退出成功！！！");
		    return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
