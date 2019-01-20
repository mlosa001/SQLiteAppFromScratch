package android.example.com.appfromscratch.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.com.appfromscratch.model.Note;
import android.graphics.SweepGradient;

import java.util.ArrayList;
import java.util.List;

public class Notedb extends SQLiteOpenHelper {
    private static Notedb databaseInstance;
    private static final String NAME = "NOTES_DATABASE.db";
    private static final String TABLE_NAME = "notes";

    private static final int VERSION_NUMBER = 1;

    private Notedb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public static synchronized Notedb getInstance(Context context){

        if(databaseInstance == null){
            databaseInstance = new Notedb(context.getApplicationContext(), NAME, null, VERSION_NUMBER);
        }
        return databaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, message TEXT);" );
        //primary key  = id_integer
        //sql and java code


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//method 2 add a note
    public void addNote(Note note) {

        //check if entry is there already if it is do nothing
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE name = '" + note.getName() +
                        "' AND message = '" + note.getMessage() +
                        "';", null);

        //if entry doesnt match then add the row
        if (cursor.getCount() == 0) {
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                    "(name, message) VALUES('" +
                    note.getName() + "', '" +
                    note.getMessage() + "');");
        }
        cursor.close();
        //dont wanna hav stream of d@a still b open
    }

    public List<Note> getNoteList() {
        //what is cursor


        List<Note> noteList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + ";", null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                //if has a first then get first row ?
                do {
                    Note note = new Note(
                            cursor.getString(cursor.getColumnIndex("name")),
                            cursor.getString(cursor.getColumnIndex("message")));
                    noteList.add(note);
                } while (cursor.moveToNext());
                //when false return noteList
            }
        }
        return noteList;
    }
}

    //Create methods which allow a user to add a note, and allow a user to get all the notes saved in the database table.

