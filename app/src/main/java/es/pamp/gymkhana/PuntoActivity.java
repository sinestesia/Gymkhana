package es.pamp.gymkhana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PuntoActivity extends AppCompatActivity  {
    private Toast toast;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        WebSettings mySettings = myWebView.getSettings();
        mySettings.setJavaScriptEnabled(true);

        String puntoActivo = getIntent().getExtras().getString("puntoId");

        String web = "file:///android_asset/elcapricho/" + puntoActivo + ".html";
        myWebView.loadUrl(web);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //TODO acciones del menu
        final Intent intentHome = new Intent(this, HomeActivity.class);
        final Intent intentInfo = new Intent(this, PuntoActivity.class);

        int id = item.getItemId();

        switch (id){
            case R.id.ajustes:{
                //TODO lanzar ajustes
                break;
            }
            case R.id.acercaDe:{
                //TODO lanzar acerca de
                break;
            }
            case R.id.home:{
                startActivity(intentHome);
                break;
            }
            case R.id.action_info:{
                //Ejecuta la acción siguiente R.id.info
            }
            case R.id.info:{
                intentInfo.putExtra("puntoId", "punto00");
                startActivity(intentInfo);
                break;
            }
            default:{
                toast = Toast.makeText(this, "Has clicado en nada", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
