public class Birthday {

    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]); // assume 0 <= n <= 365
      int trials = Integer.parseInt(args[1]);
      int[] counts = new int[1000];

      for (int i = trials; i > 0; i--) {
        int people = 0;
        boolean[] birthdays = new boolean[n];

        while (true) {
          // a person enters the room with birthday
          int birthday = (int) (Math.random() * n);
          //System.out.println("person enters with bd: " + birthday);

          // check whether this birthday is among the bds of any other
          if (birthdays[birthday]) {
            break;
          } else {
            birthdays[birthday] = true;
          }
          people++;
        }
        counts[people] += 1;
      }

      int sum = 0;
      for (int i = 0; i < counts.length; i++) {
        sum += counts[i];
        double fraction = (0.0 + sum) / trials;

        System.out.println(i+1 + "   " + counts[i] + "  " + fraction);

        if (fraction >= .5) {
          break;
        } 
      }
    }
}
