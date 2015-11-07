package com.example.administrator.addressbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_Dial extends Fragment implements View.OnClickListener{

    private int numCount = 0;
    private static StringBuilder number = new StringBuilder();
    private Button[] buttons ;
    private TextView textView;

    public static Fragment_Dial newInstance() {
        Fragment_Dial fragment = new Fragment_Dial();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Dial() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dial, container, false);
        textView = (TextView) rootView.findViewById(R.id.dial_numberText);
        buttons = new Button[14];
        buttons[0] = (Button)rootView.findViewById(R.id.dial_num1);
        buttons[1] = (Button)rootView.findViewById(R.id.dial_num2);
        buttons[2] = (Button)rootView.findViewById(R.id.dial_num3);
        buttons[3] = (Button)rootView.findViewById(R.id.dial_num4);
        buttons[4] = (Button)rootView.findViewById(R.id.dial_num5);
        buttons[5] = (Button)rootView.findViewById(R.id.dial_num6);
        buttons[6] = (Button)rootView.findViewById(R.id.dial_num7);
        buttons[7] = (Button)rootView.findViewById(R.id.dial_num8);
        buttons[8] = (Button)rootView.findViewById(R.id.dial_num9);
        buttons[9] = (Button)rootView.findViewById(R.id.dial_star);
        buttons[10] = (Button)rootView.findViewById(R.id.dial_num0);
        buttons[11] = (Button)rootView.findViewById(R.id.dial_sharp);
        buttons[12] = (Button)rootView.findViewById(R.id.dial_call);
        buttons[13] = (Button)rootView.findViewById(R.id.dial_remove);

        for(int i=0; i<buttons.length; i++)
            buttons[i].setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dial_num1:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("1");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num2:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("2");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num3:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("3");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num4:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("4");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num5:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("5");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num6:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("6");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num7:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("7");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num8:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("8");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num9:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("9");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_num0:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("0");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_star:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("*");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_sharp:
                if (numCount == 3 || numCount == 7)
                    addHyphen();
                number.append("#");
                textView.setText(number.toString());
                numCount++;
                break;
            case R.id.dial_call:
                Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number.toString()));
                textView.setText("");
                numCount = 0;
                startActivity(in);
                break;
            case R.id.dial_remove:
                if (numCount != 0) {
                    number.deleteCharAt(number.length() - 1);
                    if (numCount == 4 || numCount == 8)
                        number.deleteCharAt(number.length() - 1);

                    textView.setText(number.toString());
                    numCount--;
                }
                break;
        }
    }

    private void addHyphen() {
        number.append("-");
    }
}