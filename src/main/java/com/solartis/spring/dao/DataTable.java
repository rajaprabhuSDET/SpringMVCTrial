package com.solartis.spring.dao;

import java.util.List;

public interface DataTable {

	String[] metaData();
	List<String> tableData();
}
