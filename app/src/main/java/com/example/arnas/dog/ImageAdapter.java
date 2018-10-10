package com.example.arnas.dog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

// class to connect image data and UI components
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<Thumbnail> galleryList;

    public ImageAdapter(ArrayList<Thumbnail> galleryList) {
        this.galleryList = galleryList;
    }

    // initializing viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thumbnail, viewGroup, false);
        return new ViewHolder(view);
    }

    // passing data to viewHolder
    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        RequestOptions options = new RequestOptions()
                .error(R.drawable.error);

        Glide.with(viewHolder.img.getContext())
                .load(galleryList.get(position).getImgURL())
                .apply(options)
                .into(viewHolder.img);

        // when thumbnail is clicked full image is displayed in new activity
        viewHolder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), FullImageActivity.class);
                intent.putExtra("imgURL", galleryList.get(position).getImgURL());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }
}
