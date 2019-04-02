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
import ua.alaali.model.Group;
import ua.alaali.viewModel.AppViewModel;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<Group> groupList;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public GroupAdapter(Context context, ItemClickListener mListener, AppViewModel mViewModel) {
        inflater = LayoutInflater.from(context);
        listener = mListener;
        groupList = mViewModel.groupList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.group_item, parent, false);
        return new GroupViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.tvGroupName.setText(groupList.get(position).name);
        holder.tvArrow.setText(">>");
    }

    @Override
    public int getItemCount() {
        if (groupList == null) {
            return 0;
        } else {
            return groupList.size();
        }
    }

    public Group getItem(int position) {
        return groupList.get(position);
    }


    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvGroupName;
        private TextView tvArrow;
        private ItemClickListener listener;

        GroupViewHolder(@NonNull View itemView, ItemClickListener mListener) {
            super(itemView);

            tvGroupName = itemView.findViewById(R.id.tv_groupName);
            tvArrow = itemView.findViewById(R.id.tv_arrow);
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
