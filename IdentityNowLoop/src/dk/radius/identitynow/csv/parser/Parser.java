package dk.radius.identitynow.csv.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dk.radius.identitynow.pojo.Employee;

public class Parser {

	public static ArrayList<Employee> extractEmployees(InputStream is) throws IOException {
		
		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String cvsSplitBy = ";";

        
            while ((line = br.readLine()) != null) {
            	String[] lineData = line.split(cvsSplitBy);
            	
            	Employee emp = new Employee();
            	
            	emp.identifier = lineData[0].trim();
            	emp.managerId = lineData[1].trim();
            	emp.firstName = lineData[3].trim();
            	emp.lastName = lineData[4].trim();
            	emp.displayName = emp.lastName + ", " + emp.firstName;
            	emp.status = lineData[5].trim();
            	emp.startDate = lineData[6].trim();
            	emp.endDate = lineData[7].trim();
            	emp.type = lineData[9].trim();
            	
            	empList.add(emp);
            	
            }
            
            br.close();
            
            return empList;
	}

}
