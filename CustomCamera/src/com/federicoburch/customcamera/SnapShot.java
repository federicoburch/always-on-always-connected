//et�s go through the entire example. The following code is written to run on Android 2.2 and higher, 
//but with minor changes, it should run on versions 1.6 and higher. The sections that require higher 
//than 1.6 are noted with comments.

package com.federicoburch.customcamera;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SnapShot extends Activity implements OnClickListener,
SurfaceHolder.Callback, Camera.PictureCallback {
    SurfaceView cameraView;
    SurfaceHolder surfaceHolder;
    Camera camera;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cameraView = (SurfaceView) this.findViewById(R.id.CameraView);
        surfaceHolder = cameraView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
        cameraView.setFocusable(true);
        cameraView.setFocusableInTouchMode(true);
        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
    }
    public void onClick(View v) {
        camera.takePicture(null, null, this);
}
 //We follow this up with the onPictureTaken method as described earlier.   
    public void onPictureTaken(byte[] data, Camera camera) {
        Uri imageFileUri =
         getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
       try {
             OutputStream imageFileOS =
                getContentResolver().openOutputStream(imageFileUri);
             imageFileOS.write(data);
             imageFileOS.flush();
             imageFileOS.close();
       } catch (FileNotFoundException e) {
             Toast t = Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT);
             t.show();
       } catch (IOException e) {
             Toast t = Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT);
             t.show();
   }
       camera.startPreview();
     }
    
    //Last, we need the various SurfaceHolder.Callback methods in which we set up the Camera object.
public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    camera.startPreview();
}
public void surfaceCreated(SurfaceHolder holder) {
    camera = Camera.open();
    try {
        camera.setPreviewDisplay(holder);
        Camera.Parameters parameters = camera.getParameters();
        if (this.getResources().getConfiguration().orientation !=
         Configuration.ORIENTATION_LANDSCAPE)
        {
             parameters.set("orientation", "portrait");
             // For Android Version 2.2 and above
             camera.setDisplayOrientation(90);
             // For Android Version 2.0 and above
             parameters.setRotation(90);
          }
     // Effects are for Android Version 2.0 and higher
        List<String> colorEffects = parameters.getSupportedColorEffects();
        Iterator<String> cei = colorEffects.iterator();
        while (cei.hasNext())
        {
            String currentEffect = cei.next();
            if (currentEffect.equals(Camera.Parameters.EFFECT_SOLARIZE))
            {
            parameters.setColorEffect(Camera.Parameters.EFFECT_SOLARIZE);
break; }
        }
        // End Effects for Android Version 2.0 and higher
            camera.setParameters(parameters);
    }
    catch (IOException exception)
    {
            camera.release();
} }
public void surfaceDestroyed(SurfaceHolder holder) {
    camera.stopPreview();
    camera.release();
}
} // End the Activity

