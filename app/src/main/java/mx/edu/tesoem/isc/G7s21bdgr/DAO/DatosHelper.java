package mx.edu.tesoem.isc.G7s21bdgr.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatosHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "dbg7s21";
    protected static int DB_VERSION = 1;

    public static class tabladatos{
        public static String TablaName = "tbDatos";
        public static String TablaId = "id";
        public static String TablaNombre = "nombre";
        public static String TablaEdad = "edad";
        public static String TablaCorreo = "correo";
    }

    private static String crearTabla = "create table " + tabladatos.TablaName + "(" + tabladatos.TablaId +" integer not null primary key autoincrement, " + tabladatos.TablaNombre+" varchar, " + tabladatos.TablaEdad + " integer, " + tabladatos.TablaCorreo + " varchar)";
    private static String eliminaTabla = "drop table " + tabladatos.TablaName;

    public DatosHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(eliminaTabla);
        onCreate(db);
    }
}
