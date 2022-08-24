package edu.kh.poly.ex2.model.vo;

public class KHJCalculator implements Calculator {

   @Override
   public int plus(int num1, int num2) {
      int pl = num1 + num2;
      return pl;
   }

   @Override
   public int minus(int num1, int num2) {
      int mi = num1 - num2;
      return mi;
   }

   @Override
   public int multiple(int num1, int num2) {
      int mu = num1 * num2;
      return mu;
   }

   @Override
   public double divide(int num1, int num2) {
      double di = num1 / num2;
      return di;
   }

   @Override
   public double areaOfCircle(double r) {
      double ar = r * r * PI;
      return ar;
   }

   @Override
   public double pow(double a, int b) {
      double po = Math.pow(a, b);
      return po;
   }
   
   
   
   
}
