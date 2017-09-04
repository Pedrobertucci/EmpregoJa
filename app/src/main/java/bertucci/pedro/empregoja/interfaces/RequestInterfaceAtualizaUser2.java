package bertucci.pedro.empregoja.interfaces;

import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by pedro on 21/06/17.
 */

public interface RequestInterfaceAtualizaUser2 {
    @POST("Usuario/atualizaUsuario2/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
