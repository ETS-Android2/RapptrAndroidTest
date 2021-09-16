package com.datechnologies.androidtest.presentation.feature.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.databinding.ActivityChatBinding;
import com.datechnologies.androidtest.presentation.BaseActivity;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Screen that displays a list of chats from a chat log.
 */
public class ChatActivity extends BaseActivity {

    private static final String TAG = "ChatActivity";

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private RecyclerView recyclerView;

    private ChatAdapter chatAdapter;

    private ChatViewModel mViewModel;


    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, ChatActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatBinding binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView;

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.chat);

        chatAdapter = new ChatAdapter();

        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        mViewModel.getChats().observe(this, chats -> {
            chatAdapter.setChatLogMessageModelList(chats);
        });

        // Show error, loading and success views
        mViewModel.getStatus().observe(this, status -> {

            //Make all views invisible to start
            binding.recyclerView.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.errorMessage.setVisibility(View.INVISIBLE);


            switch (status){
                case ChatViewModel.STATUS_SUCCESS:
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    break;
                case ChatViewModel.STATUS_LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case ChatViewModel.STATUS_ERROR:
                    binding.errorMessage.setVisibility(View.VISIBLE);
                default:
                    break;
            }
        });
        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

        // TODO: Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
        // TODO: Parse this chat data from JSON into ChatLogMessageModel and display it.
    }
}
