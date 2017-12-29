package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.GetAttendenceData_AllModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.AllAttendance.StudentDetailAll_Attendance;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.Attendance.StaffNewAttendenceModel;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.Attendance.StudentDetailAttedance;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admsandroid on 11/14/2017.
 */

public class All_AttendaceListAdapter extends BaseAdapter {
    private Context mContext;
    private GetAttendenceData_AllModel getAttendenceData_allModel;
    ImageLoader imageLoader;

    // Constructor
    public All_AttendaceListAdapter(Context c, GetAttendenceData_AllModel getAttendenceData_allModel) {
        mContext = c;
        this.getAttendenceData_allModel = getAttendenceData_allModel;

    }

    private class ViewHolder {
        CircleImageView profile_image;
        TextView student_name_txt;
        Spinner standard_attendace_spinner;
        EditText remark_txt;
    }

    @Override
    public int getCount() {
        return getAttendenceData_allModel.getFinalArray().get(0).getStudentDetail().size();
    }

    @Override
    public Object getItem(int position) {
        return getAttendenceData_allModel.getFinalArray().get(0).getStudentDetail().get(position);
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
            convertView = mInflater.inflate(R.layout.list_row_student_all_attendance, null);
            viewHolder.profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
            viewHolder.student_name_txt = (TextView) convertView.findViewById(R.id.student_name_txt);
            viewHolder.standard_attendace_spinner = (Spinner) convertView.findViewById(R.id.standard_attendace_spinner);
            viewHolder.remark_txt = (EditText) convertView.findViewById(R.id.remark_txt);

            imageLoader = ImageLoader.getInstance();


            final StudentDetailAll_Attendance detail = getAttendenceData_allModel.getFinalArray().get(0).getStudentDetail().get(position);
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
                imageLoader.displayImage(detail.getStudentImage(), viewHolder.profile_image);
                viewHolder.student_name_txt.setText(detail.getStudentName().trim());
                if(!detail.getComment().equalsIgnoreCase(",,,,,,,,,,,,,,,,,,,,,,,")){
                    viewHolder.remark_txt.setText(detail.getComment());
                }

                ArrayList<String> arrayfill = new ArrayList<>();
                if (detail.getAttendenceStatus().equalsIgnoreCase("1")) {
                    arrayfill.add("Present");
                    arrayfill.add("Absent");
                    arrayfill.add("Leave");
                    arrayfill.add("On Duty");
                } else if (detail.getAttendenceStatus().equalsIgnoreCase("-1")) {
                    arrayfill.add("Leave");
                    arrayfill.add("Present");
                    arrayfill.add("Absent");
                    arrayfill.add("On Duty");
                } else if (detail.getAttendenceStatus().equalsIgnoreCase("0")) {
                    arrayfill.add("Absent");
                    arrayfill.add("Present");
                    arrayfill.add("Leave");
                    arrayfill.add("On Duty");
                } else if (detail.getAttendenceStatus().equalsIgnoreCase("3")) {
                    arrayfill.add("On Duty");
                    arrayfill.add("Present");
                    arrayfill.add("Absent");
                    arrayfill.add("Leave");
                }
                ArrayAdapter<String> adapterstandard = new ArrayAdapter<String>(mContext, R.layout.spinner_layout, arrayfill);
                viewHolder.standard_attendace_spinner.setAdapter(adapterstandard);

                viewHolder.remark_txt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        detail.setComment(viewHolder.remark_txt.getText().toString());
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        detail.setComment(viewHolder.remark_txt.getText().toString());
                    }
                });

                viewHolder.standard_attendace_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String value = parent.getSelectedItem().toString();
                        Log.d("value",value);
                        if (value.equalsIgnoreCase("Present")) {
                            detail.setAttendenceStatus("1");
                            Log.d("selectedvalue", detail.getAttendenceStatus());
                        } else if (value.equalsIgnoreCase("Absent")) {
                            detail.setAttendenceStatus("0");
                            Log.d("selectedvalue", detail.getAttendenceStatus());
                        } else if (value.equalsIgnoreCase("Leave")) {
                            detail.setAttendenceStatus("-1");
                            Log.d("selectedvalue", detail.getAttendenceStatus());
                        } else if (value.equalsIgnoreCase("On Duty")) {
                            detail.setAttendenceStatus("3");
                            Log.d("selectedvalue", detail.getAttendenceStatus());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}



