package com.example.recyclerviewexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private  final List<CardItem> mDataList;

    //클릭이벤트 리스너 생성은 인터페이스로.
    public interface MyRecyclerVIewClickListener {
        //아이템 전체 부분의 클릭
        void onItemClicked(int position);
        //쉐어 버튼 클릭
        void onShareButtonClicked(int position);
        //런모어 버튼 클릭
        void onLearnMoreButtonClicked(int position);
    }
    //삭제
    public  void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDataList.size());
    }

    public void addItem(int position, CardItem item) {
        mDataList.add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mDataList.size());
    }

    private MyRecyclerVIewClickListener mListner;

    public void setOnClickListner(MyRecyclerVIewClickListener listener) {
        mListner = listener;
    }

    public MyRecyclerAdapter(List<CardItem> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override //뷰홀더 생성 부분 레이아웃을 만드는 부분
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override //뷰홀더에 데이터를 설정하는 부분
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());

        //클릭 이벤트
        if (mListner != null) {
            //현재 위치
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListner.onItemClicked(holder.getAdapterPosition());
                }
            });

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListner.onShareButtonClicked(pos);
                }
            });

            holder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListner.onLearnMoreButtonClicked(pos);
                }
            });
        }
    }

    @Override //아이템의 수
    public int getItemCount() {
        return mDataList.size(); //사이즈만큼
    }

    //각각의 아이템의 레퍼런스를 저장할 뷰 홀더 클래스
    //반드시 리사이클러뷰.뷰홀더를 상속해야함.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        Button share;
        Button more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            contents = (TextView) itemView.findViewById(R.id.contents_text);
            share = (Button) itemView.findViewById(R.id.share_button);
            more = (Button) itemView.findViewById(R.id.learn_button2);

        }
    }
}
