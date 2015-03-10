package dz.gov.mesrs.sii.fve.web.jsf.bean.test;

import java.io.Serializable;

public class BeanJava implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

    private Integer communeId;
    private Integer dairaId;
    private Integer paysId;
    private Integer wilayaId;
	public Integer getCommuneId() {
		return communeId;
	}
	public void setCommuneId(Integer communeId) {
		this.communeId = communeId;
	}
	
	public Integer getDairaId() {
		return dairaId;
	}
	public void setDairaId(Integer dairaId) {
		this.dairaId = dairaId;
	}
	public Integer getPaysId() {
		return paysId;
	}
	public void setPaysId(Integer paysId) {
		this.paysId = paysId;
	}
	public Integer getWilayaId() {
		return wilayaId;
	}
	public void setWilayaId(Integer wilayaId) {
		this.wilayaId = wilayaId;
	}
}
