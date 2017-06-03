package bertucci.pedro.empregoja.interfaces;

/**
 * Created by b_ped on 12/03/2017.
 */

import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("/trabalhoja/api/")
    Call<ServerResponse> operation(@Body ServerRequest request);


  

}

