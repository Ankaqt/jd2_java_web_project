package by.htp.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.bean.News;
import by.htp.controller.command.Command;
import by.htp.service.NewsService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;

public class InsertNewNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session == null) {
			response.sendRedirect("Controller?command=gotosigninpage&message=Please sign in.");
			return;
		}

		Boolean isAuth = (Boolean) session.getAttribute("auth");

		if (isAuth == null || !isAuth) {
			response.sendRedirect("Controller?command=gotosigninpage&message=Please sign in.");
			return;
		}

		String title = request.getParameter("title");
		String brief = request.getParameter("brief");
		String content = request.getParameter("content");

		System.out.println("Title:" + title);
		System.out.println("Brief:" + brief);

		News news = new News(title, brief, content);

		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();

		try {
			newsService.insertNews(news);
			response.sendRedirect("Controller?command=gotomainpage&message=Changes saved successfully");

		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=gotomainpage");
		}

	}

}