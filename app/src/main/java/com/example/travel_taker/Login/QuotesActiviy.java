package com.example.travel_taker.Login;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;

import java.util.ArrayList;
import java.util.Stack;

public class QuotesActiviy extends AppCompatActivity {

    public TextView quoteTextView;
    public ImageView nextButtonImageView;
    public ImageView previousButtonImageView;
    public ImageView shareButtonImageView;
    public ImageView favButtonImageView;
    public ArrayList<quotesManager> quoteList = new ArrayList<>();
    public Stack<quotesManager> previousQuotes = new Stack<>();
    public int index;
    public boolean isPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_activiy);

        quoteTextView =(TextView)findViewById(R.id.quotes_textView);
        nextButtonImageView = (ImageView)findViewById(R.id.next_button);
        previousButtonImageView = (ImageView)findViewById(R.id.previous_button);
        shareButtonImageView = (ImageView)findViewById(R.id.share_button);
        favButtonImageView = (ImageView)findViewById(R.id.heart_fav_off);

        Resources res = getResources();
        String[] allquotes = res.getStringArray(R.array.quotes);
        addToQuoteList(allquotes);


        final int quotesLength = quoteList.size();
        index = getRandomQuoute(quotesLength-1);
        quoteTextView.setText(quoteList.get(index).toString());


        nextButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPrevious = false;
                index = getRandomQuoute(quotesLength-1);
                quoteTextView.setText(quoteList.get(index).toString());
                previousQuotes.push(quoteList.get(index));
            }
        });

        previousButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPrevious && previousQuotes.size()>0)
                    previousQuotes.pop();
                    isPrevious = true;
                if(previousQuotes.size() > 0 && isPrevious)
                    quoteTextView.setText(previousQuotes.pop().toString());
                else
                    Toast.makeText(QuotesActiviy.this, "Get New Quote!", Toast.LENGTH_SHORT).show();
            }
        });

        //share button
        shareButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,quoteList.get(index).toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        //fav button
        favButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quoteList.get(index).isFavorite()){
                   favButtonImageView.setImageResource(R.mipmap.ic_heart_fav_off);
                   quoteList.get(index).setFavorite(false);
                }
                else{
                    favButtonImageView.setImageResource(R.mipmap.ic_heart_fav_on);
                    quoteList.get(index).setFavorite(true);
                }
            }
        });



    }

    public void addToQuoteList(String[] allquotes){
        for (int i= 0; i < allquotes.length; i++){

            String quote = allquotes[i];
            quotesManager newquote = new quotesManager(quote);
            quoteList.add(newquote);
        }

    }
    public int getRandomQuoute(int length){
     return (int) (Math.random()*length)+ 1;
    }

}

