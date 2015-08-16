package SH1;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.studentmodule.R;
import com.studentmodule.StudentPortalActivity;
import com.studentmodule.TeacherProfileActivity;

import java.util.ArrayList;

import SH1.SH1_5_1;
import SH1.sh1_5Data;

/**
 * Created by aliabbasjaffri on 23/07/15.
 */
public class sh15ArrayAdapter extends ArrayAdapter<sh1_5Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh1_5Data> items = new ArrayList<>();

    public sh15ArrayAdapter(Context context, int resource, ArrayList<sh1_5Data> data)
    {
        super(context, resource , data);

        this.context = context;
        XML_ID = resource;
        this.items = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        if (convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(XML_ID, null);
        }

        final sh1_5Data item = items.get(position);

        Button button = (Button) convertView.findViewById(R.id.sh1_5Button);
        button.setText(item.getButtonInfo());

        if( item.getButtonType() )
            button.setBackgroundResource(R.drawable.regular);
        else
            button.setBackgroundResource(R.drawable.trial);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = SH1_5_1.newInstance(item.getButtonInfo(), item.getListInfo(), item.getListDetail(), position , item.getButtonType());
                ((TeacherProfileActivity)context).getSupportFragmentManager().beginTransaction().add(R.id.teacherProfile, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        TextView listInfo = (TextView) convertView.findViewById(R.id.sh1_5ListInfo);
        listInfo.setText(item.getListInfo());

        TextView listDetail = (TextView) convertView.findViewById(R.id.sh1_5ListDetail);
        listDetail.setText(item.getListDetail());

        return convertView;
    }
}
