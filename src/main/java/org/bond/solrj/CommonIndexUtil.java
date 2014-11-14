package org.bond.solrj;

import java.util.List;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

public abstract class CommonIndexUtil {
	SolrServer solrServer = null;

	/**
	 * @param url
	 *            http://localhost:8080/solr <br/>
	 *            http://localhost:8080/solr/book
	 * **/
	public CommonIndexUtil(String url) {
		solrServer = new HttpSolrServer(url);
	}

	/**
	 * Detele lucene by ID
	 * 
	 * @param id
	 */
	public void deleteById(String id) throws Exception {
		solrServer.deleteById(id);
	}

	/**
	 * Detele lucene by ID
	 * 
	 * @param id
	 * @param commitWithinMs
	 *            多少毫秒之内提交
	 */
	public void deleteById(String id, int commitWithinMs) throws Exception {
		solrServer.deleteById(id, commitWithinMs);
	}

	/**
	 * Detele lucene by IDs.
	 * 
	 * @param strings
	 */
	public void deleteById(List<String> strings) throws Exception {
		solrServer.deleteById(strings);
	}

	/**
	 * Detele lucene by IDs.
	 * 
	 * @param strings
	 * @param commitWithinMs
	 *            多少毫秒之内提交
	 */
	public void deleteById(List<String> strings, int commitWithinMs) throws Exception {
		solrServer.deleteById(strings, commitWithinMs);
	}

	/**
	 * Detele lucene by query.
	 * 
	 * @param query
	 *            查询条件
	 */
	public void deleteByQuery(String query) throws Exception {
		solrServer.deleteByQuery(query);
	}

	/**
	 * Detele lucene by query.
	 * 
	 * @param query
	 *            查询条件
	 * @param commitWithinMs
	 *            多少毫秒之内提交
	 */
	public void deleteByQuery(String query, int commitWithinMs) throws Exception {
		solrServer.deleteByQuery(query, commitWithinMs);
	}

	/**
	 * commit
	 */
	public void commit() throws Exception {
		commit(true, true);
	}

	/**
	 * Commit.
	 * 
	 * @param waitFlush
	 *            block until index changes are flushed to disk
	 * @param waitSearcher
	 *            block until a new searcher is opened and registered as the
	 *            main query searcher, making the changes visible
	 */
	public void commit(boolean waitFlush, boolean waitSearcher) throws Exception {
		solrServer.commit(waitFlush, waitSearcher);
	}

	/**
	 * optimize
	 */
	public void optimize() throws Exception {
		optimize(true, true);
	}

	/**
	 * When controlling the optimizing action at client side, finally execute
	 * optimizing.
	 * 
	 * @param waitFlush
	 *            block until index changes are flushed to disk
	 * @param waitSearcher
	 *            block until a new searcher is opened and registered as the
	 *            main query searcher, making the changes visible
	 */
	public void optimize(boolean waitFlush, boolean waitSearcher) throws Exception {
		solrServer.optimize(waitFlush, waitSearcher);
		commit(waitFlush, waitSearcher);
	}
}
