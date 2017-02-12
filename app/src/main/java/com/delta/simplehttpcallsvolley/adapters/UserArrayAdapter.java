package com.delta.simplehttpcallsvolley.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.delta.simplehttpcallsvolley.R;
import com.delta.simplehttpcallsvolley.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samantha on 2/11/17 11:48 PM .
 */

public class UserArrayAdapter extends ArrayAdapter<User> {

    private Activity activity;
    private UserDetailDecorator decorator;

    public UserArrayAdapter(Activity activity, List<User> objects) {
        super(activity, R.layout.list_cell_layout, objects);
        setActivity(activity);
        setDecorator(new UserDetailDecorator((ArrayList<User>) objects));
    }

    private void setDecorator(UserDetailDecorator decorater) {
        this.decorator = decorater;
    }

    private Activity getActivity() {
        return activity;
    }

    private void setActivity(Activity activity) {
        this.activity = activity;
    }

    private class ViewHolder {
        TextView textName, textId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View vi = view;

        ViewHolder holder = new ViewHolder();

        if (vi == null || holder.textId == null) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_cell_layout, null);
            holder = new ViewHolder();

            holder.textId = (TextView) vi.findViewById(R.id.textId);
            holder.textName = (TextView) vi.findViewById(R.id.textName);

            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.textId.setText("Id : " + decorator.getId(position));
        holder.textName.setText("Name : " + decorator.getName(position));

        return vi;

    }
}
