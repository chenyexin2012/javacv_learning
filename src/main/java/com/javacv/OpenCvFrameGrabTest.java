package com.javacv;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class OpenCvFrameGrabTest {

    public static void main(String[] args) {

        OpenCVFrameGrabber grabber = null;
        try {
            grabber = new OpenCVFrameGrabber(0);
            grabber.start();

            CanvasFrame canvas = new CanvasFrame("摄像头");
            canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            canvas.setAlwaysOnTop(true);

            while (true) {
                if(!canvas.isDisplayable()) {
                    grabber.stop();
                    break;
                }
                canvas.showImage(grabber.grab());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } finally {
            if(null != grabber) {
                try {
                    grabber.release();
                    grabber.close();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
