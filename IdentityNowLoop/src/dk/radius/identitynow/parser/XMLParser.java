package dk.radius.identitynow.parser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.sap.tc.logging.Location;

import dk.radius.identitynow.pojo.DO_Employee;
import dk.radius.identitynow.servlet.Main;
import dk.radius.identitynow.utils.Logger;
import dk.radius.identitynow.utils.Xml;

public class XMLParser {
	private static final Location location = Location.getLocation(Main.class);

	private static ArrayList<DO_Employee> extractEmployees(InputStream is) throws ServletException {
		String SIGNATURE = "extractEmployees(InputStream)";
		
		// Write to log
		Logger.writeLogEntry(SIGNATURE, location, "Start processing InputStream...");
		
		ArrayList<DO_Employee> empList = new ArrayList<DO_Employee>();
		DO_Employee emp = null;


		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			// Use charset that supports Danish special characters "זרו"
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(is, StandardCharsets.ISO_8859_1.name());

			while (reader.hasNext()) {
				XMLEvent nextEvent = reader.nextEvent();
				if (nextEvent.isStartElement()) {
					StartElement startElement = nextEvent.asStartElement();
					switch (startElement.getName().getLocalPart()) {
					case "employee":
						emp = new DO_Employee();
						break;

					case "initials":						
						emp.initials = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "firstName":
						emp.firstName = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "lastName":
						emp.lastName = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "title":
						emp.title = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "email":
						emp.email = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "phone":
						emp.phone = "" + reader.nextEvent().asCharacters().getData();
						break;
						
					case "consultantEmail":
						emp.consultantEmail = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "mobile":
						emp.mobile = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "office":
						emp.office = "" + reader.nextEvent().asCharacters().getData();
						break;

					case "hrEmployeeNo":
						emp.hrEmployeeNo = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrActiveStatus":
						emp.hrActiveStatus = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrEmployeeType":
						emp.hrEmployeeType = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrDepartmentNo":
						emp.hrDepartmentNo = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrLocationId":
						emp.hrLocationId = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrCountryCode":
						emp.hrCountryCode = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrLagalCompany":
						emp.hrLagalCompany = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrCostCenter":
						emp.hrCostCenter = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "employmentDateString":
						emp.employmentDateString = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "departureDateString":
						emp.departureDateString = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "tdbMobilePhone":
						emp.tdbMobilePhone = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "hrCompanyCode":
						emp.hrCompanyCode = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "eeSubgroup":
						emp.eeSubgroup = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "citizenship":
						emp.citizenship = "" + reader.nextEvent().asCharacters().getData();
						break;	

					case "countryOfResidence":
						emp.countryOfResidence = "" + reader.nextEvent().asCharacters().getData();
						break;	
					}
				}

				if (nextEvent.isEndElement()) {
					EndElement endElement = nextEvent.asEndElement();
					switch(endElement.getName().getLocalPart()) {
					case "employee":
						empList.add(emp);
						break;
					}
				}
			}
			
			// Write to log
			Logger.writeLogEntry(SIGNATURE, location, "Employees found: " + empList.size());

			// Return employees found
			return empList;
			
		} catch (XMLStreamException e) {
			throw new ServletException("Error parsing test XML: " + e.getMessage());
		}
	}


	/**
	 * Read and parse XML test data.
	 * @param is Test data input
	 * @return String XML representation of expected SAP ECC data
	 * @throws ServletException 
	 */
	public static String processEmployeeData(InputStream is) throws ServletException {
		// Extract employees from xml file
		ArrayList<DO_Employee> empList = extractEmployees(is);

		// Create XML string from employee list
		String response = Xml.buildEmployeeResponse(empList);

		// Return XML response
		return response;
	}


}
