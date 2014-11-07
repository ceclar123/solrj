package org.bond.solrj;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;

public class IndexFile {
	private SolrServer solrServer = null;

	/**
	 * @param url
	 *            http://localhost:8080/solr
	 * **/
	public IndexFile(String url) {
		solrServer = new HttpSolrServer(url);
	}

	public boolean index(File file) throws Exception {
		ContentStreamUpdateRequest req = new ContentStreamUpdateRequest(
				"/update/extract");
		String filePath = file.getAbsolutePath();
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		if (index < 0 || index > fileName.length() - 1) {
			throw new Exception("文件名格式不正确");
		}
		String suffixName = fileName.substring(index + 1);
		String mimeType = MimeTypeUtil.getMimeType(suffixName);
		
		if (mimeType == null || mimeType.length() == 0) {
			throw new Exception("该文件类型不支持");
		}

		req.addFile(file, mimeType);
		req.setParam("literal.id",fileName+ String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()));
		req.setParam("literal.title", fileName);
		req.setParam("literal.summary", filePath);
		req.setParam("fmap.content", "content");
		req.setMethod(METHOD.POST);
		req.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

		solrServer.request(req);

		return true;
	}

	public boolean isExists(String id) {
		boolean flag = false;
		SolrQuery query = new SolrQuery();

		return flag;
	}
}
