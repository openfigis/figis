package org.fao.dataloading.countryrfb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "FS_OBSERVATION_XML", schema = "figis")
public class ObservationXml implements Serializable {

	@Id
	@Column(name = "CD_XML", unique = true, nullable = false, length = 765)
	private String id;

	@Lob
	private String xml;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2956869464682524359L;

}
