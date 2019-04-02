package ua.alaali.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.R;
import ua.alaali.adapter.ProductAdapter;
import ua.alaali.viewModel.AppViewModel;

public class MainFragment extends Fragment {

    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        long sid;
        AppViewModel viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            sid = bundle.getLong("sid");
            viewModel.setProductListBySectionId(sid);
        } else viewModel.setProductListAll();
        RecyclerView products = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_prod);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        products.setLayoutManager(layoutManager);
        ProductAdapter.ItemClickListener listener = (view, position) -> showProduct(adapter.getItem(position).id);
        adapter = new ProductAdapter(getContext(), listener, viewModel);
        products.setAdapter(adapter);
    }

    private void showProduct(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("pid", id);
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frgmCont, productFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
