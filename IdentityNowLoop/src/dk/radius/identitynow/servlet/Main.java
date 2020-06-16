package dk.radius.identitynow.servlet;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.tc.logging.Location;

import dk.radius.identitynow.csv.parser.Parser;
import dk.radius.identitynow.utils.Logger;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Location location = Location.getLocation(Main.class);
       
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
		// Copy content type
		copyContentType(request, response);
		
		// Load test data from local file in project
		InputStream is = getServletContext().getResourceAsStream("/TestData/Test_extract_csv.csv");
		
		// Process local test data
		String employeesResponse = Parser.processEmployeeData(is);
		
		// Set response
		response.getWriter().append(employeesResponse);
	}

	private void copyContentType(HttpServletRequest request, HttpServletResponse response) {
		String SIGNATURE = "copyContentType(HttpServletRequest, HttpServletResponse)";
		
		String contentType = request.getContentType();
		response.setContentType(contentType);
		Logger.writeLogEntry(SIGNATURE, location, "Content type set: " + contentType);
	}
}
