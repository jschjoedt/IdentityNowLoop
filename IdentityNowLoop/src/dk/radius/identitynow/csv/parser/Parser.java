package dk.radius.identitynow.csv.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.sap.tc.logging.Location;

import dk.radius.identitynow.pojo.Employee;
import dk.radius.identitynow.servlet.Main;
import dk.radius.identitynow.utils.Logger;

public class Parser {
	private static final Location location = Location.getLocation(Main.class);
	
	/**
	 * Extract employee data from CSV test data and map to Employee pojo.
	 * @param is Test data input
	 * @return ArrayList<Employees> with list of Employee objects found in test data
	 * @throws IOException
	 */
	private static ArrayList<Employee> extractEmployees(InputStream is) throws IOException {
		String SIGNATURE = "extractEmployees(InputStream)";
		
		ArrayList<Employee> empList = new ArrayList<Employee>();

		// Read test data stream
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";
		String cvsSplitBy = ";";

		// Process csv lines
		while ((line = br.readLine()) != null) {
			String[] lineData = line.split(cvsSplitBy);

			// Create employee for each new line
			Employee emp = new Employee();			
			emp.managerId = lineData[1].trim();
			emp.firstName = lineData[3].trim();
			emp.lastName = lineData[4].trim();
			emp.displayName = emp.lastName + ", " + emp.firstName;
			emp.status = lineData[5].trim();
			emp.startDate = lineData[6].trim();
			emp.endDate = lineData[7].trim();
			emp.type = lineData[9].trim();
			emp.email = lineData[10].trim().toLowerCase();
			emp.identifier = lineData[11].trim();
			emp.companyCode = getCompanyCode(emp.type); //TODO: Change when included in csv data

			// Add to list
			empList.add(emp);
		}
		
		// Done reading
		br.close();
		
		// Write to log
		Logger.writeLogEntry(SIGNATURE, location, "Employees extracted from stream: " + empList.size());
		
		// Return list of loaded Employees
		return empList;
	}
	

	private static String getCompanyCode(String employeeType) {
		String companyCode = "";
		
		// Simulate a company code and return it (not part of csv yet)
		if (employeeType.equals("O") || employeeType.equals("F") || employeeType.equals("U")) {
			companyCode = "2705";
		} else if (employeeType.equals("E")) {
			companyCode = "2700";
		} else if (employeeType.equals("A")) {
			companyCode = "4500";
		}
		
		// Return company code
		return companyCode;
	}


	/**
	 * Build an XML string based on a list of employees.
	 * @param empList ArrayList<Employee> containing employee objects with data
	 * @return <i>String</i>: XML representation of employee data to match SAP ECC format
	 */
	private static String buildEmployeeResponse(ArrayList<Employee> empList) {
		String SIGNATURE = "buildemployeeResponse(ArrayList<Employee>)";
		StringBuilder sb = new StringBuilder();

		// Add XML start of response
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<ns0:EmployeesResponse xmlns:ns0=\"http://radiuselnet.dk/decs/identitynow\">")
		.append("<Employees>");

		// Process and add each employee in list
		for (Employee emp : empList) {
			sb.append("<Employee>")
				.append("<Identifier>")
				.append(emp.identifier)
				.append("</Identifier>")
	
				.append("<Type>")
				.append(emp.type)
				.append("</Type>")
	
				.append("<CompanyCode>")
				.append(emp.companyCode)
				.append("</CompanyCode>")
				
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

		// finalize XML response
		sb.append("</Employees>")
		.append("</ns0:EmployeesResponse>");

		// Write to log
		Logger.writeLogEntry(SIGNATURE, location, "Employee XML response build");
		
		// Return full response
		return sb.toString();
	}

	
	/**
	 * Read and parse CSV test data.
	 * @param is Test data input
	 * @return String XML representation of expected SAP ECC data
	 * @throws IOException
	 */
	public static String processEmployeeData(InputStream is) throws IOException {
		// Extract employees from csv file
		ArrayList<Employee> empList = extractEmployees(is);

		// Create XML string from employee list
		String response = buildEmployeeResponse(empList);

		// Return XML response
		return response;
	}



}
