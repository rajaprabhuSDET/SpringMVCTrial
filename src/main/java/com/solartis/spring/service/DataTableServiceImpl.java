package com.solartis.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solartis.spring.dao.DataTable;

@Service("dataService")
@Transactional
public class DataTableServiceImpl implements DataTableService {

	@Autowired
	private DataTable dataTable;
	@Override
	public String[] metaData() {
		// TODO Auto-generated method stub
		return dataTable.metaData();
	}

	@Override
	public List<String> tableData() {
		// TODO Auto-generated method stub
		return dataTable.tableData();
	}

}
