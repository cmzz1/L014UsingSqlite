package uicontrols.atm.mdzz.l014usingsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dis001 on 2016/10/05.
 */
public class Db extends SQLiteOpenHelper {
    public Db(Context context) {
        super(context, "db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE user(" +
                 "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "name TEXT DEFAULT \"\"," +
                 "sex TEXT DEFAULT \"\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
