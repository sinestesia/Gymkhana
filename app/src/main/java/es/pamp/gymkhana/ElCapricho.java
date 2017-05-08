package es.pamp.gymkhana;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;




public final class ElCapricho {


    /*public static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";*/
    public ElCapricho() {
    }


    public static final void iniciarPartida(Context contexto) {
        SharedPreferences capricho = contexto.getSharedPreferences("PREFERENCE_CAPRICHO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = capricho.edit();


        //activa partida
        editor.putBoolean("PartidaActiva", true);

        editor.putString("punto00", "Info"); //información del mapa, horario, como llegar...
        editor.putString("punto01", "Activo"); //Punto de inicio
        editor.putString("punto02", "Desactivado");
        editor.putString("punto03", "Desactivado");
        editor.putString("punto04", "Desactivado");
        editor.putString("punto05", "Desactivado");
        editor.putString("punto06", "Desactivado");
        editor.putString("punto07", "Desactivado");
        editor.putString("punto08", "Desactivado");
        editor.putString("punto09", "Desactivado");
        editor.putString("punto10", "Desactivado");
        editor.putString("punto11", "Desactivado");
        editor.putString("punto12", "Desactivado");
        editor.putString("punto13", "Desactivado");
        editor.putString("punto14", "Desactivado");
        editor.putString("punto15", "Desactivado");

        editor.commit();

    }

    public static final boolean estaIniciado(Context contexto) {

        SharedPreferences capricho = contexto.getSharedPreferences("PREFERENCE_CAPRICHO", Context.MODE_PRIVATE);
        return capricho.getBoolean("PartidaActiva", false);
    }
    public static final boolean estaDesactivado(Context contexto, String punto) {
        SharedPreferences capricho = contexto.getSharedPreferences("PREFERENCE_CAPRICHO", Context.MODE_PRIVATE);
        Boolean result;
        String desactivado = capricho.getString(punto, "Desactivado");
        if (desactivado.equals("Desactivado")){ //TODO ¿Por qué falla?
            result=true;
        }else{
            result = false;
        }

        return result;
    }


    public static final int icono(Context contexto, String punto){
        int i;
        SharedPreferences capricho = contexto.getSharedPreferences("PREFERENCE_CAPRICHO", Context.MODE_PRIVATE);
        switch (capricho.getString(punto, "Desactivado")){
            case "Activo":
                i=R.drawable.ic_activado;
                break;
            case "Desactivado":
                i = R.drawable.ic_desactivado;
                break;
            case "Completado":
                i= R.drawable.ic_completado;
                break;
            default:
                i = R.drawable.ic_desactivado;
                break;
        }
        return i;
    }

    public static final void completado(Context contexto, String punto){
        SharedPreferences capricho = contexto.getSharedPreferences("PREFERENCE_CAPRICHO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = capricho.edit();

        switch (punto){
            case "punto01":
                editor.putString("punto01", "Completado");
                editor.putString("punto02", "Activo");
                break;
            case "punto02":
                editor.putString("punto02", "Completado");
                editor.putString("punto03", "Activo");
                break;
            case "punto03":
                editor.putString("punto03", "Completado");
                editor.putString("punto04", "Activo");
                break;
            default:
                //TODO acciones al completar otros
                break;
        }

        editor.commit();

    }

}
