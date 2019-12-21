package be.newz.newsy.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

import be.newz.newsy.articles.Article;
import be.newz.newsy.articles.DatabaseHelper;
import be.newz.newsy.R;

public class FavoritesFragment extends Fragment {

    private DatabaseHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_favorites);

        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        db = new DatabaseHelper(getContext());
        readarticlesSQL(root);

        return root;
    }

    public void readarticlesSQL (final View root) {
        List<Article> articles = db.getArticles();

        ListView listViewArtikels = root.findViewById(R.id.listViewArticles);
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(getContext(), articles);
        listViewArtikels.setAdapter(favoritesAdapter);
    }
}