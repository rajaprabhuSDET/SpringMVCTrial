package com.solartis.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.solartis.spring.model.User;

@Repository("MelActual")
public class DataTableImpl extends AbstractDao<Integer, User> implements DataTable{

	@Override
	public String[] metaData() {
		String[] columnNames = getSession().getSessionFactory().getClassMetadata(DataTableImpl.class).getPropertyNames();
		return columnNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> tableData() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

}
