package com.pacosanchez.emergencysystem;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class fragment_members extends Fragment {

    public static fragment_members newInstance(){
        fragment_members fragment = new fragment_members();
        return fragment;
    }

    private ArrayList<familyMember> familyMembers = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        familyMembers.add(new familyMember("NP Maximo Estrada","8186542778","estrada","Suca"));
        familyMembers.add(new familyMember("Erick El Morritas Gonzales","8186542778","erick","Suca"));
        familyMembers.add(new familyMember("NP Maximo Estrada","8186542778","estrada","Suca"));
        familyMembers.add(new familyMember("Erick El Morritas Gonzales","8186542778","erick","Suca"));
        familyMembers.add(new familyMember("NP Maximo Estrada","8186542778","estrada","Suca"));
        familyMembers.add(new familyMember("Erick El Morritas Gonzales","8186542778","erick","Suca"));




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_members,container, false);
        ArrayAdapter<familyMember> adapter = new familyMemberArrayAdapter(getActivity(),0,familyMembers);


        ListView listView = v.findViewById(R.id.customListView);
        listView.setAdapter(adapter);
        return v;
    }


}

class familyMemberArrayAdapter extends ArrayAdapter<familyMember> {

    private Context context;
    private ArrayList<familyMember> familyMembers;

    public familyMemberArrayAdapter(Context context, int resource, ArrayList<familyMember> objects){
        super(context,resource,objects);

        this.context = context;
        this.familyMembers = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        familyMember member = familyMembers.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.familymember_layout, null);

        TextView name = view.findViewById(R.id.fmember_name);
        TextView phoneNumber = view.findViewById(R.id.fmember_phonenumber);
        TextView address = view.findViewById(R.id.fmember_address);
        ImageView image = view.findViewById(R.id.image);

        name.setText(member.getName());
        address.setText(member.getAddress());
        phoneNumber.setText(member.getPhoneNumber());

        int imageId = context.getResources().getIdentifier(member.getImage(),"drawable",context.getPackageName());
        image.setImageResource(imageId);

        return view;
    }
}
