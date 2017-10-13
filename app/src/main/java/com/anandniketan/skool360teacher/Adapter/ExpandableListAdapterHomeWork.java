package com.anandniketan.skool360teacher.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.HomeworkModel;
import com.anandniketan.skool360teacher.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class ExpandableListAdapterHomeWork extends BaseExpandableListAdapter {

    private Context _context;
    boolean visible = true;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<HomeworkModel.HomeworkData>> _listDataChild;
    String FontStyle, splitFont1, splitFont2, splitFont3, splitFont4;
    TextView subject_title_txt, homwork_name_txt, chapter_name_txt, objective_txt, assessment_txt;
    ImageView imgRightSign;
    LinearLayout chapter_linear, objective_linear, que_linear;
    Typeface typeface;

    public ExpandableListAdapterHomeWork(Context context, List<String> listDataHeader,
                                         HashMap<String, ArrayList<HomeworkModel.HomeworkData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public ArrayList<HomeworkModel.HomeworkData> getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             final boolean isLastChild, View convertView, ViewGroup parent) {

        ArrayList<HomeworkModel.HomeworkData> childData = getChild(groupPosition, 0);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_home_work, null);
        }

        subject_title_txt = (TextView) convertView.findViewById(R.id.subject_title_txt);
        homwork_name_txt = (TextView) convertView.findViewById(R.id.homwork_name_txt);
        chapter_name_txt = (TextView) convertView.findViewById(R.id.chapter_name_txt);
        objective_txt = (TextView) convertView.findViewById(R.id.objective_txt);
        assessment_txt = (TextView) convertView.findViewById(R.id.assessment_txt);


        subject_title_txt.setText(Html.fromHtml(childData.get(childPosition).getSubject()));

        FontStyle = "";
        splitFont1 = "";
        splitFont2 = "";
        splitFont3 = "";
//        FontStyle = childData.get(childPosition).getFont();

//        if (!FontStyle.equalsIgnoreCase("-|-|-|-")) {
//            String[] splitFontStyle = FontStyle.split("\\|");
//            Log.d("SplitFOnt", splitFontStyle[0]);
//            splitFont1 = splitFontStyle[0].toString();
//            splitFont2 = splitFontStyle[1].toString();
//            splitFont3 = splitFontStyle[2].toString();
//            splitFont4 = splitFontStyle[3].toString();
//
//            SetLanguageHomework(splitFont1);
//            SetLanguageChapterName(splitFont2);
//            SetLanguageObjective(splitFont3);
//            SetLanguageAssessmentQue(splitFont4);

        homwork_name_txt.setText(Html.fromHtml(childData.get(childPosition).getHomeWork()));
        chapter_name_txt.setText(Html.fromHtml(childData.get(childPosition).getChapterName()));
        objective_txt.setText(Html.fromHtml(childData.get(childPosition).getObjective()));
        assessment_txt.setText(Html.fromHtml(childData.get(childPosition).getAssessmentQue()));

//        } else {
//            typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/arial.ttf");
//            homework_title_txt.setTypeface(typeface);
//            lblchaptername.setTypeface(typeface);
//            lblobjective.setTypeface(typeface);
//            lblque.setTypeface(typeface);
//            homework_title_txt.setText(Html.fromHtml(childData.get(childPosition).getHomework()));
//            lblchaptername.setText(Html.fromHtml(childData.get(childPosition).getChapterName()));
//            lblobjective.setText(Html.fromHtml(childData.get(childPosition).getObjective()));
//            lblque.setText(Html.fromHtml(childData.get(childPosition).getAssessmentQue()));
//        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String[] headerTitle = getGroup(groupPosition).toString().split("\\|");

        String headerTitle1 = headerTitle[0];
        String headerTitle2 = headerTitle[1];
        String headerTitle3 = headerTitle[2];
        String headerTitle4 = headerTitle[3];

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_homework, null);
        }
        TextView Date_txt, Standard_txt, Class_txt, Subject_txt;
        ImageView arrow_txt;

        Date_txt = (TextView) convertView.findViewById(R.id.Date_txt);
        Standard_txt = (TextView) convertView.findViewById(R.id.Standard_txt);
        Class_txt = (TextView) convertView.findViewById(R.id.Class_txt);
        Subject_txt = (TextView) convertView.findViewById(R.id.Subject_txt);
        arrow_txt = (ImageView) convertView.findViewById(R.id.arrow_txt);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = sdf.parse(headerTitle1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = output.format(d);

        Date_txt.setText(formattedTime);
        Standard_txt.setText(headerTitle2);
        Class_txt.setText(headerTitle3);
        Subject_txt.setText(headerTitle4);

        if (isExpanded) {
            arrow_txt.setBackgroundResource(R.drawable.arrow_1_42_down);
        } else {
            arrow_txt.setBackgroundResource(R.drawable.arrow_1_42);
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

//    public void SetLanguageHomework(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
//
//    public void SetLanguageChapterName(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
//
//    public void SetLanguageObjective(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
//
//    public void SetLanguageAssessmentQue(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
}


