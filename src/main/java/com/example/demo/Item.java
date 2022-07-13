package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="item")
public class Item {
	@Id
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	@ManyToOne
	private Category category;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Factory factory;
	@Getter
	@Setter
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] image;
	
	public String getImageBase64() {
		return Base64.encodeBase64String(image);
	}
}
