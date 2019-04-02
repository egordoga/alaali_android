package ua.alaali.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ua.alaali.R;
import ua.alaali.model.Product;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private List<Drawable> imgList;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public PictureAdapter(Context context, ItemClickListener mListener, Product product) {
        inflater = LayoutInflater.from(context);
        listener = mListener;
        imgList = makeImgList(product);
    }

    private List<Drawable> makeImgList(Product product) {
        List<Drawable> list = new ArrayList<>();
        list.add(makeImg(product.pictureFirst, "pict1"));
        list.add(makeImg(product.picture2, "pict2"));
        list.add(makeImg(product.picture3, "pict3"));
        list.add(makeImg(product.picture4, "pict4"));
        list.add(makeImg(product.picture5, "pict5"));
        return list;
    }

    private Drawable makeImg(byte[] img, String name) {
        ByteArrayInputStream is = new ByteArrayInputStream(img);
        return Drawable.createFromStream(is, name);
    }


    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.img_item, parent, false);
        return new PictureViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        holder.ivPicture.setImageDrawable(imgList.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public Drawable getItem(int position) {
        return imgList.get(position);
    }


    public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivPicture;
        private ItemClickListener listener;

        PictureViewHolder(@NonNull View itemView, ItemClickListener listener) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_img);
            this.listener = listener;
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
