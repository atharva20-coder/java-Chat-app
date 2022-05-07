/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author HP
 */
public class choose {
    JFileChooser file_chooser = new JFileChooser();
    StringBuilder sb = new StringBuilder();
    public void pick() throws FileNotFoundException
    {
        if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            File file = file_chooser.getSelectedFile();
            Scanner scan = new Scanner(file);
            while(scan.hasNext())
            {
              sb.append(scan.nextLine());
              sb.append("\n");
            }
            scan.close();
        }
        else
        {
            sb.append("No file Selected");
            
        }
    }
    
}
