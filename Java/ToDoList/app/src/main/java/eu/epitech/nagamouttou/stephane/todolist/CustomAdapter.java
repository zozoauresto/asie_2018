package eu.epitech.nagamouttou.stephane.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nagamo_s on 03/02/2018.
 */

public class CustomAdapter extends ArrayAdapter<TaskData> {
    public CustomAdapter(Context context, ArrayList<TaskData> task) {
        super(context, 0, task);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskData task = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.task_title);
        TextView content = (TextView) convertView.findViewById(R.id.task_content);
        TextView date = (TextView) convertView.findViewById(R.id.task_date);
        Button statut = (Button) convertView.findViewById(R.id.task_statut);
        TextView id = (TextView) convertView.findViewById(R.id.task_id);
        name.setText(task.name);
        content.setText(task.content);
        date.setText(task.date);
        statut.setText(task.statut);
        id.setText(String.valueOf(task.id));
        return convertView;
    }
}
