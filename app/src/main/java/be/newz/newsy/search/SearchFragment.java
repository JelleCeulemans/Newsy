package be.newz.newsy.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.textfield.TextInputEditText;
import be.newz.newsy.R;
import be.newz.newsy.filtered.FilteredFragment;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String[] searchItems = { "choose", "world", "nation", "business", "technology", "entertainment", "sports", "science", "health" };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_search);

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        final TextInputEditText keywordTextInputEditText = root.findViewById(R.id.keywordTextInputEditText);
        final Button searchKeywordButton = (Button) root.findViewById(R.id.searchKeywordButton);
        final Spinner topicSpinner = root.findViewById(R.id.topicsSpinner);
        ArrayAdapter<CharSequence> topicsAdapter = ArrayAdapter.createFromResource(getContext(), R.array.topics, android.R.layout.simple_spinner_item);
        topicsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(topicsAdapter);
        topicSpinner.setOnItemSelectedListener(this);

        searchKeywordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = keywordTextInputEditText.getText().toString();
                if (!keyword.equals("")) {
                    startFilteredFragment(keyword, 0);
                } else {
                    Toast.makeText(getContext(), R.string.toast_empty, Toast.LENGTH_LONG).show();
                }

            }
        });
        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String topic = searchItems[i];
        if (!topic.equals("choose")) {
            startFilteredFragment(topic, 1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void startFilteredFragment(String keyword, int search) {
        FilteredFragment filteredFragment = new FilteredFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("search", search);
        bundle.putString("keyword", keyword);
        filteredFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, filteredFragment).commit();
    }
}