package ie.gmit.sw;

import java.io.*;
import java.text.DecimalFormat;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServicePollHandler extends HttpServlet {
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		// Pass in the result from ServiceHandler servlet
		String result = req.getParameter("result");
		int counter = 1;
		if (req.getParameter("counter") != null) {
			counter = Integer.parseInt(req.getParameter("counter"));
			counter++;
		}
		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.css'>");
		out.print("</head>");
		out.print("<body>");
		out.print("<center><H3>Document Title: " + title + "</H3>");
		out.print("<table width='600' cellspacing='0' cellpadding='7' border='0'>");
		out.print("<tr>");
		out.print("<td valign='top'>");

		out.print("<fieldset>");
		out.print("<legend><h3>Result</h3></legend>");
		String color = "red";
		// Just a little UX design, if the result is less than 50 it will
		// display green
		// Or else it will display red (indicating too VERY similar)
		if (Double.valueOf(result) < 50.0) {
			color = "green";
		}
		out.print("<center><h1> The comparison is <b class='animated fadeIn' style='color:" + color + "'>" + result
				+ "% </b></h1></center>");
		out.print("<center><a href='index.jsp'><input type='button' value='Compare Another Document'></a></center>"
				+ "<br>");
		out.print("</fieldset>");

		out.print("</td>");
		out.print("</tr>");
		out.print("</table></center>");
		out.print("</body>");
		out.print("</html>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}