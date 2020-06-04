package dk.radius.identitynow.servlet;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.radius.identitynow.csv.parser.Parser;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set content type
		response.setContentType(request.getContentType());
		
		// Load test data from local file in project
		InputStream is = getServletContext().getResourceAsStream("/TestData/Test_extract_csv.csv");
		
		// Process local test data
		String employeesResponse = Parser.processEmployeeData(is);
		
		// Set response
		response.getWriter().append(employeesResponse);
	}
}
