public class Huntingtons {

    // Returns the maximum number of consecutive repeats of CAG in the DNA string.
    public static int maxRepeats(String dna)
    {
      return 0;
    }

    // Returns a copy of s, with all whitespace (spaces, tabs, and newlines) removed.
    public static String removeWhitespace(String s)
    {
      return s
        .replace(" ", "")
        .replace("\t", "")
        .replace("\n", "");
    }

    // Returns one of these diagnoses corresponding to the maximum number of repeats:
    // "not human", "normal", "high risk", or "Huntington's".
    public static String diagnose(int maxRepeats)
    {
      if      (maxRepeats < 10)  return "not human";
      else if (maxRepeats < 36)  return "normal";
      else if (maxRepeats < 40)  return "high risk";
      else if (maxRepeats < 181) return "Huntington's";
      else                    return "not human";
    }

    // Sample client (see below).
    /*
     Take the name of a file as a command-line argument.
     Read the genetic sequence from the file using the In class.
     Remove any whitespace (spaces, tabs, and newlines).
     Count the number of CAG repeats.
     Print a medical diagnosis in the format below. 
     */
    public static void main(String[] args)
    {
      String fn = args[0];
      String geneSeq = new In(fn).readAll();
      int repeats = maxRepeats(removeWhitespace(geneSeq));
      StdOut.printf("max repeats: %d\n", repeats);
      StdOut.printf("%d\n", diagnose(repeats));
    }
}
