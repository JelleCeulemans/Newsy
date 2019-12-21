package be.newz.newsy.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import android.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;

import be.newz.newsy.R;

public class BrowserFragment extends Fragment{

    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_browser, container, false);

        if (container != null) {
            container.removeAllViews();
        }

        Bundle bundle = getArguments();
        String url = bundle.getString("url");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_browser);

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        for (int i = 0; i <  navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }

        webView = (WebView) root.findViewById(R.id.webViewBrowser);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        return root;
    }
}