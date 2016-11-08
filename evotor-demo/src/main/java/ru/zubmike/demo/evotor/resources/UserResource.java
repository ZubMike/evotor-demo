package ru.zubmike.demo.evotor.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import ru.zubmike.demo.evotor.logic.UserLogic;
import ru.zubmike.demo.evotor.types.dto.UserRequest;

@Path("/users")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class UserResource {

	private final UserLogic userLogic;

	@Inject
	public UserResource(ResourceConfig resourceConfig, UserLogic userLogic) {
		resourceConfig.register(this);
		this.userLogic = userLogic;
	}
	
	@POST
	public Object getBalance(UserRequest request) {
		return userLogic.executeRequest(request);
	}
}
