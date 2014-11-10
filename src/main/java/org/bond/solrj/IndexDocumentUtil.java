package org.bond.solrj;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;

public class IndexDocumentUtil extends CommonIndexUtil {
	private static final Logger LOG = Logger.getLogger(IndexDocumentUtil.class);

	/**
	 * @param url
	 *            http://localhost:8080/solr <br/>
	 *            http://localhost:8080/solr/book
	 * **/
	public IndexDocumentUtil(String url) {
		super(url);
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
				if (item.isDirectory()) {
					indexFolder(item);
				} else if (item.isFile()) {
					indexFile(item);
				}
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

		int index = fileName.lastIndexOf(".");
		if (index < 0 || index > fileName.length() - 1) {
			// throw new Exception("文件名格式不正确");
			LOG.info("文件名格式不正确:" + fileName);
			return;
		}

		String suffixName = fileName.substring(index + 1);
		String mimeType = MimeTypeUtil.getMimeType(suffixName);

		if (mimeType == null || mimeType.length() == 0) {
			// throw new Exception("该文件类型不支持");
			LOG.info("该文件类型不支持:" + fileName);
			return;
		}

		// String.valueOf(java.util.Calendar.getInstance().getTimeInMillis());
		String id = filePath;
		solrServer.deleteById(id);

		ContentStreamUpdateRequest req = new ContentStreamUpdateRequest("/update/extract");
		req.addFile(file, mimeType.toLowerCase());
		req.setParam("literal.id", id);
		req.setParam("literal.title", fileName);
		req.setParam("literal.summary", filePath);
		req.setParam("fmap.content", "content");
		req.setMethod(METHOD.POST);
		req.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

		solrServer.request(req);

		LOG.info("index done:" + filePath);
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

}
