package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ProductAPI")
public class ProductAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Product itemObj = new Product();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static Map getParasMap(HttpServletRequest request)
    {
	    Map<String, String> map = new HashMap<String, String>();
	    try
	    {
		    Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		    String queryString = scanner.hasNext() ?
		    scanner.useDelimiter("\\A").next() : "";
		    scanner.close();
		    String[] params = queryString.split("&");
		    for (String param : params)
		    {
		    	
		    	String[] p = param.split("=");
		    	map.put(p[0], p[1]);
		    	}
	    }
	    catch (Exception e)
	    {
	    }
	    return map;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = itemObj.insertProduct(request.getParameter("pname"),
				request.getParameter("ptype"),
				request.getParameter("description"),
				request.getParameter("price"),
				request.getParameter("quantity"),
				request.getParameter("projId"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = itemObj.updateProduct(paras.get("pid").toString(),
		paras.get("pname").toString(),
		paras.get("ptype").toString(),
		paras.get("description").toString(),
		paras.get("price").toString(),
		paras.get("quantity").toString(),
		paras.get("projId").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = itemObj.deleteProduct(paras.get("pid").toString());
		response.getWriter().write(output);
	}

}
