package mx.edu.tesoem.isc.G7s21bdgr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class BienvenidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent n = new Intent(BienvenidaActivity.this, MainActivity.class);
                startActivity(n);
                finish();
            }
        },5000);
    }
}