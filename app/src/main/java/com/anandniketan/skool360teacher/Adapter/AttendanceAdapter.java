package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.StaffAttendanceModel;
import com.anandniketan.skool360teacher.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admsandroid on 9/19/2017.
 */

public class AttendanceAdapter extends BaseAdapter {
    private Context mContext;
    //    private HashMap<String, ArrayList<StaffAttendanceModel.AttendanceDetails.StudentDetails>> _listDataChild;

    private List<StaffAttendanceModel.AttendanceDetails.StudentDetails> _rowchild;
    private ArrayList<StaffAttendanceModel> staffattendaceModel = new ArrayList<>();
    ImageLoader imageLoader;
    boolean[] itemChecked;
    boolean chkvalue = false;

    // Constructor
    public AttendanceAdapter(Context c, ArrayList<StaffAttendanceModel> staffattendaceModel) {
        mContext = c;
        this.staffattendaceModel = staffattendaceModel;

    }

    private class ViewHolder {
        CircleImageView profile_image;
        TextView student_name_txt;
        //        RadioButton present_chk, absent_chk, leave_chk;
        RadioGroup attendancechk;
        CheckBox present_chk, absent_chk, leave_chk;

    }

    @Override
    public int getCount() {
        return staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().size();
    }

    @Override
    public Object getItem(int position) {
        return staffattendaceModel.get(position);
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
            convertView = mInflater.inflate(R.layout.list_row_student_attendance, null);
            viewHolder.profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
            viewHolder.student_name_txt = (TextView) convertView.findViewById(R.id.student_name_txt);
            viewHolder.present_chk = (CheckBox) convertView.findViewById(R.id.present_chk);
            viewHolder.absent_chk = (CheckBox) convertView.findViewById(R.id.absent_chk);
            viewHolder.leave_chk = (CheckBox) convertView.findViewById(R.id.leave_chk);
//            viewHolder.attendancechk = (RadioGroup) convertView.findViewById(R.id.attendancechk);
            imageLoader = ImageLoader.getInstance();
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
            try {

                DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .imageScaleType(ImageScaleType.EXACTLY)
                        .displayer(new FadeInBitmapDisplayer(300))
                        .build();
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                        mContext)
                        .threadPriority(Thread.MAX_PRIORITY)
                        .defaultDisplayImageOptions(defaultOptions)
                        .memoryCache(new WeakMemoryCache())
                        .denyCacheImageMultipleSizesInMemory()
                        .tasksProcessingOrder(QueueProcessingType.LIFO)// .enableLogging()
                        .build();
                imageLoader.init(config.createDefault(mContext));
                imageLoader.displayImage(staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getStudentImage(), viewHolder.profile_image);
                viewHolder.student_name_txt.setText(staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getStudentName().trim());

//                viewHolder.attendancechk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                        if (radioGroup.getCheckedRadioButtonId() == R.id.present_chk) {
//                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("1");
//                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.absent_chk) {
//                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("0");
//                        } else if (radioGroup.getId() == R.id.leave_chk) {
//                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("-1");
//                        } else {
//                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("-2");
//                        }
//                    }
//
//                if (staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getAttendenceStatus().equalsIgnoreCase("-2")) {
//                    viewHolder.present_chk.setChecked(true);
//                    Log.d("if", "Megha");
//                } else {
//                    viewHolder.present_chk.setChecked(false);
//                }
//                if (staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getAttendenceStatus().equalsIgnoreCase("-1")) {
//                    viewHolder.leave_chk.setChecked(true);
//                } else {
//                    viewHolder.leave_chk.setChecked(false);
//                }
//                if (staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getAttendenceStatus().equalsIgnoreCase("1")) {
//                    viewHolder.present_chk.setChecked(true);
//                } else {
//                    viewHolder.present_chk.setChecked(false);
//                }
//                if (staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getAttendenceStatus().equalsIgnoreCase("0")) {
//                    viewHolder.absent_chk.setChecked(true);
//                } else {
//                    viewHolder.absent_chk.setChecked(false);
//                }


                viewHolder.present_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        int getPosition = (int) compoundButton.getTag();
//                        staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(getPosition).setSelected(compoundButton.isChecked());
                        if (b == true) {
                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("1");
                            viewHolder.absent_chk.setChecked(false);
                            viewHolder.leave_chk.setChecked(false);

                        }
                    }
                });
                viewHolder.absent_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        int getPosition = (int) compoundButton.getTag();
//                        staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(getPosition).setAbsent_selected(compoundButton.isChecked());
                        if (b == true) {
                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("0");
                            viewHolder.present_chk.setChecked(false);
                            viewHolder.leave_chk.setChecked(false);
                        }
                    }
                });
                viewHolder.leave_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        int getPosition = (int) compoundButton.getTag();
//                        staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(getPosition).setLeave_Selected(compoundButton.isChecked());
                        if (b == true) {
                            staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).setAttendenceStatus("-1");
                            viewHolder.absent_chk.setChecked(false);
                            viewHolder.present_chk.setChecked(false);
                        }
                    }
                });
//                convertView.setTag(R.id.present_chk, viewHolder.present_chk);
//                convertView.setTag(R.id.absent_chk, viewHolder.absent_chk);
//                convertView.setTag(R.id.leave_chk, viewHolder.leave_chk);

//                viewHolder.present_chk.setTag(position);
//                viewHolder.present_chk.setChecked(true);
//                viewHolder.absent_chk.setTag(position);
//                viewHolder.absent_chk.setChecked(staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).isAbsent_selected());
//                viewHolder.leave_chk.setTag(position);
//                viewHolder.leave_chk.setChecked(staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).isLeave_Selected());


                viewHolder.absent_chk.setChecked(false);
                viewHolder.present_chk.setChecked(false);
                viewHolder.leave_chk.setChecked(false);

                switch (Integer.parseInt(staffattendaceModel.get(0).getAttendanceList().get(0).getStudentList().get(position).getAttendenceStatus())) {
                    case 0:
                        viewHolder.absent_chk.setChecked(true);
                        break;
                    case 1:
                        viewHolder.present_chk.setChecked(true);
                        break;
                    case -1:
                        viewHolder.leave_chk.setChecked(true);
                        break;
                    default:
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return convertView;
    }

    public ArrayList<StaffAttendanceModel> getData() {
        return staffattendaceModel;
    }


}

