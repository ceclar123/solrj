package org.bond.solrj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

public class IndexDocumentUtil {
	private static final Logger LOG = Logger.getLogger(IndexDocumentUtil.class);
	private SolrServer solrServer = null;
	private ContentStreamUpdateRequest req = null;

	/**
	 * @param url
	 *            http://localhost:8080/solr <br/>
	 *            http://localhost:8080/solr/book
	 * **/
	public IndexDocumentUtil(String url) {
		solrServer = new HttpSolrServer(url);
		req = new ContentStreamUpdateRequest("/update/extract");
	}

	/**
	 * Index Folder
	 * 
	 * @param folder
	 *            文件夹
	 */
	public void indexFolder(File folder) throws Exception {
		if (folder.isDirectory()) {
			for (File item : folder.listFiles()) {
				indexFile(item);
			}
		} else if (folder.isFile()) {
			indexFile(folder);
		}
	}

	/**
	 * Index Folder
	 * 
	 * @param folderName
	 *            文件夹名字
	 */
	public void indexFolder(String folderName) throws Exception {
		File folder = new File(folderName);
		indexFolder(folder);
	}

	/**
	 * Index File
	 * 
	 * @param file
	 *            文件
	 */
	public void indexFile(File file) throws Exception {
		String filePath = file.getAbsolutePath();
		String fileName = file.getName();

		LOG.info(fileName);

		int index = fileName.lastIndexOf(".");
		if (index < 0 || index > fileName.length() - 1) {
			throw new Exception("文件名格式不正确");
		}
		String suffixName = fileName.substring(index + 1);
		String mimeType = MimeTypeUtil.getMimeType(suffixName);

		if (mimeType == null || mimeType.length() == 0) {
			throw new Exception("该文件类型不支持");
		}

		String id = fileName + String.valueOf(java.util.Calendar.getInstance().getTimeInMillis());
		solrServer.deleteById(id);

		req.addFile(file, mimeType);
		req.setParam("literal.id", id);
		req.setParam("literal.title", fileName);
		req.setParam("literal.summary", filePath);
		req.setParam("fmap.content", "content");
		req.setMethod(METHOD.POST);
		req.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

		solrServer.request(req);
	}

	/**
	 * Index File
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void indexFile(String fileName) throws Exception {
		File file = new File(fileName);
		indexFile(file);
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
