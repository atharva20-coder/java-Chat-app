 package chat_client;

import java.awt.Color;
import java.awt.HeadlessException;
import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.JOptionPane;
import static sun.security.jgss.GSSUtil.login;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sql.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class client_frame extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    public client_frame() 
    {
        initComponents();
        frame.setVisible(false);
         login.setVisible(false);
         profile.setVisible(false);
         setting.setVisible(false);
         timeline.setVisible(false);
         theme.setVisible(true);
         sign.setEnabled(false);
         go.setEnabled(false);
         ne.setVisible(false);
          we.setVisible(false);
          n_phone.setVisible(false);
          jl1.setVisible(false);
          jl2.setVisible(false);
          jl3.setVisible(false);
          update.setVisible(false);
         delete.setVisible(false);
           ne.setVisible(false);
          we.setVisible(false);
          n_phone.setVisible(false);
          jl11.setVisible(false);
          jl2.setVisible(false);
          jl33.setVisible(false);
          update.setVisible(false);
          delete.setVisible(false);
          uo.setVisible(false);
          sat.setVisible(false);
          tf_port.setVisible(false);
          tf_address.setVisible(false);
          tf_username.setEnabled(false);
          tf_password.setEnabled(false);
    }

    private  void dataentry(String finder) {
      
      String tum = new String(s_pass.getPassword());
        try {  
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost/chat";
                Class.forName("com.mysql.jdbc.Driver"); 
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chat","root","root");
           Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String stmt="SELECT name,password,phone_number FROM member where phone_number = '"+finder+"'";
                        
                st.executeQuery(stmt);  
                String sql=stmt;
                ResultSet rs = st.executeQuery(sql);
               
                  if(rs.first())
                  {
                    String a = rs.getString("name");
                    tf_username.setText(a);
                    String h = rs.getString("password"); 
                    String nasa = rs.getString("phone_number");
                    if(h.equalsIgnoreCase(tum) && finder.equals(nasa))
                    {
                    login.setVisible(false);
                    profile.setVisible(false);
                    b_anonymous.setVisible(false);
                    frame.setVisible(true);
                    b_connect.setVisible(false);
                    b_connect.doClick(); 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Corresponding Phone number or Password Wrong!!! Please Try Again");
                        login.setVisible(false);
                        b_anonymous.setVisible(false);
                        frame.setVisible(false);
                        b_connect.setVisible(false);
                        profile.setVisible(true);
                    }
                  }
                else 
                {
                    frame.setVisible(false);
                    profile.setVisible(false);
                    b_anonymous.setVisible(false);
                    login.setVisible(true);
                    JOptionPane.showMessageDialog(null, "New user!! Please SignUp");
                    
                }
               
           } catch (ClassNotFoundException | SQLException e) {
               JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           } 

    }

   
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        welcome = new javax.swing.JPanel();
        go = new javax.swing.JButton();
        sign = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tata = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 =  new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back7.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };
        profile = new javax.swing.JPanel();
        photo = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        s_pass = new javax.swing.JPasswordField();
        jButton7 = new javax.swing.JButton();
        s_phone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 =  new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back1.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };
        login = new javax.swing.JPanel();
        a = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        e = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        f = new javax.swing.JPasswordField();
        g = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back8.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };
        jLabel9 = new javax.swing.JLabel();
        frame = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_username = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_address = new javax.swing.JLabel();
        b_anonymous = new javax.swing.JButton();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        time = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back1.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };
        clear = new javax.swing.JButton();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        theme = new javax.swing.JPanel();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        tf_port = new javax.swing.JTextField();
        tf_password = new javax.swing.JPasswordField();
        lb_port = new javax.swing.JLabel();
        lb_password = new javax.swing.JLabel();
        setting = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        uo = new javax.swing.JButton();
        jl1 = new javax.swing.JLabel();
        ne = new javax.swing.JPasswordField();
        jl2 = new javax.swing.JLabel();
        we = new javax.swing.JTextField();
        jl3 = new javax.swing.JLabel();
        n_phone = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jl11 = new javax.swing.JLabel();
        jl33 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back3.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };
        sat = new javax.swing.JLabel();
        timeline = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        on = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel()
        {
            public void paintComponent(Graphics g)
            {
                ImageIcon im=new ImageIcon("D:\\back2.png");
                Image i=im.getImage();
                g.drawImage(i, 0, 0, this.getSize().width, this.getSize().height, this);
            }
        };

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bharat Chat");
        setName("client"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcome.setBackground(new java.awt.Color(255, 255, 255));
        welcome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        go.setBackground(new java.awt.Color(255, 255, 51));
        go.setText("Log-in");
        go.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });
        welcome.add(go, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 150, -1));

        sign.setBackground(new java.awt.Color(255, 255, 51));
        sign.setText("Sign-Up");
        sign.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));
        sign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signActionPerformed(evt);
            }
        });
        welcome.add(sign, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 140, -1));

        jScrollPane2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jScrollPane2FocusGained(evt);
            }
        });

        tata.setColumns(20);
        tata.setForeground(new java.awt.Color(153, 153, 153));
        tata.setLineWrap(true);
        tata.setRows(5);
        tata.setText("Please Accept the Terms and Condition.");
        tata.setWrapStyleWord(true);
        tata.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        jScrollPane2.setViewportView(tata);

        welcome.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 350, 130));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setForeground(new java.awt.Color(153, 153, 153));
        jCheckBox1.setText(" I Accept");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        welcome.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));
        welcome.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        getContentPane().add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 490));

        profile.setBackground(new java.awt.Color(255, 255, 255));
        profile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        profile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        photo.setBackground(new java.awt.Color(255, 255, 255));
        photo.setFont(new java.awt.Font("Tempus Sans ITC", 0, 3)); // NOI18N
        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/cartoon-violinist-868062.png"))); // NOI18N
        photo.setOpaque(true);
        profile.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 170, 150));

        jButton4.setBackground(new java.awt.Color(255, 0, 51));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Back");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        profile.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 443, 70, 30));

        jButton5.setBackground(new java.awt.Color(255, 255, 0));
        jButton5.setText("Set your Profile Photo");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        profile.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 170, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Password");
        jLabel12.setOpaque(true);
        profile.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 80, 20));

        jButton6.setBackground(new java.awt.Color(255, 255, 0));
        jButton6.setText("Get Set GO !!!");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        profile.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, 110, 30));

        s_pass.setEchoChar('*');
        profile.add(s_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 150, -1));

        jButton7.setBackground(new java.awt.Color(37, 211, 102));
        jButton7.setText("New User/Sign-Up");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(37, 211, 102), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        profile.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 150, 30));
        profile.add(s_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 150, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("Phone Number");
        profile.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 90, 20));
        profile.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 190));

        getContentPane().add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 490));

        login.setBackground(new java.awt.Color(255, 255, 255));
        login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        login.add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 110, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 51));
        jButton3.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        jButton3.setText("------>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        login.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 100, 30));
        login.add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 110, -1));
        login.add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 110, -1));

        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("First  Nmae");
        login.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 250, 20));

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Last Name");
        login.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 250, 20));

        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Phone Number");
        login.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 250, 20));

        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Password");
        login.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 250, 20));

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Confirm Password");
        login.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 250, 20));

        f.setEchoChar('*');
        login.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 110, -1));

        g.setEchoChar('*');
        login.add(g, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 110, -1));

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        login.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 90, 30));

        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("E-Mail Id");
        login.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 250, 20));
        login.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 110, -1));

        jLabel9.setFont(new java.awt.Font("SimSun-ExtB", 3, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sign-Up");
        jPanel3.add(jLabel9);

        login.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 190));

        getContentPane().add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 490));

        frame.setBackground(new java.awt.Color(255, 255, 255));
        frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        frame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        ta_chat.setWrapStyleWord(true);
        ta_chat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        jScrollPane1.setViewportView(ta_chat);

        frame.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 184, 460, 260));

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });
        frame.add(tf_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 89, -1));

        lb_username.setForeground(new java.awt.Color(255, 255, 255));
        lb_username.setText("Username :");
        frame.add(lb_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 87, 20));

        tf_address.setText("localhost");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });
        frame.add(tf_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 51, 89, -1));

        lb_address.setForeground(new java.awt.Color(255, 255, 255));
        lb_address.setText("Address : ");
        frame.add(lb_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 62, -1));

        b_anonymous.setBackground(new java.awt.Color(102, 153, 255));
        b_anonymous.setText("Anonymous Login");
        b_anonymous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_anonymousActionPerformed(evt);
            }
        });
        frame.add(b_anonymous, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 20, 20));

        tf_chat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        frame.add(tf_chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 456, 352, -1));

        b_send.setBackground(new java.awt.Color(102, 51, 0));
        b_send.setForeground(new java.awt.Color(255, 255, 255));
        b_send.setText("SEND");
        b_send.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 51, 0), 1, true));
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
        frame.add(b_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 455, 100, -1));

        jButton8.setBackground(new java.awt.Color(255, 51, 51));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        frame.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 10, 30));

        time.setBackground(new java.awt.Color(255, 51, 51));
        time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeMouseClicked(evt);
            }
        });
        time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeActionPerformed(evt);
            }
        });
        frame.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 10, 80));

        clear.setBackground(new java.awt.Color(37, 211, 102));
        clear.setText("Clear");
        clear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(37, 211, 102), 1, true));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        b_connect.setBackground(new java.awt.Color(255, 255, 0));
        b_connect.setText("Connect");
        b_connect.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setBackground(new java.awt.Color(255, 0, 51));
        b_disconnect.setText("Disconnect");
        b_disconnect.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 1, true));
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        theme.setBackground(new java.awt.Color(255, 255, 255));
        theme.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select  theme", 0, 0, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        theme.setOpaque(false);
        theme.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rb1.setBackground(new java.awt.Color(102, 102, 102));
        buttonGroup1.add(rb1);
        rb1.setForeground(new java.awt.Color(255, 255, 255));
        rb1.setText("Wabed");
        rb1.setOpaque(false);
        rb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb1ItemStateChanged(evt);
            }
        });
        theme.add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 20));

        rb2.setBackground(new java.awt.Color(102, 102, 102));
        buttonGroup1.add(rb2);
        rb2.setForeground(new java.awt.Color(255, 255, 255));
        rb2.setText("Musta");
        rb2.setOpaque(false);
        rb2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb2ItemStateChanged(evt);
            }
        });
        theme.add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 43, 70, 20));

        tf_port.setText("2222");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        tf_password.setText("jPasswordField1");

        lb_port.setForeground(new java.awt.Color(255, 255, 255));
        lb_port.setText("Port :");

        lb_password.setForeground(new java.awt.Color(255, 255, 255));
        lb_password.setText("Password : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lb_password)
                                .addGap(18, 18, 18)
                                .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(theme, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addComponent(theme, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_password))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        frame.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 190));

        getContentPane().add(frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 490));

        setting.setBackground(new java.awt.Color(255, 255, 255));
        setting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        setting.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        list.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        list.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1. Account Setting", "2. Check for updates ", "3. Suggestion for app", "4. Delete Account" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(list);

        setting.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, 300));

        uo.setBackground(new java.awt.Color(255, 255, 0));
        uo.setText("Set Your Profile");
        uo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));
        uo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uoActionPerformed(evt);
            }
        });
        setting.add(uo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 140, -1));

        jl1.setBackground(new java.awt.Color(255, 255, 255));
        jl1.setText("PassWord");
        jl1.setOpaque(true);
        setting.add(jl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 80, 20));

        ne.setForeground(new java.awt.Color(102, 102, 102));
        setting.add(ne, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 110, -1));

        jl2.setBackground(new java.awt.Color(255, 255, 255));
        jl2.setText("User Name");
        jl2.setOpaque(true);
        setting.add(jl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 80, 20));

        we.setForeground(new java.awt.Color(102, 102, 102));
        we.setText("UserName");
        setting.add(we, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 110, -1));

        jl3.setBackground(new java.awt.Color(255, 255, 255));
        jl3.setText("Phone Number");
        jl3.setOpaque(true);
        setting.add(jl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 80, 20));

        n_phone.setForeground(new java.awt.Color(102, 102, 102));
        n_phone.setText("New-Phone Number");
        setting.add(n_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 110, -1));

        update.setBackground(new java.awt.Color(37, 211, 102));
        update.setText("Update");
        update.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(37, 211, 102), 1, true));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        setting.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 70, -1));

        jButton9.setBackground(new java.awt.Color(255, 0, 0));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Back");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        setting.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 70, 30));

        delete.setBackground(new java.awt.Color(255, 0, 51));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete Account");
        delete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 1, true));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        setting.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 120, 20));

        jl11.setBackground(new java.awt.Color(255, 255, 255));
        jl11.setText("Password");
        jl11.setOpaque(true);
        setting.add(jl11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 80, 20));

        jl33.setBackground(new java.awt.Color(255, 255, 255));
        jl33.setText("Phone Number");
        jl33.setOpaque(true);
        setting.add(jl33, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 80, 20));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sat.setBackground(new java.awt.Color(255, 255, 255));
        sat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/cartoon-violinist-868062.png"))); // NOI18N
        sat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        sat.setOpaque(true);
        jPanel5.add(sat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 140, 120));

        setting.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 190));

        getContentPane().add(setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 490));

        timeline.setBackground(new java.awt.Color(255, 255, 255));
        timeline.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        timeline.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setText("Send");
        timeline.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 260, -1));

        jButton11.setText("Online Users");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        timeline.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 170, -1));

        on.setColumns(20);
        on.setRows(5);
        on.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        jScrollPane4.setViewportView(on);

        timeline.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 190, 300));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        jScrollPane5.setViewportView(jTextArea1);

        timeline.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 260, -1));
        timeline.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 190));

        getContentPane().add(timeline, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
         b_connect.setVisible(true);
          
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_anonymousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_anonymousActionPerformed
       
        tf_username.setText("");
        if (isConnected == false) 
        {
            String anon="anon";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;
            
            tf_username.setText(anon);
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }        
    }//GEN-LAST:event_b_anonymousActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
         ta_chat.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed
        // TODO add your handling code here:
        welcome.setVisible(false);
        frame.setVisible(false);
        login.setVisible(false);
        profile.setVisible(true);
        timeline.setVisible(false);
        b_anonymous.setVisible(false);
       
    }//GEN-LAST:event_goActionPerformed

    private void signActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signActionPerformed
        // TODO add your handling code here:
         login.setVisible(true);
          welcome.setVisible(false);
          timeline.setVisible(false);
          
    }//GEN-LAST:event_signActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String p,q,s,t,coc,mai;
        p=a.getText();
         q=e.getText();
         coc = p+q;
         mai= id.getText();
         int r=Integer.parseInt(d.getText());
           s=new String(f.getPassword());
            t=new String(g.getPassword());
            if(s.equals(t))
            {
               tf_password.setText(t);
               s_pass.setText(t);
            }
           
            try{
     Class.forName("java.sql.DriverManager");
    Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost/chat","root","root");
    Statement stmt=(Statement)con.createStatement();
    String query="insert into member(Name,phone_number,mail,password) values('"+coc+"','"+r+"','"+mai+"','"+t+"');";
    stmt.executeUpdate(query);
    JOptionPane.showMessageDialog(null,"Thank You!! for Signing-Up");
            }
      catch(Exception e){
      JOptionPane.showMessageDialog(this, e.getMessage());
 
    }  
            tf_username.setText(p+" "+q);
         
           
            login.setVisible(false);
           frame.setVisible(false);
           b_connect.setVisible(false);
           profile.setVisible(true);
           //b_connect.doClick();
            b_anonymous.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        login.setVisible(false);
           welcome.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
         JFileChooser file = new JFileChooser();
         file.setCurrentDirectory(new File("user.dir"));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file","png","jpg","jpeg","gif");
         file.addChoosableFileFilter(filter);
         int w = file.showSaveDialog(null);
         if(w==JFileChooser.APPROVE_OPTION)
         {
             photo.setText(file.getSelectedFile().getAbsolutePath());
         }
         String q = photo.getText();
         photo.setIcon(new ImageIcon(q));
         sat.setIcon(new ImageIcon(q));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        dataentry(s_phone.getText());
       
        String show = new String(s_pass.getPassword());
        tf_password.setText(show);
        d.setText(s_phone.getText());
        tf_password.setEchoChar('*');
        /*login.setVisible(false);
        profile.setVisible(false);
        b_anonymous.setVisible(false);
        frame.setVisible(true);
        b_connect.setVisible(false);
        b_connect.doClick();*/       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         profile.setVisible(false);
           welcome.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        profile.setVisible(false); 
        login.setVisible(true);
        welcome.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void rb2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb2ItemStateChanged
        // TODO add your handling code here:
        if(rb2.isSelected())
        {
            /*welcome.setBackground(new Color(102,102,102));
            profile.setBackground(new Color(102,102,102));
            login.setBackground(new Color(102,102,102));
            frame.setBackground(new Color(102,102,102));
            theme.setBackground(new Color(102,102,102));
            setting.setBackground(new Color(102,102,102));
            rb2.setBackground(new Color(102,102,102));
            rb1.setBackground(new Color(102,102,102));
             b_disconnect.setBackground(Color.red);
           // rb2.setText("Default");*/
            welcome.setBackground(Color.black);
            profile.setBackground(Color.black);
            login.setBackground(Color.black);
            frame.setBackground(Color.black);
            theme.setBackground(Color.black);
            setting.setBackground(Color.black);
            ta_chat.setBackground(Color.BLACK);
            ta_chat.setForeground(Color.blue);
             b_disconnect.setBackground(Color.red);
            
        }
        
       
        
    }//GEN-LAST:event_rb2ItemStateChanged

    private void rb1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb1ItemStateChanged
        // TODO add your handling code here:
         if(rb1.isSelected())
        {
             /*welcome.setBackground(new Color(211,211,116));
            profile.setBackground(new Color(211,211,116));
            login.setBackground(new Color(211,211,116));
            frame.setBackground(new Color(211,211,116));
            setting.setBackground(new Color(211,211,116));
            theme.setBackground(new Color(211,211,116));
            rb1.setBackground(new Color(211,211,116));
            rb2.setBackground(new Color(211,211,116));
             b_disconnect.setBackground(Color.red);
            //rb1.setText("Default");*/
            welcome.setBackground(Color.white);
            profile.setBackground(Color.white);
            login.setBackground(Color.white);
            frame.setBackground(Color.white);
            setting.setBackground(Color.white);
            theme.setBackground(Color.white);
            ta_chat.setBackground(Color.white);
            ta_chat.setForeground(Color.black);
             b_disconnect.setBackground(Color.red);
        }
        
    }//GEN-LAST:event_rb1ItemStateChanged

    private void jScrollPane2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jScrollPane2FocusGained
        // TODO add your handling code here:
        tata.setEditable(false);
    }//GEN-LAST:event_jScrollPane2FocusGained

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
         sign.setEnabled(true);
        go.setEnabled(true);
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        profile.setVisible(false); 
        login.setVisible(false);
        welcome.setVisible(false);
        frame.setVisible(false);
        setting.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void uoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uoActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
         file.setCurrentDirectory(new File("user.dir"));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file","png","jpg","jpeg","gif");
         file.addChoosableFileFilter(filter);
         int w = file.showSaveDialog(null);
         if(w==JFileChooser.APPROVE_OPTION)
         {
             photo.setText(file.getSelectedFile().getAbsolutePath());
         }
         String q = photo.getText();
         photo.setIcon(new ImageIcon(q));
         sat.setIcon(new ImageIcon(q));
    }//GEN-LAST:event_uoActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        // TODO add your handling code here:
        /*String obj = list.getSelectedValue();
        if(obj.equalsIgnoreCase("1.Account Setting"))
        {
          ne.setVisible(true);
          we.setVisible(true);
          n_phone.setVisible(true);
           jl1.setVisible(true);
          jl2.setVisible(true);
          jl3.setVisible(true);
        }*/
        
    }//GEN-LAST:event_listMouseClicked

    private void timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeActionPerformed
        // TODO add your handling code here:
           /*login.setVisible(false);
           frame.setVisible(false);
           b_connect.setVisible(false);
           profile.setVisible(false);
           timeline.setVisible(true);*/
    }//GEN-LAST:event_timeActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        /* on.append("\n Online users : \n");
        for (String current_user : users)
        {
            on.append(  current_user);
            on.append("\n");
        } */  
        time.setEnabled(false);
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void timeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Sorry This Feature is Under Developement");
    }//GEN-LAST:event_timeMouseClicked

    private void listMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMousePressed
        // TODO add your handling code here:
          String obj = (String)list.getSelectedValue();
        if(obj.equalsIgnoreCase("1. Account Setting"))
        {
          ne.setVisible(true);
          we.setVisible(true);
          n_phone.setVisible(true);
          jl1.setVisible(true);
          jl2.setVisible(true);
          jl3.setVisible(true);
          update.setVisible(true);
          uo.setVisible(true);
          sat.setVisible(true);
          delete.setVisible(false);
        }
        else if(obj.equalsIgnoreCase("2. Check for update"))
        {
            JOptionPane.showMessageDialog(null, "Checking");
        }
        else if(obj.equalsIgnoreCase("4. Delete Account"))
        {
          ne.setVisible(true);
          we.setVisible(true);
          n_phone.setVisible(true);
          jl11.setVisible(true);
          jl2.setVisible(true);
          jl33.setVisible(true);
          update.setVisible(false);
          delete.setVisible(true);
          
        }
    }//GEN-LAST:event_listMousePressed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here
        String ph = new String(ne.getPassword());
        int p = Integer.parseInt(n_phone.getText());
        String yum = new String(tf_password.getPassword());
        int you = Integer.parseInt(s_phone.getText());
        Connection con = null;
        Statement st = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/chat","root","root");
            st = con.createStatement();
            String sqlconn = "UPDATE `member` SET `Name` = '" +we.getText() +
                   // "',`DOB` = '" + jTextField3.getText() +
                    "',`password` = '" + ph + 
                    "',`phone_number` = '" +p + 
                   
                    "' WHERE `member`.`Name` = '" + tf_username.getText() + 
                    "' and `password` = '" + yum +
                    "' and `phone_number` = '" +you +
                    "'";
            st.executeUpdate(sqlconn);
            
          JOptionPane.showMessageDialog(null,"YOUR RECORD HAS BEEN UPDATED");
          
      }catch(HeadlessException | SQLException ex){
          JOptionPane.showMessageDialog(null,ex.getMessage());
      }  //call view method for refresh
    }//GEN-LAST:event_updateActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        profile.setVisible(false); 
        login.setVisible(false);
        welcome.setVisible(false);
        frame.setVisible(false);
        setting.setVisible(false);
        frame.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
         int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete "+we.getText()+" record ?", "System Notification", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            
         Connection con = null;
        Statement st = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/chat","root","root");
            st = con.createStatement();
            String sqlconn = "delete from `member` where `phone_number` = '" + n_phone.getText() + 
                  //  "' and `DOB` = '" + b1 +
                    "' and `name` = '" + we.getText() +
                    "' and `password` = '" + new String(ne.getPassword()) +
                    "'";
            st.executeUpdate(sqlconn);
          JOptionPane.showMessageDialog(null,"YOUR RECORD HAS BEEN DELETED");
            
      }catch(HeadlessException | SQLException ex){
          JOptionPane.showMessageDialog(null,ex.getMessage());
      }  
        }
    }//GEN-LAST:event_deleteActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a;
    private javax.swing.JButton b_anonymous;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clear;
    private javax.swing.JTextField d;
    private javax.swing.JButton delete;
    private javax.swing.JTextField e;
    private javax.swing.JPasswordField f;
    private javax.swing.JPanel frame;
    private javax.swing.JPasswordField g;
    private javax.swing.JButton go;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl11;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl33;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JList<String> list;
    private javax.swing.JPanel login;
    private javax.swing.JTextField n_phone;
    private javax.swing.JPasswordField ne;
    private javax.swing.JTextArea on;
    private static javax.swing.JLabel photo;
    private javax.swing.JPanel profile;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private static javax.swing.JPasswordField s_pass;
    private static javax.swing.JTextField s_phone;
    private javax.swing.JLabel sat;
    private javax.swing.JPanel setting;
    private javax.swing.JButton sign;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextArea tata;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JPasswordField tf_password;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    private javax.swing.JPanel theme;
    private javax.swing.JButton time;
    private javax.swing.JPanel timeline;
    private javax.swing.JButton uo;
    private javax.swing.JButton update;
    private javax.swing.JTextField we;
    private javax.swing.JPanel welcome;
    // End of variables declaration//GEN-END:variables
}
