package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="factory")
public class Factory {
	@Id
	@Getter
	@Setter
	private Integer code;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	@OneToMany(mappedBy="factory")
	private List<Item> items;
}
