package tvcompany.salemanager.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.opengl.EGLExt;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.OderActivity;
import tvcompany.salemanager.model.Child;
import tvcompany.salemanager.model.Group;

/**
 * Created by Administrator on 6/26/2016.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Group> groups;

    public ExpandListAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        this.groups = groups;
    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> child =groups.get(groupPosition).getItem();
        return child.size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> child =groups.get(groupPosition).getItem();
        return child.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        Log.d("Context_log",String.valueOf(context));
        GroupHoder hoder = new GroupHoder();
        hoder.tv1  = (TextView) convertView.findViewById(R.id.tv_1);
        hoder.tv2  = (TextView) convertView.findViewById(R.id.tv_2);
        hoder.tv3  = (TextView) convertView.findViewById(R.id.tv_3);
        hoder.imageView = (CircularImageView) convertView.findViewById(R.id.image);
        hoder.tv1.setText(group.getText1());
        hoder.tv2.setText(group.getText2());
        hoder.tv3.setText(group.getText3());
        hoder.imageView.setImageResource(group.getImage());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Child child = (Child) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        ChildHoder hoder = new ChildHoder();
        hoder.tv1 = (TextView) convertView.findViewById(R.id.tv_sl);
        hoder.btn = (Button) convertView.findViewById(R.id.btn_ok);
        hoder.edt = (EditText) convertView.findViewById(R.id.edt_soluong);
        hoder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group g = (Group) getGroup(groupPosition);
                Toast.makeText(context,"Postion"+g.text1,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class GroupHoder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        CircularImageView imageView;
    }

    class ChildHoder{
        TextView tv1;
        EditText edt;
        Button btn;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
