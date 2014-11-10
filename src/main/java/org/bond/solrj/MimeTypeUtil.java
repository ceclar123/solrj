package org.bond.solrj;

import java.util.HashMap;

public class MimeTypeUtil {

	private static HashMap<String, String> mimeMap = null;
	static {
		mimeMap = new HashMap<String, String>();
		mimeMap.put("xml", "text/xml");
		mimeMap.put("csv", "text/csv");
		mimeMap.put("json", "application/json");
		mimeMap.put("pdf", "application/pdf");
		mimeMap.put("rtf", "text/rtf");
		mimeMap.put("html", "text/html");
		mimeMap.put("htm", "text/html");
		mimeMap.put("doc", "application/msword");
		mimeMap.put("docx",
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		mimeMap.put("ppt", "application/vnd.ms-powerpoint");
		mimeMap.put("pptx",
				"application/vnd.openxmlformats-officedocument.presentationml.presentation");
		mimeMap.put("xls", "application/vnd.ms-excel");
		mimeMap.put("xlsx",
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		mimeMap.put("odt", "application/vnd.oasis.opendocument.text");
		mimeMap.put("ott", "application/vnd.oasis.opendocument.text");
		mimeMap.put("odp", "application/vnd.oasis.opendocument.presentation");
		mimeMap.put("otp", "application/vnd.oasis.opendocument.presentation");
		mimeMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
		mimeMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet");
		mimeMap.put("txt", "text/plain");
		mimeMap.put("log", "text/plain");
	}

	/**
	 * @param suffixName
	 *            文件后缀名 doc,docx
	 * */
	public static String getMimeType(String suffixName) {
		return mimeMap.get(suffixName);
	}
}
