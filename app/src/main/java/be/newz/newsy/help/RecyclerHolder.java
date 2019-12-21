package be.newz.newsy.help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.github.florent37.expansionpanel.ExpansionLayout;
import be.newz.newsy.R;

public final class RecyclerHolder extends RecyclerView.ViewHolder {

    private static final int LAYOUT = R.layout.expendable_recycleview_page;

    ExpansionLayout expansionLayout;

    public static RecyclerHolder buildFor(ViewGroup viewGroup) {
        return new RecyclerHolder(LayoutInflater
                .from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
    }

    public RecyclerHolder(View itemView) {
        super(itemView);
        expansionLayout = itemView.findViewById(R.id.expansionLayout);

    }

    public void bind(Object object) {
        expansionLayout.collapse(false);
    }

    public ExpansionLayout getExpansionLayout() {
        return expansionLayout;
    }
}