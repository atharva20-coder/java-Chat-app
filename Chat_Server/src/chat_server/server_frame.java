package chat_server;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.ImageIcon;

public class server_frame extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        ta_chat.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_users = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dekhlo = new javax.swing.JTextArea();
        jPanel2 =  new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back5.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        ta_chat.setWrapStyleWord(true);
        ta_chat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        ta_chat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ta_chatFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(ta_chat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 179, 401, 290));

        b_users.setBackground(new java.awt.Color(37, 211, 102));
        b_users.setText("Online Users");
        b_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_usersActionPerformed(evt);
            }
        });
        jPanel1.add(b_users, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 181, 210, -1));

        b_clear.setBackground(new java.awt.Color(255, 255, 0));
        b_clear.setText("Clear");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });
        jPanel1.add(b_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 475, 401, -1));

        dekhlo.setColumns(20);
        dekhlo.setRows(5);
        dekhlo.setWrapStyleWord(true);
        dekhlo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jScrollPane2.setViewportView(dekhlo);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 210, 214, 288));

        b_start.setBackground(new java.awt.Color(51, 153, 255));
        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_end.setBackground(new java.awt.Color(255, 0, 0));
        b_end.setText("END");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(414, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_start, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(b_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(b_start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_end)
                .addGap(16, 16, 16))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
        
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
        dekhlo.append("\n Online users : \n");
        for (String current_user : users)
        {
            dekhlo.append(  current_user);
            dekhlo.append("\n");
        }    
        
    }//GEN-LAST:event_b_usersActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    private void ta_chatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ta_chatFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ta_chatFocusGained

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				ta_chat.append("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        ta_chat.append("Before " + name + " added. \n");
        users.add(name);
        ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		ta_chat.append("Sending: " + message + "\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JTextArea dekhlo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
