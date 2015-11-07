package com.example.administrator.addressbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-07.
 */
public class Fragment_group extends Fragment {
    private static PhonebookReader pbr;
    private static ArrayList<Member> memberlist = null;
    private static ArrayList<String[]> grouplist= null;

    public static Fragment_group newInstance(Context ctx) {
        Fragment_group fragment = new Fragment_group();
        Bundle args = new Bundle();
        pbr = new PhonebookReader(ctx);
        memberlist = pbr.getMemberList();
        grouplist = pbr.getGroupArrayList();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        Group_list_Adapter adapter = new Group_list_Adapter(this.getActivity().getApplicationContext(), R.layout.item_group, grouplist, memberlist);
        ListView listview = (ListView)rootView.findViewById(R.id.group_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Member member = (Member) parent.getAdapter().getItem(position);
                Intent i = new Intent(getActivity().getBaseContext(), Activity_People_Info.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("Member", member);
                if (i != null)
                    getContext().startActivity(i);
            }
        });

        return rootView;
    }
}

class Group_list_Adapter extends BaseAdapter {
    private ArrayList<String[]> grouplist = null;
    private ArrayList<Member> memberlist = null;
    private LayoutInflater inflater;
    private Context ctx = null;
    private int layout;

    public Group_list_Adapter(Context ctx, int alayout, ArrayList<String[]> grouplist, ArrayList<Member> memberlist){
        this.ctx = ctx;
        this.inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        this.grouplist = grouplist;
        this.memberlist = memberlist;
        this.layout = alayout;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    //어댑터의 항목 수 조사
    @Override
    public int getCount() {
        return grouplist.size();
    }

    // position 위치의 Name반환
    @Override
    public Object getItem(int position) {
        return grouplist.get(position);
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

        TextView groupName = (TextView)convertView.findViewById(R.id.groupName);
        groupName.setText(grouplist.get(position)[1]);

        return convertView;
    }
}
