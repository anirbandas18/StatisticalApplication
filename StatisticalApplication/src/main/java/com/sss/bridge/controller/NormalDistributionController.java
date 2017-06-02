package com.sss.bridge.controller;

//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sss.bridge.bean.NormalDistribution;
import com.sss.bridge.exception.NormalDistributionException;
import com.sss.bridge.service.NormalDistributionService;

/**
 * Servlet implementation class NormalDistribution
 */
@WebServlet("/NormalDistributionController")
public class NormalDistributionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NormalDistributionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @param INTEGER
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		NormalDistribution d = new NormalDistribution();
		String x = request.getParameter("samples");
		String m = request.getParameter("mean");
		String v = request.getParameter("variance");
		d.setSamples(Integer.parseInt(x));
		d.setMean(Double.parseDouble(m));
		d.setVariance(Double.parseDouble(v));
		System.out.println(d);
		System.out.println("Successful..");
		try {
			NormalDistributionService service = new NormalDistributionService("F:/script/sample.r");
			String graphInBytes = service.plotScatter(d);
			PrintWriter out = response.getWriter();
			out.println(graphInBytes);
			out.flush();
		} catch (NormalDistributionException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
