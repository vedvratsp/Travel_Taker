package com.example.travel_taker.Login;

public class quotesManager {

    private String quotes;
    private boolean isFavorite;

    public quotesManager(String quote) {
        this.quotes = quote;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return quotes + '\'' ;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite= favorite;
    }
}

