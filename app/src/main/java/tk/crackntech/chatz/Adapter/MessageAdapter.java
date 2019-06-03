package tk.crackntech.chatz.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import me.himanshusoni.chatmessageview.ChatMessageView;
import tk.crackntech.chatz.AppDialog;
import tk.crackntech.chatz.R;
import tk.crackntech.chatz.model.Chat;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> implements View.OnClickListener {

    RecyclerView view;
    private RelativeLayout mListener;
    private Context mContext;
    private int a=1;
    private List<Chat> chats,chatsall;
    private String reciever,sender;

    public MessageAdapter(Context mContext, List<Chat> chats,RecyclerView listener) {
        this.chatsall = chats;
        this.chats = new ArrayList<>();
        this.chats.add(chatsall.get(0));
        this.mContext = mContext;
        this.view = listener;
        this.reciever = "anuroop";
        this.view.setOnClickListener(this);

        for (Chat chat : chats) {
            if (!chat.getUser().getId().equals("info") && !chat.getUser().getId().equals("anuroop"))
            {
                this.sender = chat.getUser().getId();
                break;
            }

        }


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        if (i==0)
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_left,viewGroup,false);
        else if (i==1)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_center,viewGroup,false);
        else
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_right,viewGroup,false);
        v.setOnClickListener(this);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {


        Chat chat = chats.get(i);
        viewHolder.chatView.setOnClickListener(this);

        if (!chat.getUser().getId().equals("info"))
        viewHolder.user.setText(chat.getUser().getName());
        if (chat.getType().equals("img")) {

            FirebaseStorage.getInstance().getReferenceFromUrl("gs://mychat-ca6b0.appspot.com/img").child(chat.getImage())
                    .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(mContext).load(uri.toString()).into(viewHolder.image);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });


           // viewHolder.image.setImageBitmap(BitmapFactory.decodeFile(chat.getImage().getPath()));
            viewHolder.image.setVisibility(View.VISIBLE);
            viewHolder.message.setVisibility(View.GONE);
        }
        else{
            viewHolder.message.setText(chat.getMessage());
            viewHolder.message.setVisibility(View.VISIBLE);
            if (!chat.getUser().getId().equals("info"))
            viewHolder.image.setVisibility(View.GONE);

        }





    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    @Override
    public void onClick(View v) {
        Log.i("TAG","HERE");
        if (isNetworkAvailable())
        updatedata();
        else
            new AppDialog(mContext).noInternet().show();

    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ChatMessageView chatView;
        TextView message,user;
        ImageView image;
       public viewHolder(@NonNull View itemView) {
           super(itemView);
           chatView = itemView.findViewById(R.id.chatm);
           message = itemView.findViewById(R.id.message);
           user = itemView.findViewById(R.id.user);
           image = itemView.findViewById(R.id.mimg);



       }
   }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
  public void updatedata(){
        int b = a;
       if (b<chatsall.size()){
           chats.add(chatsall.get(b));
           a=b+1;
           notifyDataSetChanged();
           view.scrollToPosition(chats.size()-1);
       }
  }

    @Override
    public int getItemViewType(int position) {

        if (chats.get(position).getUser().getId().equals(reciever))
            return 2;
        else if (chats.get(position).getUser().getId().equals("info"))
            return 1;
        else
            return 0;

    }
}
