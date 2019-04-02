package ua.alaali.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.R;
import ua.alaali.model.Product;
import ua.alaali.viewModel.AppViewModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public ProductAdapter(Context context, ItemClickListener mListener, AppViewModel viewModel) {
        inflater = LayoutInflater.from(context);
        listener = mListener;
        productList = viewModel.productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ByteArrayInputStream is = new ByteArrayInputStream(productList.get(position).pictureFirst);
        Drawable img = Drawable.createFromStream(is, "first_pict");
        holder.ivPict.setImageDrawable(img);
        holder.tvPrice.setText(String.valueOf(productList.get(position).cost));
        holder.tvName.setText(productList.get(position).name);
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public Product getItem(int position) {
        return productList.get(position);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivPict;
        private TextView tvName;
        private TextView tvPrice;
        private ItemClickListener listener;

        ProductViewHolder(@NonNull View itemView, ItemClickListener mListener) {
            super(itemView);

            ivPict = itemView.findViewById(R.id.iv_prodCard);
            tvName = itemView.findViewById(R.id.tv_prodName);
            tvPrice = itemView.findViewById(R.id.tv_prodPrice);
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
