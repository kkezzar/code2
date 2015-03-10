/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.TypePieceBckBean.java] 
 * @author MAKERRI Sofiane on : 25 mai 2014  11:39:56
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 25 mai 2014 11:39:56
 */
@ManagedBean(name = "typePieceBckBean")
@RequestScoped
public class TypePieceBckBean implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:40:19
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(TypePieceBckBean.class);
	@ManagedProperty(value = "#{refTypePieceConstitutiveServiceImpl}")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveServiceImpl;
	private List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto;
	private ResourceBundle bundle;
	@ManagedProperty("#{param.idTd}")
	private String idTd;
	@ManagedProperty("#{param.id}")
	private String id;
	private Integer idTp;
	private Integer idTypeDossier;
	private RefTypePieceConstitutiveDto refTypePieceConstitutiveDto;
	private boolean numberChange;
	private boolean rangChange;
	private boolean typePieceChange;
	private int rang = 1;
	private List<SelectItem> listItemTypePiece;
	private List<NomenclatureDto> listTypePiece;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;

	/**
	 * [TypePieceBckBean.TypePieceBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:39:56
	 */
	public TypePieceBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {

		if (idTp != null) {
			refTypePieceConstitutiveDto = refTypePieceConstitutiveServiceImpl
					.findById(idTp);
		}
		// Integer id = UtilConstant.strToInt(idTd);

		if (refTypePieceConstitutiveDto == null) {
			refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
			if (idTp == null && idTypeDossier != null) {
				NomenclatureDto typeDossier = nomenclatureServiceImpl
						.findById(idTypeDossier);
				refTypePieceConstitutiveDto.setNcLcLatinTypeDossier(typeDossier
						.getLibelleCourtFr());
				refTypePieceConstitutiveDto.setNcLlArabeTypeDossier(typeDossier
						.getLibelleLongAr());
				refTypePieceConstitutiveDto.setNcLlLatinTypeDossier(typeDossier
						.getLibelleLongFr());
				refTypePieceConstitutiveDto.setAFournir(true);
				int rang = refTypePieceConstitutiveServiceImpl.findLastRang(
						idTypeDossier,
						refTypePieceConstitutiveDto.getAFournir());
				if(rang == 0) {
					rang++;
				}
				refTypePieceConstitutiveDto.setRang(rang);
				
			}
		}

		if (idTypeDossier != null) {
			listRefTypePieceConstitutiveDto = refTypePieceConstitutiveServiceImpl
					.findByIdTypeDossier(idTypeDossier,
							refTypePieceConstitutiveDto.getAFournir());
			listTypePiece = nomenclatureServiceImpl
					.findTypePiece(idTypeDossier);
			loadListItemTypePiece();
		}
	}

	/**
	 * [TypePieceBckBean.refTypePieceConstitutiveServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @return the refTypePieceConstitutiveServiceImpl
	 */
	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveServiceImpl() {
		return refTypePieceConstitutiveServiceImpl;
	}

	/**
	 * [TypePieceBckBean.refTypePieceConstitutiveServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @param refTypePieceConstitutiveServiceImpl
	 *            the refTypePieceConstitutiveServiceImpl to set
	 */
	public void setRefTypePieceConstitutiveServiceImpl(
			RefTypePieceConstitutiveService refTypePieceConstitutiveServiceImpl) {
		this.refTypePieceConstitutiveServiceImpl = refTypePieceConstitutiveServiceImpl;
	}

	/**
	 * [TypePieceBckBean.listRefTypePieceConstitutiveDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @return the listRefTypePieceConstitutiveDto
	 */
	public List<RefTypePieceConstitutiveDto> getListRefTypePieceConstitutiveDto() {
		return listRefTypePieceConstitutiveDto;
	}

	/**
	 * [TypePieceBckBean.listRefTypePieceConstitutiveDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @param listRefTypePieceConstitutiveDto
	 *            the listRefTypePieceConstitutiveDto to set
	 */
	public void setListRefTypePieceConstitutiveDto(
			List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto) {
		this.listRefTypePieceConstitutiveDto = listRefTypePieceConstitutiveDto;
	}

	/**
	 * [TypePieceBckBean.idTd] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 16:19:12
	 * @return the idTd
	 */
	public String getIdTd() {
		return idTd;
	}

	/**
	 * [TypePieceBckBean.idTd] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 16:19:12
	 * @param idTd
	 *            the idTd to set
	 */
	public void setIdTd(String idTd) {
		if ((idTd != null) && (idTd.equals("null"))) {
			this.idTd = null;
		} else {
			int _id = RefUtilConstant.strToInt(idTd);
			if (_id != -1) {
				setIdTypeDossier(_id);
			}
			this.idTd = idTd;
		}
	}

	/**
	 * [TypePieceBckBean.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * [TypePieceBckBean.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		if ((id != null) && (id.equals("null"))) {
			this.id = null;
		} else {
			int _id = RefUtilConstant.strToInt(id);
			if (_id != -1) {
				setIdTp(_id);
			}
			this.id = id;
		}

	}

	/**
	 * [TypePieceBckBean.refTypePieceConstitutiveDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @return the refTypePieceConstitutiveDto
	 */
	public RefTypePieceConstitutiveDto getRefTypePieceConstitutiveDto() {
		return refTypePieceConstitutiveDto;
	}

	/**
	 * [TypePieceBckBean.refTypePieceConstitutiveDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:42:52
	 * @param refTypePieceConstitutiveDto
	 *            the refTypePieceConstitutiveDto to set
	 */
	public void setRefTypePieceConstitutiveDto(
			RefTypePieceConstitutiveDto refTypePieceConstitutiveDto) {
		this.refTypePieceConstitutiveDto = refTypePieceConstitutiveDto;
	}

	/**
	 * [TypePieceBckBean.idTp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:44:50
	 * @return the idTp
	 */
	public Integer getIdTp() {
		return idTp;
	}

	/**
	 * [TypePieceBckBean.idTp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 11:44:50
	 * @param idTp
	 *            the idTp to set
	 */
	public void setIdTp(Integer idTp) {
		this.idTp = idTp;
	}

	/**
	 * [TypePieceBckBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:09:56
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		if (refTypePieceConstitutiveDto != null && idTypeDossier != null) {
			refTypePieceConstitutiveDto.setIdTypeDossier(idTypeDossier);
		}
		String msgType = (refTypePieceConstitutiveDto.getId() == null) ? "msg_success_enregistrement" :  "msg_success_modification";
		try {
			if (refTypePieceConstitutiveDto != null
					&& refTypePieceConstitutiveDto.getIdTypePiece() != null
					&& idTypeDossier != null) {
				refTypePieceConstitutiveServiceImpl
						.insertOrUpdate(refTypePieceConstitutiveDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString(msgType));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;

			} /*
			 * else { ncNamesDto = ncNamesServiceImpl.save(ncNamesDto);
			 * msg.setSeverity(FacesMessage.SEVERITY_INFO);
			 * msg.setSummary(bundle.getString("msg_success") + ": " +
			 * bundle.getString("msg_success_enregistrement"));
			 * FacesContext.getCurrentInstance().addMessage(null, msg);
			 * setIdNc(ncNamesDto.getId().toString()); }
			 */
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
			return;
		}

	}

	/**
	 * [TypePieceBckBean.newItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:11:04
	 */
	public void newItem() {
		refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
	}

	/**
	 * [TypePieceBckBean.numberChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:14:39
	 * @param event
	 */
	public void numberChanged(ValueChangeEvent event) {
		numberChange = true;
	}

	/**
	 * [TypePieceBckBean.rangChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:14:53
	 * @param event
	 */
	public void rangChanged(ValueChangeEvent event) {
		rangChange = true;
	}

	/**
	 * [TypePieceBckBean.numberChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:13:28
	 * @return the numberChange
	 */
	public boolean isNumberChange() {
		return numberChange;
	}

	/**
	 * [TypePieceBckBean.numberChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:13:28
	 * @param numberChange
	 *            the numberChange to set
	 */
	public void setNumberChange(boolean numberChange) {
		this.numberChange = numberChange;
	}

	/**
	 * [TypePieceBckBean.rangChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:13:28
	 * @return the rangChange
	 */
	public boolean isRangChange() {
		return rangChange;
	}

	/**
	 * [TypePieceBckBean.rangChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:13:28
	 * @param rangChange
	 *            the rangChange to set
	 */
	public void setRangChange(boolean rangChange) {
		this.rangChange = rangChange;
	}

	/**
	 * [TypePieceBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 12:15:55
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [TypePieceBckBean.saveRang] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 16:04:17
	 */
	public void saveRang() {
		log.info("save rang");
		if (rangChange) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			try {

				RefTypePieceConstitutiveDto _refTypePieceConstitutiveDto = refTypePieceConstitutiveServiceImpl
						.findById(Integer.parseInt(value));
				if (_refTypePieceConstitutiveDto.getRang() != rang) {
					_refTypePieceConstitutiveDto.setRang(rang);
					refTypePieceConstitutiveServiceImpl
							.update(_refTypePieceConstitutiveDto);
					if (id != null
							&& _refTypePieceConstitutiveDto.getId().equals(
									Integer.parseInt(id))) {
						_refTypePieceConstitutiveDto.setRang(rang);
						setRangChange(true);
						setRefTypePieceConstitutiveDto(_refTypePieceConstitutiveDto);
					}
				}
				rang++;
				return _refTypePieceConstitutiveDto;
			} catch (ConverterException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"invalid domaine"));
			} catch (RuntimeException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Update Error",
						"erreur de mise � jour"));
			}

		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		log.info("getAsString Object:" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((RefTypePieceConstitutiveDto) value).getId().toString();
		}
	}

	public void typePieceChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			typePieceChange = true;
			// fonctionChange = true;
			// listFonctionDto =
			// refFonctionServiceImpl.findFonctions((Integer)event.getNewValue());
		}
		setId(event.getNewValue().toString());
	}

	

	
	/**
	 * [TypePieceBckBean.typePieceChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 17:09:10
	 * @return the typePieceChange
	 */
	public boolean isTypePieceChange() {
		return typePieceChange;
	}

	/**
	 * [TypePieceBckBean.typePieceChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 17:09:10
	 * @param typePieceChange
	 *            the typePieceChange to set
	 */
	public void setTypePieceChange(boolean typePieceChange) {
		this.typePieceChange = typePieceChange;
	}

	/**
	 * [TypePieceBckBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 17:29:49
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [TypePieceBckBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 17:29:49
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [TypePieceBckBean.listItemTypePiece] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 18:08:45
	 * @return the listItemTypePiece
	 */
	public List<SelectItem> getListItemTypePiece() {
		return listItemTypePiece;
	}

	/**
	 * [TypePieceBckBean.listItemTypePiece] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 18:08:45
	 * @param listItemTypePiece
	 *            the listItemTypePiece to set
	 */
	public void setListItemTypePiece(List<SelectItem> listItemTypePiece) {
		this.listItemTypePiece = listItemTypePiece;
	}

	/**
	 * [TypePieceBckBean.listTypePiece] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 18:08:45
	 * @return the listTypePiece
	 */
	public List<NomenclatureDto> getListTypePiece() {
		return listTypePiece;
	}

	/**
	 * [TypePieceBckBean.listTypePiece] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 18:08:45
	 * @param listTypePiece
	 *            the listTypePiece to set
	 */
	public void setListTypePiece(List<NomenclatureDto> listTypePiece) {
		this.listTypePiece = listTypePiece;
	}

	/**
	 * [TypePieceBckBean.loadListItemTypePiece] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 18:16:14
	 */
	public void loadListItemTypePiece() {
		listItemTypePiece = new ArrayList<SelectItem>();
		for (NomenclatureDto typePiece : listTypePiece) {
			SelectItem selectItem = new SelectItem(typePiece.getId(),
					typePiece.getLibelleLongFr());
			listItemTypePiece.add(selectItem);
		}
	}

	/**
	 * [TypePieceBckBean.idTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 09:16:32
	 * @return the idTypeDossier
	 */
	public Integer getIdTypeDossier() {
		return idTypeDossier;
	}

	/**
	 * [TypePieceBckBean.idTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 09:16:32
	 * @param idTypeDossier
	 *            the idTypeDossier to set
	 */
	public void setIdTypeDossier(Integer idTypeDossier) {
		this.idTypeDossier = idTypeDossier;
	}

	/**
	 * [TypePieceBckBean.fournirChanged] Method 
	 * @author MAKERRI Sofiane  on : 26 mai 2014  10:33:16
	 */
	public void fournirChanged() {

		int rang = refTypePieceConstitutiveServiceImpl.findLastRang(
				idTypeDossier, refTypePieceConstitutiveDto.getAFournir());
		refTypePieceConstitutiveDto.setRang(rang);


	}

}
