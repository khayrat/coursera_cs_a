import java.util.Arrays;

public class BarChartRacer
{
    public static void main(String[] args)
    {
      String fileName = args[0];
      int k = Integer.parseInt(args[1]);

      StdDraw.setCanvasSize(1000, 700);
      StdDraw.enableDoubleBuffering();
//      StdAudio.loop("soundtrackA.wav");

      In in = new In(fileName);

      // read the header
      String title = in.readLine();
      String xAxis = in.readLine();
      String source = in.readLine();

      BarChart chart = new BarChart(title, xAxis, source);

      in.readLine(); // empty line

      // read the groups
      while(in.hasNextLine())
      {
        String caption = null;
        int n = Integer.parseInt(in.readLine());

        Bar[] bars = new Bar[n];
        
        // read records
        for (int i = 0; i < n; i++)
        {
          String record = in.readLine();
          String[] fields = record.split(",");

          caption     = fields[0];
          
          String name = fields[1];
          int value   = Integer.parseInt(fields[3]);
          String category = fields[4];
          bars[i] = new Bar(name, value, category);
        }
        
        in.readLine(); // empty line

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
          }
        }

        // draw
        StdDraw.clear();
        chart.draw();
        StdDraw.show();
        StdDraw.pause(10);
        //StdDraw.pause(70);
      }
    }
}
