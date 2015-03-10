package dz.gov.mesrs.sii.commons.web.util;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;

public class SearchModeMapper {

	public static SearchMode map(int first, int pageSize, String sortField, SortOrder sortOrder) {
		SearchMode searchMode = new SearchMode(pageSize, first);
		if (!StringUtils.isEmpty(sortField)) {
			SearchMode.SortOrder order = null;
			if (sortOrder != null) {
				if (sortOrder.equals(SortOrder.DESCENDING)) {
					order = SearchMode.SortOrder.DESC;
				} else {
					order = SearchMode.SortOrder.ASC;
				}

			}
			SearchMode.SortField sort = new SearchMode.SortField(sortField, order);
			searchMode.addSortField(sort);
		}
		return searchMode;
	}

}
