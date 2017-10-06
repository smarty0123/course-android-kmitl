package kmitl.com.lab07.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.com.lab07.lazyinstagram.adapter.PostAdapter;
import kmitl.com.lab07.lazyinstagram.api.LazyInstagramApi;
import kmitl.com.lab07.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);

    }
    private void getUserProfile(String usrName){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(LazyInstagramApi.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();
                    ImageView imageProfile = findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
                    TextView textName = (TextView)findViewById(R.id.textName);
                    textName.setText("@"+userProfile.getUser());
                    TextView textPost = findViewById(R.id.textPost);
                    textPost.setText("Post\n" + userProfile.getPost());
                    TextView textFollowing = findViewById(R.id.textFollowing);
                    textFollowing.setText("Following\n"+userProfile.getFollowing());
                    TextView textFollower = findViewById(R.id.textFollower);
                    textFollower.setText("Follower\n" + userProfile.getFollower());
                    TextView textBio = findViewById(R.id.textBio);
                    textBio.setText(userProfile.getBio());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
