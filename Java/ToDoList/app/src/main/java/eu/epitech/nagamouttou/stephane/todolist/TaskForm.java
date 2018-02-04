package eu.epitech.nagamouttou.stephane.todolist;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Date;

public class TaskForm extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Form";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);
    }

    public void sendForm(View view) {
        Intent intent = new Intent(this, TaskAdd.class);

        intent.putExtra("Task", ((EditText)findViewById(R.id.Task)).getText().toString());
        intent.putExtra("Content", ((EditText)findViewById(R.id.Content)).getText().toString());
        intent.putExtra("DateTime", ((EditText)findViewById(R.id.txtdate)).getText().toString() + " " + ((EditText)findViewById(R.id.txttime)).getText().toString() + ":00");
        startActivity(intent);
    }

    public void onStart(){
        super.onStart();
        EditText txtDate=(EditText)findViewById(R.id.txtdate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        EditText txtTime = (EditText)findViewById(R.id.txttime);
        txtTime.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    TimeDialog dialog = new TimeDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "TimePicker");
                }
            }
        });
    }

}
