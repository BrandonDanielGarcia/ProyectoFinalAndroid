package mx.edu.tesoem.isc.G7s21bdgr.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import mx.edu.tesoem.isc.G7s21bdgr.DAO.DatosHelper.tabladatos;
import java.util.ArrayList;
import java.util.List;

import mx.edu.tesoem.isc.G7s21bdgr.DTO.DatosDTO;

public class ConexionDAO {
    SQLiteDatabase basedatos;
    DatosHelper datosHelper;
    Context context;
    List<DatosDTO> listado = new ArrayList<DatosDTO>();
    DatosDTO datosID = new DatosDTO();

    public ConexionDAO(Context context){
        this.context = context;
    }
    public ConexionDAO abriConexion(){
        datosHelper = new DatosHelper(context);
        basedatos = datosHelper.getWritableDatabase();
        return this;
    }
    public void cerrar(){
        datosHelper.close();
    }

    public boolean insertar(ContentValues contentValues){
        boolean estado = true;
        int afectados = (int) basedatos.insert(DatosHelper.tabladatos.TablaName,null,contentValues);
        if (afectados < 0) estado = false;
        return estado;
    }

    public boolean cargartodos(){
        boolean estado = true;
        String[] columnas = {tabladatos.TablaId,DatosHelper.tabladatos.TablaNombre,DatosHelper.tabladatos.TablaEdad,DatosHelper.tabladatos.TablaCorreo};
        DatosDTO datos;
        Cursor cursor = basedatos.query(tabladatos.TablaName,columnas,null,null,null,null,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                datos = new DatosDTO();
                datos.setId(Integer.parseInt(cursor.getString(0)));
                datos.setNombre(cursor.getString(1));
                datos.setEdad(Integer.parseInt(cursor.getString(2)));
                datos.setCorreo(cursor.getString(3));
                listado.add(datos);
                cursor.moveToNext();
            }
        }else if (cursor.getCount() == 0){}
        else estado = false;
        return estado;
    }

    public boolean consultaID(String[] parametrocondicion){
        boolean estado = true;
        String[] columnas = {tabladatos.TablaId,tabladatos.TablaNombre,tabladatos.TablaEdad,tabladatos.TablaCorreo};
        String condicion = tabladatos.TablaId + " = ?";
        Cursor cursor = basedatos.query(tabladatos.TablaName,columnas,condicion,parametrocondicion,null,null,null,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            datosID.setId(Integer.parseInt(cursor.getString(0)));
            datosID.setNombre(cursor.getString(1));
            datosID.setEdad(Integer.parseInt(cursor.getString(2)));
            datosID.setCorreo(cursor.getString(3));
        }else if (cursor.getCount() == 0){}
        else {
            estado=false;
        }
        return  estado;
    }

    public boolean actualiza(ContentValues contentValues, String[] condiciones){
        boolean estado = true;
        String condicion = tabladatos.TablaId + " = ?";
        int afectados = (int) basedatos.update(tabladatos.TablaName,contentValues,condicion,condiciones);
        if (!(afectados>=0)) estado = false;
        return  estado;
    }

    public boolean eliminar(String[] condiciones){
        boolean estado = true;
        String condicion = tabladatos.TablaId +" = ?";
        int afectados = (int) basedatos.delete(tabladatos.TablaName,condicion,condiciones);
        if (!(afectados>=0)) estado = false;
        return estado;
    }

    public List<DatosDTO> getListado(){
        return listado;
    }

    public DatosDTO regresaID(){
        return  datosID;
    }
}
