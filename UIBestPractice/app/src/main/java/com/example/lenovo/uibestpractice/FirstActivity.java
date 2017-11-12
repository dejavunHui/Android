package com.example.lenovo.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initMsgs();

        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.button_send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size());//当有新消息时，刷新ListView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);//将Listview定位到最后一行
                    inputText.setText("");//清空输入框里面的内容
                }
            }
        });
    }

    private void initMsgs(){
        Msg msg1=new Msg("Hello",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hi",Msg.TYPE_SEND);
        msgList.add(msg2);
    }
}
