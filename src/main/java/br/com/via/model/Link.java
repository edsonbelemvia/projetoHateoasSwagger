package br.com.via.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "links")
@Entity
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLink")
	@JsonProperty("idLink")
	@JsonIgnore
	private Integer idLink;

	@JsonProperty("rel")
	@Column(name = "rel")
	private String rel;

	@JsonProperty("href")
	@Column(name = "href")
	private String href;

	@JsonProperty("type")
	@Column(name = "type")
	private String type;

	@JsonProperty("action")
	@Column(name = "action")
	private String action;

	public Link() {

	}

	public Link(Integer idLink, String rel, String href, String type, String action) {
		this.idLink = idLink;
		this.rel = rel;
		this.href = href;
		this.type = type;
		this.action = action;
	}

	@Override
	public String toString() {
		return "Link [idLink=" + idLink + ", rel=" + rel + ", href=" + href + ", type=" + type + ", action=" + action
				+ "]";
	}

	public Integer getIdLink() {
		return idLink;
	}

	public void setIdLink(Integer idLink) {
		this.idLink = idLink;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
