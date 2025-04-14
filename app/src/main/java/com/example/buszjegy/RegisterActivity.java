package com.example.buszjegy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG=RegisterActivity.class.getName();
    private static final String PREF_KEY=MainActivity.class.getPackage().toString();
    private static final int SECRET_KEY=99;
    EditText userNameEditText;
    EditText userEmailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;

    private SharedPreferences preferences;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Bundle bundle=getIntent().getExtras();
//        bundle.getInt("SECRET_KEY");
        int secret_key=getIntent().getIntExtra("SECRET_KEY", 0);

        if (secret_key!=99){
            finish();
        }

        userNameEditText=findViewById(R.id.userNameEditText);
        userEmailEditText=findViewById(R.id.userEmailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        passwordAgainEditText=findViewById(R.id.passwordAgainEditText);

        preferences=getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userName=preferences.getString("userName", "");
        String password=preferences.getString("password", "");

        userNameEditText.setText(userName);
        passwordEditText.setText(password);
        passwordAgainEditText.setText(password);

        //mAuth=FirebaseAuth.getInstance();


        Log.i(LOG_TAG, "onCreate");
    }

    public void register(View view) {
        String userName=userNameEditText.getText().toString();
        String email=userEmailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String passwordAgain=passwordAgainEditText.getText().toString();

        if (!password.equals(passwordAgain)){
            Log.e(LOG_TAG, "Nem egyezik a két jelszó!");
            return;
        }

        Log.i(LOG_TAG, "Regisztrált:"+ userName+" e-mail: "+email);
        startJegyvasarlas();

//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    Log.d(LOG_TAG, "Felhasználó sikeresen létrehozva");
//                    startJegyvasarlas();
//                }else{
//                    Log.d(LOG_TAG, "Sikertelen felhasználó létrehozás!");
//                }
//            }
//        });
    }

    public void cancel(View view) {
        finish();
    }

    private void startJegyvasarlas(/*user*/){
        Intent intent = new Intent(this, jegyek.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }
}