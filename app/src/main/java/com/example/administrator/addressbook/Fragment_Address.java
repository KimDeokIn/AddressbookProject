package com.example.administrator.addressbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-03.
 */
public class Fragment_Address extends Fragment{
    private static PhonebookReader pbr;
    private static ArrayList<Member> memberlist = null;
    private static ArrayList<Member> aList_item_list = null;

    public static Fragment_Address newInstance(Context ctx) {
        Fragment_Address fragment = new Fragment_Address();
        Bundle args = new Bundle();
        pbr = new PhonebookReader(ctx);
        memberlist = pbr.getMemberList();
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Address() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_list, container, false);

        //List_User_item클래스 형태 데이터 준비
        aList_item_list = new ArrayList<Member>();
        for (Member member: memberlist)
            aList_item_list.add(member);

        Address_list_Adapter adapter = new Address_list_Adapter(this.getActivity().getApplicationContext(), R.layout.item_address, aList_item_list);
        ListView listview = (ListView)rootView.findViewById(R.id.memlist);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Member member = (Member)parent.getAdapter().getItem(position);

                Intent i = new Intent(getActivity().getBaseContext(), Activity_People_Info.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("Member", member);
                if(i !=null)
                    getContext().startActivity(i);
            }
        });

        return rootView;
    }
}

class Address_list_Adapter extends BaseAdapter{
    private ArrayList<Member> item_list = null;
    private LayoutInflater inflater;
    private Context ctx = null;
    private int layout;

    public Address_list_Adapter(Context ctx, int alayout, ArrayList<Member> list){
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        this.item_list = list;
        this.layout = alayout;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    //어댑터의 항목 수 조사
    @Override
    public int getCount() {
        return item_list.size();
    }

    // position 위치의 Name반환
    @Override
    public Object getItem(int position) {
        return item_list.get(position);
    }

    //position 위치의 ID값 반환
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView peopleName = (TextView) convertView.findViewById(R.id.PeopleName);
        peopleName.setText(item_list.get(position).name);

        ImageButton callBtn = (ImageButton) convertView.findViewById(R.id.CallBtn);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = null;
                if (item_list.get(position).mobilePhone != null)
                    number = item_list.get(position).mobilePhone;
                else if (item_list.get(position).homePhone != null)
                    number = item_list.get(position).homePhone;
                else if (item_list.get(position).officePhone != null)
                    number = item_list.get(position).officePhone;

                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (i != null)
                    ctx.startActivity(i);
            }
        });

        return convertView;
    }
}
