package tk.crackntech.chatz.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tk.crackntech.chatz.Activity.ChatActivity;
import tk.crackntech.chatz.R;
import tk.crackntech.chatz.model.Chats;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.viewHolder> {


    List<Chats> chatsList;

    public ChatAdapter(List<Chats> chatsList) {
        this.chatsList = chatsList;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView chatid,chatname;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            chatid = itemView.findViewById(R.id.chatid);
            chatname = itemView.findViewById(R.id.chatname);

        }
    }

    @NonNull
    @Override
    public ChatAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat,viewGroup,false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.viewHolder viewHolder, int i) {

        viewHolder.chatid.setText(chatsList.get(i).getId());
        viewHolder.chatname.setText(chatsList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }
}
