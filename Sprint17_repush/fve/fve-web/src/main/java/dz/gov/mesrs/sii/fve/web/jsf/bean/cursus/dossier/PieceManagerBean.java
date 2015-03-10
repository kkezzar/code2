/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.dossier.PCManagerBean.java] 
 * @author MAKERRI Sofiane on : 21 mai 2014  13:55:59
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.PieceConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.PieceConstitutiveService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;

/**
 * @author MAKERRI Sofiane on : 21 mai 2014 13:55:59
 * @updated MESSAOUDI Mounir on : 04 octobre 2014 16:00:00
 * 
 *          Le managed bean n'utilise plus les webservice
 */
/**
 * @author MAKERRI Sofiane  on : 4 déc. 2014  12:45:49
 */
/**
 * @author MAKERRI Sofiane on : 4 déc. 2014 12:45:50
 */
@ManagedBean(name = "pieceManagerBean")
@ViewScoped
public class PieceManagerBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:56:08
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{pieceConstitutiveService}")
	private PieceConstitutiveService pieceConstitutiveService;
	private String codeTypeDossier;

	private Integer idTypeDossier;

	private Integer id_Dossier;
	private List<PieceConstitutiveDto> listPieceConstitutiveDto;
	private List<PieceConstitutiveDto> listPcProvided;
	private List<PieceConstitutiveDto> listPcToProvid;
	private List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto;

	private ResourceBundle tpBundle;
	@ManagedProperty(value = "#{dossierService}")
	private DossierService dossierService;
	private DossierDto dossierDto;
	private String dossierId;
	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;
	private boolean disableSave;

	@ManagedProperty(value = "#{refTypePieceConstitutiveServiceImpl}")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveService;
	private boolean allProvided;
	private boolean allToProvid;
	private boolean readOnly;

	/**
	 * [PCManagerBean.PCManagerBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:55:59
	 */
	public PieceManagerBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		tpBundle = facesContext.getApplication().getResourceBundle(facesContext,
				CursusConstants.PIECE_CONSTITUTIVE_BUNDLE_MSG_NAME);
	}

	@PostConstruct
	public void init() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		String _codeTypeDossier = (String) request.getAttribute("codeTypeDossier");

		if (codeTypeDossier == null) {
			setCodeTypeDossier(_codeTypeDossier);
		}

		// recup avec c:set pour conge academique
		String _dossierId = (Integer) request.getAttribute("dossierId") + "";

		if (dossierId == null) {
			setDossierId(_dossierId);
		}

		// loadTypePieceConstitutive();
	}

	/**
	 * [PCManagerBean.loadTypePieceConstitutive] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 15:02:28
	 * @param codeTypeDossier
	 * @param annee
	 * @return
	 */
	public void loadTypePieceConstitutive() {

		listRefTypePieceConstitutiveDto = new ArrayList<RefTypePieceConstitutiveDto>();
		listPieceConstitutiveDto = new ArrayList<PieceConstitutiveDto>();
		listPcProvided = new ArrayList<PieceConstitutiveDto>();
		listPcToProvid = new ArrayList<PieceConstitutiveDto>();
		allProvided = true;
		allToProvid = true;
		try {
			if (id_Dossier != null) {
				dossierDto = dossierService.findById(id_Dossier);
			}
			// if (dossierDto == null || dossierDto.getDateCreation() == null) {
			// if (idTypeDossier != null)
			// listRefTypePieceConstitutiveDto = refTypePieceConstitutiveService
			// .findByIdTypeDossier(idTypeDossier);
			// } else {
			// listRefTypePieceConstitutiveDto = refTypePieceConstitutiveService
			// .findByCodeTypeDossierDate(codeTypeDossier,
			// dossierDto.getDateCreation());
			// }

			List<PieceConstitutiveDto> list = pieceConstitutiveService.findByIdDossier(id_Dossier);
			if (list != null) {
				for (PieceConstitutiveDto piece : list) {
					if (piece.getRefTypePieceAFournir() != null && piece.getRefTypePieceAFournir()) {

						if (piece.getDateFourniture() == null) {
							piece.setDateFourniture(new Date());
						}

						listPcProvided.add(piece);
						if (!piece.getFournie()) {
							allProvided = false;
						}
					} else {
						listPcToProvid.add(piece);
						if (!piece.getFournie()) {
							allToProvid = false;
						}
					}
				}
			}

			// for (RefTypePieceConstitutiveDto current :
			// listRefTypePieceConstitutiveDto) {
			// if (id_Dossier != null) {
			// pieceConstitutiveDto = pieceConstitutiveService
			// .findByIdDossierAndCodePiece(id_Dossier,
			// current.getIdTypePiece());
			// }
			// if (pieceConstitutiveDto == null) {
			// pieceConstitutiveDto = new PieceConstitutiveDto();
			// if (idTypeDossier != null) {
			// pieceConstitutiveDto.setIdTypeDossier(idTypeDossier);
			// }
			// pieceConstitutiveDto.setFournie(false);
			// }
			// // mapper.map(current, pieceConstitutiveDto);
			// pieceConstitutiveDto.setPieceAFournir(current.getAFournir());
			// if (current.getAFournir()
			// && pieceConstitutiveDto.getDateFourniture() == null) {
			// pieceConstitutiveDto.setDateFourniture(new Date());
			// }
			// //pieceConstitutiveDto.setObligatoire(current.getObligatoire());
			// pieceConstitutiveDto.setPieceRang(current.getRang());
			// pieceConstitutiveDto.setNcTypePieceCode(current
			// .getCodeTypePiece());
			// pieceConstitutiveDto.setNombre(current.getNombre());
			// //pieceConstitutiveDto.setObligatoire(current.getObligatoire());
			// pieceConstitutiveDto.setNcTypePieceLibelleLongFr(current
			// .getNcLlLatinTypePiece());
			// if (pieceConstitutiveDto.getPieceAFournir() != null
			// && pieceConstitutiveDto.getPieceAFournir()) {
			// listPcProvided.add(pieceConstitutiveDto);
			// if (!pieceConstitutiveDto.getFournie()) {
			// allProvided = false;
			// }
			// } else {
			// listPcToProvid.add(pieceConstitutiveDto);
			// if (!pieceConstitutiveDto.getFournie()) {
			// allToProvid = false;
			// }
			// }
			// // listPieceConstitutiveDto.add(pieceConstitutiveDto);
			//
			// }
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * [PCManagerBean.pieceConstitutiveService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:59:27
	 * @return the pieceConstitutiveService
	 */
	public PieceConstitutiveService getPieceConstitutiveService() {
		return pieceConstitutiveService;
	}

	/**
	 * [PCManagerBean.pieceConstitutiveService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:59:27
	 * @param pieceConstitutiveService
	 *            the pieceConstitutiveService to set
	 */
	public void setPieceConstitutiveService(PieceConstitutiveService pieceConstitutiveService) {
		this.pieceConstitutiveService = pieceConstitutiveService;
	}

	/**
	 * [PCManagerBean.codeTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 14:08:12
	 * @return the codeTypeDossier
	 */
	public String getCodeTypeDossier() {
		return codeTypeDossier;
	}

	/**
	 * [PCManagerBean.codeTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 14:08:12
	 * @param codeTypeDossier
	 *            the codeTypeDossier to set
	 */
	public void setCodeTypeDossier(String codeTypeDossier) {
		if ((codeTypeDossier != null) && (codeTypeDossier.equals("null"))) {
			this.codeTypeDossier = null;
		} else {
			this.codeTypeDossier = codeTypeDossier;
		}

	}

	/**
	 * [PCManagerBean.listPieceConstitutiveDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 14:09:16
	 * @return the listPieceConstitutiveDto
	 */
	public List<PieceConstitutiveDto> getListPieceConstitutiveDto() {
		return listPieceConstitutiveDto;
	}

	/**
	 * [PCManagerBean.listPieceConstitutiveDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 14:09:16
	 * @param listPieceConstitutiveDto
	 *            the listPieceConstitutiveDto to set
	 */
	public void setListPieceConstitutiveDto(List<PieceConstitutiveDto> listPieceConstitutiveDto) {
		this.listPieceConstitutiveDto = listPieceConstitutiveDto;
	}

	/**
	 * [PCManagerBean.id_Dossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 14:10:58
	 * @return the id_Dossier
	 */
	public Integer getId_Dossier() {
		return id_Dossier;
	}

	/**
	 * [PCManagerBean.id_Dossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 14:10:58
	 * @param id_Dossier
	 *            the id_Dossier to set
	 */
	public void setId_Dossier(Integer id_Dossier) {
		this.id_Dossier = id_Dossier;
	}

	/**
	 * [PCManagerBean.listRefTypePieceConstitutiveDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 16:23:53
	 * @return the listRefTypePieceConstitutiveDto
	 */
	public List<RefTypePieceConstitutiveDto> getListRefTypePieceConstitutiveDto() {
		return listRefTypePieceConstitutiveDto;
	}

	/**
	 * [PCManagerBean.listRefTypePieceConstitutiveDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 16:23:53
	 * @param listRefTypePieceConstitutiveDto
	 *            the listRefTypePieceConstitutiveDto to set
	 */
	public void setListRefTypePieceConstitutiveDto(List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto) {
		this.listRefTypePieceConstitutiveDto = listRefTypePieceConstitutiveDto;
	}

	/**
	 * [PieceManagerBean.listPcProvided] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 12:14:46
	 * @return the listPcProvided
	 */
	public List<PieceConstitutiveDto> getListPcProvided() {
		return listPcProvided;
	}

	/**
	 * [PieceManagerBean.listPcProvided] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 12:14:46
	 * @param listPcProvided
	 *            the listPcProvided to set
	 */
	public void setListPcProvided(List<PieceConstitutiveDto> listPcProvided) {
		this.listPcProvided = listPcProvided;
	}

	/**
	 * [PieceManagerBean.listPcToProvid] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 12:14:46
	 * @return the listPcToProvid
	 */
	public List<PieceConstitutiveDto> getListPcToProvid() {
		return listPcToProvid;
	}

	/**
	 * [PieceManagerBean.listPcToProvid] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 12:14:46
	 * @param listPcToProvid
	 *            the listPcToProvid to set
	 */
	public void setListPcToProvid(List<PieceConstitutiveDto> listPcToProvid) {
		this.listPcToProvid = listPcToProvid;
	}

	/**
	 * [PieceManagerBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 14:27:18
	 */
	public String save() {

		try {
			for (PieceConstitutiveDto current : listPcProvided) {
				if (current.getFournie() && current.getDateFourniture() == null) {
					Utility.showErrorMessage(tpBundle.getString("piece_data_table_required_date_fourniture") + "("
							+ current.getNcTypePieceLibelleLongFr() + ")");
					return "NOK";
				}
				pieceConstitutiveService.insertOrUpdate(current);
			}
			for (PieceConstitutiveDto current : listPcToProvid) {
				if (current.getFournie() && current.getDateFourniture() == null) {
					Utility.showErrorMessage(tpBundle.getString("piece_data_table_required_date_fourniture") + "("
							+ current.getNcTypePieceLibelleLongFr() + ")");
					return "NOK";
				}
				pieceConstitutiveService.insertOrUpdate(current);

			}

			if (!disableSave) {
				Utility.showSuccessUpdateMessage();
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			return "NOK";

		}
		return "OK";

	}

	/**
	 * [PieceManagerBean.checkChange] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 12:32:37
	 * @param event
	 */
	public void checkChange(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event.getComponent();
		PieceConstitutiveDto _pieceConstitutiveDto = (PieceConstitutiveDto) permit.getAttributes()
				.get("selectedRecord");
		boolean checked = (Boolean) permit.getValue();
		_pieceConstitutiveDto.setFournie(checked);
	}

	/**
	 * [PieceManagerBean.dossierService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 juin 2014 10:58:09
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [PieceManagerBean.dossierService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 juin 2014 10:58:09
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [PieceManagerBean.dossierDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 juin 2014 11:06:29
	 * @return the dossierDto
	 */
	public DossierDto getDossierDto() {
		return dossierDto;
	}

	/**
	 * [PieceManagerBean.dossierDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 juin 2014 11:06:29
	 * @param dossierDto
	 *            the dossierDto to set
	 */
	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	/**
	 * [PieceManagerBean.dossierId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 juin 2014 14:15:09
	 * @return the dossierId
	 */
	public String getDossierId() {
		return dossierId;
	}

	/**
	 * [PieceManagerBean.dossierId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 juin 2014 14:15:09
	 * @param dossierId
	 *            the dossierId to set
	 */
	public void setDossierId(String dossierId) {
		if ((dossierId != null) && (dossierId.equals("null"))) {
			this.dossierId = null;
		} else {
			try {
				if (dossierId != null) {
					this.dossierId = dossierId;
					int id = Integer.parseInt(dossierId);
					setId_Dossier(id);
				} else
					setId_Dossier(0);
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [PieceManagerBean.dossierEtudiantBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 juin 2014 16:59:17
	 * @return the dossierEtudiantBean
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * [PieceManagerBean.dossierEtudiantBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 juin 2014 16:59:17
	 * @param dossierEtudiantBean
	 *            the dossierEtudiantBean to set
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		this.dossierEtudiantBean = dossierEtudiantBean;
		// if (dossierEtudiantBean != null && dossierId == null) {
		// setDossierId(dossierEtudiantBean.getDossierEtudiantId());
		// }
		// if (dossierEtudiantBean != null && codeTypeDossier == null) {
		// setCodeTypeDossier(CursusConstants.CODE_TYPE_DOSSIER_ETUDIANT);
		// }
	}

	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveService() {
		return refTypePieceConstitutiveService;
	}

	public void setRefTypePieceConstitutiveService(RefTypePieceConstitutiveService refTypePieceConstitutiveService) {
		this.refTypePieceConstitutiveService = refTypePieceConstitutiveService;
	}

	/**
	 * [PieceManagerBean.disableSave] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 15:31:04
	 * @return the disableSave
	 */
	public boolean isDisableSave() {
		return disableSave;
	}

	/**
	 * [PieceManagerBean.disableSave] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 15:31:04
	 * @param disableSave
	 *            the disableSave to set
	 */
	public void setDisableSave(boolean disableSave) {
		this.disableSave = disableSave;
	}

	/**
	 * [PieceManagerBean.getIdTypeDossier] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:29:44
	 * @return
	 */
	public Integer getIdTypeDossier() {
		return idTypeDossier;
	}

	/**
	 * [PieceManagerBean.setIdTypeDossier] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:29:48
	 * @param idTypeDossier
	 */
	public void setIdTypeDossier(Integer idTypeDossier) {
		this.idTypeDossier = idTypeDossier;
	}

	/**
	 * [PieceManagerBean.pieceProvidedCheckAll] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 12:43:42
	 */
	public void pieceProvidedCheckAll() {
		if (listPcProvided != null) {
			for (PieceConstitutiveDto piece : listPcProvided) {
				piece.setFournie(allProvided);
			}
		}
	}

	/**
	 * [PieceManagerBean.pieceToProvidCheckAll] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 12:45:53
	 */
	public void pieceToProvidCheckAll() {
		if (listPcToProvid != null) {
			for (PieceConstitutiveDto piece : listPcToProvid) {
				piece.setFournie(allToProvid);
			}
		}
	}

	/**
	 * [PieceManagerBean.allProvided] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:25:54
	 * @return the allProvided
	 */
	public boolean isAllProvided() {
		return allProvided;
	}

	/**
	 * [PieceManagerBean.allProvided] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:25:54
	 * @param allProvided
	 *            the allProvided to set
	 */
	public void setAllProvided(boolean allProvided) {
		this.allProvided = allProvided;
	}

	/**
	 * [PieceManagerBean.allToProvid] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:25:54
	 * @return the allToProvid
	 */
	public boolean isAllToProvid() {
		return allToProvid;
	}

	/**
	 * [PieceManagerBean.allToProvid] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:25:54
	 * @param allToProvid
	 *            the allToProvid to set
	 */
	public void setAllToProvid(boolean allToProvid) {
		this.allToProvid = allToProvid;
	}

	/**
	 * [PieceManagerBean.readOnly] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:32:28
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * [PieceManagerBean.readOnly] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 15:32:28
	 * @param readOnly
	 *            the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
