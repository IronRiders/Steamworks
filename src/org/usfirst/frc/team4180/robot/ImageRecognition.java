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
	
	private class ContourData
	{
		public Rect boudingRect;
		
		public double x;
		public double y;
		public double width;
		public double height;
		public double area;
	}
	
	private class Pair<A, B>
	{
		public Pair(A a, B b) {
			this.a = a;
			this.b = b;	
		}
		
		public A a;
		public B b;
	}
	
	public ImageRecognition()
	{
		thread = new Thread(this::cameraThread);
		thread.start();
	}
	
	public ContourData getContourData(MatOfPoint contour)
	{
		ContourData contourData = new ContourData();
		
		double minX, maxX, minY, maxY;
		minX = minY = Double.MAX_VALUE;
		maxX = maxY = Double.MIN_VALUE;
		
		Point[] points = contour.toArray();
		
		for (int i = 0; i < points.length; ++i) {
			minX = Math.min(minX, points[i].x);
			minY = Math.min(minY, points[i].y);
			maxX = Math.max(maxX, points[i].x);
			maxY = Math.max(maxY, points[i].y);
		}
		
		contourData.boudingRect = Imgproc.boundingRect(contour);
		contourData.x = minX;
		contourData.y = minY;
		contourData.width = maxX - minX;
		contourData.height = maxY - minY;
		contourData.area = Imgproc.contourArea(contour);
		
		return contourData;
	}
	
//	public double calculateAngleToTarget(ArrayList<MatOfPoint> list)
//	{
//		if (list.size() != 2) {
//			return Double.NaN;
//		}
		
	//	ArrayList<Pair<>>
//	}
	
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
