package be.newz.newsy.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.List;
import be.newz.newsy.articles.Article;
import be.newz.newsy.articles.ArticleAdapter;
import be.newz.newsy.articles.DatabaseHelper;
import be.newz.newsy.articles.HttpReader;
import be.newz.newsy.articles.JsonHelper;
import be.newz.newsy.preferences.Preference;
import be.newz.newsy.R;

public class HomeFragment extends Fragment {

    private List<Article> articles;

    private DatabaseHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_home);

        db = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getArticles(root);
        return root;
    }

    public void getArticles(final View root) {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                NetworkInfo info = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                if (info != null) {
                    JsonHelper jsonHelper = new JsonHelper();
                    articles = jsonHelper.getArticles(result);

                    final ListView listViewArtikels = (ListView) root.findViewById(R.id.listViewArticles);
                    ArticleAdapter artikelAdapter = new ArticleAdapter(getContext(), articles);
                    listViewArtikels.setAdapter(artikelAdapter);
                } else {
                    Toast.makeText(getContext(), R.string.toast_internet, Toast.LENGTH_LONG).show();
                }
            }
        });

        Preference preference = db.getPreference();
        String country = preference.getCountry();
        String language = preference.getLanguage();

        //74d66a7eec0a38cd21e8f7c48652c021
        //f8437c31cb1a27be78ccbd616bc732ec
        httpReader.execute("https://gnews.io/api/v3/top-news?country="+country+"&lang="+language+"&token=74d66a7eec0a38cd21e8f7c48652c021");
    }
}