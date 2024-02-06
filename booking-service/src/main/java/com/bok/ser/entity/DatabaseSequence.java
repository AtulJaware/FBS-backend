package com.bok.ser.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document("database_Sequences")
public class DatabaseSequence {
	
	@Id
	private String id;
	
	private long seq;

}
