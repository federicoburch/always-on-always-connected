package com.apress.proandroidmedia.ch2.snapshot;
import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class SnapShot extends Activity implements SurfaceHolder.Callback {
    SurfaceView cameraView;
    SurfaceHolder surfaceHolder;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cameraView = (SurfaceView) this.findViewById(R.id.CameraView);
        surfaceHolder = cameraView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
}
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    }
  public void surfaceCreated(SurfaceHolder holder) {
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}