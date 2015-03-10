/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.PagedListDataModel.java] 
 * @author rlaib on : 30 juil. 2014  17:04:45
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

import java.util.List;

import javax.faces.model.DataModel;

/**
 * @author rlaib  on : 30 juil. 2014  17:04:45
 */
public class PagedListDataModel extends DataModel {
	
	private int rowIndex = -1;

	private int totalNumRows;

	private int pageSize;

	private List list;

	public PagedListDataModel() {
	super();
	}

	public PagedListDataModel(List list, int totalNumRows, int pageSize) {
	super();
	setWrappedData(list);
	this.totalNumRows = totalNumRows;
	this.pageSize = pageSize;
	}

	public boolean isRowAvailable() {
	if(list == null)
	return false;

	int rowIndex = getRowIndex();
	if(rowIndex >=0 && rowIndex < list.size())
	return true;
	else
	return false;
	}

	public int getRowCount() {
	return totalNumRows;
	}

	public Object getRowData() {
	if(list == null)
	return null;
	else if(!isRowAvailable())
	throw new IllegalArgumentException();
	else {
	int dataIndex = getRowIndex();
	return list.get(dataIndex);
	}
	}

	public int getRowIndex() {
	return (rowIndex % pageSize);
	}

	public void setRowIndex(int rowIndex) {
	this.rowIndex = rowIndex;
	}

	public Object getWrappedData() {
	return list;
	}

	public void setWrappedData(Object list) {
	this.list = (List) list;
	}

}
