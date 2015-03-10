package dz.gov.mesrs.sii.fve.business.model.dto.bac;

public class NotesBachelierDto implements java.io.Serializable {

    /**
     * serialVersionUID
     *
     * @author Rafik LAIB on : 21 mai 2014 14:01:46
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private int idDossierBachelier;
    private int idImportation;
    private String refCodeMatiere;
    private String refCodeMatiereLibelleFr;
    private String refCodeMatiereLibelleAr;

    private double note;

    public NotesBachelierDto() {
    }

    /**
     * [NotesBachelierDto.id] Getter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * [NotesBachelierDto.id] Setter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * [NotesBachelierDto.idDossierBachelier] Getter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @return the idDossierBachelier
     */
    public int getIdDossierBachelier() {
        return idDossierBachelier;
    }

    /**
     * [NotesBachelierDto.idDossierBachelier] Setter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @param idDossierBachelier the idDossierBachelier to set
     */
    public void setIdDossierBachelier(int idDossierBachelier) {
        this.idDossierBachelier = idDossierBachelier;
    }

    /**
     * [NotesBachelierDto.idImportation] Getter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @return the idImportation
     */
    public int getIdImportation() {
        return idImportation;
    }

    /**
     * [NotesBachelierDto.idImportation] Setter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @param idImportation the idImportation to set
     */
    public void setIdImportation(int idImportation) {
        this.idImportation = idImportation;
    }

    /**
     * [NotesBachelierDto.refCodeMatiere] Getter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @return the refCodeMatiere
     */
    public String getRefCodeMatiere() {
        return refCodeMatiere;
    }

    /**
     * [NotesBachelierDto.refCodeMatiere] Setter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @param refCodeMatiere the refCodeMatiere to set
     */
    public void setRefCodeMatiere(String refCodeMatiere) {
        this.refCodeMatiere = refCodeMatiere;
    }

    /**
     * [NotesBachelierDto.note] Getter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @return the note
     */
    public double getNote() {
        return note;
    }

    /**
     * [NotesBachelierDto.note] Setter
     *
     * @author Rafik LAIB on : 22 mai 2014 14:17:36
     * @param note the note to set
     */
    public void setNote(double note) {
        this.note = note;
    }

    public String getRefCodeMatiereLibelleAr() {
        return refCodeMatiereLibelleAr;
    }

    public void setRefCodeMatiereLibelleAr(String refCodeMatiereLibelleAr) {
        this.refCodeMatiereLibelleAr = refCodeMatiereLibelleAr;
    }

    public String getRefCodeMatiereLibelleFr() {
        return refCodeMatiereLibelleFr;
    }

    public void setRefCodeMatiereLibelleFr(String refCodeMatiereLibelleFr) {
        this.refCodeMatiereLibelleFr = refCodeMatiereLibelleFr;
    }

}
