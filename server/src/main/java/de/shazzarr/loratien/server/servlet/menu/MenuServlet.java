package de.shazzarr.loratien.server.servlet.menu;

import java.lang.invoke.MethodHandles;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import de.shazzarr.loratien.server.bean.player.Player;
import de.shazzarr.loratien.server.bean.player.PlayerHibernateH2RepositoryImpl;
import de.shazzarr.loratien.server.exception.PlayerNotFoundException;
import de.shazzarr.loratien.server.exception.RegistrationFailureException;
import de.shazzarr.loratien.server.servlet.menu.dto.LoginRequest;
import de.shazzarr.loratien.server.servlet.menu.dto.RegisterRequest;

@Path("/menu")
public class MenuServlet {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final Gson gson = new Gson();
	private final PlayerHibernateH2RepositoryImpl playerRepository = new PlayerHibernateH2RepositoryImpl();

	@POST
	@Path("/player/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(String body) {
		final LoginRequest loginRequest = gson.fromJson(body, LoginRequest.class);
		try {
			final Player player = playerRepository.getByName(loginRequest.getName());
			return Response.status(Response.Status.OK).entity(gson.toJson(player)).build();
		} catch (final PlayerNotFoundException pnfe) {
			log.error(pnfe.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/player/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(String body) {
		final RegisterRequest registerRequest = gson.fromJson(body, RegisterRequest.class);
		final String name = registerRequest.getName();
		try {
			playerRepository.getByName(name);
			log.error("Name is already in use : " + name);
			return Response.status(Response.Status.NOT_MODIFIED).build();
		} catch (final PlayerNotFoundException pnfe1) {
			try {
				final Player player = playerRepository.register(name);
				return Response.status(Response.Status.CREATED).entity(gson.toJson(player)).build();
			} catch (final RegistrationFailureException rfe) {
				log.error(rfe.getMessage());
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		}
	}
}