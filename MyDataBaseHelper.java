package vn.itplus.sqlite_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private final String TAG = "DBManger";
    private static final String DATABASE_NAME = "Job_List";
    private static final String ID = "id";
    private static final String TABLE_NAME = "Jobs";
    private static final String TIEUDE = "title";
    private static final String NOIDUNG = "content";
    private static int VERSION = 1;

    private Context context;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " ("+
            ID + " INTEGER PRIMARY KEY, " +
            TIEUDE + " TEXT, " +
            NOIDUNG + " TEXT) ";

    public MyDataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
        this.context=context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpdate: ");
    }

    public void addJob(Job job){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIEUDE, job.getTieude());
        values.put(NOIDUNG, job.getNoiDung());
        database.insert(TABLE_NAME,null,values);
        database.close();
        Log.d(TAG,"Add Successfully");
    }

    public List<Job>getAllJob(){
        List<Job> list =new ArrayList<>();
        String selectedQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database =getWritableDatabase();
        Cursor cursor = database.rawQuery(selectedQuery,null);
        if(cursor.moveToFirst()){
            do{
                Job job = new Job();
                job.setId(cursor.getInt(0));
                job.setTieude(cursor.getString(1)+"");
                job.setNoiDung(cursor.getString(2)+"");
                list.add(job);
            }while (cursor.moveToNext());
            database.close();
        }
        return list;
    }
    public int upDateDataBase (Job job){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIEUDE, job.getTieude());
        values.put(NOIDUNG, job.getNoiDung());

        return database.update(TABLE_NAME,values, ID + "=?",new String[]{String.valueOf(job.getId())});
    }
    public int deleteDataBase (int id){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
}
