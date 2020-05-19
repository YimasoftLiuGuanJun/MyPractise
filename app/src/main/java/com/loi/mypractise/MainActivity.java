package com.loi.mypractise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout;
import com.qmuiteam.qmui.widget.tab.QMUITab;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pager)
    QMUIViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private List<String> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        ButterKnife.bind(this);
        for (int i = 0; i < 4; i++) {
            mItems.add(String.valueOf(i));
        }
        initTopBar();
        initTabs();
        initPages();
    }

    private void initTopBar() {
//        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        mTopBar.setTitle("hello");
//        mTopBar.setSubTitle("world");
    }

    private void initPages(){
        QMUIPagerAdapter qmuiPagerAdapter = new QMUIPagerAdapter() {

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mItems.get(position);
            }

            @Override
            @NonNull
            protected Object hydrate(@NonNull ViewGroup container, int position) {
                View view = getLayoutInflater().inflate(R.layout.ac_test,null);
                return view;
//                return new ItemView(getContext());
            }

            @Override
            protected void populate(@NonNull ViewGroup container, @NonNull Object item, int position) {
//                ItemView itemView = (ItemView) item;
//                itemView.setText(mItems.get(position));
                QMUIWindowInsetLayout view = (QMUIWindowInsetLayout)item;
                container.addView(view);
            }

            @Override
            protected void destroy(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        };

        mViewPager.setAdapter(qmuiPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
    }

    private Context getContext(){
        return getApplicationContext();
    }

    private void initTabs() {
        QMUITabBuilder builder = mTabSegment.tabBuilder();
        builder.setSelectedIconScale(1.2f)
                .setTextSize(QMUIDisplayHelper.sp2px(getContext(), 13), QMUIDisplayHelper.sp2px(getContext(), 15))
                .setDynamicChangeIconColor(false);
        QMUITab component = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setText("News")
                .build(getContext());
        QMUITab util = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setText("Picture")
                .build(getContext());
        QMUITab lab = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setText("Music")
                .build(getContext());

        QMUITab labAbout = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.note_flat))
                .setText("Weather")
                .build(getContext());

        mTabSegment.addTab(component)
                .addTab(util)
                .addTab(lab)
                .addTab(labAbout);
    }

    static class ItemView extends FrameLayout {
        private TextView mTextView;

        public ItemView(Context context) {
            super(context);
            mTextView = new TextView(context);
            mTextView.setTextSize(20);
            mTextView.setTextColor(ContextCompat.getColor(context, R.color.app_color_theme_5));
            mTextView.setGravity(Gravity.CENTER);
            mTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.qmui_config_color_red));
//            int size = QMUIDisplayHelper.dp2px(context, 300);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.gravity = Gravity.CENTER;
            addView(mTextView, lp);
        }

        public void setText(CharSequence text) {
            mTextView.setText(text);
        }
    }
}
