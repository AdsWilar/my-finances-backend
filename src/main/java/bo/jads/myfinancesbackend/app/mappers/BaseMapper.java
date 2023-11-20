package bo.jads.myfinancesbackend.app.mappers;

public interface BaseMapper<Entity, Request, Response> extends EntityToResponseMapper<Entity, Response> {

    Entity requestToEntity(Request request);

}
