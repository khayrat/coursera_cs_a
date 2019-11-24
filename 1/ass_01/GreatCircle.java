public class GreatCircle {

//  2rarcsin(sin2(x2−x12)+cosx1cosx2sin2(y2−y12)‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾√)

    public static void main(String[] args) {
      double  r=6371.0;

      double x1 = Math.toRadians(Double.parseDouble(args[0]));
      double y1 = Math.toRadians(Double.parseDouble(args[1]));
      double x2 = Math.toRadians(Double.parseDouble(args[2]));
      double y2 = Math.toRadians(Double.parseDouble(args[3]));

      double s1 = Math.sin((x2-x1)/2);
      s1 = s1*s1;

      double s2 = Math.sin((y2-y1)/2);
      s2 = s2*s2;

      double distance = 2 * r * Math.asin(Math.sqrt(s1 + Math.cos(x1)*Math.cos(x2)*s2));

      System.out.println(distance + " kilometers");

    }
}
