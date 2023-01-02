package com.example.bonplan;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

        private Context context;
        private List<Product> productList;

        public ProductAdapter(Context context , List<Product> products){
            this.context = context;
            productList = products;
        }
        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
            return new ProductHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

            Product product = productList.get(position);
            holder.rating.setText(product.getPrice().toString());
            holder.title.setText(product.getName());
            holder.overview.setText(product.getDate());
            Glide.with(context).load(product.getImage()).into(holder.imageView);

            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , DetailActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("title" , product.getName());
                    bundle.putString("overview" , product.getDate());
                    bundle.putString("poster" , product.getImage());
                    bundle.putString("rating" , product.getPrice());
                    bundle.putString("description" , product.getDescription());
                    bundle.putString("tel" , product.getTel());

                    intent.putExtras(bundle);

                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class ProductHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            TextView title , overview , rating;
            ConstraintLayout constraintLayout;

            public ProductHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageview);
                title = itemView.findViewById(R.id.title_tv);
                overview = itemView.findViewById(R.id.overview_tv);
                rating = itemView.findViewById(R.id.rating);
                constraintLayout = itemView.findViewById(R.id.main_layout);
            }
        }
    }


