package escope.esprit.escope.Drawer;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

 import escope.esprit.escope.R;
import escope.esprit.escope.User.LoginActivity;
import escope.esprit.escope.utile.Url;

import static android.app.Activity.RESULT_OK;


public class MakeAnalysisFragment extends Fragment {

    Button makeAnalysis;
    Button takeImage;
    Uri photoPath;
    ImageView imageVirus;
    static int TAKE_PICTURE = 1;
    public  static final int RequestPermissionCode  = 1 ;
    boolean check = true;
    Bitmap bitmap;
    ProgressDialog progressDialog ;
    String ImagePathFieldOnServer = "image_path" ;
    String ImageUploadPathOnSever = Url.URLL+"addimage.php" ;

    public MakeAnalysisFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_analysis, container, false);

         makeAnalysis = (Button)view.findViewById(R.id.makeanalysis);
        takeImage = (Button) view.findViewById(R.id.takeimage);
        imageVirus = (ImageView) view.findViewById(R.id.imagevirus);

        takeImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PICTURE);
            }


        });
        makeAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                       ImageUploadToServerFunction();

                Fragment newFragment = new ResultAnalyticFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.flcontent, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();          }
        });


         return  view;
    }
   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {


        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            bitmap =  (Bitmap)intent.getExtras().get("data");
                // adding captured image in imageview.
                imageVirus.setImageBitmap(bitmap);
                imageVirus.setVisibility(View.VISIBLE);


        }
    }




    public void ImageUploadToServerFunction() {

        ByteArrayOutputStream byteArrayOutputStreamObject;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        // Converting bitmap image to jpeg format, so by default image will upload in jpeg format.
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                // Showing progress dialog at image upload time.
                progressDialog = ProgressDialog.show(getActivity(), "Image is Uploading", "Please Wait", false, false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(getActivity(), string1, Toast.LENGTH_LONG).show();

                // Position image as transparent after done uploading.
                imageVirus.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {
                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImagePathFieldOnServer, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest(ImageUploadPathOnSever, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }
    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity(),"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getActivity(),"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
