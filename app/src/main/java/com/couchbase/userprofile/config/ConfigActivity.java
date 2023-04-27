package com.couchbase.userprofile.config;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.couchbase.userprofile.R;
import com.couchbase.userprofile.login.LoginActivity;
import com.couchbase.userprofile.profile.UserProfileActivity;
import com.couchbase.userprofile.util.DatabaseManager;

public class ConfigActivity extends AppCompatActivity {

    EditText appServicesInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        appServicesInput = findViewById(R.id.appServicesURLInput);
    }

    public void onClickSaveAppServicesURL(View view) {

        // TODO: Url validation for app services
        Boolean isValidURL = Patterns.WEB_URL.matcher(appServicesInput.getText().toString()).matches();

        if (!isValidURL) {
            appServicesInput.setError("Invalid App Services Url");
        }

        // Set app services endpoint
        DatabaseManager.setAppServicesEndpoint(appServicesInput.getText().toString());

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}