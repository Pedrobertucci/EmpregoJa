package bertucci.pedro.empregoja.interfaces;

import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by pedro on 20/06/17.
 */

public interface RequestInterfaceEnsinoList {
    @POST("Ensino/listaEnsino/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
