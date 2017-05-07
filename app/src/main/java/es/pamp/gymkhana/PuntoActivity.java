package es.pamp.gymkhana;

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

        SharedPreferences estado = getSharedPreferences("Estado",this.MODE_PRIVATE);
        String puntoActivo = estado.getString("PuntoActivo", "puntopordefecto");

        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        WebSettings mySettings = myWebView.getSettings();
        mySettings.setJavaScriptEnabled(true);

        //TODO recoger valor del punto y generar url
        String web = "file:///android_asset/elcapricho/" + puntoActivo + ".html";
        myWebView.loadUrl(web);
        //myWebView.loadUrl("file:///android_asset/elcapricho/punto01.html");




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

        int id = item.getItemId();

        switch (id){
            case R.id.ajustes:{
                toast = Toast.makeText(this, "Has clicado en ajustes", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.acercaDe:{
                toast = Toast.makeText(this, "Has clicado en acerca de", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.action_buscar:{
                toast = Toast.makeText(this, "Has clicado en Home", Toast.LENGTH_SHORT);
                toast.show();
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

}
