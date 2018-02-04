package eu.epitech.nagamouttou.stephane.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import eu.epitech.nagamouttou.stephane.todolist.db.TaskContract;
import eu.epitech.nagamouttou.stephane.todolist.db.TaskDbHelper;

public class TaskAdd extends AppCompatActivity {
    private TaskDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        mHelper = new TaskDbHelper(this);
        Intent intent;
        intent = getIntent();

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COL_TASK_TITLE, intent.getStringExtra("Task"));
        values.put(TaskContract.TaskEntry.COL_TASK_CONTENT, intent.getStringExtra("Content"));
        values.put(TaskContract.TaskEntry.COL_TASK_DATE, intent.getStringExtra("DateTime"));
        values.put(TaskContract.TaskEntry.COL_TASK_STATUT, "TODO");
        db.insertWithOnConflict(TaskContract.TaskEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}
