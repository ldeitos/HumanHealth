package br.com.humanhealth.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Description {
    private @Id String id;
	
    private @NonNull String language;
	
    private @NonNull String description;
}
