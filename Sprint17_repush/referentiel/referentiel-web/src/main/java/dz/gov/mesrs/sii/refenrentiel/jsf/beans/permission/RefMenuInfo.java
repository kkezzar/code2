/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.RefMenuInfo.java] 
 * @author MAKERRI Sofiane on : 5 mars 2014  15:08:02
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MAKERRI Sofiane  on : 5 mars 2014  15:08:02
 */
public class RefMenuInfo  implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 mars 2014  14:39:12
	 */
	private static final long serialVersionUID = 1L;
	public static List<RefMenuItem> listItems = null;
	/**
	 * [RefMenuInfo.RefMenuInfo()] Constructor
	 * @author MAKERRI Sofiane  on : 5 mars 2014  15:08:02	
	 */
	public static void init() {
		if(listItems == null || listItems.size() == 0) {
			 listItems = new ArrayList<RefMenuItem>();
			 listItems.add(new RefMenuItem("/pages/action/actionSearch.xhtml", "ui-icon-notice", "/images/folder_user148.png", "Action", false));
			 listItems.add(new RefMenuItem("/pages/nomenclature/ncNamesSearch.xhtml", "ui-icon-radio-off", "/images/folder_user148.png", "Nomenclature", false));
			 listItems.add(new RefMenuItem("/pages/nomenclature/ncSearch.xhtml", "ui-icon-bookmark", "/images/folder_user148.png", "Nomenclature", false));
			 listItems.add(new RefMenuItem("/pages/parametrage/paramIhmSearch.xhtml", "ui-icon-grip-diagonal-se", "/images/folder_user148.png", "Nomenclature", false));
			 listItems.add(new RefMenuItem("/pages/permission/permissionSearch.xhtml", "ui-icon-transferthick-e-w", "/images/folder_user148.png", "Nomenclature", false));
		     listItems.add(new RefMenuItem("/pages/individu/individuSearch.xhtml", "ui-icon-person", "/images/folder_user148.png", "Individu", true));
		     listItems.add(new RefMenuItem("/pages/groupe/groupeSearch.xhtml", "ui-icon-bullet", "/images/folder_users48.png", "Groupe", true));
		     listItems.add(new RefMenuItem("/pages/structure/structureSearch.xhtml", "ui-icon-contact", "/images/folder_sites48.png", "Structure", true));
		     listItems.add(new RefMenuItem("/pages/contrat/contratSearch.xhtml", "ui-icon-document", "/images/folder_docs_alt_248.png", "Contrat", true));
		     listItems.add(new RefMenuItem("/pages/avenant/avenantSearch.xhtml", "ui-icon-note", "/images/folder_docs_alt48.png", "Avenant", true));
		     listItems.add(new RefMenuItem("/pages/nomenclature/ncNamesSearch.xhtml", "ui-icon-image", "/images/folder_dev48.png", "NClature", false));
		     listItems.add(new RefMenuItem("/pages/nomenclature/ncSearch.xhtml", "ui-icon-folder-open", "/images/folder_sites_alt48.png", "Items_NC", false));
		     listItems.add(new RefMenuItem("/pages/lieu/lieuSearch.xhtml", "ui-icon-image", "/images/folder_pictures_alt48.png", "Lieu", true));
		     listItems.add(new RefMenuItem("/pages/equipement/equipementSearch.xhtml", "ui-icon-battery-2", "/images/folder_utilities_alt48.png", "Equipement", true));
		     listItems.add(new RefMenuItem("/pages/evenement/evenementSearch.xhtml", "ui-icon-star", "/images/folder_target48.png", "Evenement", true));
		     listItems.add(new RefMenuItem("/pages/document/documentSearch.xhtml", "ui-icon-video", "/images/folder_docs_alt48.png", "Document", true));
		     listItems.add(new RefMenuItem("/pages/domaine/domaineSearch.xhtml", "ui-icon-script", "/images/folder_domaines48.png", "Domaines applicatifs", false));
		     listItems.add(new RefMenuItem("/pages/module/moduleSearch.xhtml", "ui-icon-person", "/images/folder_domaines48.png", "Modules", false));
		     listItems.add(new RefMenuItem("/pages/fonction/fonctionSearch.xhtml", "ui-icon-grip-diagonal-se", "/images/folder_domaines48.png", "Fonctions", false));
		     listItems.add(new RefMenuItem("/pages/compte/compteSearch.xhtml", "ui-icon-radio-off", "/images/User_Icon_48.png", "Compte", true));
		     listItems.add(new RefMenuItem("/pages/horaireaccess/horaireAccessSearch.xhtml", "ui-icon-calendar", "/images/User_Icon_48.png", "horaires d'acces", false));
		     listItems.add(new RefMenuItem("/pages/plageadresse/plageAdresseSearch.xhtml", "ui-icon-link", "/images/User_Icon_48.png", "Plage d'adresses", false));
		     listItems.add(new RefMenuItem("/pages/parametrage/paramConfigSearch.xhtml", "ui-icon-grip-diagonal-se", "/images/User_Icon_48.png", "Plage d'adresses", false));
		     listItems.add(new RefMenuItem("/pages/periode/periodeSearch.xhtml", "ui-icon-volume-off", "/images/User_Icon_48.png", "Plage d'adresses", false));
		     listItems.add(new RefMenuItem("/pages/etablissement/etabSearch.xhtml", "ui-icon-flag", "/images/User_Icon_48.png", "Etablissement", false));
		     listItems.add(new RefMenuItem("/pages/configuration/parametreSearch.xhtml", "ui-icon-clipboard", null, null, false));
		     listItems.add(new RefMenuItem("/pages/domainelmd/domainelmdSearch.xhtml", "ui-icon-bullet", null, null, false));
		     listItems.add(new RefMenuItem("/pages/filierelmd/filierelmdSearch.xhtml", "ui-icon-radio-off", null, null, false));
		     listItems.add(new RefMenuItem("/pages/specialitelmd/specialitelmdSearch.xhtml", "ui-icon-radio-on", null, null, false));
		     listItems.add(new RefMenuItem("/pages/configuration/parametreEtabSearch.xhtml", "ui-icon-bookmark", null, null, false));
		     listItems.add(new RefMenuItem("/pages/nomenclature/typePieceSearch.xhtml", "ui-icon-lightbulb", null, null, false));
		}
		
	}
	
	/**
	 * [RefMenuInfo.findItemInfo] Method 
	 * @author MAKERRI Sofiane  on : 5 mars 2014  15:27:41
	 * @param url
	 * @return
	 */
	public static RefMenuItem  findItemInfo(String url) {
		
		for(RefMenuItem refMenuItem : listItems) {
			if(refMenuItem.getUrl().equals(url)) {
				return refMenuItem;
			}
		}
		
		return null;
		
	}

}
