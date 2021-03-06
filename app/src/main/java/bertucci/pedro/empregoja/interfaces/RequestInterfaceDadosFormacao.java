package bertucci.pedro.empregoja.interfaces;

import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by pedro on 10/08/17.
 */

public interface RequestInterfaceDadosFormacao {

    @POST("Ensino/editaEnsino")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
