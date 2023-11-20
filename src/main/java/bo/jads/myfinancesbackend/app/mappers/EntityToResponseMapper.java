package bo.jads.myfinancesbackend.app.mappers;

import java.util.List;

public interface EntityToResponseMapper<Entity, Response> {

    Response entityToResponse(Entity entity);

    List<Response> entitiesToResponses(List<Entity> entities);

}
