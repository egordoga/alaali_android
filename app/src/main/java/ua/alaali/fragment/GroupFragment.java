package ua.alaali.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.R;
import ua.alaali.adapter.GroupAdapter;
import ua.alaali.model.Group;
import ua.alaali.viewModel.AppViewModel;

public class GroupFragment extends Fragment {

    private GroupAdapter groupAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        AppViewModel viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        viewModel.setGroupListAll();
        RecyclerView groups = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_group);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        groups.setLayoutManager(layoutManager);
        GroupAdapter.ItemClickListener listener = (view, position) -> showSectionList(groupAdapter.getItem(position));
        groupAdapter = new GroupAdapter(getContext(), listener, viewModel);
        groups.setAdapter(groupAdapter);

    }

    private void showSectionList(Group group) {
        // ArrayList<Section> sections = new ArrayList<>(group.sections);
        Bundle bundle = new Bundle();
        bundle.putLong("gid", group.id);
        //bundle.putParcelableArrayList("sections", (ArrayList<? extends Parcelable>) sections);
        bundle.putSerializable("sections", (Serializable) group.sections);
        SectionFragment sectionFrag = new SectionFragment();
        sectionFrag.setArguments(bundle);
        FragmentManager fragManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frgmCont, sectionFrag);
        fragTransaction.addToBackStack(null);
        fragTransaction.commit();
    }
}
