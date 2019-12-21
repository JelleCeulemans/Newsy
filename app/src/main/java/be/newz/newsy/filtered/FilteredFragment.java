package be.newz.newsy.filtered;

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

public class FilteredFragment extends Fragment {

    private List<Article> articles;

    private DatabaseHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_filtered);

        View root = inflater.inflate(R.layout.fragment_filtered, container, false);

        db = new DatabaseHelper(getContext());
        Preference preference = db.getPreference();
        String country = preference.getCountry();
        String language = preference.getLanguage();

        Bundle bundle = getArguments();

        if (bundle != null) {
            int search = bundle.getInt("search");
            String keyword = bundle.getString("keyword");

            if (search == 0) {
                String url = "https://gnews.io/api/v3/search?q="+ keyword +"&country="+country+"&lang="+language+"&token=74d66a7eec0a38cd21e8f7c48652c021";
                getArticles(root, url);
            }
            else if (search == 1) {
                String url = "https://gnews.io/api/v3/topics/"+ keyword +"?country="+country+"&lang="+language+"&token=74d66a7eec0a38cd21e8f7c48652c021";
                getArticles(root, url);
            } else {
                Toast.makeText(getContext(), R.string.toast_failed_fetching, Toast.LENGTH_LONG).show();
            }
        }


        return root;
    }

    public void getArticles(final View root, String url) {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                NetworkInfo info = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
               if (info != null) {
                   JsonHelper jsonHelper = new JsonHelper();
                   articles = jsonHelper.getArticles(result);

                   final ListView listViewArticles = (ListView) root.findViewById(R.id.listViewArticles);
                   ArticleAdapter artikelAdapter = new ArticleAdapter(getContext(), articles);
                   listViewArticles.setAdapter(artikelAdapter);
               } else {
                   Toast.makeText(getContext(), R.string.toast_internet, Toast.LENGTH_LONG).show();
               }
            }
        });
        httpReader.execute(url);
    }
}