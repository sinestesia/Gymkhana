package es.pamp.gymkhana;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaCaprichoActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker mInicio;
    private CameraUpdate campUp1;
    private LatLng posicionInicio;
    private Intent intent;
    private Context contexto;

    //TODO Convertir en lista los marcadores
    //marcadores
    private Marker mPunto00;
    private Marker mPunto01;
    private Marker mPunto02;
    private Marker mPunto03;
    private Marker mPunto04;
    private Marker mPunto05;
    private Marker mPunto06;
    private Marker mPunto07;
    private Marker mPunto08;
    private Marker mPunto09;
    private Marker mPunto10;
    private Marker mPunto11;
    private Marker mPunto12;
    private Marker mPunto13;
    private Marker mPunto14;
    private Marker mPunto15;
    private Float zoom;
    private Double latitude;
    private Double longitude;


    //permisos
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_capricho);
        contexto = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState!=null){
            zoom = savedInstanceState.getFloat("ZOOM");
            latitude = savedInstanceState.getDouble("LATITUDE");
            longitude = savedInstanceState.getDouble("LONGITUDE");
        }
        intent = new Intent(this, PuntoActivity.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Aplicamos el estilo personalizado de mapa
        mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.formato_mapa));
        // margen para poder visualizar el boton mylocation
        mMap.setPadding(0,110,0,0);


        // Marker https://developers.google.com/maps/documentation/android-api/marker?hl=es-419
        posicionInicio = new LatLng(40.4566113,-3.5988973);
        //TODO poner getLongitud, getLatitud y visible de la clase ElCapricho
        mPunto00 = mMap.addMarker(new MarkerOptions()
                .position(posicionInicio)
                .title("El Capricho")
                .snippet("")
                //.visible(false) Si se quiere ocultar
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_info))
        );
        mPunto00.setTag(0);

        if (zoom != null){
            //Se restauran los valores guardados en saveinstance
            campUp1 = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), (float)zoom);
        }else{
            campUp1 = CameraUpdateFactory.newLatLngZoom(posicionInicio, (float)16);
        }

        mMap.moveCamera(campUp1);

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMarkerClickListener(this);

        pintarMarcadores();

        enableMyLocation();

    }

    public void pintarMarcadores(){

        //TODO cargar la información de marcadores a una lista

        mPunto01 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.4550444,-3.6007091))
                .title("Inicio")
                .snippet("texto snippet")
                        //.visible(false) Si se quiere ocultar
                .icon(BitmapDescriptorFactory.fromResource(ElCapricho.icono(contexto,"punto01")))
                );
        mPunto01.setTag(1);

        mPunto02 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.454911,-3.6001023))
                .title("Bloqueado")
                .snippet("texto snippet")
                //.visible(false) Si se quiere ocultar
                .icon(BitmapDescriptorFactory.fromResource(ElCapricho.icono(contexto,"punto02")))
        );
        mPunto02.setTag(2);

        mPunto03 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.455852,-3.5976546))
                .title("Bloqueado")
                .snippet("Completa pruebas para desbloquearlo")
                //.visible(false) Si se quiere ocultar
                .icon(BitmapDescriptorFactory.fromResource(ElCapricho.icono(contexto,"punto03")))
        );
        mPunto03.setTag(3);

        mPunto04 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.456542,-3.5961936))
                .title("Bloqueado")
                .snippet("Completa pruebas para desbloquearlo")
                //.visible(false) Si se quiere ocultar
                .icon(BitmapDescriptorFactory.fromResource(ElCapricho.icono(contexto,"punto04")))
        );
        mPunto04.setTag(4);


    }

    /** Se llama al hacer click en un marcador */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Recupera la información del marcador
        Integer marcadorId = (Integer) marker.getTag();

        //le pasa el nombre del punto punto00...punto99
        if (marcadorId<10){
            intent.putExtra("puntoId", "punto0"+marcadorId);
            startActivity(intent);
        }
        else{
            intent.putExtra("puntoId", "punto"+marcadorId);
            startActivity(intent);
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);

        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        //Toast.makeText(this, "has pulsado en el botón mi localización", Toast.LENGTH_SHORT).show();
        //Permite poner una acción al clicar en el botón mi posición.

        return false;
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
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
                intentInfo.putExtra("puntoId", "punto00");
                startActivity(intentInfo);
                break;
            }
            case R.id.info:{
                intentInfo.putExtra("puntoId", "punto00");
                startActivity(intentInfo);
                break;
            }
            default:{

                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("ZOOM", mMap.getCameraPosition().zoom);
        outState.putDouble("LATITUDE",mMap.getCameraPosition().target.latitude);
        outState.putDouble("LONGITUDE",mMap.getCameraPosition().target.longitude);

    }

}
