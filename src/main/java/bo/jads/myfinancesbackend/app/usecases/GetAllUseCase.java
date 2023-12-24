package bo.jads.myfinancesbackend.app.usecases;

import bo.jads.myfinancesbackend.app.mappers.EntityToResponseMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GetAllUseCase<
        Entity,
        Repository extends JpaRepository<Entity, ?>,
        Response,
        Mapper extends EntityToResponseMapper<Entity, Response>
        > {

    private final Repository repository;
    private final Mapper mapper;

    public GetAllUseCase(Repository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Response> execute() {
        List<Entity> entities = repository.findAll();
        return mapper.entitiesToResponses(entities);
    }

}
