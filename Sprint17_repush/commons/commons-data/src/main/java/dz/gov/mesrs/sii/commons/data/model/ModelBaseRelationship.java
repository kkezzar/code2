package dz.gov.mesrs.sii.commons.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 21-10-2014
 * @description Classe commune pour les entites a cles double
 */
@MappedSuperclass
public abstract class ModelBaseRelationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8063672691152749895L;

	@Embeddable
	public static class Id implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "ENTITY1_ID")
		private Integer entityId1;

		@Column(name = "ENTITY2_ID")
		private Integer entityId2;

		public Integer getEntityId1() {
			return entityId1;
		}

		public void setEntityId1(Integer entityId1) {
			this.entityId1 = entityId1;
		}

		public Integer getEntityId2() {
			return entityId2;
		}

		public void setEntityId2(Integer entityId2) {
			this.entityId2 = entityId2;
		}

		public Id() {
		}

		public Id(Integer entityId1, Integer entityId2) {
			this.entityId1 = entityId1;
			this.entityId2 = entityId2;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((entityId1 == null) ? 0 : entityId1.hashCode());
			result = prime * result + ((entityId2 == null) ? 0 : entityId2.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (entityId1 == null) {
				if (other.entityId1 != null)
					return false;
			} else if (!entityId1.equals(other.entityId1))
				return false;
			if (entityId2 == null) {
				if (other.entityId2 != null)
					return false;
			} else if (!entityId2.equals(other.entityId2))
				return false;
			return true;
		}

	}

	@EmbeddedId
	protected Id id = new Id();

	public Id getId() {
		return id;
	}

	protected void setId(Id theId) {
		id = theId;
	}

}
