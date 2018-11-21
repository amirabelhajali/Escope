package escope.esprit.escope.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;

import com.android.volley.ServerError;
import com.android.volley.TimeoutError;

import org.json.JSONTokener;

import escope.esprit.escope.Drawer.MainActivity;
import escope.esprit.escope.R;
import escope.esprit.escope.utile.Url;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    String   newString;

    // String URL = Url.URLL+"loginNew.php";
    String URL = Url.URL+"login";

    public static final String PREFS_USER = "prefs_user";
    EditText emailText;
    EditText passwordText;
    Button loginButton;
    String  successvalue;
Context context;
    private static final String PREFS = "PREFS";
    private static final String PREFS_EMAIL = "PREFS_EMAIL";
    private static final String PREFS_PASSWORD = "PREFS_PASSWORD";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText=(EditText) findViewById(R.id.login);
        passwordText=(EditText) findViewById(R.id.password);
        loginButton=(Button) findViewById(R.id.loginBtm);
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);




            sharedPreferences
                    .edit()
                    .putString(PREFS_EMAIL, emailText.getText().toString())
                    .putString(PREFS_PASSWORD, passwordText.getText().toString())
                    .apply();





        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Start the Signup activity
                login();



                User user = new User();
                user.setEmail(emailText.getText().toString());
                user.setPassword(passwordText.getText().toString());

            }
        });


    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentification en cours...");
        progressDialog.show();





        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){

            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String token = jsonObject.getString("token");
                    ((Constants) LoginActivity.this.getApplication()).setTokenUser(token);
                    JSONObject jsonObjectInfo=jsonObject.getJSONObject("user");
                    Integer idConnected= Integer.valueOf(jsonObjectInfo.getString("id"));
                    ((Constants) LoginActivity.this.getApplication()).setIdConnectedUser(String.valueOf(idConnected));




                    System.out.println(jsonObject+"jajajajajjajaj");
                    //JSONObject user = json.getJSONObject("user");
                    successvalue = (String) jsonObject.get("success");

                    System.out.println(successvalue+"jajajajajjajaj");


                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);






                }catch (JSONException e) {
                    System.out.println("!!!!!!!!!!!!!!  wrong");
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //  VolleyLog.e("Error: "+error.toString(), error);
                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";

                        } else if (error instanceof ServerError) {

                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", emailText.getText().toString());
                params.put("password", passwordText.getText().toString());
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();


    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Erreur Login", Toast.LENGTH_LONG).show();



        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String motdepasse = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Enter a valid email address");
            valid = false;

        } else {
            emailText.setError(null);
        }

        if (motdepasse.isEmpty() ) {
            passwordText.setError("Wrong password");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (motdepasse.isEmpty() &&  email.isEmpty() ) {
            passwordText.setError("Wrong password");
            emailText.setError("Enter a valid email address");

            valid = false;
        } else {
            passwordText.setError(null);
            emailText.setError(null);
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}


