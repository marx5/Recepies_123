package hr.martin.mirenic.recepies123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {
    ImageView imageView;
    TextView email;
    Button signOut;
    Button goToSearch;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

       // Toast.makeText(SecondActivity.this, "Uspio si!", Toast.LENGTH_LONG).show();
        goToSearch = findViewById(R.id.goToSearch);
        goToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToSearch();
               // Toast.makeText(SecondActivity.this, "Uspio si!", Toast.LENGTH_LONG).show();

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.personPic);
        email = findViewById(R.id.email);
        signOut = findViewById(R.id.signOutBtn);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.signOutBtn:
                        signOut();
                        break;
                }

            }
        });


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            email.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SecondActivity.this, "Uspješna odjava!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    private void goToSearch() {
       Intent intent = new Intent(SecondActivity.this, SearchActivity.class);
            startActivity(intent);
      //  Toast.makeText(SecondActivity.this, "Uspio si!",Toast.LENGTH_LONG).show();
    }
}
