package com.emasagae.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.*;

/**
 * The @Entity tells Objectify about our entity.  We also register it in {@link OfyHelper}
 * Our primary key @Id is set automatically by the Google Datastore for us.
 *
 * We add a @Parent to tell the object about its ancestor. We are doing this to support many
 * guestbooks.  Objectify, unlike the AppEngine library requires that you specify the fields you
 * want to index using @Index.  Only indexing the fields you need can lead to substantial gains in
 * performance -- though if not indexing your data from the start will require indexing it later.
 *
 * NOTE - all the properties are PUBLIC so that we can keep the code simple.
 **/

@Entity
public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Objectify auto-generates Long IDs just like JDO / JPA
	@Id private Long id;
	
	public Report() {
		// Empty constructor needed for GWT serialization and Objectify
	}

}
