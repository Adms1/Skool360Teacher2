package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Activities.DashBoardActivity;
import com.anandniketan.skool360teacher.Models.LessonPlanResponse.LessonDatum;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class TeacherLessonPlanAdapter extends BaseAdapter implements OnPageChangeListener, OnLoadCompleteListener {
    private Context mContext;
    private ArrayList<LessonDatum> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog = null;
    FragmentManager activity;
    private AlertDialog alertDialogAndroid = null;

    Button close_btn;

    File filepdf;
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    String file1;
    WebView webview;


    // Constructor
    public TeacherLessonPlanAdapter(Context c, ArrayList<LessonDatum> arrayList, FragmentManager activity) {
        mContext = c;
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    private void setTitle(String format) {
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
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
                        String saveFilePath = null;
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        Log.d("date", "" + currentTime);
                        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

                        if (isSDSupportedDevice && isSDPresent) {
                            // yes SD-card is present
//                            Utility.ping(mContext, "present");
                            extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                            saveFilePath = String.valueOf(new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + currentTime).getPath());

                        } else {
                            // Sorry
//                            Utility.ping(mContext, "notpresent");

                            File cDir = mContext.getExternalFilesDir(null);
                            saveFilePath = String.valueOf(new File(cDir.getPath() + "/" + currentTime + "Code.pdf"));
                            Log.d("path", saveFilePath);
                        }
//
                        Log.d("path", extStorageDirectory);

                        String fileURL = "http://192.168.1.9:8085/Backend/lessonplanpdf.aspx?ID=" + arrayList.get(position).getID();
                        Log.d("URL", fileURL);

                        Ion.with(mContext)
                                .load(fileURL)  // download url
                                .write(new File(saveFilePath))  // File no path
                                .setCallback(new FutureCallback<File>() {
                                    //                                    @Override
                                    public void onCompleted(Exception e, File file) {

                                        if (file.length() > 0) {
                                            Utility.ping(mContext,"Download complete.");
                                            file1 = file.getPath();
                                            filepdf = file.getAbsoluteFile();
                                            Log.d("file11", "" + filepdf);
//                                            LayoutInflater lInflater = (LayoutInflater) mContext
//                                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                                            final View layout = lInflater.inflate(R.layout.pdf_view, null);
//
//                                            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
//                                            alertDialogBuilderUserInput.setView(layout);
//
//                                            alertDialogAndroid = alertDialogBuilderUserInput.create();
//                                            alertDialogAndroid.setCancelable(false);
//                                            alertDialogAndroid.show();
//                                            Window window = alertDialogAndroid.getWindow();
//                                            window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, 1200);
//                                            WindowManager.LayoutParams wlp = window.getAttributes();
//
//                                            wlp.gravity = Gravity.CENTER;
//                                            wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//                                            window.setAttributes(wlp);
//                                            alertDialogAndroid.show();
//                                            close_btn = (Button) layout.findViewById(R.id.close_btn);
//                                            pdfView = (PDFView) layout.findViewById(R.id.pdfView);
//
////                                            displayFromAsset(file1);
//                                            showCustomNotification();
//
//                                            close_btn.setOnClickListener(new View.OnClickListener()
//
//                                            {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    alertDialogAndroid.dismiss();
//                                                }
//                                            });
                                            showCustomNotification();
                                        } else {
                                            Utility.ping(mContext, "Something error");
                                        }
                                    }


                                });
                    }
                });
                viewHolder.word_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String extStorageDirectory = "";
                        String saveFilePath = null;
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        Log.d("date", "" + currentTime);
                        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

                        if (isSDSupportedDevice && isSDPresent) {
                            // yes SD-card is present
//                            Utility.ping(mContext, "present");
                            extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                            saveFilePath = String.valueOf(new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + currentTime).getPath());

                        } else {
                            // Sorry
//                            Utility.ping(mContext, "notpresent");

                            File cDir = mContext.getExternalFilesDir(null);
                            saveFilePath = String.valueOf(new File(cDir.getPath() + "/" + currentTime + "Word.doc"));
                            Log.d("path", saveFilePath);
                        }
//
                        Log.d("path", extStorageDirectory);

                        final String fileURL = "http://192.168.1.9:8085/Backend/LessonPlanWord.aspx?ID=" + arrayList.get(position).getID();
                        Log.d("URL", fileURL);
                        Ion.with(mContext)
                                .load(fileURL)  // download url
                                .write(new File(saveFilePath))  // File no path
                                .setCallback(new FutureCallback<File>() {
                                    //                                    @Override
                                    public void onCompleted(Exception e, File file) {

                                        if (file.length() > 0) {
                                            Utility.ping(mContext,"Download complete.");
                                            file1 = file.getPath();
                                            Log.d("file11", file1);
                                            filepdf = file.getAbsoluteFile();
//                        LayoutInflater lInflater = (LayoutInflater) mContext
//                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        final View layout = lInflater.inflate(R.layout.pdf_view, null);
//
//                        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
//                        alertDialogBuilderUserInput.setView(layout);
//
//                        alertDialogAndroid = alertDialogBuilderUserInput.create();
//                        alertDialogAndroid.setCancelable(false);
//                        alertDialogAndroid.show();
//                        Window window = alertDialogAndroid.getWindow();
//                        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, 1200);
//                        WindowManager.LayoutParams wlp = window.getAttributes();
//
//                        wlp.gravity = Gravity.CENTER;
//                        wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//                        window.setAttributes(wlp);
//                        alertDialogAndroid.show();
//                        close_btn = (Button) layout.findViewById(R.id.close_btn);
//                        webview = (WebView) layout.findViewById(R.id.webview);
//
//                        webview.setWebViewClient(new WebViewClient() {
//                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                                view.loadUrl(url);
//                                return false;
//                            }
//                        });
//                        webview.getSettings().setJavaScriptEnabled(true);
//                        webview.getSettings().setUseWideViewPort(true);
//                        webview.getSettings().setDisplayZoomControls(true);
//                        webview.getSettings().setLoadsImagesAutomatically(true);
//                        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//                        webview.loadUrl(fileURL);
//
//                        close_btn.setOnClickListener(new View.OnClickListener()
//
//                        {
//                            @Override
//                            public void onClick(View v) {
//                                alertDialogAndroid.dismiss();
//                            }
//                        });
                                            showCustomNotification();
                                        } else {
                                            Utility.ping(mContext, "Something error");
                                        }
                                    }
                                });
                    }
                });
            } catch (Exception e)

            {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromFile(filepdf)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(mContext))
                .load();
    }

    private void showCustomNotification() {
        final int NOTIFICATION_ID = 1;
        String ns = Context.NOTIFICATION_SERVICE;
        final NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(ns);

        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, mContext.getString(R.string.app_name), when);
        Intent notificationIntent = new Intent(mContext, DashBoardActivity.class);
        PendingIntent intent = PendingIntent.getActivity(mContext, 0,
                notificationIntent, 0);


        notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.drawable.downloading_updates)
                        .setContentTitle("Downloading file")
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setContentText(file1);
        // NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }


}


