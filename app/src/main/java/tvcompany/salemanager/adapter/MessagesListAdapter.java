package tvcompany.salemanager.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Message;

public class MessagesListAdapter extends BaseAdapter {
    private Context context;
    private List<Message> messagesItems;
    public MessagesListAdapter(Context context, List<Message> navDrawerItems) {


        this.context = context;
        this.messagesItems = navDrawerItems;
    }
    @Override
    public int getCount() {
        return messagesItems.size();
    }
    @Override
    public Object getItem(int position) {
        return messagesItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * The following list not implemented reusable list items as list items
         * are showing incorrect data Add the solution if you have one
         * */
        Message m = messagesItems.get(position);
        int lastMessage;
        try {
            lastMessage = messagesItems.get(position -1).getTypeAction();
        }catch (Exception e)
        {
            lastMessage = -1;
        }

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // Identifying the message owner
        if (m.getTypeAction() == 0 ) {
            // message belongs to you, so load the right aligned layout
            if(lastMessage == m.getTypeAction() && lastMessage != -1)
            {
                convertView = mInflater.inflate(R.layout.list_item_message_right_notincludename,
                        null);
            }
            else
            {
                convertView = mInflater.inflate(R.layout.list_item_message_right,
                        null);
            }
        } else if (m.getTypeAction() == 1){
            // message belongs to other person, load the left aligned layout
            if(lastMessage == m.getTypeAction() && lastMessage != -1)
            {
                convertView = mInflater.inflate(R.layout.list_item_message_left_notincludename,
                        null);
            }
            else
            {
                convertView = mInflater.inflate(R.layout.list_item_message_left,
                        null);
            }
        }
        else if (m.getTypeAction() == 2){
            // message belongs to other person, load the left aligned layout
            convertView = mInflater.inflate(R.layout.list_item_message_action,
                    null);
        }
        else if (m.getTypeAction() == 3){
            // message belongs to other person, load the left aligned layout
            convertView = mInflater.inflate(R.layout.list_item_message_view,
                    null);
        }
        try {
            TextView txtMsg = (TextView) convertView.findViewById(R.id.txtMsg);
            txtMsg.setText(m.getData());
            if((m.getTypeAction() !=2 && m.getTypeAction() !=3)  && (m.getTypeAction() != lastMessage) || lastMessage == -1)
            {
                TextView lblFrom = (TextView) convertView.findViewById(R.id.lblMsgFrom);
                lblFrom.setText(m.getUserSend());
            }
        }catch (Exception e){}


        return convertView;
    }

}