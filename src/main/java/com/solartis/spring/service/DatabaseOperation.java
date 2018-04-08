package com.solartis.spring.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.solartis.spring.exception.DatabaseException;

public class DatabaseOperation {
	@Autowired
	private Environment environment;
	private static Connection conn = null;
	private static String JDBC_DRIVER = null;
	private static String DB_URL = null;
	private static String USER = null;
	private static String PASS = null;
	protected String query = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected int rs_row = 1;
	protected LinkedHashMap<Integer, LinkedHashMap<String, String>> table = null;
	protected ResultSetMetaData meta = null;

	public void ConnectionSetup() throws DatabaseException {
		JDBC_DRIVER = environment.getRequiredProperty("jdbc.driverClassName");
		DB_URL = environment.getRequiredProperty("jdbc.url");
		USER = environment.getRequiredProperty("jdbc.user");
		PASS = environment.getRequiredProperty("jdbc.pass");
		if (conn == null) {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				throw new DatabaseException("ERROR IN JDBC_DRIVER : " + JDBC_DRIVER, e);
			}
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (SQLException e) {
				throw new DatabaseException("ERROR IN DB - URL / USERNAME / PASSWORD", e);
			}
		}
	}

	public void switchDB(String db) throws DatabaseException {
		try {
			conn.setCatalog(db);
		} catch (SQLException e) {
			throw new DatabaseException("Error while switch DataBase", e);
		}
	}

	public static void ConnectionSetup(String JDBC_DRIVER, String DB_URL, String USER, String password)
			throws DatabaseException {
		if (conn == null) {

			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				throw new DatabaseException("ERROR IN JDBC_DRIVER : " + JDBC_DRIVER, e);
			}
			try {
				conn = DriverManager.getConnection(DB_URL, USER, password);
			} catch (SQLException e) {
				throw new DatabaseException("ERROR IN DB - URL / USERNAME / PASSWORD", e);
			}
		}
	}

	public static void CloseConn() throws DatabaseException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DatabaseException("PROBLEM WITH CLOSING DB-CONNECTION", e);
		}
		conn = null;
	}

	public String[] getColumnNames() throws DatabaseException {
		String[] columnNames = null;
		try {
			columnNames = new String[meta.getColumnCount()];
			for (int columnIterator = 1; columnIterator <= meta.getColumnCount(); columnIterator++) {
				columnNames[columnIterator - 1] = meta.getColumnName(columnIterator);
			}
		} catch (Exception e) {
			throw new DatabaseException("PROBLEM WITH RESULT-SET OBTAINED FROM DB", e);
		}
		return columnNames;

	}

	public LinkedHashMap<Integer, LinkedHashMap<String, String>> GetDataObjects(String query) throws DatabaseException {
		this.query = query;
		LinkedHashMap<String, String> row = null;
		// System.out.println("Query"+this.query);
		try {
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(this.query);
			table = new LinkedHashMap<Integer, LinkedHashMap<String, String>>();
			meta = rs.getMetaData();
			while (rs.next()) {
				row = new LinkedHashMap<String, String>();
				for (int columnIterator = 1; columnIterator <= meta.getColumnCount(); columnIterator++) {
					String key = meta.getColumnName(columnIterator);
					String value = rs.getString(key);
					row.put(key, value);
				}
				table.put(rs_row, row);
				rs_row = rs_row + 1;
			}
			return table;
		} catch (SQLException e) {
			throw new DatabaseException("PROBLEM WITH RESULT-SET OBTAINED FROM DB", e);
		}

	}

	/*
	 * public void ExecuteQuery(String query) throws SQLException { stmt =
	 * conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
	 * rs = stmt.executeQuery(query); }
	 */

	public void UpdateRow(Integer rowNumber, LinkedHashMap<String, String> row) {
		try {
			java.sql.PreparedStatement stmt = null;
			ResultSet rs = null;
			stmt = conn.prepareStatement(this.query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(this.query);
			rs.first();
			ResultSetMetaData meta = rs.getMetaData();
			int rowIterator = 1;
			do {
				if (rowNumber == rowIterator) {
					for (int i = 1; i <= meta.getColumnCount(); i++) {

						rs.updateString(meta.getColumnName(i), row.get(meta.getColumnName(i)));
					}
					rs.updateRow();
				}

				rowIterator++;
			} while (rs.next());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateTable(LinkedHashMap<Integer, LinkedHashMap<String, String>> table)
			throws DatabaseException, SQLException {
		this.table = table;
		LinkedHashMap<String, String> row = null;
		ResultSetMetaData meta = rs.getMetaData();
		try {
			rs.first();
			int rowIterator = 1;
			do {
				for (int columnIterator = 1; columnIterator <= meta.getColumnCount(); columnIterator++) {
					row = table.get(rowIterator);
					rs.updateString(meta.getColumnName(columnIterator), row.get(meta.getColumnName(columnIterator)));
				}

				rs.updateRow();
				rowIterator++;
			} while (rs.next());
		}

		catch (SQLException e) {
			throw new DatabaseException("PROBLEM WITH UPDATE ROW IN DB", e);
		}
	}

}
