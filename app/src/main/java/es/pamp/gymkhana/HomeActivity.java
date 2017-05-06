package es.pamp.gymkhana;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
}