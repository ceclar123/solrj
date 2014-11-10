package org.bond.solrj;

import java.util.Collection;

import org.apache.log4j.Logger;

public class IndexBeanUtil extends CommonIndexUtil {
	private static final Logger LOG = Logger.getLogger(IndexBeanUtil.class);

	/**
	 * @param url
	 *            http://localhost:8080/solr <br/>
	 *            http://localhost:8080/solr/book
	 * **/
	public IndexBeanUtil(String url) {
		super(url);
	}

	/**
	 * add bean
	 * 
	 * @param bean
	 */
	public void addBean(Object bean) throws Exception {
		solrServer.addBean(bean);
	}

	/**
	 * add bean
	 * 
	 * @param bean
	 * @param commitWithinMs
	 */
	public void addBean(Object bean, int commitWithinMs) throws Exception {
		solrServer.addBean(bean, commitWithinMs);
	}

	/**
	 * add beans
	 * 
	 * @param beans
	 */
	public void addBeans(Collection<Object> beans) throws Exception {
		solrServer.addBeans(beans);
	}

	/**
	 * add beans
	 * 
	 * @param beans
	 * @param commitWithinMs
	 */
	public void addBeans(Collection<Object> beans, int commitWithinMs) throws Exception {
		solrServer.addBeans(beans, commitWithinMs);
	}
}
