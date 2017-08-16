package test.dao;
 
import java.util.List; 
import org.hibernate.Query; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.mail.util.QEncoderStream;
 
import test.entity.QingJiaHis;

 
public class QingJiaHisDAO  extends  HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(QingJiaHisDAO.class);
	 
	public void save(QingJiaHis  qingJiaHis) {
		log.debug("saving QingJiaHis instance");
		try {
		      getHibernateTemplate().save(qingJiaHis);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
  
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding QingJiaHis instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from QingJiaHis as model where model."
					+ propertyName + "= ? order by dataTm asc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
 
}