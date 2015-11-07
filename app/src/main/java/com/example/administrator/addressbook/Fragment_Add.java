package com.example.administrator.addressbook;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment_Add extends Fragment implements View.OnClickListener{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button savebnt, listplusbtn;
    private ArrayList<AddItem> list;
    private ListView listView;
    private View tmpView;
    private AddListAdapter adapter;
    private ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment_Add newInstance(Context context) {
        Fragment_Add fragment = new Fragment_Add();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public Fragment_Add() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_address_add, container, false);
        tmpView = rootView;
        rootView = inflater.inflate(R.layout.fragment_add, container, false);

        listView = (ListView) rootView.findViewById(R.id.add_list);

        savebnt = (Button) rootView.findViewById(R.id.add_addbtn);
        listplusbtn = (Button) rootView.findViewById(R.id.add_listplustbtn);

        list = new ArrayList<AddItem>();

        adapter = new AddListAdapter(listView.getContext(), R.layout.item_address_add, list);
        listView.setAdapter(adapter);

        for(int i=0; i<5; i++)
            list.add(new AddItem((EditText)tmpView.findViewById(R.id.add_nameText),
                    (EditText)tmpView.findViewById(R.id.add_numberText)));

        adapter.notifyDataSetChanged();

        savebnt.setOnClickListener(this);
        listplusbtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_addbtn:
                //저장 버튼 눌렀을 때 DB에 저장되는 부분
                String name = null, number = null;
                for(int i=0; i<list.size(); i++) {
                    AddItem item = adapter.getItem(i);
                    item.nameTxt.getText();
                    item.numberTxt.getText();
                    if(!item.equals("/")) {

                    }else {
                        //name = adapter.getItem(i);
                    }
                }

                Toast.makeText(this.getActivity().getApplicationContext(), "Save BTN", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_listplustbtn:
                //리스트의 갯수를 증가시켜주는 버튼

                for(int i=0; i<5; i++) {
                    list.add(new AddItem((EditText)tmpView.findViewById(R.id.add_nameText),
                            (EditText)tmpView.findViewById(R.id.add_numberText)));
                    adapter.notifyDataSetChanged();
                }

                Toast.makeText(this.getActivity().getApplicationContext(), "List plus BTN", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
class AddItem {
    public EditText nameTxt;
    public EditText numberTxt;

    public AddItem(EditText nameTxt, EditText numberTxt) {
        this.nameTxt = nameTxt;
        this.numberTxt = numberTxt;
    }
}

class AddListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<AddItem> list;

    int layout;

    public AddListAdapter(Context context, int layout, ArrayList<AddItem> list) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public AddItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }

        EditText nametxt = (EditText) convertView.findViewById(R.id.add_nameText);
        EditText numbertxt = (EditText) convertView.findViewById(R.id.add_numberText);

        return convertView;
    }
}