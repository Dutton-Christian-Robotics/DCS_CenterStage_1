package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
public class PropVisionProcessor implements VisionProcessor {
    public Rect rectLeft = new Rect(0, 120, 100, 360);
    public Rect rectMiddle = new Rect(120, 120, 400, 150);
    public Rect rectRight = new Rect(540, 120, 100, 360);

    PropPosition position = PropPosition.NONE;
    DefenderBot.Alliance alliance = DefenderBot.Alliance.NONE;
    double detectedHue = 0;
    Mat submat = new Mat();
    Mat hsvMat = new Mat();

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    public Object processFrame(Mat frame, long captureTimeNanos) {
	   Imgproc.cvtColor(frame, hsvMat, Imgproc.COLOR_RGB2HSV);

	   double satRectLeft = getAvgSaturation(hsvMat, rectLeft);
	   double satRectMiddle = getAvgSaturation(hsvMat, rectMiddle);
	   double satRectRight = getAvgSaturation(hsvMat, rectRight);

	   if ((satRectLeft > satRectMiddle) && (satRectLeft > satRectRight)) {
		  detectedHue = getAvgHue(hsvMat, rectLeft);
		  alliance = hueToAlliance(detectedHue);
		  return PropPosition.LEFT;
	   } else if ((satRectMiddle > satRectLeft) && (satRectMiddle > satRectRight)) {
		  detectedHue = getAvgHue(hsvMat, rectMiddle);
		  alliance = hueToAlliance(detectedHue);
		  return PropPosition.MIDDLE;
	   }
	   detectedHue = getAvgHue(hsvMat, rectRight);
	   alliance = hueToAlliance(detectedHue);
	   return PropPosition.RIGHT;
    }

    protected double getAvgSaturation(Mat input, Rect rect) {
	   submat = input.submat(rect);
	   Scalar color = Core.mean(submat);
	   return color.val[1];
    }

    protected double getAvgHue(Mat input, Rect rect) {
	   submat = input.submat(rect);
	   Scalar color = Core.mean(submat);
	   return color.val[0];
    }

    private android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
	   int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
	   int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
	   int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
	   int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);
	   return new android.graphics.Rect(left, top, right, bottom);
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight,
					   float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
	   Paint selectedPaint = new Paint();
	   selectedPaint.setColor(Color.RED);
	   selectedPaint.setStyle(Paint.Style.STROKE);
	   selectedPaint.setStrokeWidth(scaleCanvasDensity * 4);
	   Paint nonSelectedPaint = new Paint(selectedPaint);
	   nonSelectedPaint.setColor(Color.GREEN);
	   android.graphics.Rect drawRectangleLeft = makeGraphicsRect(rectLeft,
			 scaleBmpPxToCanvasPx);
	   android.graphics.Rect drawRectangleMiddle = makeGraphicsRect(rectMiddle,
			 scaleBmpPxToCanvasPx);
	   android.graphics.Rect drawRectangleRight = makeGraphicsRect(rectRight,
			 scaleBmpPxToCanvasPx);
	   position = (PropPosition) userContext;
	   switch (position) {
		  case LEFT:
			 canvas.drawRect(drawRectangleLeft, selectedPaint);
			 canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
			 canvas.drawRect(drawRectangleRight, nonSelectedPaint);
			 break;
		  case MIDDLE:
			 canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
			 canvas.drawRect(drawRectangleMiddle, selectedPaint);
			 canvas.drawRect(drawRectangleRight, nonSelectedPaint);
			 break;
		  case RIGHT:
			 canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
			 canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
			 canvas.drawRect(drawRectangleRight, selectedPaint);
			 break;

		  case NONE:
			 canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
			 canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
			 canvas.drawRect(drawRectangleRight, nonSelectedPaint);
			 break;
	   }
    }

    public PropPosition getPosition() {
	   return position;
    }

    public DefenderBot.Alliance getAlliance() {
	   return alliance;
    }

    public double getDetectedHue() {
	   return detectedHue;
    }

    public DefenderBot.Alliance hueToAlliance(double h) {
	   if (h > 50) {
		  return DefenderBot.Alliance.BLUE;
	   } else {
		  return DefenderBot.Alliance.RED;
	   }
    }

    public enum PropPosition {
	   NONE,
	   LEFT, MIDDLE, RIGHT
    }

//    public enum Alliance {
//	   NONE,
//	   RED,
//	   BLUE
//    }
}