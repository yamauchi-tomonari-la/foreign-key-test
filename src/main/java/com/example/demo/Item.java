package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="item")
public class Item {
	@Id
	@Getter
	@Setter
	private Integer id;
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
	private String name;
}
