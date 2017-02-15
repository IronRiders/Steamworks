package org.usfirst.frc.team4180.robot;

import java.util.ArrayList;
import java.util.Collections;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class ImageRecognition
{
	private static final int CAMERA_WIDTH = 1280;
	private static final int CAMERA_HEIGHT = 720;
	private static final double HORIZONTAL_FOV = Double.NaN;
	
	private Thread thread;
	
	public void ImageRecognition()
	{
		thread = new Thread(this::cameraThread);
		thread.start();
	}
	
	public double calculateAngleToTarget(ArrayList<MatOfPoint> list)
	{
		if (list.size() != 2) {
			return Double.NaN;
		}
		
		ArrayList<Rect> rects = new ArrayList<Rect>();
		
		for (int i = 0; i < list.size(); ++i) {
			rects.add(Imgproc.boundingRect(list.get(i)));
		}
		
		Collections.sort(rects, (r0, r1) -> ((Integer)r0.x).compareTo((Integer)r1.x));
		
		int xCenter = (rects.get(0).x + rects.get(0).width) + (rects.get(1).x - rects.get(1).width) / 2;
		
		double angle = (xCenter - CAMERA_WIDTH / 2) * HORIZONTAL_FOV / CAMERA_WIDTH;
	}
	
	public void cameraThread()
	{
	    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(CAMERA_WIDTH, CAMERA_HEIGHT);
	    
	    CvSink cvSink = CameraServer.getInstance().getVideo();
	    CvSource outputStream = CameraServer.getInstance().putVideo("Targets", CAMERA_WIDTH, CAMERA_HEIGHT);
	    
	    Mat mat = new Mat();
	    GripPipeline pipeline = new GripPipeline();
	    
	    while(!Thread.interrupted()) {
	        cvSink.grabFrame(mat);       
	        pipeline.process(mat);
	        
	        ArrayList<MatOfPoint> contours = twoBiggestContours(pipeline.filterContoursOutput());
	        
	        for (int i = 0; i < contours.size(); ++i) {
	        	Rect r = Imgproc.boundingRect(contours.get(i));
	        	Imgproc.rectangle(mat, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0.0, 0.0, 255.0, 255), 2);
	        }

	        outputStream.putFrame(mat);
	    }
	}
	
	public ArrayList<MatOfPoint> twoBiggestContours(ArrayList<MatOfPoint> list) {
		ArrayList<MatOfPoint> copy = new ArrayList<MatOfPoint>(list);
		
		if (copy.size() > 2) {
			Collections.sort(list, (m0, m1) -> ((Double)Imgproc.contourArea(m1)).compareTo((Double)Imgproc.contourArea(m0)));
		}
		
		return copy;
	}
}
