package com.example.techjini.recyclerbasics.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.techjini.recyclerbasics.R;

import com.example.techjini.recyclerbasics.databinding.RecyclerItemsBinding;
import com.example.techjini.recyclerbasics.helpers.DBOpenHelper;
import com.example.techjini.recyclerbasics.model.Bookmark;

import java.util.List;

/**
 * Created by techjini on 8/9/16.
 */
public class CustomAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Bookmark> bookmarkList;
    DBOpenHelper db;

    public CustomAdapter(List<Bookmark> bookmarkList, Context mContext, DBOpenHelper db) {
        this.bookmarkList = bookmarkList;
        this.mContext = mContext;
        this.db = db;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_items, parent, false);
        return new BookMarkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((BookMarkViewHolder) holder).binding.setBookmark(bookmarkList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    private class BookMarkViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        RecyclerItemsBinding binding;

        public BookMarkViewHolder(final RecyclerItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.checkbox.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.checkbox:
                    if (isChecked) {
                        Toast.makeText(mContext, "Updating " + bookmarkList.get(getLayoutPosition()).getTitle() + " checked State to 1", Toast.LENGTH_SHORT).show();
                        db.checkBookmark(bookmarkList.get(getLayoutPosition()).getTitle());
                    } else {
                        db.uncheckBookmark(bookmarkList.get(getLayoutPosition()).getTitle());
                        Toast.makeText(mContext, "Changing " + bookmarkList.get(getLayoutPosition()).getTitle() + " checked State to 0", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
