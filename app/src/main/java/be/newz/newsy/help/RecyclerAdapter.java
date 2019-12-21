package be.newz.newsy.help;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.List;

import be.newz.newsy.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    private final List<Page> list = new ArrayList<>();
    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    public RecyclerAdapter() {
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.bind(list.get(position));
        TextView textViewTitle = holder.itemView.findViewById(R.id.textViewTitle);
        textViewTitle.setText(list.get(position).getTitle());

        LinearLayout linearLayoutContainer = holder.itemView.findViewById(R.id.container);
        View view = LayoutInflater.from(holder.itemView.getContext()).inflate(list.get(position).getLayoutFile(), null);
        linearLayoutContainer.addView(view);

        ImageView imageViewIcon = holder.itemView.findViewById(R.id.iconRecycler);
        imageViewIcon.setImageResource(list.get(position).getIcon());
        imageViewIcon.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), list.get(position).getColor()));

        expansionsCollection.add(holder.getExpansionLayout());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(List<Page> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
    }
}
