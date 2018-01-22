package com.anandniketan.anandniketanskool360shilajTeacher.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.anandniketan.anandniketanskool360shilajTeacher.AsyncTasks.LoginAsyncTask;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.LoginModel;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.AppConfiguration;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;
import com.anandniketan.anandniketanskool360shilajTeacher.databinding.ActivityLoginBinding;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginAsyncTask loginAsyncTask = null;
    private Context mContext;
    private ProgressDialog progressDialog;
    private ArrayList<LoginModel> logindetailModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mContext = this;
        binding.UserNameEdt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user, 0, 0, 0);
        checkUnmPwd();
    }
    public void login(View view) {
        if (Utility.isNetworkConnected(mContext)) {

            if (!binding.UserNameEdt.getText().toString().equalsIgnoreCase("")) {
                if (!binding.PasswordEdt.getText().toString().equalsIgnoreCase("")) {
                    if (binding.PasswordEdt.getText().toString().length() >= 3 && binding.PasswordEdt.getText().toString().length() <= 13) {
                        progressDialog = new ProgressDialog(mContext);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    HashMap<String, String> params = new HashMap<String, String>();
                                    params.put("UserID", binding.UserNameEdt.getText().toString().trim());
                                    params.put("Password", binding.PasswordEdt.getText().toString().trim());

                                    loginAsyncTask = new LoginAsyncTask(params);
                                    logindetailModels = loginAsyncTask.execute().get();

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            if (logindetailModels.size() > 0) {
//                              TODO: Store result values for future use

                                                AppConfiguration.Logintype = logindetailModels.get(0).getType();

                                                if (binding.RememberChk.isChecked()) {
                                                    saveUserNamePwd(binding.UserNameEdt.getText().toString(), binding.PasswordEdt.getText().toString());
                                                }
                                                Utility.setPref(mContext, "StaffID", logindetailModels.get(0).getStaffID());
                                                Utility.setPref(mContext, "Emp_Code", logindetailModels.get(0).getEmp_Code());
                                                Utility.setPref(mContext, "Emp_Name", logindetailModels.get(0).getEmp_Name());
                                                Utility.setPref(mContext, "DepratmentID", logindetailModels.get(0).getDepratmentID());
                                                Utility.setPref(mContext, "DesignationID", logindetailModels.get(0).getDesignationID());
                                                Utility.setPref(mContext, "DeviceId", logindetailModels.get(0).getDeviceId());
                                                Utility.setPref(mContext, "LoginType", logindetailModels.get(0).getType());

                                                Utility.pong(mContext, "Login Successful");
                                                Intent intentDashboard = new Intent(LoginActivity.this, DashBoardActivity.class);
                                                startActivity(intentDashboard);

                                                finish();
                                            } else {
                                                Utility.pong(mContext, "Invalid Credentials. Please try again...");
                                            }
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } else {
                        binding.PasswordEdt.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake));
                        Utility.ping(mContext, "Password must be 3 to 13 character");
                    }
                } else {
                    binding.PasswordEdt.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake));
                    Utility.ping(mContext, "Enter Password");
                }
            } else {
                binding.UserNameEdt.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake));
                Utility.ping(mContext, "Enter Username");
            }
        } else {
            Utility.ping(mContext, "Network not available");
        }
//        }
    }


    public void checkUnmPwd() {
        if (!Utility.getPref(mContext, "unm").equalsIgnoreCase("")) {
            Intent intentDashboard = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(intentDashboard);
            finish();
        }
    }

    public void saveUserNamePwd(String unm, String pwd) {
        Utility.setPref(mContext, "unm", unm);
        Utility.setPref(mContext, "pwd", pwd);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
