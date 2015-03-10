package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertStatistiquesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TransfertStatistiquesTypeTransfertDto;

/**
 *
 * @author Mounir.MESSAOUDI
 */
public interface TransfertService {

    public TransfertDto findById(int id);

    public List<TransfertDto> findByQuery(String query);

    public List<TransfertDto> findByRefCodeEtablissement(String refCodeEtab);


    public List<TransfertDto> findTransfertInterneByQuery(String query);
    public List<TransfertDto> findTransfertInterneByRefCodeEtab(String refCodeEtab);
    public List<TransfertDto> findTransfertInterneByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<TransfertDto> findTransfertExterneByQuery(String query);
    public List<TransfertDto> findTransfertExterneByRefCodeEtab(String refCodeEtab);
    public List<TransfertDto> findTransfertExterneByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
  
    // transferts par type bachelier noveau/ancien
    public List<TransfertDto> findTransfertInterneBachelierByQuery(String query);
    public List<TransfertDto> findTransfertInterneBachelierByRefCodeEtab(String refCodeEtab);
    public List<TransfertDto> findTransfertInterneBachelierByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<TransfertDto> findTransfertExterneBachelierByQuery(String query);
    public List<TransfertDto> findTransfertExterneBachelierByRefCodeEtab(String refCodeEtab);
    public List<TransfertDto> findTransfertExterneBachelierByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);

    // transferts par type transfert
    public List<TransfertDto> findTransfertTypeTransfertByQuery(String query);
    public List<TransfertDto> findTransfertTypeTransfertyRefCodeEtab(String refCodeEtab);
    public List<TransfertDto> findTransfertTypeTransfertByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    public List<TransfertDto> findTransfertTypeTransfertByIdAnnAcadRefCodeEtabRefCodeTypeTransfert(Integer idAnneeAcademique, String refCodeEtab, String refCodeTypeTransdfert);
    
    // transferts sortantes
    public List<TransfertDto> findTransfertExterneSortanteByQuery(String query);
    public List<TransfertDto> findTransfertExterneSortanteByRefCodeEtab(String refCodeEtab);
    public List<TransfertDto> findTransfertExterneSortanteByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);

    /**
     * Recherche par annnee academique et code etablissement
     * @author Mounir.MESSAOUDI on : 31 ao�t 2014 12:54:57 
     * @param idAnneeAcademique
     * @param refCodeEtab
     * @return
     */
    public List<TransfertDto> findByIdAnneeAcademiqueRefCodeEtablissement(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<TransfertDto> findAll();
    
    public List<TransfertStatistiquesDto> findStatsByIdAnneeAcademiqueRefCodeEtablissement(Integer idAnneeAcademique, String refCodeEtab);
    
	public List<TransfertStatistiquesTypeTransfertDto> findStatsByIdAnneeAcademiqueRefCodeEtablissementTypeTransfert(Integer idAnneeAcademique, String refCodeEtab, String refCodeTypeTransfert);

}
