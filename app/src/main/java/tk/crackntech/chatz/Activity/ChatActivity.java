package tk.crackntech.chatz.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tk.crackntech.chatz.Adapter.MessageAdapter;
import tk.crackntech.chatz.R;
import tk.crackntech.chatz.model.Chat;
import tk.crackntech.chatz.model.Chats;

public class ChatActivity extends AppCompatActivity {

    RecyclerView Rchat;
    MessageAdapter adapter;
    RelativeLayout layoutroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String id = getIntent().getStringExtra("id");
        Rchat = findViewById(R.id.chatRecycler);
        layoutroot = findViewById(R.id.root);

        FirebaseDatabase.getInstance().getReference("chats").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Chats chats = dataSnapshot.getValue(Chats.class);
                adapter = new MessageAdapter(ChatActivity.this,chats.getChats(),Rchat);
                Rchat.setHasFixedSize(true);
                Rchat.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
