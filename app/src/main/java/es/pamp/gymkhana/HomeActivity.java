package es.pamp.gymkhana;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Toast toast;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contexto = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button nuevaBoton = (Button) findViewById(R.id.nueva);
        final Button continuarBoton = (Button) findViewById(R.id.continuar);

        if (ElCapricho.estaIniciado(contexto)) {
            continuarBoton.setVisibility(View.VISIBLE);
        } else{
            continuarBoton.setVisibility(View.INVISIBLE);
        }

        final Intent intent = new Intent(this, MapaCaprichoActivity.class);

        nuevaBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ElCapricho.estaIniciado(contexto)){
                    //TODO mostrar dialogo de confirmación
                    /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                    builder.setMessage("Ya tienes una partida activa si continuas la perderás").setNeutralButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();*/
                    ElCapricho.iniciarPartida(contexto);

                }else{

                    continuarBoton.setVisibility(View.VISIBLE);
                }

                //lanza mapa
                startActivity(intent);
                //overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out); //TODO poner solo en la portada

            }
        });

        continuarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });
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
}
