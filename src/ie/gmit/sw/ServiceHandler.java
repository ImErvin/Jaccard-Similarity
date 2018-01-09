package ie.gmit.sw;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/* NB: You will need to add the JAR file $TOMCAT_HOME/lib/servlet-api.jar to your CLASSPATH 
 *     variable in order to compile a servlet from a command line.
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB. The file size in bytes after which the file will be temporarily stored on disk. The default size is 0 bytes.
                 maxFileSize=1024*1024*10,      // 10MB. The maximum size allowed for uploaded files, in bytes
                 maxRequestSize=1024*1024*50)   // 50MB. he maximum size allowed for a multipart/form-data request, in bytes.
public class ServiceHandler extends HttpServlet {
	// Composition of a Server
	private Server api;
	private String somethingMoreAppropriate = null;
	private static long jobNumber = 0;

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		somethingMoreAppropriate = ctx.getInitParameter("SOME_GLOBAL_OR_ENVIRONMENTAL_VARIABLE"); 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		String hashingMethod = req.getParameter("hashingMethod");
		Part part = req.getPart("txtDocument");
		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");		
		out.print("<link rel='stylesheet' href='includes/basic.css'>");
		out.print("</head>");		
		out.print("<body>");
		
		//We could use the following to track asynchronous tasks. Comment it out otherwise...
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Add job to in-queue
		}else{
			RequestDispatcher dispatcher = req.getRequestDispatcher("/poll");
			dispatcher.forward(req,resp);
			//Check out-queue for finished job with the given taskNumber
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
		// Server is an instance of proxyserver for error handling
		api = new ProxyServer();
		// If the authenticate returns false it will display an error message and won't continue
		if(!api.authenticate(title, br.readLine())){
			out.print("<center><H1 style='color:crimson'> Please set a valid title and upload a valid file! </H1>");
			out.print("<a href='index.jsp'>Go back</a></center>");
		}else{
			// If the authenticate returns true it will continue to process the document
			out.print("<center><H1 class='animated flash infinite'>Processing request for Job#: " + taskNumber + "</H1>");
			out.print("<h3 style='color:crimson'> The hashing algorithm you have selected will have an impact on the result - depending on how the other files are hashed </h3>");
			out.print("<table width='600' cellspacing='0' cellpadding='7' border='0'>");
			out.print("<tr>");
			out.print("<td valign='top'>");
			
			out.print("<fieldset class='animated fadeIn'>");
			out.print("<legend><h3>Uploaded Document</h3></legend>");
			out.print("<center><H3>Document Title: " + title + "</H3>");
			out.print("<font color=\"0000ff\">");	
			// Reset the buffered reader as I have read one line already above to authenticate
			br = new BufferedReader(new InputStreamReader(part.getInputStream()));
			// Set the result as a formatted string to 3 decimal places and the reuslt of proxyserver.process
			String result = String.format("%.3g%n", api.process(title, br, (hashingMethod.hashCode() == -745445883 ? HashingMethod.MINHASH : HashingMethod.HASHCODE)));
			// Display the document for UX reasons
			for(String s:api.displayDocument()){
				out.print(s+" ");
			}
			// Close the session
			api.finish();
			out.print("</center></font>");	
			out.print("</fieldset>");					
			
			out.print("</td>");
			out.print("</tr>");
			out.print("</table></center>");	
			out.print("<form name=\"frmRequestDetails\" action=\"poll\">");
			out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
			out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
			// Pass on the result to the /poll servlet
			out.print("<input name=\"result\" type=\"hidden\" value=\"" + result + "\">");
			out.print("</form>");								
			out.print("</body>");	
			out.print("</html>");	
			out.print("<script>");
			out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);"); //Refresh every 10 seconds
			out.print("</script>");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}