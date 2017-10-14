package kmitl.lab07.nattapon58070036.mylazyinstagram.api;


import kmitl.lab07.nattapon58070036.mylazyinstagram.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LazyInstagramApi {
    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String usrName);

}
