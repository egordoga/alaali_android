package ua.alaali.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.R;
import ua.alaali.model.Section;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {
    private List<Section> sectionList;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public SectionAdapter(Context context, ItemClickListener mListener, List<Section> list) {
        inflater = LayoutInflater.from(context);
        listener = mListener;
        sectionList = list;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.section_item, parent, false);
        return new SectionViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        holder.tvSectionName.setText(sectionList.get(position).name);
    }

    @Override
    public int getItemCount() {
        if (sectionList != null) {
            return sectionList.size();
        }
        return 0;
    }

    public Section getItem(int position) {
        return sectionList.get(position);
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvSectionName;
        private ItemClickListener listener;

        SectionViewHolder(@NonNull View itemView, ItemClickListener mListener) {
            super(itemView);

            tvSectionName = itemView.findViewById(R.id.tv_sectionName);
            listener = mListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClickItem(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onClickItem(View view, int position);
    }
}
