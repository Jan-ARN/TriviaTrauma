package com.codekobolde.triviatrauma;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;
public class UserModel implements Contract.Model {

    public UserModel() {


    }

    @Override
    public void newUser(String Username, String Password, Context context) {
        String url = "https://triviaapi.azurewebsites.net/api/user/register";

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("Username", Username);
            requestBody.put("Password", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(context);

        CompletableFuture<JSONObject> future = new CompletableFuture<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        future.complete(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        future.completeExceptionally(error);
                    }
                });

        queue.add(jsonObjectRequest);

        future.thenAcceptAsync(response -> {
            // context code will be executed when the response is received
            int statusCode = response.optInt("statusCode", -1);

            if (statusCode == 201) {
                // Registrierung erfolgreich
                Toast.makeText(context, "User wurde erfolgreich erstellt", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                }, 2000);

            } else {
                // Registrierung fehlgeschlagen, zeige serverseitige Fehlermeldung an, falls vorhanden
                String errorMessage = response.optString("message", "Registrierung fehlgeschlagen");
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            }
        }).exceptionally(e -> {
            // Handle exception if there is a network error
            Toast.makeText(context, "Der User existiert bereits oder es gab einen Netzwerkfehler!", Toast.LENGTH_SHORT).show();
            return null;
        });
    }

    @Override
    public void authenticateUser(String Username, String Password, Context context) {
        String url = "https://triviaapi.azurewebsites.net/api/v1/users/login";

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("Username", Username);
            requestBody.put("Password", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(context);

        CompletableFuture<JSONObject> future = new CompletableFuture<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        future.complete(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        future.completeExceptionally(error);
                    }
                });

        queue.add(jsonObjectRequest);

        future.thenAcceptAsync(response -> {

            int statusCode = response.optInt("statusCode", -1);
            if (statusCode == 200) {
                Log.d("LOGIN", "authenticateUser: " + statusCode + response.toString());
                //LOGIN ERFOLGREICH
                Intent intent = new Intent(context, Lobby.class);
                context.startActivity(intent);
            } else {
                // Registrierung fehlgeschlagen, zeige serverseitige Fehlermeldung an, falls vorhanden
                String errorMessage = response.optString("message", "Login ist fehlgeschlagen");
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            }
        }).exceptionally(e -> {
            // Handle exception if there is a network error
            Toast.makeText(context, "Login ist fehlgeschlagen!", Toast.LENGTH_SHORT).show();
            return null;
        });
    }
}
