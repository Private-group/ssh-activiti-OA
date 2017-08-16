package test.dao;
  
import java.util.List; 
import org.hibernate.Query; 
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import test.entity.QingJia; 

 
public class QingJiaDAO  extends  HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(QingJiaDAO.class);
	 
	public void save(QingJia qingJia) {
		log.debug("saving QingJia instance");
		try {
		      getHibernateTemplate().save(qingJia);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
  
	@SuppressWarnings("rawtypes")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding QingJia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from QingJia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
 
	@SuppressWarnings("rawtypes")
	public List findMyShenpi(String currentUserName) {
		 String sql=" select q.* from (select t.qid  from act_qingjia_his t  where t.executor = ?  and qid is not null group by t.qid) h, act_qingjia q where h.qid = q.qid";
		SQLQuery query=getSession().createSQLQuery(sql).addEntity(QingJia.class);
		query.setString(0,currentUserName); 
		return query.list(); 
	}
	
}