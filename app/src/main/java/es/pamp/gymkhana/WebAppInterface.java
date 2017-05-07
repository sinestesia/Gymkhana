package es.pamp.gymkhana;

/**
 * Created by pamp on 06/05/17.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;


public class WebAppInterface {
    Context context;

    public WebAppInterface(Context context){
        this.context=context;
    }
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
    }
}