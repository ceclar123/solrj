package org.bond.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bond.util.DateUtil;
import org.bond.bean.DocBean;
import org.bond.solrj.IndexBeanUtil;

public class AddDocument extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5802252990615405008L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String summary = req.getParameter("summary");
			String author = req.getParameter("author");
			Date date = DateUtil.StringToDate(req.getParameter("date"), "yyyy-MM-dd");

			String title = req.getParameter("title");
			String keywords = req.getParameter("keywords");
			String content = req.getParameter("content");

			DocBean bean = new DocBean();
			bean.setId(id);
			bean.setName(name);
			bean.setSummary(summary);
			bean.setAuthor(author);
			bean.setDate(date);

			bean.setTitle(title);
			bean.setKeywords(keywords);
			bean.setContent(content);

			String url = req.getServletContext().getInitParameter("SOLR_URL");
			IndexBeanUtil index = new IndexBeanUtil(url);
			index.addBean(bean);

			req.setAttribute("msg", "操作成功");
			req.getServletContext().getRequestDispatcher("/document/addDoc.jsp").forward(req, resp);
		} catch (Exception ex) {
			ex.printStackTrace();

			String path = req.getContextPath();
			String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/document/addDoc.jsp";

			System.out.println(basePath);
			req.setAttribute("msg", "操作失败");
			req.getServletContext().getRequestDispatcher(basePath).forward(req, resp);

			// resp.sendRedirect("addDoc.jsp");
		}
	}
}
