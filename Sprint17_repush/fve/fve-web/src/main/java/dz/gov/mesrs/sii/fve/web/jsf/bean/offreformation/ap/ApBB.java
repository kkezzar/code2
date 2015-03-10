package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.ap;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * Classe de type Backingbean pour la gestion en même temps de la vue Edit et
 * Detail d'une Atome pédagogique
 * 
 * @author kheireddine omrani
 * 
 */

@ManagedBean(name = "apBB")
@ViewScoped
public class ApBB {

	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;

	private AtomePedagogiqueDto ap;

	private List<SelectItem> typeAPList;

	private List<SelectItem> modeEnseignementList;

	private boolean showApDetail;

	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;

	private MatiereConstitutiveDto mc;

	/**
	 * 
	 */
	public ApBB() {
	}

	@PostConstruct
	public void init() {
		ap = new AtomePedagogiqueDto();

	}

	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	// ************** getter/setter des propriété & composants **************/

	public AtomePedagogiqueDto getAp() {
		return ap;
	}

	/**
	 * Obtient la liste des types APs
	 * 
	 * @return La liste des types APs
	 */
	public List<SelectItem> getTypeAPList() {
		if (typeAPList == null)
			typeAPList = utilBean
					.loadNomenclatureLibelleItem(Const.NC_LMD_TYPE_AP);

		return typeAPList;
	}

	/**
	 * Obtient la liste des mode d'enseignements
	 * 
	 * @return La liste des mode d'enseignements
	 */
	public List<SelectItem> getModeEnseignementList() {
		if (modeEnseignementList == null)
			modeEnseignementList = utilBean
					.loadNomenclatureLibelleItem(Const.NC_LMD_MODE_ENSEIGNEMENT);

		return modeEnseignementList;
	}

	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	/**
	 * Appliquer les changements éfféctués sur l'atome pédagogique en cours
	 * 
	 */
	public void saveAp() {

		if (mc == null || mc.getId() == 0) {
			Utility.showErrorMessage("MC manquante");
			return;
		}
		ap.setMatiereConstitutiveId(mc.getId());
		ap = atomePedagogiqueService.insertOrUpdate(ap);

	}

	/**
	 * [ApBB.apAlreadyExist] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 16:12:05
	 * @return
	 */
	public boolean apAlreadyExist() {
		if (ap.getNcTypeApId() != null) {
			AtomePedagogiqueDto _ap = atomePedagogiqueService.findUniqueByType(
					mc.getId(), ap.getNcTypeApId());
			if (_ap != null && _ap.getId() != ap.getId()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * [ApBB.apCodeAlreadyExist] Method 
	 * @author MAKERRI Sofiane  on : 24 nov. 2014  16:13:06
	 * @return
	 */
	public boolean apCodeAlreadyExist() {
		if (ap.getCode() != null && mc.getId() != 0) {
			AtomePedagogiqueDto _ap = atomePedagogiqueService.findUniqueByCode(
					ap.getCode(), mc.getId());
			if (_ap != null && _ap.getId() != ap.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * [ApBB.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 10:04:30
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			ap = ((AtomePedagogiqueDto) event.getObject());
			showApDetail = true;

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [ApBB.showApDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 10:05:44
	 * @return the showApDetail
	 */
	public boolean getShowApDetail() {
		return showApDetail;
	}

	/**
	 * [ApBB.showApDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 10:05:44
	 * @param showApDetail
	 *            the showApDetail to set
	 */
	public void setShowApDetail(boolean showApDetail) {
		this.showApDetail = showApDetail;
	}

	/**
	 * [ApBB.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 10:11:56
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [ApBB.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 10:11:56
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [ApBB.createNewAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:53:16
	 */
	public void createNewAp() {
		ap = new AtomePedagogiqueDto();
	}

	/**
	 * [ApBB.mc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:54:03
	 * @return the mc
	 */
	public MatiereConstitutiveDto getMc() {
		return mc;
	}

	/**
	 * [ApBB.mc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:54:03
	 * @param mc
	 *            the mc to set
	 */
	public void setMc(MatiereConstitutiveDto mc) {
		this.mc = mc;
	}

	/**
	 * [ApBB.ap] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:54:03
	 * @param ap
	 *            the ap to set
	 */
	public void setAp(AtomePedagogiqueDto ap) {
		this.ap = ap;
	}

	/**
	 * [ApBB.deleteAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:41:46
	 */
	public void deleteAp() {
		if (ap != null && ap.getId() != 0) {
			atomePedagogiqueService.remove(ap);
		}
	}

}
