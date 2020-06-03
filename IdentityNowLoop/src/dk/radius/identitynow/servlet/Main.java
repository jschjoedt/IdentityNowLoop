package dk.radius.identitynow.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.setContentType("application/xml");
		
		// Create dummy xml response
		
		
		response.getWriter()
			.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
			.append("<Employees>")
				.append("<Employee>")
					.append("<Identifier>")
					.append("11111")
					.append("</Identifier>")

					.append("<Type>")
					.append("Permanent")
					.append("</Type>")
					
					.append("<FirstName>")
					.append("Testy")
					.append("</FirstName>")

					.append("<LastName>")
					.append("McTest Face")
					.append("</LastName>")
					
					.append("<DisplayName>")
					.append("Testy McTest Face")
					.append("</DisplayName>")
					
					.append("<EmailAddress>")
					.append("testy.mctestface@radius.dk")
					.append("</EmailAddress>")
					
					.append("<ManagerUniqueIdentifier>")
					.append("9999999")
					.append("</ManagerUniqueIdentifier>")
					
					.append("<Status>")
					.append("Active")
					.append("</Status>")
					
					.append("<StartDate>")
					.append("03-06-2020")
					.append("</StartDate>")
					
					.append("<EndDate>")
					.append("03-06-2020")
					.append("</EndDate>")
					
				.append("</Employee>")						
					.append("<Employee>")
					.append("<Identifier>")
					.append("22222")
					.append("</Identifier>")
					
					.append("<Type>")
					.append("Contractor")
					.append("</Type>")
					
					.append("<FirstName>")
					.append("Hello")
					.append("</FirstName>")
	
					.append("<LastName>")
					.append("Kitty")
					.append("</LastName>")
					
					.append("<DisplayName>")
					.append("Hello Kitty")
					.append("</DisplayName>")
					
					.append("<EmailAddress>")
					.append("hello.kitty@radius.dk")
					.append("</EmailAddress>")
					
					.append("<ManagerUniqueIdentifier>")
					.append("9999999")
					.append("</ManagerUniqueIdentifier>")
					
					.append("<Status>")
					.append("Inactive")
					.append("</Status>")
					
					.append("<StartDate>")
					.append("03-06-2020")
					.append("</StartDate>")
					
					.append("<EndDate>")
					.append("03-06-2025")
					.append("</EndDate>")
				.append("</Employee>")							
				
				.append("<Employee>")
					.append("<Identifier>")
					.append("33333")
					.append("</Identifier>")
					
					.append("<Type>")
					.append("Third-Party")
					.append("</Type>")
					
					.append("<FirstName>")
					.append("Xternal")
					.append("</FirstName>")
	
					.append("<LastName>")
					.append("Guy")
					.append("</LastName>")
					
					.append("<DisplayName>")
					.append("Xternal Guy")
					.append("</DisplayName>")
					
					.append("<EmailAddress>")
					.append("xternal.guy@radius.dk")
					.append("</EmailAddress>")
					
					.append("<ManagerUniqueIdentifier>")
					.append("9999999")
					.append("</ManagerUniqueIdentifier>")
					
					.append("<Status>")
					.append("Active")
					.append("</Status>")
					
					.append("<StartDate>")
					.append("03-06-2020")
					.append("</StartDate>")
					
					.append("<EndDate>")
					.append("03-06-2025")
					.append("</EndDate>")
				.append("</Employee>")	
			.append("</Employees>");
	}
}
