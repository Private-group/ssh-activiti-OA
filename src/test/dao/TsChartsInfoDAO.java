package test.dao;
 
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import test.entity.TsChartsInfo; 

/**
 * A data access object (DAO) providing persistence and search support for
 * TsChartsInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see test.entity.TsChartsInfo
 * @author MyEclipse Persistence Tools
 */
public class TsChartsInfoDAO  extends  HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TsChartsInfoDAO.class);
	// property constants
	public static final String CHART_NAME = "chartName";
	public static final String CHART_DESC = "chartDesc";
	public static final String CHART_USE = "chartUse";
	public static final String KEY_FIELD = "keyField";
	public static final String JS_NAME = "jsName";
	public static final String XTYPE = "xtype";

	public void save(TsChartsInfo transientInstance) {
		log.debug("saving TsChartsInfo instance");
		//Transaction transaction=null;
		try {
		      getHibernateTemplate().save(transientInstance);
//			 Session session = getSession();
//			  transaction = session.beginTransaction(); 
//			  session.save(transientInstance); 
//			 transaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
		//	transaction.rollback();
			throw re;
		}
	}

	public void delete(TsChartsInfo persistentInstance) {
		log.debug("deleting TsChartsInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TsChartsInfo findById(java.math.BigDecimal id) {
		log.debug("getting TsChartsInfo instance with id: " + id);
		try {
			TsChartsInfo instance = (TsChartsInfo) getSession().get(
					"test.entity.TsChartsInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsChartsInfo instance) {
		log.debug("finding TsChartsInfo instance by example");
		try {
			List results = getSession()
					.createCriteria("test.entity.TsChartsInfo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TsChartsInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TsChartsInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByChartName(Object chartName) {
		return findByProperty(CHART_NAME, chartName);
	}

	public List findByChartDesc(Object chartDesc) {
		return findByProperty(CHART_DESC, chartDesc);
	}

	public List findByChartUse(Object chartUse) {
		return findByProperty(CHART_USE, chartUse);
	}

	public List findByKeyField(Object keyField) {
		return findByProperty(KEY_FIELD, keyField);
	}

	public List findByJsName(Object jsName) {
		return findByProperty(JS_NAME, jsName);
	}

	public List findByXtype(Object xtype) {
		return findByProperty(XTYPE, xtype);
	}

	public List findAll() {
		log.debug("finding all TsChartsInfo instances");
		try {
			String queryString = "from TsChartsInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsChartsInfo merge(TsChartsInfo detachedInstance) {
		log.debug("merging TsChartsInfo instance");
		try {
			TsChartsInfo result = (TsChartsInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsChartsInfo instance) {
		log.debug("attaching dirty TsChartsInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsChartsInfo instance) {
		log.debug("attaching clean TsChartsInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}