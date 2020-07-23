package dk.radius.identitynow.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sap.tc.logging.Location;

import dk.radius.identitynow.parser.XMLParser;
import dk.radius.identitynow.utils.Logger;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEST_FILE_NAME = "/WEB-INF/lib/EmployeesFullPayload.xml";
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
		String SIGNATURE = "doGet(HttpServletRequest, HttpServletResponse)";
		
		// Set start time
		Instant startTimer = Instant.now();
		
		// Log start
		Logger.writeLogEntry(SIGNATURE, location, "==> Start processing employee test data @" + startTimer);

		// Copy content type
		copyContentType(request, response);
		
		// Load test data from local file in project
		InputStreamReader isr = new InputStreamReader(getServletContext().getResourceAsStream(TEST_FILE_NAME), StandardCharsets.UTF_8.name());
		Logger.writeLogEntry(SIGNATURE, location, "Test data read from: " + TEST_FILE_NAME + ", and ready to use");

		// Process local test data
		String employeesResponse = XMLParser.processEmployeeData(isr);

		// Set response
		response.getWriter().append(employeesResponse);
		Logger.writeLogEntry(SIGNATURE, location, "Employee payload written to response");
		
		// Set end time
		Instant endTimer = Instant.now();
		
		// Write to log
		Logger.writeLogEntry(SIGNATURE, location, "<== End processing employee test data @" + endTimer);
		Logger.writeLogEntry(SIGNATURE, location, "# Total runtime: " + Duration.between(startTimer, endTimer).toMillis() + " ms #");
	}


	private void copyContentType(HttpServletRequest request, HttpServletResponse response) {
		String SIGNATURE = "copyContentType(HttpServletRequest, HttpServletResponse)";

		String contentType = "application/xml";
		response.setContentType(contentType);
		Logger.writeLogEntry(SIGNATURE, location, "Content type set: " + contentType);
	}
}
