package kmitl.com.lab07.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kmitl.com.lab07.lazyinstagram.adapter.PostAdapter;
import kmitl.com.lab07.lazyinstagram.api.LazyInstagramApi;
import kmitl.com.lab07.lazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private Spinner accountSpinner;
    private ArrayList<String> account = new ArrayList<>();
    private String layoutType = "grid";
    private String user = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountSpinner = findViewById(R.id.spinner);
        createAccount();
        ArrayAdapter<String> adapterAccount = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, account);
        accountSpinner.setAdapter(adapterAccount);
        accountSpinner.setOnItemSelectedListener(this);
        getUserProfile(user);
    }

    private void getUserProfile(String userName) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(LazyInstagramApi.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = lazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    display(userProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    private void display(UserProfile userProfile) {
        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
        /*TextView textName = findViewById(R.id.textUser);
        textName.setText("@"+userProfile.getUser());*/
        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n" + userProfile.getPost());
        TextView textFollowing = findViewById(R.id.textFollowing);
        textFollowing.setText("Following\n" + userProfile.getFollowing());
        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + userProfile.getFollower());
        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        postAdapter = new PostAdapter(this);
        postAdapter.setData(userProfile.getPosts());
        postAdapter.setLayoutType(layoutType);

        recyclerView = findViewById(R.id.list);
        if (layoutType.equals("grid")) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else if (layoutType.equals("list")) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        recyclerView.setAdapter(postAdapter);
    }

    private void createAccount() {
        account.add("@android");
        account.add("@cartoon");
        account.add("@nature");
    }

    public void onGrid(View view) {
        layoutType = "grid";
        getUserProfile(user);
    }

    public void onList(View view) {
        layoutType = "list";
        getUserProfile(user);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        switch (item) {
            case "@android":
                user = "android";
                break;
            case "@cartoon":
                user = "cartoon";
                break;
            default:
                user = "nature";
                break;
        }
        getUserProfile(user);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
