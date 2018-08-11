package cic.ipn.mx.cameraexample.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cic.ipn.mx.cameraexample.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    private static int REQUEST_IMAGE_CAPTURE = 999;
    private static int CAMERA_PERMISSION = 999;

    private File photoFile;
    private ImageView ivImage;
    private TextView tvImagePath;

    public static CameraFragment newInstance() {

        Bundle args = new Bundle();

        CameraFragment fragment = new CameraFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.ivImage = view.findViewById(R.id.ivImage);
        this.tvImagePath = view.findViewById(R.id.tvImagePath);

        FloatingActionButton fab = view.findViewById(R.id.fabTakePhoto);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

    }

    private void takePhoto() {

        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

            String[] permissions = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this.getActivity(), permissions, CAMERA_PERMISSION);

        } else {


            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(this.getContext().getPackageManager()) != null) {

                // Create the File where the photo should go
                try {

                    this.photoFile = createImageFile();


                    if (this.photoFile != null) {

                        Uri photoURI = FileProvider.getUriForFile(this.getContext(),
                                "cic.ipn.mx.cameraexample.fileprovider",
                                this.photoFile);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


                    }

                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Log.e("TAG", "Error al intentar captura la imágen");
                }
            }


        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */);

        return image;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.takePhoto();
            } else {
                Toast.makeText(this.getContext(), "No tienes permiso para sacar fotos", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            if (this.photoFile != null) {

                Options bmOptions = new Options();
                Bitmap bitmap = BitmapFactory.decodeFile(this.photoFile.getAbsolutePath(), bmOptions);

                this.ivImage.setImageBitmap(bitmap);
                this.tvImagePath.setText(this.photoFile.getAbsolutePath());

            }

        }

    }
}
