package kmitl.com.lab07.lazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LazyInstagramApi {
    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";
    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String usrName);

}
