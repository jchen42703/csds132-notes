import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * An example of a poorly designed GUI.  All code occurs in the same thread.  As a result, when
 * a really slow sort is started, the GUI freezes while that code is being executed.
 */
public class BadSwingGUI {
  private JFrame frame;
  private JPanel sortPanel;
  private static final int SORTSIZE = 50000;
  private int clickCount = 0;
  private int sortCount = 0;
  
  /**
   * Create a frame with two buttons.  One records how often it is clicked.  The other lauches
   * a really slow sort routine and a progress bar that tracks the sort.
   */
  private BadSwingGUI() {
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
        ((JButton)(e.getSource())).setText("Clicked " + (++clickCount) + " times");
      }
    });
    
    // button 2 will start a sort and have a progress bar track the sort
    but2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JProgressBar bar = new JProgressBar(0, SORTSIZE);
        sortPanel.add(new JLabel("Sort " + ++sortCount));
        sortPanel.add(bar);
        frame.pack();
        
        int[] a = new int[SORTSIZE];
        for (int i = 0; i < a.length; i++) {
          a[i] = (int)(Math.random() * a.length);
        }
        slowSort(a, bar);
      }
    });
    frame.setSize(300,100);
    frame.setVisible(true);
  }
  
  /**
   * Launches the GUI
   */
  public static void main(String[] args) {
    new BadSwingGUI();
  }
  
  /**
   * A really slow sort.
   * @param array  the array to sort
   * @param bar    the progress bar used to track the sort progress
   */
  private void slowSort(int[] array, JProgressBar bar) {
    // this loop is a really slow way to sort
    for (int i = 0; i < array.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < array[min])
          min = j;
      }
      int temp = array[i];
      array[i] = array[min];
      array[min] = temp;
    
      if (((i + 1) % 500) == 0)           // update the progress bar each 500 elements
        bar.setValue(i+1);
    }
    bar.setValue(SORTSIZE);               // sort is done so update the progress bar
  } 
}

