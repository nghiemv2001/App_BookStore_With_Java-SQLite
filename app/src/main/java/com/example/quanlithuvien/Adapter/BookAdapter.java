package com.example.quanlithuvien.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlithuvien.DTO.Book;
import com.example.quanlithuvien.R;
import com.example.quanlithuvien.giaodien.Sach.CHINHSUA_XOA_SACH;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {

    private List<Book> listBook, listBookOld;
    Context context;

    public BookAdapter(Context context, List<Book> listBook) {
        this.context = context;
        this.listBook = listBook;
        this.listBookOld = listBook;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = listBook.get(position);
        if (book == null) {
            return;
        }
        holder.id_txt.setText(book.getId());
        holder.theloai_txt.setText(book.getTheloai());
        holder.ten_txt.setText(book.getTensach());
        holder.tacgia_txt.setText(book.getTacgia());
        holder.gia_txt.setText(book.getGia());
        holder.sotrang_txt.setText(book.getSotrang());
        holder.soluongton_txt.setText(book.getSoluongton());
        holder.mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CHINHSUA_XOA_SACH.class);
                intent.putExtra("id", book.getId());
                intent.putExtra("loaisach", book.getTheloai());
                intent.putExtra("tensach", book.getTensach());
                intent.putExtra("tacgia", book.getTacgia());
                intent.putExtra("giasach", book.getGia());
                intent.putExtra("sotrang", book.getSotrang());
                intent.putExtra("soluongton", book.getSoluongton());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listBook != null) {
            return listBook.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strShare = charSequence.toString();
                if (strShare.isEmpty()) {
                    listBook = listBookOld;
                } else {
                    List<Book> list = new ArrayList<>();
                    for (Book book : listBookOld) {
                        if (book.getTensach().toLowerCase().contains(strShare.toLowerCase())) {
                            list.add(book);
                        }
                    }
                    listBook = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listBook;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listBook = (List<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView id_txt, ten_txt, theloai_txt, tacgia_txt, gia_txt, sotrang_txt, soluongton_txt;
        LinearLayout mainLinearLayout;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.book_id_txt);
            ten_txt = itemView.findViewById(R.id.book_tensach_txt);
            theloai_txt = itemView.findViewById(R.id.book_theloai_txt);
            tacgia_txt = itemView.findViewById(R.id.book_tacgia_txt);
            gia_txt = itemView.findViewById(R.id.book_giasach_txt);
            sotrang_txt = itemView.findViewById(R.id.book_sotrang_txt);
            soluongton_txt = itemView.findViewById(R.id.book_soluongton_txt);
            mainLinearLayout = itemView.findViewById(R.id.mainLinearlayout);

        }
    }

}
