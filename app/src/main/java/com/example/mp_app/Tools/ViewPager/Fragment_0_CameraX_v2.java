package com.example.mp_app.Tools.ViewPager;

import static android.media.MediaRecorder.VideoSource.CAMERA;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.LifecycleCameraController;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.example.mp_app.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
public class Fragment_0_CameraX_v2 extends Fragment {
    PreviewView previewView;//null ptr error
    private ImageCapture imageCapture;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    ProcessCameraProvider myCameraProvider;
    LifecycleCameraController controller;
    public Fragment_0_CameraX_v2() {
        // Required empty public constructor
    }

    public void work(){
        imageCapture.takePicture(ContextCompat.getMainExecutor(getContext()), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                super.onCaptureSuccess(image);
                // get and do with image
                image.close();
                System.out.println("work()");
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_camerax, container, false);

        //getActivity()  vs getContext()??
        if (ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {android.Manifest.permission.CAMERA}, CAMERA);
        }

        previewView = view.findViewById(R.id.previewView);
        controller = new LifecycleCameraController(getContext());
        controller.bindToLifecycle(this);
        cameraProviderFuture = ProcessCameraProvider.getInstance(view.getContext());
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                myCameraProvider = cameraProvider;



                Preview preview = new Preview.Builder().build();

                imageCapture = new ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build();

                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(view.getContext()));

        //
        //controller = new LifecycleCameraController(getContext());
        //controller.bindToLifecycle((LifecycleOwner) getContext());
//        cameraProvider = ProcessCameraProvider.getInstance(getContext());
//        cameraProvider.addListener(()->{
//            try{
//
//            } catch (Exception e){
//
//            }
//        }, ContextCompat.getMainExecutor(getContext()));
        //

        return view;
    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);
        myCameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview, imageCapture);
    }
    public void onPause() {
        super.onPause();
        myCameraProvider.unbindAll();
        System.out.println();
        System.out.println("onPause()");
        System.out.println();
    }
    public void onStop() {
        System.out.println();
        System.out.println("STOP()");
        System.out.println();
        super.onStop();
    }
    public void onDestroyView() {
        System.out.println();
        System.out.println("VIEW DESTROY()");
        System.out.println();
        myCameraProvider.unbindAll();
        super.onDestroyView();
    }
    public void onDestroy() {
        System.out.println();
        System.out.println("DESTROY()");
        System.out.println();
        super.onDestroy();
    }

//    public class CustomLifecycle  implements LifecycleOwner {
//        private LifecycleRegistry lifecycleRegistry;
//        public CustomLifecycle () {
//            lifecycleRegistry = new LifecycleRegistry(this);
//            lifecycleRegistry.markState(Lifecycle.State.CREATED);
//        }
//        public void doOnResume() {
//            lifecycleRegistry.markState(Lifecycle.State.RESUMED);
//        }
//        @NonNull
//        @Override
//        public Lifecycle getLifecycle() {
//            return lifecycleRegistry;
//        }
//    }
}