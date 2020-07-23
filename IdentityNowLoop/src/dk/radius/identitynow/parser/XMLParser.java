package dk.radius.identitynow.parser;

import java.io.InputStreamReader;
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

	private static ArrayList<DO_Employee> extractEmployees(InputStreamReader isr) throws ServletException {
		String SIGNATURE = "extractEmployees(InputStream)";

		// Write to log
		Logger.writeLogEntry(SIGNATURE, location, "Start: Extracting employees");

		ArrayList<DO_Employee> empList = new ArrayList<DO_Employee>();
		DO_Employee emp = null;


		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(isr);

			while (reader.hasNext()) {
				XMLEvent nextEvent = reader.nextEvent();
				if (nextEvent.isStartElement()) {
					StartElement startElement = nextEvent.asStartElement();
					switch (startElement.getName().getLocalPart()) {
					case "employee":
						emp = new DO_Employee();
						break;

					case "initials":		
						if (reader.peek().isCharacters()) {
							emp.initials = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "firstName":
						if (reader.peek().isCharacters()) {
							emp.firstName = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "lastName":
						if (reader.peek().isCharacters()) {
							emp.lastName = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "title":
						if (reader.peek().isCharacters()) {
							emp.title = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "email":
						if (reader.peek().isCharacters()) {
							emp.email = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "phone":
						if (reader.peek().isCharacters()) {
							emp.phone = reader.nextEvent().asCharacters().getData().trim();
						}
						break;

					case "consultantEmail":
						if (reader.peek().isCharacters()) {
							emp.consultantEmail = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "mobile":
						if (reader.peek().isCharacters()) {
							emp.mobile = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "office":
						if (reader.peek().isCharacters()) {
							emp.office = reader.nextEvent().asCharacters().getData();
						}
						break;

					case "hrEmployeeNo":
						if (reader.peek().isCharacters()) {
							emp.hrEmployeeNo = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrManagerNo":
						if (reader.peek().isCharacters()) {
							emp.hrManagerNo = reader.nextEvent().asCharacters().getData();
						}
						break;	
						
					case "hrActiveStatus":
						if (reader.peek().isCharacters()) {
							emp.hrActiveStatus = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrEmployeeType":
						if (reader.peek().isCharacters()) {
							emp.hrEmployeeType = reader.nextEvent().asCharacters().getData();
						}
						break;
						
					case "hrConsultantType":
						if (reader.peek().isCharacters()) {
							emp.hrConsultantType = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrDepartmentNo":
						if (reader.peek().isCharacters()) {
							emp.hrDepartmentNo = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrLocationId":
						if (reader.peek().isCharacters()) {
							emp.hrLocationId = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrCountryCode":
						if (reader.peek().isCharacters()) {
							emp.hrCountryCode = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrLagalCompany":
						if (reader.peek().isCharacters()) {
							emp.hrLegalCompany = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrCostCenter":
						if (reader.peek().isCharacters()) {
							emp.hrCostCenter = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "employmentDateString":
						if (reader.peek().isCharacters()) {
							emp.employmentDateString = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "departureDateString":
						if (reader.peek().isCharacters()) {
							emp.departureDateString = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "tdbMobilePhone":
						if (reader.peek().isCharacters()) {
							emp.tdbMobilePhone = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "hrCompanyCode":
						if (reader.peek().isCharacters()) {
							emp.hrCompanyCode = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "eeSubgroup":
						if (reader.peek().isCharacters()) {
							emp.eeSubgroup = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "citizenship":
						if (reader.peek().isCharacters()) {
							emp.citizenship = reader.nextEvent().asCharacters().getData();
						}
						break;	

					case "countryOfResidence":
						if (reader.peek().isCharacters()) {
							emp.countryOfResidence = reader.nextEvent().asCharacters().getData();
						}
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
			Logger.writeLogEntry(SIGNATURE, location, "End: Extracting employees (found: " + empList.size() + ")");

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
	public static String processEmployeeData(InputStreamReader isr) throws ServletException {
		// Extract employees from xml file
		ArrayList<DO_Employee> empList = extractEmployees(isr);

		// Create XML string from employee list
		String response = Xml.buildEmployeeResponse(empList);

		// Return XML response
		return response;
	}


}
