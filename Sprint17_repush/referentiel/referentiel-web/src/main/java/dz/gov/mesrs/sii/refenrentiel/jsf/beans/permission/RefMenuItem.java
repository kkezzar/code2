/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.MenuModel.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  15:02:26
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;


/**
 * @author MAKERRI Sofiane  on : 4 mars 2014  15:02:26
 */
public class RefMenuItem implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 mars 2014  14:38:55
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String icon;
	private String image;
	private String infobul;
	private boolean dock;
	/**
	 * [MenuModel.MenuModel()] Constructor
	 * @author MAKERRI Sofiane  on : 4 mars 2014  15:02:26	
	 */
	public RefMenuItem() {
		
	}
	
	/**
	 * [RefMenuItem.RefMenuItem()] Constructor
	 * @author MAKERRI Sofiane  on : 5 mars 2014  15:05:56
	 * @param url
	 * @param icon	
	 */
	public RefMenuItem(String url, String icon) {
		this.url = url;
		this.icon = icon;
	}
	
	/**
	 * [RefMenuItem.RefMenuItem()] Constructor
	 * @author MAKERRI Sofiane  on : 5 mars 2014  15:06:39
	 * @param url
	 * @param image
	 * @param infobul	
	 */
	public RefMenuItem(String url, String image, String infobul, boolean dock) {
		this.url = url;
		this.image = image;
		this.infobul = infobul;
		this.dock = dock;
	}
	
	/**
	 * [RefMenuItem.RefMenuItem()] Constructor
	 * @author MAKERRI Sofiane  on : 5 mars 2014  15:12:25
	 * @param url
	 * @param icon
	 * @param image
	 * @param infobul	
	 */
	public RefMenuItem(String url, String icon, String image, String infobul, boolean dock) {
		this.url = url;
		this.icon = icon;
		this.image = image;
		this.infobul = infobul;
		this.dock = dock;
	}
	
	/**
	 * [RefMenuItem.url] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * [RefMenuItem.url] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * [RefMenuItem.icon] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * [RefMenuItem.icon] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * [RefMenuItem.image] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * [RefMenuItem.image] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * [RefMenuItem.infobul] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @return the infobul
	 */
	public String getInfobul() {
		return infobul;
	}
	/**
	 * [RefMenuItem.infobul] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  15:05:27
	 * @param infobul the infobul to set
	 */
	public void setInfobul(String infobul) {
		this.infobul = infobul;
	}

	/**
	 * [RefMenuItem.dock] Getter 
	 * @author MAKERRI Sofiane on : 6 mars 2014  08:35:05
	 * @return the dock
	 */
	public boolean isDock() {
		return dock;
	}

	/**
	 * [RefMenuItem.dock] Setter 
	 * @author MAKERRI Sofiane on : 6 mars 2014  08:35:05
	 * @param dock the dock to set
	 */
	public void setDock(boolean dock) {
		this.dock = dock;
	}
	
	
	
	
	

}
