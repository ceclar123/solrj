package org.bond.solrj;

import java.io.File;

public class IndexTest {

	public static void main(String[] args) {
		try {
			System.out.println("---开始处理---");
			String url = "http://127.0.0.1:8989/solr/book";
			IndexDocumentUtil dd = new IndexDocumentUtil(url);

			dd.indexFolder(new File("D:\\需求\\黄浦区政府"));
			// dd.indexFile("D:\\需求\\黄浦区政府\\~$区政府文档系统升级项目实施方案V1.7.doc");
			dd.commit();

			System.out.print("---結束处理---");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
