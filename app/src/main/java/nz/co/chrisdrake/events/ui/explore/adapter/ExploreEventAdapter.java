package nz.co.chrisdrake.events.ui.explore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;
import nz.co.chrisdrake.events.R;
import nz.co.chrisdrake.events.domain.model.Event;
import nz.co.chrisdrake.events.ui.widget.FooterAdapter;
import nz.co.chrisdrake.events.ui.widget.ItemClickListener;

public class ExploreEventAdapter extends FooterAdapter<ExploreEventAdapter.EventViewHolder>
    implements ItemClickListener {

    public interface Callbacks {
        void onEventClick(Event event);
    }

    private final ArrayList<Event> events = new ArrayList<>();
    private final Picasso picasso;
    private final Callbacks callbacks;

    public ExploreEventAdapter(Context context, Picasso picasso, Callbacks callbacks) {
        super(context, R.layout.list_item_load_more, false);
        this.picasso = picasso == null ? Picasso.with(context) : picasso;
        this.callbacks = callbacks;

        setHasStableIds(true);
    }

    @Override public EventViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View v = getInflater().inflate(R.layout.list_item_explore_event, parent, false);
        return new EventViewHolder(v, this);
    }

    @Override public void onBindItemViewHolder(EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.title.setText(event.name);
        holder.location.setText(event.locationSummary);
        holder.date.setText(event.dateTimeSummary);
        holder.moreSessions.setVisibility(event.hasMultipleSessions ? View.VISIBLE : View.GONE);

        // Remove line-breaks.
        String description = event.description.replaceAll("(\\r|\\n)", " ");
        holder.description.setText(description);

        picasso.load(event.thumbnailUrl).placeholder(R.drawable.ic_placeholder).into(holder.image);
    }

    @Override public long getItemId(int position) {
        return getItemViewType(position) == VIEW_TYPE_FOOTER ? super.getItemId(position)
            : events.get(position).id;
    }

    @Override public int getItemCount() {
        return super.getItemCount() + events.size();
    }

    @Override public void onItemClick(View v, int position) {
        if (callbacks != null) {
            Event event = events.get(position);
            callbacks.onEventClick(event);
        }
    }

    @NonNull public ArrayList<Event> getAll() {
        return events;
    }

    public void addAll(Collection<? extends Event> events) {
        int insertPosition = getItemCount();
        this.events.addAll(events);
        notifyItemRangeInserted(insertPosition, events.size());
    }

    public void clear() {
        events.clear();
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.event_title) TextView title;
        @BindView(R.id.event_description) TextView description;
        @BindView(R.id.event_location) TextView location;
        @BindView(R.id.event_date) TextView date;
        @BindView(R.id.event_more_sessions) TextView moreSessions;
        @BindView(R.id.event_image) ImageView image;

        public EventViewHolder(View itemView, final ItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    itemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
