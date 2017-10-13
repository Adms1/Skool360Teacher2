package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.TeacherLessonPlanModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class TeacherLessonPlanAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TeacherLessonPlanModel.LessonPlan.LessonPlanData> teacherLessonPlanModels = new ArrayList<>();

    // Constructor
    public TeacherLessonPlanAdapter(Context c, ArrayList<TeacherLessonPlanModel.LessonPlan.LessonPlanData> arrayList) {
        mContext = c;
        this.teacherLessonPlanModels = arrayList;

    }

    private class ViewHolder {
        TextView chapter_no_txt, chapter_name_txt;
        ImageView pdf_img, word_img;
    }

    @Override
    public int getCount() {
        return teacherLessonPlanModels.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherLessonPlanModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View view = null;
        convertView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_lessonplan, null);

            viewHolder.chapter_no_txt = (TextView) convertView.findViewById(R.id.chapter_no_txt);
            viewHolder.chapter_name_txt = (TextView) convertView.findViewById(R.id.chapter_name_txt);
            viewHolder.pdf_img = (ImageView) convertView.findViewById(R.id.pdf_img);
            viewHolder.word_img = (ImageView) convertView.findViewById(R.id.word_img);


            try {

                viewHolder.chapter_no_txt.setText(teacherLessonPlanModels.get(position).getChapterNo());
                viewHolder.chapter_name_txt.setText(Html.fromHtml(teacherLessonPlanModels.get(position).getChapterName()));
                viewHolder.pdf_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                viewHolder.word_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

}


