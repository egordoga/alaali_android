package ua.alaali.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.R;
import ua.alaali.adapter.SectionAdapter;
import ua.alaali.model.Section;
import ua.alaali.viewModel.AppViewModel;

public class SectionFragment extends Fragment {

    private SectionAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_section, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        RecyclerView sections = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_section);
       // AppViewModel viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        sections.setLayoutManager(layoutManager);
        SectionAdapter.ItemClickListener listener = (view, position) -> showProductsBySectionId(adapter.getItem(position).id);
        Bundle bundle = this.getArguments();
//        long gid = bundle.getLong("gid");
        List<Section> sectionsList = (List<Section>) bundle.getSerializable("sections");
        adapter = new SectionAdapter(getContext(), listener, sectionsList);
        sections.setAdapter(adapter);
    }

    private void showProductsBySectionId(long id) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("sid", id);
        mainFragment.setArguments(bundle);
        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frgmCont, mainFragment);
        transaction.commit();
    }
}
