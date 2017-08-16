package test;
 
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    
	  public static void main(String[] args) {
			  ApplicationContext ctx =  new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		 
				 
				 // 读取配置文件
			        Configuration cfg = new Configuration().configure();
			        // 创建SchemaExport对象
			        SchemaExport export = new SchemaExport(cfg);
			        // 创建数据库表
			        export.create(true, true);
			        
			        
	}

}
