import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * An example of a good GUI.  All code to update the display is executed in the swing
 * "event dispatch thread".  All other code is executed in a different thread.  As a result,
 * the GUI will respond properly even if there is a lot of stuff being done by the program.
 */
public class GoodSwingGUI {
  private JFrame frame;
  private JPanel sortPanel;
  private static final int SORTSIZE = 50000;
  private int clickCount = 0;
  private int sortCount = 0;
  
  /**
   * Create a frame with two buttons.  One records how often it is clicked.  The other lauches
   * a really slow sort routine and a progress bar that tracks the sort.
   */
  public GoodSwingGUI() {
    // the constructor is not running in the event dispatch thread
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // code here is scheduled to be run by the event dispatch thread
        launchDisplay();
      }
    });
  }
  
  /**
   * Create the display.  This code is the same as the constructor in BadGUI, but it now will
   * be called by the invokeLater above to be run by the event dispatch thread.
   */
  private void launchDisplay() {
    frame = new JFrame();
    JButton but1 = new JButton("Clicked 0 times");
    JButton but2 = new JButton("Start sort");
    sortPanel = new JPanel(new GridLayout(0,2));
    frame.getContentPane().add(but1, "North");
    frame.getContentPane().add(sortPanel, "South");
    frame.getContentPane().add(but2, "Center");
    
    // button 1 will update itself with the number of times it is clicked
    but1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // this code is run by the event dispatch thread
        ((JButton)(e.getSource())).setText("Clicked " + (++clickCount) + " times");
      }
    });
    
    but2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // this code is run by the event dispatch thread
        JProgressBar bar = new JProgressBar(0, SORTSIZE);
        sortPanel.add(new JLabel("Sort " + ++sortCount));
        sortPanel.add(bar);
        frame.pack();
        
        MyWorker worker = new MyWorker(bar);  // worker contains code that should not be run
        worker.execute();                     // by the event dispatch thread.
      }
    });
    frame.setSize(300,100);
    frame.setVisible(true);
  }
  
  /** 
   * A class used in the event dispatch thread to run code in a different thread
   * Used for any code that will take a long time so it does not prevent the GUI from working.
   */
  public class MyWorker extends SwingWorker<Void, Integer> {
    private JProgressBar bar;
    
    /** Store the progress bar for the sort */
    public MyWorker(JProgressBar bar) {
      this.bar = bar;
    }
    
    /** This method is used to run code in a different thread from the event dispatch thread */
    public Void doInBackground() {
      // this code is not run by the event dispatch thread, instead it is run by a different thread
      int[] a = new int[SORTSIZE];
      for (int i = 0; i < a.length; i++) {
        a[i] = (int)(Math.random() * a.length);
      }
      slowSort(a, bar);
      return null;
    }
    
    /** This method is run by the event dispatch thread after doInBackground completes in
      * the other thread.
      */
    public void done() {
      bar.setValue(SORTSIZE);
    }
    
    /**
     * This code is run by the event dispatch thread.  It is used to receive data sent by the
     * other thread.
     * @param data  data sent to this method from the doInBackground method
     */
    protected void process(java.util.List<Integer> data) {
      bar.setValue(data.get(data.size()-1));    // update the progress bar with the last value in data
    }
    
    /**
     * A really slow sort.  This method is called by the doInBackground method above so it
     * will run in a separate thread from the event dispatch thread.
     * @param array  the array to sort
     * @param bar    the progress bar used to track the sort progress
     */
    private void slowSort(int[] array, JProgressBar bar) {
      for (int i = 0; i < array.length - 1; i++) {
        int min = i;
        for (int j = i + 1; j < array.length; j++) {
          if (array[j] < array[min])
            min = j;
        }
        int temp = array[i];
        array[i] = array[min];
        array[min] = temp;
        
        if (((i + 1) % 500) == 0)  // every 500 elements, we need to update the progress bar
          publish(i+1);            // so send the data to the process method above that is run
      }                            // by the event dispatch thread
    } 
  }
    
  /** launches the GUI */
  public static void main(String[] args) {
    new GoodSwingGUI();
  }
  
}

