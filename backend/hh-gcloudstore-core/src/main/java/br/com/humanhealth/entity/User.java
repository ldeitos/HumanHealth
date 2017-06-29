package br.com.humanhealth.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class User {
	private @Id String id;
	
	private @NonNull String name;
	
	private @NonNull String mail;
	
	private @NonNull UserType type;
}
