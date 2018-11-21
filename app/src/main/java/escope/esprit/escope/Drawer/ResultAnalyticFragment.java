package escope.esprit.escope.Drawer;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;
//import com.itextpdf.text.Document;
import com.android.volley.TimeoutError;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import escope.esprit.escope.*;


 import escope.esprit.escope.NewsRSS.MyAdapter;
import escope.esprit.escope.NewsRSS.VerticalSpace;
import escope.esprit.escope.utile.Url;


public class ResultAnalyticFragment extends Fragment {
    Context context;

    Button pdf ,cancel,hospital_location, addAnalysis;
    TextView namePatient, nbVirus,result,disease;
    int MY_SOCKET_TIMEOUT_MS=4000;

    public static final String CONNECTIVITY_SERVICE = "connectivity";
    public static ProgressDialog progressDialog;
    String HTTP_JSON_URL = "http://192.168.43.135:5000/test";
    //   String HTTP_JSON_URL="http://escope.amitechss.com/result";
    String HTTP_JSON_URL_ADD= Url.URL+"createAnalysis";
    JsonArrayRequest jsonArrayRequest ;
    PdfDocument document;
    RequestQueue requestQueue ;
    ParcelFileDescriptor pfd = null;
    private View mRootView;
    private static final String TAG = "PdfCreatorActivity";

    private Button mCreateButton;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;

    public ResultAnalyticFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result_analytic, container, false);

        pdf = (Button)view.findViewById(R.id.pdf);
        namePatient = (TextView)view.findViewById(R.id.name_patient);
        result = (TextView)view.findViewById(R.id.result);
        disease = (TextView)view.findViewById(R.id.disease);
        addAnalysis=(Button)view.findViewById(R.id.add_analysis);
        hospital_location = (Button)view.findViewById(R.id.hospital_location);
        cancel = (Button)view.findViewById(R.id.cancel);

        // finish the page
        System.out.println("tttt"+HTTP_JSON_URL);



        class AsyncTaskUploadClass extends AsyncTask<Void, Void, Void> {


            public AsyncTaskUploadClass (Context context){

                progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Loading");

            }

            @Override
            protected Void doInBackground(Void... voids) {
                JSON_DATA_WEB_CALL();
                System.out.println("done");
                return null;
            }

            @Override
            protected void onPreExecute() {
                //    progressDialog.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Auto-closing Dialog");
                builder.setMessage("Just wait few secondes please");
                builder.setCancelable(true);
                final AlertDialog dlg = builder.create();

                dlg.show();

                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, (30000));



                super.onPreExecute();


            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass(getContext());

        AsyncTaskUploadClassOBJ.execute();
        //  progressDialog.dismiss();




        addAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAnalyse();
                Toast.makeText(getActivity(), "ajout avec succées", Toast.LENGTH_LONG).show();
                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                 transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (namePatient.getText().toString().isEmpty()){
                    namePatient.setError("Body is empty");
                    namePatient.requestFocus();
                    return;
                }
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {

                }
            }
        });

        return view;
    }

    public void   addAnalyse()
    {
        StringRequest request = new StringRequest(Request.Method.POST, HTTP_JSON_URL_ADD, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                Toast.makeText(getActivity(), "Envoie avec succées", Toast.LENGTH_LONG).show();

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();


                parameters.put("result", result.getText().toString());
                parameters.put("disease", disease.getText().toString());
                parameters.put("numberOfVirus", nbVirus.getText().toString());
                parameters.put("comment","ttt");
                parameters.put("date", new Date().toString());
                parameters.put("patient_id","1");

                return parameters;
            }
        };



        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(getActivity());
        rQueue.add(request);


    }















    public void JSON_DATA_WEB_CALL(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, HTTP_JSON_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);


                            String Diseases = obj.getString("Disease");
                            String resultss = obj.getString("Result");


                            namePatient.setText("J-0");
                            disease.setText(Diseases);
                            result.setText(resultss);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: "+error.toString(), error);
                    }
                });

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RequestQueue request = Volley.newRequestQueue(getActivity());
        request.add(stringRequest);
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    void loading(String message){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    private void createPdfWrapper() throws FileNotFoundException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);

        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getActivity().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(getActivity(),"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }

    }


}