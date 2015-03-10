/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.Note.java] 
 * @author MAKERRI Sofiane on : 21 oct. 2014  15:33:27
 */
package dz.gov.mesrs.sii.commons.business.util;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane on : 21 oct. 2014 15:33:27
 */
public class CrossTab implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 15:33:33
	 */
	private static final long serialVersionUID = 1L;
	private String row;
	private String value;
	private String column;

	/**
	 * [Note.Note()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 21 oct. 2014 15:33:27
	 */
	public CrossTab() {
		super();
	}

	/**
	 * [BilanPrint.value] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 08:17:06
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * [BilanPrint.value] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 08:17:06
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * [CrossTab.column] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 08:55:25
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * [CrossTab.column] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 08:55:25
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * [BilanPrint.row] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 08:40:11
	 * @return the row
	 */
	public String getRow() {
		return row;
	}

	/**
	 * [BilanPrint.row] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 08:40:11
	 * @param row
	 *            the row to set
	 */
	public void setRow(String row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "row = " + row + " column = " + column + " value = " + value;
	}

}
