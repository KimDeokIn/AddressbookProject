package com.example.administrator.addressbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Activity_People_Info extends AppCompatActivity {
    private Member member = null;
    private ArrayList<String[]> itemlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_info);

        Intent i = getIntent();
        member = (Member)i.getSerializableExtra("Member");
        itemlist = member.getitemlist();


        TextView name_back_btn = (TextView)findViewById(R.id.name_back_btn);
        name_back_btn.setText("< "+member.name);

        ImageButton edit_info_Btn = (ImageButton)findViewById(R.id.edit_info_btn);
        info_list_Adapter adapter = new info_list_Adapter(this.getBaseContext(), R.layout.number_info, itemlist);
        ListView listView = (ListView)findViewById(R.id.people_info_list);
        listView.setAdapter(adapter);

        TextView email = (TextView)findViewById(R.id.email);
        if(member.email != null)
            email.setText(member.email);
        else
            email.setText("None email");

        TextView group = (TextView)findViewById(R.id.group);
        if(member.groupName != null)
            email.setText(member.groupName);
        else
            email.setText("default group");


        //Activity 종료 후 이전 Activity로 돌아감 생성주기 함수 선택
        name_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //현재 member객체를 연락처 수정 Activity로 전달
        edit_info_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent i = new Intent(this, 전달할 Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("Member", member);
                if(i !=null)
                    getContext().startActivity(i);
                */
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class info_list_Adapter extends BaseAdapter {
        private ArrayList<String[]> item_list = null;
        private LayoutInflater inflater;
        private Context ctx = null;
        private int layout;

        public info_list_Adapter(Context ctx, int alayout, ArrayList<String[]> list){
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

            TextView numberpart = (TextView)convertView.findViewById(R.id.numpberpart);
            numberpart.setText(item_list.get(position)[0]);

            TextView numberinfo = (TextView)convertView.findViewById(R.id.numberinfo);
            numberinfo.setText(item_list.get(position)[1]);

            Button callbtn = (Button)convertView.findViewById(R.id.number_callbtn);
            callbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item_list.get(position)[1]));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (i != null)
                        ctx.startActivity(i);
                }
            });
            return convertView;
        }
    }

}
