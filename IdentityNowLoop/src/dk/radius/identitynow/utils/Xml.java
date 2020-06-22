package dk.radius.identitynow.utils;

import java.util.ArrayList;

import com.sap.tc.logging.Location;

import dk.radius.identitynow.pojo.DO_Employee;

public class Xml {

	private static final Location location = Location.getLocation(Xml.class);
	
	/**
	 * Build an XML string based on a list of employees.
	 * @param empList ArrayList<Employee> containing employee objects with data
	 * @return <i>String</i>: XML representation of employee data to match SAP ECC format
	 */
	public static String buildEmployeeResponse(ArrayList<DO_Employee> empList) {
		String SIGNATURE = "buildemployeeResponse(ArrayList<Employee>)";
		StringBuilder sb = new StringBuilder();

		// Add XML start of response
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<employees>");

		// Process and add each employee in list
		for (DO_Employee emp : empList) {
			sb.append("<employee>")
				.append("<initials>")
				.append(emp.initials)
				.append("</initials>")
			
				.append("<firstName>")
				.append(emp.firstName)
				.append("</firstName>")

				.append("<lastName>")
				.append(emp.lastName)
				.append("</lastName>")
			
				.append("<title>")
				.append(emp.title)
				.append("</title>")
				
				.append("<email>")
				.append(emp.email)
				.append("</email>")
				
				.append("<phone>")
				.append(emp.phone)
				.append("</phone>")
				
				.append("<consultantEmail>")
				.append(emp.consultantEmail)
				.append("</consultantEmail>")
				
				.append("<mobile>")
				.append(emp.mobile)
				.append("</mobile>")
				
				.append("<office>")
				.append(emp.office)
				.append("</office>")
				
				.append("<hrEmployeeNo>")
				.append(emp.hrEmployeeNo)
				.append("</hrEmployeeNo>")
				
				.append("<hrActiveStatus>")
				.append(emp.hrActiveStatus)
				.append("</hrActiveStatus>")
				
				.append("<hrEmployeeType>")
				.append(emp.hrEmployeeType)
				.append("</hrEmployeeType>")
				
				.append("<hrDepartmentNo>")
				.append(emp.hrDepartmentNo)
				.append("</hrDepartmentNo>")
				
				.append("<hrLocationId>")
				.append(emp.hrLocationId)
				.append("</hrLocationId>")
				
				.append("<hrCountryCode>")
				.append(emp.hrCountryCode)
				.append("</hrCountryCode>")
				
				.append("<hrLagalCompany>")
				.append(emp.hrLagalCompany)
				.append("</hrLagalCompany>")
				
				.append("<hrCostCenter>")
				.append(emp.hrCostCenter)
				.append("</hrCostCenter>")
				
				.append("<employmentDateString>")
				.append(emp.employmentDateString)
				.append("</employmentDateString>")
				
				.append("<departureDateString>")
				.append(emp.departureDateString)
				.append("</departureDateString>")
				
				.append("<tdbMobilePhone>")
				.append(emp.tdbMobilePhone)
				.append("</tdbMobilePhone>")
				
				.append("<hrCompanyCode>")
				.append(emp.hrCompanyCode)
				.append("</hrCompanyCode>")
				
				.append("<eeSubgroup>")
				.append(emp.eeSubgroup)
				.append("</eeSubgroup>")
				
				.append("<citizenship>")
				.append(emp.citizenship)
				.append("</citizenship>")
				
				.append("<countryOfResidence>")
				.append(emp.countryOfResidence)
				.append("</countryOfResidence>")
				
			.append("</employee>");		
		}

		// finalize XML response
		sb.append("</employees>");

		// Write to log
		Logger.writeLogEntry(SIGNATURE, location, "Employee XML response build");
		
		// Return full response
		return sb.toString();
	}

}
