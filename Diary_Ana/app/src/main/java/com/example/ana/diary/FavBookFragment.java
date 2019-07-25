package com.example.ana.diary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavBookFragment extends Fragment {

    // Array of strings storing books titles
    String[] book_titles = new String[] {
            "Harry Potter and the Half Blood Price",
            "The Hitchhiker's Guide to the Galaxy",
            "A Series of Unfortunate Events"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] books = new int[]{
            R.mipmap.pic_hp_foreground,
            R.mipmap.pic_hgg_foreground,
            R.mipmap.pic_sue_foreground
    };

    // Array of strings to store books authors
    String[] book_authors = new String[]{
            "J. K. Rowling",
            "Douglas Adam",
            "Lemony Snicket"
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).setActionBarTitle("My Favorite Books");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_favbook, container, false);

        TextView book_title = (TextView) v.findViewById(R.id.book_title);
        TextView book_author = (TextView) v.findViewById(R.id.book_author);
        ImageView img_book = (ImageView) v.findViewById(R.id.img_book);

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<books.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("book_title", book_titles[i]);
            hm.put("book_author",book_authors[i]);
            hm.put("book", Integer.toString(books[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "book","book_title","book_author" };

        // Ids of views in listview_layout
        int[] to = { R.id.img_book,R.id.book_title,R.id.book_author};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(v.getContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) v.findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

        return v;

        //return inflater.inflate(R.layout.fragment_favbook, container, false);
    }
}
