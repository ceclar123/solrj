package org.bond.solrj;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.common.util.NamedList;

public class CoreAdminRequestUtil {
	public static final String SOLR_URL = "http://172.168.63.233:8983/solr";
	public static final String DEFAULT_CORE_NAME = "collection1";
	public static final String NEW_CORE_NAME = "demo";

	/**
	 * 
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void reloadCore(String coreName) throws SolrServerException,
			IOException {
		// 连接solr服务器
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);
		CoreAdminRequest.reloadCore(coreName, server);
		System.out.println("重新加载" + coreName + "成功");
	}

	/**
	 * 
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void createCore(String coreName) throws SolrServerException,
			IOException {
		// 连接solr服务器
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);

		// 获得solr.xml配置好的cores作为默认，获得默认core的路径
		NamedList<Object> list = CoreAdminRequest
				.getStatus(DEFAULT_CORE_NAME, server).getCoreStatus()
				.get(DEFAULT_CORE_NAME);
		String path = (String) list.get("instanceDir");

		// 获得solrhome,也就是solr放置索引的主目录
		String solrHome = path.substring(0, path.indexOf(DEFAULT_CORE_NAME));

		// 建立新core所在文件夹
		File corePath = new File(solrHome + File.separator + NEW_CORE_NAME);
		if (!corePath.exists()) {
			corePath.mkdir();
		}
		// 建立新core下的conf文件夹
		File confPath = new File(corePath.getAbsolutePath() + File.separator
				+ "conf/");
		if (!confPath.exists()) {
			confPath.mkdir();
		}
		// 将默认core下conf里的solrconfig.xml和schema.xml拷贝到新core的conf下。这步是必须的
		// 因为新建的core solr会去其conf文件夹下找这两个文件，如果没有就会报错，新core则不会创建成功
		FileUtils.copyFile(new File(path + "conf/solrconfig.xml"), new File(
						confPath.getAbsolutePath() + File.separator
								+ "solrconfig.xml"));
		FileUtils.copyFile(new File(path + "conf/schema.xml"), new File(
				confPath.getAbsolutePath() + File.separator + "schema.xml"));

		// 创建新core,同时会把新core的信息添加到solr.xml里
		CoreAdminRequest.createCore(coreName, coreName, server);
	}
}
