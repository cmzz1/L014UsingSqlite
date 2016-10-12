package uicontrols.atm.mdzz.l014usingsqlite;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    private SimpleCursorAdapter adapter;
    private EditText etName,etSex;
    private Button btnAdd;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues cv = new ContentValues();
            cv.put("name",etName.getText().toString());
            cv.put("sex",etSex.getText().toString());
            dbWrite.insert("user",null,cv);
            refreshListView();
        }
    };
    private AdapterView.OnItemLongClickListener ListViewItemLongClickListener =
    new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView,
                                       View view, final int i, long l) {

            android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    new AlertDialog.Builder(MainActivity.this).setTitle("tixing").setMessage("niyazuosi").setNegativeButton("qu",null).setPositiveButton("sure", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Cursor c = adapter.getCursor();
                            c.moveToPosition(i);
                            int itemId = c.getInt(c.getColumnIndex("_id"));
                            dbWrite.delete("user","_id=?",new String[]{itemId+""});
                            refreshListView();
                        }
                    }).show();
            return true;
        }
    };

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etName);
        etSex = (EditText) findViewById(R.id.etSex);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(btnAddListener);

        db =new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        adapter = new SimpleCursorAdapter(this,R.layout.user_list_cell,null,new String[]{"name","sex"},
                new int[]{R.id.tvName,R.id.tvSex});
        setListAdapter(adapter);
        refreshListView();
        getListView().setOnItemLongClickListener(ListViewItemLongClickListener);

    }
    private void refreshListView(){
        Cursor c  = dbRead.query("user",null,null,null,null,null,null);
        adapter.changeCursor(c);
    }


}
