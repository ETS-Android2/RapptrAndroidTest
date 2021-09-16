package com.datechnologies.androidtest.presentation.feature.chat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.presentation.ui_model.ChatUiModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A recycler view adapter used to display chat log messages in {@link ChatActivity}.

 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>
{

    private static final String TAG = "ChatAdapter";
    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private List<ChatUiModel> chatLogMessageModelList;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public ChatAdapter()
    {
        chatLogMessageModelList = new ArrayList<>();
    }

    //==============================================================================================
    // Class Instance Methods
    //==============================================================================================

    public void setChatLogMessageModelList(List<ChatUiModel> chatLogMessageModelList) {
        Log.d(TAG, "setChatLogMessageModelList()");
        this.chatLogMessageModelList = chatLogMessageModelList;
        this.notifyItemRangeInserted(0, chatLogMessageModelList.size());
    }

    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);

        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder viewHolder, int position)
    {
        ChatUiModel c = chatLogMessageModelList.get(position);
        viewHolder.messageTextView.setText(c.getMessage());
        viewHolder.usernameTextView.setText(c.getUsername());
        Picasso.get().load(c.getAvatarUrl()).placeholder(R.drawable.ic_avatar_placeholder).into(viewHolder.avatarImageView);

    }

    @Override
    public int getItemCount()
    {
        return chatLogMessageModelList.size();
    }

    //==============================================================================================
    // ChatViewHolder Class
    //==============================================================================================

    public static class ChatViewHolder extends RecyclerView.ViewHolder
    {
        ImageView avatarImageView;
        TextView messageTextView;
        TextView usernameTextView;

        public ChatViewHolder(View view)
        {
            super(view);
            avatarImageView = (ImageView)view.findViewById(R.id.avatarImageView);
            messageTextView = (TextView)view.findViewById(R.id.messageTextView);
            usernameTextView = (TextView)view.findViewById(R.id.username);
        }
    }

}
