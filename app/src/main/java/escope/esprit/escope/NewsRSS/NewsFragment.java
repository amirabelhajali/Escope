package escope.esprit.escope.NewsRSS;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import escope.esprit.escope.R;



public class NewsFragment extends Fragment {

    RecyclerView recyclerView;



    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        Feed feed = new Feed(getContext(), recyclerView);
        feed.execute();
        return view;
    }
}
