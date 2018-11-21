package escope.esprit.escope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import escope.esprit.escope.Drawer.*;
import escope.esprit.escope.NewsRSS.MainNewsActivity;
import escope.esprit.escope.User.LoginActivity;
import escope.esprit.escope.utile.Url;


public class IpAdressesActivity extends AppCompatActivity {
EditText edIpAdress;
Button changeIpAdress;
  public static String urll;
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_adresses);
        edIpAdress=(EditText) findViewById(R.id.ip_adress);
        changeIpAdress=(Button) findViewById(R.id.change_ip_adress);

        changeIpAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urll="http://"+edIpAdress.getText().toString()+"/escope/";
                url="http://"+edIpAdress.getText().toString()+":8888/apii/";

                Intent i = new Intent(IpAdressesActivity.this, MainActivity.class);
                startActivity(i);
            }

        });





    }
}
