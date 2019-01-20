package android.example.com.appfromscratch;

import android.example.com.appfromscratch.database.Notedb;
import android.example.com.appfromscratch.model.Note;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class NoteSubmissionActivity2 extends AppCompatActivity {
    //access db
    private EditText firstText;
    private EditText secT;
    private Button submit;
    private Notedb notedb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_submission2);

        firstText = findViewById(R.id.first_et);
        secT = findViewById(R.id.second_et);
        submit = findViewById(R.id.submit_b);

        notedb = Notedb.getInstance(getApplicationContext());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = firstText.getText().toString();
                    String message = secT.getText().toString();
                    Note note = new Note(name, message);
                    notedb.addNote(note);
                    Toast.makeText(getApplicationContext(), "data Added", Toast.LENGTH_LONG).show();
                    //show if d@a added


                }catch(Exception e){
                    e.printStackTrace();
                }
                //show if d@a added
        // get db log it, iterate trhu list
                for( int i = 0; i < notedb.getNoteList().size(); i++){
                    //log it when loop
                    Log.d("dbRow", notedb.getNoteList().get(i).getName()
                            + " " +  notedb.getNoteList().get(i).getMessage());


                }
            }
        });
    }
}
