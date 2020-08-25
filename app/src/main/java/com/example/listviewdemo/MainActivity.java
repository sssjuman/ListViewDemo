package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView_member = (ListView) findViewById(R.id.listView_member);

        List<Member> memberList = getMemberList();

        listView_member.setAdapter(new MemberAdpater(this, memberList));


        //adapterView為被點擊的ListView
        //view為在BaseAdapter.getView()所被動態載入的layout檔
        //i為被點擊的索引位置
        //l為在BaseAdapter.getItemId()所回傳的自定義物件
        listView_member.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //呼叫getItemAtPosition()會取得在BaseAdapter.getItem()所回傳的自定義物件
                Member member = (Member) adapterView.getItemAtPosition(i);
                String text = "ID = " + member.getId() + " , Name = " + member.getName();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MemberAdpater extends BaseAdapter {
        private Context context;
        private List<Member> memberList;

        MemberAdpater(Context context, List<Member> memberList) {
            this.context = context;
            this.memberList = memberList;
        }


        //系統自動呼叫getCount()來問我們有幾筆資料
        @Override
        public int getCount() {
            return memberList.size();   //我們回復有幾筆資料(12筆)
        }

        //系統得到回復後，自動呼叫getView()12次
        //i代表第幾筆資料的索引位置，從0開始
        //View在一開始畫面尚未呈現時為null
        //viewGroup代表ListView
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //判斷是否有view可以重複利用
            if (view == null) {

                //LayoutInflater呼叫inflate()把layout檔動態載入View並依附在ListView
                //layout檔與ListView兩者為間接依存，故設為false
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                view = layoutInflater.inflate(R.layout.item_view, viewGroup, false);
            }

            Member member = memberList.get(i);  //取得data

            //data binding
            //view代表剛剛被動態載入的layout檔
            ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
            iv_image.setImageResource(member.getImage());

            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_id.setText(String.valueOf(member.getId()));

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(member.getName());

            return view;
        }

        //呼叫getItemAtPosition()時會自動呼叫getItem()
        @Override
        public Object getItem(int i) {
            return memberList.get(i);
        }

        //依照使用者點選哪一筆資料時會自動呼叫getItemId()
        @Override
        public long getItemId(int i) {
            return memberList.get(i).getId();
        }

    }


    public List<Member> getMemberList() {
        List<Member> members = new ArrayList<>();
        members.add(new Member(R.drawable.ic_accessibility_black_48dp, 30, "Curry"));
        members.add(new Member(R.drawable.ic_accessible_black_48dp, 27, "Mike"));
        members.add(new Member(R.drawable.ic_account_balance_black_48dp, 83, "John"));
        members.add(new Member(R.drawable.ic_account_circle_black_48dp, 48, "Joan"));
        members.add(new Member(R.drawable.ic_add_shopping_cart_black_48dp, 22, "Brown"));
        members.add(new Member(R.drawable.ic_alarm_add_black_48dp, 01, "TMac"));
        members.add(new Member(R.drawable.ic_alarm_black_48dp, 03, "Lee"));
        members.add(new Member(R.drawable.ic_android_black_48dp, 11, "Thompson"));
        members.add(new Member(R.drawable.ic_donut_large_black_48dp, 19, "Jerry"));
        members.add(new Member(R.drawable.ic_bug_report_black_48dp, 23, "James"));
        members.add(new Member(R.drawable.ic_assignment_ind_black_48dp, 33, "Dean"));
        members.add(new Member(R.drawable.ic_all_out_black_48dp, 86, "Bob"));
        return members;
    }
}