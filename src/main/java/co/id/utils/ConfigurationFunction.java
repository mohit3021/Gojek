package co.id.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class ConfigurationFunction {

	public static Connection connection;

	public static ArrayList readDataForExcelColumn(String strfile,
			String queryColumn) {
		ArrayList<String> array = new ArrayList<String>();
		try {
			Fillo fillo = new Fillo();
			connection = fillo.getConnection(strfile);
			Recordset recordset = connection
					.executeQuery("Select * from \"TestData\"");
			System.out.println("Total No of rows matching your creteria for "
					+ queryColumn + ": " + +recordset.getCount());
			while (recordset.next()) {
				String strval = recordset.getField(queryColumn);
				array.add(strval);

			}
			recordset.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return array;
	}

	

}
