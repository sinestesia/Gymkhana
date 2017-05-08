package es.pamp.gymkhana;

/**
 * Created by pamp on 06/05/17.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;


public class WebAppInterface {
    AppCompatActivity context;

    public WebAppInterface(AppCompatActivity context){
        this.context=context;
    }

    @JavascriptInterface
    public void volver() {
        Intent intent = new Intent(context, MapaCaprichoActivity.class);
        context.startActivity(intent);
        context.finish();

    }

    @JavascriptInterface
    public void completado(String punto) {
        ElCapricho.completado(context,punto);
        Intent intent = new Intent(context, MapaCaprichoActivity.class);
        context.startActivity(intent);
        context.finish();

    }

/*

    Ejemplo de script para la web
    <script type="text/javascript">
      function showAndroidDialog (){
        user=document.getElementById("user").value;
        pass=document.getElementById("pass").value;
        Android.showDialog(user, pass);
      }
  </script>



    @JavascriptInterface
    public void showDialog(String user, String pass) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage("Usuario: " + user + " Contraseña: " + pass).setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }*/
}