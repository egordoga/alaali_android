package ua.alaali;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.adapter.PictureAdapter;
import ua.alaali.auth.AuthVisitorDto;
import ua.alaali.model.Product;
import ua.alaali.viewModel.AppViewModel;

public class ProductFragment extends Fragment {

    private TextView tvPrice;
    private TextView tvDescription;
    private PictureAdapter adapter;
    private Button btnBuy;
    private Product product;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        tvPrice = view.findViewById(R.id.tv_price);
        tvDescription = view.findViewById(R.id.tv_description);
        btnBuy = view.findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(v -> onClickBuy());

        AppViewModel viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        Bundle bundle = this.getArguments();
        long id = bundle.getLong("pid");
        product = viewModel.getOne(id);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //AppViewModel viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        //Bundle bundle = this.getArguments();
        //long id = bundle.getLong("pid");
        //Product product = viewModel.getOne(id);

        RecyclerView pictures = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_img);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pictures.setLayoutManager(layoutManager);
        PictureAdapter.ItemClickListener listener = (view, position) -> showLargePict(adapter.getItem(position));
        adapter = new PictureAdapter(getContext(), listener, product);
        pictures.setAdapter(adapter);
        tvPrice.setText(String.valueOf(product.cost));
        tvDescription.setText(String.valueOf(product.description));
    }

    private void showLargePict(Drawable item) {
        Dialog imgDialog = new Dialog(getContext());
        imgDialog.setContentView(R.layout.picture_dialog);
        ImageView ivDialog = imgDialog.findViewById(R.id.iv_img_one);
        ivDialog.setImageDrawable(item);
        imgDialog.show();
    }

    private void onClickBuy() {
        if (AuthVisitorDto.getInstance() == null || !AuthVisitorDto.getInstance().auth) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
