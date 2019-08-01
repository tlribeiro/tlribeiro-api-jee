package br.com.tlr.api;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.tlr.domain.IUser;
import br.com.tlr.domain.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Api de usuario.
 * 
 * @author Thiago Ribeiro
 * @since 31.07.2019 23:52
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class UserApi {

	@Inject
	private IUser iuser;

	@GET
	public Response getAll() {
		// Variaveis de resultado.
		Response result = null;
		List<User> resultList;

		try {
			// Realiza a pesquisa.
			resultList = iuser.getList();

			// Define o resultado.
			result = Response.status(Status.OK).entity(resultList).build();
		} catch (Exception e) {
			// Registra a pilha do erro.
			e.printStackTrace();
			// Informa que houve um erro interno.
			result = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		// Retorna a lista.
		return result;
	}

	@POST
	public Response save(User user, @Context HttpServletRequest req, @Context UriInfo uriInfo) {

		// Variaveis.
		UriBuilder builder = null;
		Response response = null;

		try {
			// Valida se usu�rio n�o foi informado.
			// Valida se usu�rio j� tem um id.
			// Caso uma das duas valida��es seja verdadeira retorna um status
			// 400 - BAD_REQUEST.
			if (user == null || user.getUserId() != null) {
				return Response.status(Status.BAD_REQUEST).build();
			} else {
				// Salva o usu�rio no banco de dados.
				User result = this.iuser.save(user);
				// Pega a url absoluta.
				builder = uriInfo.getAbsolutePathBuilder();
				// Gera a url que o recurso estar� dispon�vel.
				// � retornado na resposta no atributo 'Location'.
				builder.path(Long.toString(result.getUserId()));
				// Gera a resposta e retorna o resultado.
				response = Response.created(builder.build()).build();
			}
		} catch (Exception e) {
			// TODO Remover depois que a api estiver est�vel.
			e.printStackTrace();
			// Informa que houve um erro no servidor, mas n�o informa a causa.
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		// Resposta da opera��o.
		return response;
	}
}
