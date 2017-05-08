package es.pamp.gymkhana;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogoAlerta dialogo = new DialogoAlerta();
                    dialogo.show(fragmentManager, "tagAlerta");


                }else{
                    ElCapricho.iniciarPartida(contexto);
                    startActivity(intent);
                    finish();

                }

            }
        });

        continuarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();

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
