package com.bvn.demo.jerseyrest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bvn.demo.jerseyrest.model.Alien;
import com.bvn.demo.jerseyrest.repo.AlienRepository;

@Path("aliens")
public class AlienResource {
	
	AlienRepository repo = new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Alien> getAliens() {
		System.out.println("getAlien called");
		
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien getAlien(@PathParam("id") int id) {
		System.out.println("getAlien called");
		
		return repo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien createAlien(Alien a1) {
		repo.create(a1);
		
		return a1;
	}
	
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien updateAlien(Alien a1) {
		if( repo.getAlien(a1.getId()).getId() == 0 ) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		
		return a1;
	}
	
	@DELETE
	@Path("alien/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien deleteAlien(@PathParam("id") int id) {
		Alien alien = repo.getAlien(id);
		if( alien.getId() != 0 ) {
			repo.delete(id);
		}
		
		return alien;
	}
}
