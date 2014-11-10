package org.bond.solrj;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QueryTest {
	public static void main(String[] args) {
		try {
			System.out.println("---开始处理---");
			String url = "http://127.0.0.1:8989/solr/book";

			CommonQueryUtil qt = new CommonQueryUtil(url);
			String[] fields = { "id", "summary", "title" };
			List<Map<String, Object>> list = qt.query(fields, "text:OA升级","title:OA升级");
			//List<Map<String, Object>> list = qt.query(fields, 2, 2, "text:抄告");

			for (Map<String, Object> item : list) {
				Iterator<String> iter = item.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Object value = item.get(key);

					System.out.print(key + ":");
					System.out.println(value);
				}
				System.out.println("--------------------------------");
			}

			System.out.print("---結束处理---");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
