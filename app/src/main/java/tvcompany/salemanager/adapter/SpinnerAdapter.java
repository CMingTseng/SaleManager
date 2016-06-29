package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Group;

/**
 * Created by Administrator on 6/28/2016.
 */
public class SpinnerAdapter extends BaseAdapter implements android.widget.SpinnerAdapter{
    private Activity context;
    private ArrayList<Group> list;

    public SpinnerAdapter(Activity context, ArrayList<Group> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View mySpinner = inflater.inflate(R.layout.spinnercustom, parent, false);
        Group group = (Group) list.get(position);
        TextView tv = (TextView) mySpinner.findViewById(R.id.spinner_tv);
        tv.setText(group.getText1());
        return mySpinner;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View mySpinner = inflater.inflate(R.layout.spinnercustom, parent, false);
        Group group = (Group) list.get(position);
        TextView tv = (TextView) mySpinner.findViewById(R.id.spinner_tv);
        tv.setText(group.getText1());
        return mySpinner;
    }
}

