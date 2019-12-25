import java.util.Arrays;

public class BarChartRacer
{
    public static void main(String[] args)
    {
      String fileName = args[0];
      int k = Integer.parseInt(args[1]);

      StdDraw.setCanvasSize(1000, 700);
      StdDraw.enableDoubleBuffering();
      StdAudio.loop("soundtrackA.wav");

      In in = new In(fileName);

      //StdOut.printf("fn: %s\n", fileName);

      String title = in.readLine();
      String xAxis = in.readLine();
      String source = in.readLine();

      BarChart chart = new BarChart(title, xAxis, source);

      while(in.hasNextLine())
      {
        String caption = null;
        in.readLine(); // empty line
        int n = Integer.parseInt(in.readLine());

        Bar[] bars = null; 
        //if (n < k) bars = new Bar[k];
        //else       bars = new Bar[n];
        bars = new Bar[n];

        //StdOut.printf("records: '%d'\n", n);
        
        // read records
        for (int i = 0; i < n; i++)
        {
          String record = in.readLine();
          String[] fields = record.split(",");

          if (i == 0) caption = fields[0];

          String name = fields[1];
          int value = Integer.parseInt(fields[3]);
          String category = fields[4];

          bars[i] = new Bar(name, value, category);
          //StdOut.printf("bar: '%s'\n", bars[i]);
        }
/*
        // padding
        if (n < k)
        {
          for (int i = n; i < k; i++)
            bars[i] = new Bar("", 0, "");
        }
*/
        Arrays.sort(bars);

        // fill chart
        chart.reset();
        chart.setCaption(caption);
        for (int i = n-1; i >= (n-k); i--)
        {
          Bar bar = bars[i];
          if (bar.getValue() > 0)
          {
            chart.add(bar.getName(), bar.getValue(), bar.getCategory());
            //StdOut.printf("add bar to chart: '%s'\n", bar);
          }
        }
/*
*/
        // draw
        StdDraw.clear();
        chart.draw();
        StdDraw.show();
        //StdDraw.pause(10);
        StdDraw.pause(100);
      }
    }
}
