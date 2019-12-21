package be.newz.newsy.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import be.newz.newsy.R;

public class HelpFragment extends Fragment {

    private  RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_help);

        View root = inflater.inflate(R.layout.fragment_help, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        List<Page> pages = new ArrayList<>();

        pages.add(new Page(getResources().getString(R.string.help_home_title), R.layout.help_home, R.drawable.home, R.color.colorButtonBrowser));
        pages.add(new Page(getResources().getString(R.string.help_search_title), R.layout.help_search, R.drawable.ic_menu_search, R.color.colorButtonBrowser));
        pages.add(new Page(getResources().getString(R.string.help_favorites_title), R.layout.help_favorites, R.drawable.heart, R.color.colorButtonBrowser));
        pages.add(new Page(getResources().getString(R.string.help_settings_title), R.layout.help_settings,  R.drawable.ic_menu_manage, R.color.colorButtonBrowser));
        pages.add(new Page(getResources().getString(R.string.help_authors_title), R.layout.help_authors, R.drawable.people, R.color.colorButtonBrowser));

        adapter.setItems(pages);
        recyclerView.setAdapter(adapter);
        return root;
    }
}