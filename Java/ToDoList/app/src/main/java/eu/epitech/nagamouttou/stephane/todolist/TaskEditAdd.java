package eu.epitech.nagamouttou.stephane.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import eu.epitech.nagamouttou.stephane.todolist.db.TaskContract;
import eu.epitech.nagamouttou.stephane.todolist.db.TaskDbHelper;

public class TaskEditAdd extends AppCompatActivity {
    private TaskDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit_add);

        mHelper = new TaskDbHelper(this);
        Intent intent;
        intent = getIntent();

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int id = Integer.valueOf(intent.getStringExtra("Id"));

        values.put(TaskContract.TaskEntry.COL_TASK_TITLE, intent.getStringExtra("Task"));
        values.put(TaskContract.TaskEntry.COL_TASK_CONTENT, intent.getStringExtra("Content"));
        values.put(TaskContract.TaskEntry.COL_TASK_DATE, intent.getStringExtra("Date"));

        db.update(TaskContract.TaskEntry.TABLE, values, TaskContract.TaskEntry._ID + " = " + id,null);
        db.close();

        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}
