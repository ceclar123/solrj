package org.bond.solrj;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class IndexTextUtil {
	private static final Logger LOG = Logger.getLogger(IndexTextUtil.class);
	private SolrServer solrServer = null;

	/**
	 * @param url
	 *            http://localhost:8080/solr <br/>
	 *            http://localhost:8080/solr/book
	 * **/
	public IndexTextUtil(String url) {
		solrServer = new HttpSolrServer(url);
	}

	public void addBean() throws Exception {
		solrServer.addBean(null);
	}
}
