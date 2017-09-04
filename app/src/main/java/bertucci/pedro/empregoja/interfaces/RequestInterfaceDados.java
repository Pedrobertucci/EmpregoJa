package bertucci.pedro.empregoja.interfaces;

import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by pedro on 18/06/17.
 */

public interface RequestInterfaceDados {

    @POST("Usuario/dadosUsuario/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
