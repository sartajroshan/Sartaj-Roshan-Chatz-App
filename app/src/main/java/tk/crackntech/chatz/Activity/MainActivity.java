package tk.crackntech.chatz.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tk.crackntech.chatz.Adapter.ChatAdapter;
import tk.crackntech.chatz.ItemClickSupport;
import tk.crackntech.chatz.R;
import tk.crackntech.chatz.model.Chat;
import tk.crackntech.chatz.model.Chats;
import tk.crackntech.chatz.model.User;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatAdapter adapter;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDatabase = FirebaseDatabase.getInstance().getReference("chats");
       adddata();

        recyclerView = findViewById(R.id.recycler);

        adapter = new ChatAdapter(chatscreate());
        recyclerView.setAdapter(adapter);



        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Bundle b;

                startActivity(new Intent(MainActivity.this,ChatActivity.class)
                .putExtra("id","2"));
            }
        });



    }

    public List<Chats> chatscreate(){
        List<Chats> a = new ArrayList<>();
        Chats b  = new Chats("1","Chat One",null);
        a.add(b);

        return a;
    }

    public void adddata(){

        Chats c = new Chats("2","Chat Two",makechat("Surya"));
        //String userId = mDatabase.push().getKey();

        mDatabase.child("2").setValue(c);
    }

    public List<Chat> makechat(String sender){
        List<Chat> list = new ArrayList<>();
        User senders = new User(sender.replaceAll("\\s","").toLowerCase(),sender);
        User reciever = new User("anuroop","Anuroop");

        Chat a;
        a = new Chat("",senders,"msg","Hi",null);
        list.add(a);
        a = new Chat("",reciever,"msg","Hello",null);
        list.add(a);
        a = new Chat("",senders,"msg","How Are You?",null);
        list.add(a);
        a = new Chat("",reciever,"msg","Fine, U?",null);
        list.add(a);
        a = new Chat("",senders,"msg","Fine too",null);
        list.add(a);
        a = new Chat("",reciever,"msg","where are you now?",null);
        list.add(a);
        a = new Chat("",new User("info",null),"msg","Sending Image",null);
        list.add(a);
        a = new Chat("",senders,"img","","unnamed.jpg");
        list.add(a);
        a = new Chat("",reciever,"msg","ok. bye!",null);
        list.add(a);
        a = new Chat("",senders,"msg","Bye",null);
        list.add(a);
        a = new Chat("",senders,"msg","See you",null);
        list.add(a);
        for (int i = 0;i<40;i++){
            if (i%2==0)
                a = new Chat("",senders,"msg","hi",null);
            else
                a = new Chat("",reciever,"msg","hello",null);

            list.add(a);


        }




        return list;
    }
}
