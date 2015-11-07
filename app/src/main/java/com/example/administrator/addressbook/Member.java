package com.example.administrator.addressbook;

import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-03.
 */
public class Member implements Serializable{
    public int id;
    public String name;
    public String mobilePhone;
    public String homePhone;
    public String officePhone;
    public String fax;
    public String homeAddr;
    public String homeZip;
    public String officeAddr;
    public String officeZip;
    public String email;
    public String memo;
    public String company;
    public String duty;
    public String homepage;
    public String birthday;
    public long groupNo = 0;
    public String groupName;

    public ArrayList<String[]> getitemlist(){
        ArrayList<String[]> list = new ArrayList<>();
        if(mobilePhone != null) {
            String numer[] = {"cell phone", mobilePhone};
            list.add(numer);
        }
        if(homePhone != null) {
            String numer[] = {"homePhone", homePhone};
            list.add(numer);
        }
        if(officePhone != null) {
            String numer[] = {"officePhone", officePhone};
            list.add(numer);
        }

        return list;
    }

    public void log(String tag)
    {
        // 로그용인데 스트링빌더 필요없겠지...
        String text = "[" + id + "] " + name;
        Log.d(tag, text);
        text = "- " +
                mobilePhone + ", " + homePhone + ", " + officePhone + ", " + fax + ", " +
                homeAddr + ", " + homeZip + ", " + officeAddr + ", " + officeZip + ", " +
                email + ", " + memo + ", " + company + ", " + duty + ", " + homepage + ", " + birthday + ", " + groupName;
        Log.d(tag, text);
    }
}
