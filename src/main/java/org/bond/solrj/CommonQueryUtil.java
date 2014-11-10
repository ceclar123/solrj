package org.bond.solrj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

public class CommonQueryUtil {
	SolrServer solrServer = null;

	/**
	 * @param url
	 *            http://localhost:8080/solr <br/>
	 *            http://localhost:8080/solr/book
	 * **/
	public CommonQueryUtil(String url) {
		solrServer = new HttpSolrServer(url);
	}

	/**
	 * Query.
	 * 
	 * @param params
	 *            查询参数
	 * @param fields
	 *            返回字段
	 * @return
	 */
	public List<Map<String, Object>> query(SolrParams params, String[] fields) throws Exception {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		SolrDocumentList documents = solrServer.query(params).getResults();
		Iterator<SolrDocument> iter = documents.iterator();
		while (iter.hasNext()) {
			SolrDocument doc = iter.next();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String field : fields) {
				map.put(field, doc.getFieldValue(field));
			}
			results.add(map);
		}

		return results;
	}

	/**
	 * Query.
	 *
	 * @param fields
	 *            返回字段
	 * @param fq
	 *            title:Oracle
	 * @return
	 */
	public List<Map<String, Object>> query(String[] fields, String... fq) throws Exception {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		SolrQuery query = new SolrQuery();
		for (String item : fq) {
			query.setQuery(item);
		}

		SolrDocumentList documents = solrServer.query(query).getResults();
		Iterator<SolrDocument> iter = documents.iterator();
		while (iter.hasNext()) {
			SolrDocument doc = iter.next();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String field : fields) {
				map.put(field, doc.getFieldValue(field));
			}
			results.add(map);
		}

		return results;
	}

	/**
	 * Query.
	 *
	 * @param fields
	 *            返回字段
	 * @param fq
	 *            title:Oracle
	 * @return
	 */
	public List<Map<String, Object>> query(String[] fields, int start, int count, String... fq) throws Exception {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		SolrQuery query = new SolrQuery();
		for (String item : fq) {
			query.setQuery(item);
		}
		query.setStart(start);
		query.setRows(count);

		SolrDocumentList documents = solrServer.query(query).getResults();
		Iterator<SolrDocument> iter = documents.iterator();
		while (iter.hasNext()) {
			SolrDocument doc = iter.next();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String field : fields) {
				map.put(field, doc.getFieldValue(field));
			}
			results.add(map);
		}

		return results;
	}

	/**
	 * Query.
	 *
	 * @param fields
	 *            返回字段
	 * @param sorts
	 *            排序字段:Map<String, String>:("title","1")
	 * @param fq
	 *            title:Oracle
	 * @return
	 */
	public List<Map<String, Object>> query(String[] fields, Map<String, String> sorts, int start, int count, String... fq) throws Exception {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		SolrQuery query = new SolrQuery();
		for (String item : fq) {
			query.setQuery(item);
		}
		query.setStart(start);
		query.setRows(count);

		Iterator<String> iter = sorts.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String value = sorts.get(key);

			if (value.equals("1")) {
				query.addSort(key, SolrQuery.ORDER.asc);
			} else {
				query.addSort(key, SolrQuery.ORDER.desc);
			}
		}

		SolrDocumentList documents = solrServer.query(query).getResults();
		Iterator<SolrDocument> iterRtn = documents.iterator();
		while (iterRtn.hasNext()) {
			SolrDocument doc = iterRtn.next();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String field : fields) {
				map.put(field, doc.getFieldValue(field));
			}
			results.add(map);
		}

		return results;
	}
}
