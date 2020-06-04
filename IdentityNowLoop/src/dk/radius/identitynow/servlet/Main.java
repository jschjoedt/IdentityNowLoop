package dk.radius.identitynow.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.radius.identitynow.csv.parser.Parser;
import dk.radius.identitynow.pojo.Employee;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Set content type
		response.setContentType(request.getContentType());
		
		// Load test data from local file in project
		InputStream is = getServletContext().getResourceAsStream("/TestData/Test_extract_csv.csv");
		
		// Extract employees from csv file
		ArrayList<Employee> empList = Parser.extractEmployees(is);
				
				
		String employeesResponse = buildEmployeeResponse(empList);
		
		response.getWriter().append(employeesResponse);
	}

	private String buildEmployeeResponse(ArrayList<Employee> empList) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<ns0:EmployeesResponse xmlns:ns0=\"http://radiuselnet.dk/desc/identitynow\">")
		.append("<Employees>");
		for (Employee emp : empList) {
			sb.append("<Employee>")
			.append("<Identifier>")
			.append(emp.identifier)
			.append("</Identifier>")

			.append("<Type>")
			.append(emp.type)
			.append("</Type>")
			
			.append("<FirstName>")
			.append(emp.firstName)
			.append("</FirstName>")

			.append("<LastName>")
			.append(emp.lastName)
			.append("</LastName>")
			
			.append("<DisplayName>")
			.append(emp.displayName)
			.append("</DisplayName>")
			
			.append("<EmailAddress>")
			.append(emp.email)
			.append("</EmailAddress>")
			
			.append("<ManagerUniqueIdentifier>")
			.append(emp.managerId)
			.append("</ManagerUniqueIdentifier>")
			
			.append("<Status>")
			.append(emp.status)
			.append("</Status>")
			
			.append("<StartDate>")
			.append(emp.startDate)
			.append("</StartDate>")
			
			.append("<EndDate>")
			.append(emp.endDate)
			.append("</EndDate>")	
		.append("</Employee>");		
		}
		sb.append("</Employees>")
		.append("</ns0:EmployeesResponse>");
		
		return sb.toString();
	}
}
