package com.example.mini.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.PowerManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.example.mini.game.logic.GlobalState;
import com.example.mini.game.util.enums.MoveType;
import com.example.mini.game.util.screenMovement.MovementController;
import com.example.mini.game.util.screenMovement.ShipMovement;

import static com.example.mini.game.logic.GlobalState.*;

/**
 * Created by dybisz on 2014-11-23.
 */
public class CustomGlSurfaceView extends GLSurfaceView{
    MovementController movementController;
    public static Context context;
    public static float screenHeight;
    public static float screenWidth;
    protected GameRenderer gameRenderer;
    /**
     * @param context
     */
    public CustomGlSurfaceView(Context context) {
        super(context);
        this.context = context;
        /*
        GameRenderer now takes song path as second parameter
         */
        gameRenderer = new GameRenderer(context);
        movementController = new MovementController();
        setEGLContextClientVersion(2);
        setRenderer(gameRenderer);
    }

    /**
     * @param e
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(GlobalState.isTouch) {
            return movementController.handleMovement(e);
        }
        else
            return true;
    }
//    public boolean onSensorChanged(SensorEvent event){
//
//    }

    public void onClickAudio() {
        gameRenderer.startAudio();
    }

    public void onClickstopAudio(){gameRenderer.stopAudio();}

}
