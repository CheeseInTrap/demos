package me.campochu.sectionapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.view.LayoutInflater;
import me.campochu.sectionapp.R;
import me.campochu.sectionapp.Section;
import me.campochu.sectionapp.SectionAdapter;
import me.campochu.sectionapp.SectionItemView;
import me.campochu.sectionapp.SectionLayoutManager;
import me.campochu.sectionapp.SectionListView;
import me.campochu.sectionapp.SectionViewFactroy;
import me.campochu.sectionapp.model.Item1;
import me.campochu.sectionapp.view.Item1SectionItemView;

public class MainActivity extends AppCompatActivity {

    private SectionListView mDemoList;

    private GridLayoutManager mLayoutManager;
    private SectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDemoList = (SectionListView)findViewById(R.id.demo_list);
        mAdapter = new SectionAdapter(new SectionViewFactroy(MainActivity.this) {
            @Override
            protected SectionItemView createImpl(LayoutInflater inflater, int section) {
                SectionItemView sectionItemView = null;

                switch (section) {
                    case ITEM_1:
                        sectionItemView = Item1SectionItemView.CREATOR.create(inflater, mDemoList);
                        break;
                    default:
                        break;
                }

                return sectionItemView;
            }

            @Override
            protected int getSpan(int section) {
                return section & SPAN_MASK;
            }
        });
        mLayoutManager = new SectionLayoutManager(MainActivity.this);
        mLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getSpan(position);
            }
        });
        mDemoList.setAdapter(mAdapter);
        mDemoList.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(new Item1("哈哈"));
        sectionList.add(new Item1("嘿嘿"));
        sectionList.add(new Item1("吼吼"));
        sectionList.add(new Item1("哦哦"));
        sectionList.add(new Item1("哇哇"));
        sectionList.add(new Item1("嘎嘎"));
        mAdapter.setItems(sectionList);
    }
}
