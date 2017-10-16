package com.anandniketan.skool360teacher.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anandniketan.skool360teacher.Models.LessonPlanResponse.LessonDatum;
import com.anandniketan.skool360teacher.Models.NewResponse.SubjectMark;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class TeacherLessonPlanAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<LessonDatum> arrayList = new ArrayList<>();

    // Constructor
    public TeacherLessonPlanAdapter(Context c, ArrayList<LessonDatum> arrayList) {
        mContext = c;
        this.arrayList = arrayList;

    }

    private class ViewHolder {
        TextView chapter_no_txt, chapter_name_txt;
        ImageView pdf_img, word_img;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_lessonplan, null);

            viewHolder.chapter_no_txt = (TextView) convertView.findViewById(R.id.chapter_no_txt);
            viewHolder.chapter_name_txt = (TextView) convertView.findViewById(R.id.chapter_name_txt);
            viewHolder.pdf_img = (ImageView) convertView.findViewById(R.id.pdf_img);
            viewHolder.word_img = (ImageView) convertView.findViewById(R.id.word_img);


            try {
                viewHolder.chapter_no_txt.setText(Integer.toString(arrayList.get(position).getChapterNo()));
                viewHolder.chapter_name_txt.setText(Html.fromHtml(arrayList.get(position).getChapterName()));
                viewHolder.pdf_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String extStorageDirectory = "";
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        Log.d("date", "" + currentTime);
                        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

                        if (isSDSupportedDevice && isSDPresent) {
                            // yes SD-card is present
                            Utility.ping(mContext, "present");
                            extStorageDirectory = Environment.getExternalStorageDirectory().toString() + "/" + "Skool 360 Teacher" + "/" + "pdf" +
                                    "/" + currentTime + ".pdf";

                        } else {
                            // Sorry
                            Utility.ping(mContext, "notpresent");
                            extStorageDirectory = Environment.getDownloadCacheDirectory() + "/" + "Skool 360 Teacher" + "/" + "pdf" +
                                    "/" + currentTime + ".pdf";
                        }

                        Log.d("path", extStorageDirectory);

                        String fileURL = "http://192.168.1.9:8085/Backend/lessonplanpdf.aspx?ID=" + arrayList.get(position).getID();
                        Log.d("URL", fileURL);
                        Ion.with(mContext)
                                .load(fileURL)  // download url
                                .write(new File(extStorageDirectory))  // File no path
                                .setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File file) {
                                        Utility.ping(mContext, e.getMessage());
                                        System.out.print(e.getMessage());
//                                        Intent in = new Intent(this,webviewActivity.class);
//                                        in.putExtra(pathpasskravno) // ahiya tari file no path    ??aa file ne mare webview ma load kravi hoy to?
//                                        startActivity(in);
                                    }
                                });

                    }


                });

                viewHolder.word_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String wordfile = "http://192.168.1.9:8085/Backend/LessonPlanWord.aspx?ID=" + arrayList.get(position).getID();
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        Log.d("date", "" + currentTime);
                        String extStorageDirectory = Environment.getExternalStorageDirectory().toString() + "Skool 360 Teacher" + "/" + "word" +
                                "/" + currentTime + ".word";
                        Log.d("path", extStorageDirectory);
                        Log.d("URL", wordfile);
                        Ion.with(mContext)
                                .load(wordfile)  // download url
                                .write(new File(extStorageDirectory))  // File no path
                                .setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File file) {
                                        // download done...
                                        // do stuff with the File or error
                                        // Popup batadvanu k done thai gayu download
//                                        if (!file.getP) {
//                                            Utility.ping(mContext, file.getPath());
//                                        } else {
                                        Utility.ping(mContext, e.getMessage());
//                                        }
//                                        Intent in = new Intent(this,webviewActivity.class);
//                                        in.putExtra(pathpasskravno) // ahiya tari file no path    ??aa file ne mare webview ma load kravi hoy to?
//                                        startActivity(in);
                                    }
                                });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }


}


