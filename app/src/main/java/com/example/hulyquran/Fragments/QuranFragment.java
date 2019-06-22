package com.example.hulyquran.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hulyquran.Adapters.SurahRecyclerAdapter;
import com.example.hulyquran.Models.Surah;
import com.example.hulyquran.R;
import com.example.hulyquran.SurahLines;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuranFragment extends Fragment {


    public QuranFragment() {
        // Required empty public constructor
    }

    private View view;


    private SurahRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<Surah> list;
    private static String fileName;
    private static String surahName;
    private static Typeface secondfont;
    private static int surahIndex;

    public static int getSurahIndex() {
        return surahIndex;
    }

    public static void setSurahIndex(int surahIndex) {
        QuranFragment.surahIndex = surahIndex;
    }

    public static Typeface getSecondfont() {
        return secondfont;
    }

    public static String getSurahName() {
        return surahName;
    }

    public static String getFileName() {
        return fileName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quran, container, false);


        secondfont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/khara.otf");


        fillList();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new SurahRecyclerAdapter(list, secondfont);
        adapter.setOnItemViewClickListener(new SurahRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Surah surah, int position) {

                startActivity(new Intent(getActivity(), SurahLines.class));
                fileName = position + 1 + ".txt";
                surahName = surah.getSurahName();
                surahIndex = position + 1;

            }
        });
        layoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);


        return view;
    }

    private void fillList() {

        list = new ArrayList<>();

        String[] ArSuras = {"الفاتحه", "البقرة", "آل عمران", "النساء", "المائدة", "الأنعام", "الأعراف", "الأنفال", "التوبة", "يونس", "هود"
                , "يوسف", "الرعد", "إبراهيم", "الحجر", "النحل", "الإسراء", "الكهف", "مريم", "طه", "الأنبياء", "الحج", "المؤمنون"
                , "النّور", "الفرقان", "الشعراء", "النّمل", "القصص", "العنكبوت", "الرّوم", "لقمان", "السجدة", "الأحزاب", "سبأ"
                , "فاطر", "يس", "الصافات", "ص", "الزمر", "غافر", "فصّلت", "الشورى", "الزخرف", "الدّخان", "الجاثية", "الأحقاف"
                , "محمد", "الفتح", "الحجرات", "ق", "الذاريات", "الطور", "النجم", "القمر", "الرحمن", "الواقعة", "الحديد", "المجادلة"
                , "الحشر", "الممتحنة", "الصف", "الجمعة", "المنافقون", "التغابن", "الطلاق", "التحريم", "الملك", "القلم", "الحاقة", "المعارج"
                , "نوح", "الجن", "المزّمّل", "المدّثر", "القيامة", "الإنسان", "المرسلات", "النبأ", "النازعات", "عبس", "التكوير", "الإنفطار"
                , "المطفّفين", "الإنشقاق", "البروج", "الطارق", "الأعلى", "الغاشية", "الفجر", "البلد", "الشمس", "الليل", "الضحى", "الشرح"
                , "التين", "العلق", "القدر", "البينة", "الزلزلة", "العاديات", "القارعة", "التكاثر", "العصر",
                "الهمزة", "الفيل", "قريش", "الماعون", "الكوثر", "الكافرون", "النصر", "المسد", "الإخلاص", "الفلق", "الناس"};
        for (int i = 0; i < ArSuras.length; i++) {
            String ArSura = ArSuras[i];
            list.add(new Surah(ArSura));
        }

    }

}
