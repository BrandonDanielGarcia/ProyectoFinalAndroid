package mx.edu.tesoem.isc.G7s21bdgr;

import androidx.appcompat.app.AppCompatActivity;

import mx.edu.tesoem.isc.G7s21bdgr.DAO.ConexionDAO;
import mx.edu.tesoem.isc.G7s21bdgr.DAO.DatosHelper.tabladatos;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {
    EditText txtnombre, txtedad, txtcorreo;
    Button btnrguardar, btnrcancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtnombre = findViewById(R.id.txtNombre);
        txtedad = findViewById(R.id.txtEdad);
        txtcorreo = findViewById(R.id.txtCorreo);
        btnrguardar = findViewById(R.id.btnRGuardar);
        btnrcancelar = findViewById(R.id.btnRCancelar);

        btnrguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("vista","Registrar");
                guardar();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        btnrcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
    private void guardar(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(tabladatos.TablaNombre, txtnombre.getText().toString());
        contentValues.put(tabladatos.TablaEdad, Integer.valueOf(txtedad.getText().toString()));
        contentValues.put(tabladatos.TablaCorreo, txtcorreo.getText().toString());

        ConexionDAO conexion = new ConexionDAO(this);
        conexion.abriConexion();
        if (conexion.insertar(contentValues)){
            Toast.makeText(this, "Se grabaron los datos correctamente...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos...", Toast.LENGTH_SHORT).show();
        }
        conexion.cerrar();
    }
}