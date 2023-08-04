package bo.jads.myfinancesbackend.app.usecases;

public interface BaseUseCase<Input, Output> {

    Output execute(Input input) throws Exception;

}
