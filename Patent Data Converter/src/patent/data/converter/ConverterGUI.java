package patent.data.converter;

import java.awt.EventQueue;
import java.io.File;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.Timer;
import patent.data.converter.utilities.Record;
import patent.data.converter.utilities.Tools;

/**
 *
 * @author Nano
 */
public class ConverterGUI extends JFrame {

    //<editor-fold desc=" Custom Variable Declarations ">
    private File currentDirectory;
    private static HashMap records;
    private static Record currentRecord;
    private static Timer t;
    //</editor-fold>

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        records = new HashMap();
        currentRecord = new Record();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                //System.out.println(info.getName());
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConverterGUI().setVisible(true);
            }
        });
    }

    public void resetBarValue() {
        loadingProgressBar.setValue(0);
    }

    /**
     *
     * @param folder
     */
    public void openDirectory(File folder) {
        File[] files;

        resetBarValue();

        if (folder.isDirectory()) {
            records = new HashMap();
            files = folder.listFiles();

            /**
             * Opens and parses all the files.
             */
            Runnable openingFiles = new Runnable() {
                /**
                 *
                 */
                @Override
                public void run() {
                    int counter = 0;
                    int value;
                    double percent;
                    Record r;
                    String filePath;
                    int eventsTimed = 0;
                    int estimate = 0;
                    double time = 0;
                    long start = 0;
                    long finish = 0;
                    long duration = 0;
                    long sum = 0;
                    long average = 0;
                    boolean timed = false;
                    for (File f : files) {
                        if (f.isFile()) {
                            filePath = f.getAbsolutePath();
                            if ("xml".equals(Tools.getExtension(filePath))) {
                                System.out.println(Tools.Contstants.ANSI_GREEN + "Reading: "
                                        + filePath + Tools.Contstants.ANSI_RESET);
                                r = new Record();
                                if (!timed) {
                                    start = System.nanoTime();
                                }
                                r.parse(filePath);
                                try {
                                    records.put(r.getRecordID(), r);
                                    if (eventsTimed < 10) {
                                        finish = System.nanoTime();
                                        duration = finish - start;
                                        sum += duration;
                                        eventsTimed++;
                                        if (eventsTimed == 10) {
                                            average = (long) ((double) (sum) / 10.0);
                                            time = ((double) (average) / (1000000000.0));
                                            estimate = (int) (files.length * time);
                                            System.out.println("Average: " + average
                                                    + " Time: " + time + "Estimate" + estimate);
                                            if (files.length > 100) {
                                                JOptionPane.showMessageDialog(null, "The program should take about " + estimate + " seconds.");
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println(Tools.Contstants.ANSI_RED + "Error: "
                                            + e + Tools.Contstants.ANSI_RESET);
                                }
                            } else {
                                System.out.println(Tools.Contstants.ANSI_RED + filePath
                                        + " is not an XML File (" + Tools.getExtension(filePath)
                                        + ")" + Tools.Contstants.ANSI_RESET);
                            }
                        }
                        counter++;
                        percent = ((double) counter) / ((double) files.length);
                        value = (int) (percent * 100.0);
                        loadingProgressBar.setValue(value);
                    }
                }
            };
            Thread t = new Thread(openingFiles);
            t.start();
        }
    }

    /**
     *
     */
    public ConverterGUI() {
        currentDirectory = null;
        initComponents();
    }

    /**
     *
     */
    public void initGUI() {
        File f = chooseGraphicDirectory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        folderChooser = new javax.swing.JFileChooser();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        tabbedPane = new javax.swing.JTabbedPane();
        recordPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputDocOptionsPanel = new javax.swing.JPanel();
        docTypeBox = new javax.swing.JCheckBox();
        docNumBox = new javax.swing.JCheckBox();
        appNumBox = new javax.swing.JCheckBox();
        engTitleBox = new javax.swing.JCheckBox();
        frTitleBox = new javax.swing.JCheckBox();
        inventorsBox = new javax.swing.JCheckBox();
        ownersBox = new javax.swing.JCheckBox();
        applicantsBox = new javax.swing.JCheckBox();
        agentBox = new javax.swing.JCheckBox();
        issuedBox = new javax.swing.JCheckBox();
        reissuedBox = new javax.swing.JCheckBox();
        filedBox = new javax.swing.JCheckBox();
        openToPubInspBox = new javax.swing.JCheckBox();
        examReqBox = new javax.swing.JCheckBox();
        reExamCertBox = new javax.swing.JCheckBox();
        canPatClassBox = new javax.swing.JCheckBox();
        intPatentClassBox = new javax.swing.JCheckBox();
        patentCoopTreatyBox = new javax.swing.JCheckBox();
        appPriorityDataBox = new javax.swing.JCheckBox();
        availOfLicBox = new javax.swing.JCheckBox();
        recordViewPanel = new javax.swing.JPanel();
        docNumSearchBox = new javax.swing.JTextField();
        docNumSearchLbl = new javax.swing.JLabel();
        searchDocNumBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        docTypeTxtBox = new javax.swing.JTextField();
        docNumTxtBox = new javax.swing.JTextField();
        appNumTxtBox = new javax.swing.JTextField();
        engTitleTxtBox = new javax.swing.JTextField();
        frTitleTxtBox = new javax.swing.JTextField();
        investorTxtBox = new javax.swing.JTextField();
        ownersTxtBox = new javax.swing.JTextField();
        applicantsTxtBox = new javax.swing.JTextField();
        agentTxtBox = new javax.swing.JTextField();
        issuedTxtBox = new javax.swing.JTextField();
        reissuedTxtBox = new javax.swing.JTextField();
        filedTxtBox = new javax.swing.JTextField();
        openToPubTxtBox = new javax.swing.JTextField();
        examRequestTxtBox = new javax.swing.JTextField();
        reExamCertTxtBox = new javax.swing.JTextField();
        canPatClassTxtBox = new javax.swing.JTextField();
        intPatentClassTxtBox = new javax.swing.JTextField();
        patCoopTreTxtBox = new javax.swing.JTextField();
        appPrioDataTxtBox = new javax.swing.JTextField();
        availOfLicTxtBox = new javax.swing.JTextField();
        docTypeLbl = new javax.swing.JLabel();
        docNumLbl = new javax.swing.JLabel();
        appNumLbl = new javax.swing.JLabel();
        engTitleLbl = new javax.swing.JLabel();
        frTitleLbl = new javax.swing.JLabel();
        invLbl = new javax.swing.JLabel();
        ownersLbl = new javax.swing.JLabel();
        applicantsLbl = new javax.swing.JLabel();
        agentLbl = new javax.swing.JLabel();
        issuedLbl = new javax.swing.JLabel();
        reissuedLbl = new javax.swing.JLabel();
        filedLbl = new javax.swing.JLabel();
        openToPubLbl = new javax.swing.JLabel();
        examReqLbl = new javax.swing.JLabel();
        reExamCertLbl = new javax.swing.JLabel();
        canPatClassLbl = new javax.swing.JLabel();
        intPatClassLbl = new javax.swing.JLabel();
        patCoopTreatyLbl = new javax.swing.JLabel();
        appPrioDataLbl = new javax.swing.JLabel();
        availOfLicLbl = new javax.swing.JLabel();
        loadingProgressBar = new javax.swing.JProgressBar();
        exportBtn = new javax.swing.JButton();
        tab2Panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openFolderItem = new javax.swing.JMenuItem();
        exportDataItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        viewMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tabbedPane.setToolTipText("Other Data Panel");

        recordPanel.setToolTipText("Record Data Panel");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        docTypeBox.setText("(12) Document Type");

        docNumBox.setText("Document Number");

        appNumBox.setText("Application Number");

        engTitleBox.setText("English Title");

        frTitleBox.setText("French Title");

        inventorsBox.setText("Inventors");

        ownersBox.setText("Owners");

        applicantsBox.setText("Applicants");

        agentBox.setText("Agent");

        issuedBox.setText("Issued");

        reissuedBox.setText("Reissued");

        filedBox.setText("Filed");

        openToPubInspBox.setText("Open To Public Inspection");

        examReqBox.setText("Examination Requested");

        reExamCertBox.setText("Re-examination Certificate");

        canPatClassBox.setText("Canadian Patent Classification");

        intPatentClassBox.setText("International Patent Classification");

        patentCoopTreatyBox.setText("Patent Cooperation Treaty");

        appPriorityDataBox.setText("Application Priority Data");

        availOfLicBox.setText("Availability of License");

        javax.swing.GroupLayout outputDocOptionsPanelLayout = new javax.swing.GroupLayout(outputDocOptionsPanel);
        outputDocOptionsPanel.setLayout(outputDocOptionsPanelLayout);
        outputDocOptionsPanelLayout.setHorizontalGroup(
            outputDocOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputDocOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outputDocOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(docNumBox)
                    .addComponent(ownersBox)
                    .addComponent(applicantsBox)
                    .addComponent(appNumBox)
                    .addComponent(docTypeBox)
                    .addComponent(frTitleBox)
                    .addComponent(engTitleBox)
                    .addComponent(inventorsBox)
                    .addComponent(agentBox)
                    .addComponent(reissuedBox)
                    .addComponent(canPatClassBox)
                    .addComponent(intPatentClassBox)
                    .addComponent(filedBox)
                    .addComponent(issuedBox)
                    .addComponent(examReqBox)
                    .addComponent(openToPubInspBox)
                    .addComponent(reExamCertBox)
                    .addComponent(patentCoopTreatyBox)
                    .addComponent(appPriorityDataBox)
                    .addComponent(availOfLicBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        outputDocOptionsPanelLayout.setVerticalGroup(
            outputDocOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputDocOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(docTypeBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(docNumBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appNumBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(engTitleBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frTitleBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventorsBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownersBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applicantsBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agentBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(issuedBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reissuedBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filedBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openToPubInspBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(examReqBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reExamCertBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canPatClassBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intPatentClassBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patentCoopTreatyBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appPriorityDataBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(availOfLicBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(outputDocOptionsPanel);

        recordViewPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        docNumSearchBox.setToolTipText("Search Using Document Number");
        docNumSearchBox.setName("Search Box"); // NOI18N

        docNumSearchLbl.setText("Document Number to Search:");

        searchDocNumBtn.setText("Search");
        searchDocNumBtn.setToolTipText("Search");
        searchDocNumBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDocNumBtnActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        docTypeTxtBox.setEditable(false);
        docTypeTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        docNumTxtBox.setEditable(false);
        docNumTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        appNumTxtBox.setEditable(false);
        appNumTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        engTitleTxtBox.setEditable(false);
        engTitleTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        frTitleTxtBox.setEditable(false);
        frTitleTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        investorTxtBox.setEditable(false);
        investorTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        ownersTxtBox.setEditable(false);
        ownersTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        applicantsTxtBox.setEditable(false);
        applicantsTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        agentTxtBox.setEditable(false);
        agentTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        issuedTxtBox.setEditable(false);
        issuedTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        reissuedTxtBox.setEditable(false);
        reissuedTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        filedTxtBox.setEditable(false);
        filedTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        openToPubTxtBox.setEditable(false);
        openToPubTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        examRequestTxtBox.setEditable(false);
        examRequestTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        reExamCertTxtBox.setEditable(false);
        reExamCertTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        canPatClassTxtBox.setEditable(false);
        canPatClassTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        intPatentClassTxtBox.setEditable(false);
        intPatentClassTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        patCoopTreTxtBox.setEditable(false);
        patCoopTreTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        appPrioDataTxtBox.setEditable(false);
        appPrioDataTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        availOfLicTxtBox.setEditable(false);
        availOfLicTxtBox.setBackground(new java.awt.Color(255, 255, 255));

        docTypeLbl.setText("(12) Document Type");
        docTypeLbl.setToolTipText("Document Type");

        docNumLbl.setText("(11) Document Number");
        docNumLbl.setToolTipText("Document Number");

        appNumLbl.setText("(21) Application Number");
        appNumLbl.setToolTipText("Application Number");

        engTitleLbl.setText("(54) English Title");
        engTitleLbl.setToolTipText("English Title");

        frTitleLbl.setText("(54) French Title");
        frTitleLbl.setToolTipText("French Title");

        invLbl.setText("(72) Investor(s) (Country)");
        invLbl.setToolTipText("Investors (Country)");

        ownersLbl.setText("(73) Owner(s) (Country)");
        ownersLbl.setToolTipText("Owners (Country)");

        applicantsLbl.setText("(71) Applicant(s) (Country)");
        applicantsLbl.setToolTipText("Applicants (Country)");

        agentLbl.setText("(74) Agent");
        agentLbl.setToolTipText("Agent");

        issuedLbl.setText("(45) Issued");
        issuedLbl.setToolTipText("Issued");

        reissuedLbl.setText("Reissued");
        reissuedLbl.setToolTipText("Reissued");

        filedLbl.setText("(22) Filed");
        filedLbl.setToolTipText("Filed");

        openToPubLbl.setText("(41) Open to Public Inspection");
        openToPubLbl.setToolTipText("Open to Public Inspection");

        examReqLbl.setText("Examination Requested");
        examReqLbl.setToolTipText("Examination Requested");

        reExamCertLbl.setText("Re-examination Certificate(s)");
        reExamCertLbl.setToolTipText("Re-examination Certificate(s)");

        canPatClassLbl.setText("(52) Canadian Patent Classification");
        canPatClassLbl.setToolTipText("Canadian Patent Classification");

        intPatClassLbl.setText("(51) International Patent Classification");
        intPatClassLbl.setToolTipText("International Patent Classification");

        patCoopTreatyLbl.setText("Patent Cooperation Treaty");
        patCoopTreatyLbl.setToolTipText("Patent Cooperation Treaty");

        appPrioDataLbl.setText("(30) Application Priority Data");
        appPrioDataLbl.setToolTipText("Application Priority Data");

        availOfLicLbl.setText("Availability of License");
        availOfLicLbl.setToolTipText("Availability of License");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(docTypeTxtBox)
                            .addComponent(docTypeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(docNumTxtBox)
                            .addComponent(docNumLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(appNumTxtBox)
                            .addComponent(appNumLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(engTitleTxtBox)
                            .addComponent(engTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(frTitleTxtBox)
                            .addComponent(frTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(investorTxtBox)
                            .addComponent(invLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ownersTxtBox)
                            .addComponent(ownersLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(applicantsTxtBox)
                            .addComponent(applicantsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(agentTxtBox)
                            .addComponent(agentLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(issuedTxtBox)
                            .addComponent(issuedLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reissuedTxtBox)
                            .addComponent(reissuedLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(filedTxtBox)
                            .addComponent(filedLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(openToPubTxtBox)
                            .addComponent(openToPubLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(examRequestTxtBox)
                            .addComponent(examReqLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reExamCertTxtBox)
                            .addComponent(reExamCertLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(canPatClassTxtBox)
                            .addComponent(canPatClassLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(intPatentClassTxtBox)
                            .addComponent(intPatClassLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(patCoopTreTxtBox)
                            .addComponent(patCoopTreatyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(appPrioDataTxtBox)
                            .addComponent(appPrioDataLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(availOfLicTxtBox)
                            .addComponent(availOfLicLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(appNumLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appNumTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(docNumLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(docNumTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(docTypeLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(docTypeTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(invLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(investorTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(frTitleLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(frTitleTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(engTitleLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(engTitleTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(agentLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(applicantsLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(applicantsTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(ownersLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ownersTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(filedLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filedTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(reissuedLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(reissuedTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(issuedLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(issuedTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(reExamCertLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reExamCertTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(examReqLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(examRequestTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(openToPubLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(openToPubTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(patCoopTreatyLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patCoopTreTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(intPatClassLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(intPatentClassTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(canPatClassLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(canPatClassTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(availOfLicLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(availOfLicTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(appPrioDataLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appPrioDataTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout recordViewPanelLayout = new javax.swing.GroupLayout(recordViewPanel);
        recordViewPanel.setLayout(recordViewPanelLayout);
        recordViewPanelLayout.setHorizontalGroup(
            recordViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recordViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(recordViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(recordViewPanelLayout.createSequentialGroup()
                        .addComponent(docNumSearchLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(docNumSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchDocNumBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        recordViewPanelLayout.setVerticalGroup(
            recordViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recordViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(recordViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docNumSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docNumSearchLbl)
                    .addComponent(searchDocNumBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loadingProgressBar.setToolTipText("");
        loadingProgressBar.setStringPainted(true);

        exportBtn.setText("Export");
        exportBtn.setEnabled(false);
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout recordPanelLayout = new javax.swing.GroupLayout(recordPanel);
        recordPanel.setLayout(recordPanelLayout);
        recordPanelLayout.setHorizontalGroup(
            recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recordViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(loadingProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        recordPanelLayout.setVerticalGroup(
            recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recordPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(recordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(recordPanelLayout.createSequentialGroup()
                        .addComponent(loadingProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(recordViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabbedPane.addTab("Record Data", recordPanel);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout tab2PanelLayout = new javax.swing.GroupLayout(tab2Panel);
        tab2Panel.setLayout(tab2PanelLayout);
        tab2PanelLayout.setHorizontalGroup(
            tab2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab2PanelLayout.setVerticalGroup(
            tab2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("tab2", tab2Panel);

        fileMenu.setText("File");

        openFolderItem.setText("Open Folder");
        openFolderItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFolderItemActionPerformed(evt);
            }
        });
        fileMenu.add(openFolderItem);

        exportDataItem.setText("Export Data");
        fileMenu.add(exportDataItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        menuBar.add(editMenu);

        viewMenu.setText("View");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        viewMenu.add(jMenuItem1);

        menuBar.add(viewMenu);

        helpMenu.setText("Help");
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void openFolderItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFolderItemActionPerformed
        currentDirectory = chooseGraphicDirectory();
        openDirectory(currentDirectory);
    }//GEN-LAST:event_openFolderItemActionPerformed

    /**
     *
     * @param evt
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     *
     * @param evt
     */
    private void searchDocNumBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDocNumBtnActionPerformed
        resetTxtBoxes();
        Record r;
        String searchNum;

        searchNum = docNumSearchBox.getText();
        r = null;
        r = (Record) records.get(searchNum);

        if (r == null) {
            JOptionPane.showMessageDialog(null, "No such record found.", "Warning", JOptionPane.CANCEL_OPTION);
        } else {
            docNumTxtBox.setText(r.getDataPoints().getDocumentNumber());
            engTitleTxtBox.setText(r.getDataPoints().getEnglishTitle());
            frTitleTxtBox.setText(r.getDataPoints().getFrenchTitle());
        }
    }//GEN-LAST:event_searchDocNumBtnActionPerformed

    /**
     *
     * @param evt
     */
    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        System.out.println("Exporting");
        
    }//GEN-LAST:event_exportBtnActionPerformed

    /**
     * Resets JTextField boxes
     */
    public void resetTxtBoxes() {
        docTypeTxtBox.setText("");
        docNumTxtBox.setText("");
        appNumTxtBox.setText("");
        engTitleTxtBox.setText("");
        frTitleTxtBox.setText("");
        investorTxtBox.setText("");
        ownersTxtBox.setText("");
        applicantsTxtBox.setText("");
        agentTxtBox.setText("");
        issuedTxtBox.setText("");
        reissuedTxtBox.setText("");
        filedTxtBox.setText("");
        openToPubTxtBox.setText("");
        examRequestTxtBox.setText("");
        reExamCertTxtBox.setText("");
        canPatClassTxtBox.setText("");
        intPatentClassTxtBox.setText("");
        patCoopTreTxtBox.setText("");
        appPrioDataTxtBox.setText("");
        availOfLicTxtBox.setText("");
    }

    /**
     *
     * @param evt
     * @return
     */
    public File chooseGraphicDirectory() {
        File out = null;
        JFileChooser folderChooser = new javax.swing.JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (folderChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("Directory: " + folderChooser.getSelectedFile());
            out = folderChooser.getSelectedFile();
        } else {
            System.out.println("No Option");
        }
        return out;
    }

    //<editor-fold defaultstate="collapsed" desc=" Default Private Variable Declarations ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox agentBox;
    private javax.swing.JLabel agentLbl;
    private javax.swing.JTextField agentTxtBox;
    private javax.swing.JCheckBox appNumBox;
    private javax.swing.JLabel appNumLbl;
    private javax.swing.JTextField appNumTxtBox;
    private javax.swing.JLabel appPrioDataLbl;
    private javax.swing.JTextField appPrioDataTxtBox;
    private javax.swing.JCheckBox appPriorityDataBox;
    private javax.swing.JCheckBox applicantsBox;
    private javax.swing.JLabel applicantsLbl;
    private javax.swing.JTextField applicantsTxtBox;
    private javax.swing.JCheckBox availOfLicBox;
    private javax.swing.JLabel availOfLicLbl;
    private javax.swing.JTextField availOfLicTxtBox;
    private javax.swing.JCheckBox canPatClassBox;
    private javax.swing.JLabel canPatClassLbl;
    private javax.swing.JTextField canPatClassTxtBox;
    private javax.swing.JCheckBox docNumBox;
    private javax.swing.JLabel docNumLbl;
    private javax.swing.JTextField docNumSearchBox;
    private javax.swing.JLabel docNumSearchLbl;
    private javax.swing.JTextField docNumTxtBox;
    private javax.swing.JCheckBox docTypeBox;
    private javax.swing.JLabel docTypeLbl;
    private javax.swing.JTextField docTypeTxtBox;
    private javax.swing.JMenu editMenu;
    private javax.swing.JCheckBox engTitleBox;
    private javax.swing.JLabel engTitleLbl;
    private javax.swing.JTextField engTitleTxtBox;
    private javax.swing.JCheckBox examReqBox;
    private javax.swing.JLabel examReqLbl;
    private javax.swing.JTextField examRequestTxtBox;
    private javax.swing.JButton exportBtn;
    private javax.swing.JMenuItem exportDataItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JCheckBox filedBox;
    private javax.swing.JLabel filedLbl;
    private javax.swing.JTextField filedTxtBox;
    private javax.swing.JFileChooser folderChooser;
    private javax.swing.JCheckBox frTitleBox;
    private javax.swing.JLabel frTitleLbl;
    private javax.swing.JTextField frTitleTxtBox;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel intPatClassLbl;
    private javax.swing.JCheckBox intPatentClassBox;
    private javax.swing.JTextField intPatentClassTxtBox;
    private javax.swing.JLabel invLbl;
    private javax.swing.JCheckBox inventorsBox;
    private javax.swing.JTextField investorTxtBox;
    private javax.swing.JCheckBox issuedBox;
    private javax.swing.JLabel issuedLbl;
    private javax.swing.JTextField issuedTxtBox;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JProgressBar loadingProgressBar;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openFolderItem;
    private javax.swing.JCheckBox openToPubInspBox;
    private javax.swing.JLabel openToPubLbl;
    private javax.swing.JTextField openToPubTxtBox;
    private javax.swing.JPanel outputDocOptionsPanel;
    private javax.swing.JCheckBox ownersBox;
    private javax.swing.JLabel ownersLbl;
    private javax.swing.JTextField ownersTxtBox;
    private javax.swing.JTextField patCoopTreTxtBox;
    private javax.swing.JLabel patCoopTreatyLbl;
    private javax.swing.JCheckBox patentCoopTreatyBox;
    private javax.swing.JCheckBox reExamCertBox;
    private javax.swing.JLabel reExamCertLbl;
    private javax.swing.JTextField reExamCertTxtBox;
    private javax.swing.JPanel recordPanel;
    private javax.swing.JPanel recordViewPanel;
    private javax.swing.JCheckBox reissuedBox;
    private javax.swing.JLabel reissuedLbl;
    private javax.swing.JTextField reissuedTxtBox;
    private javax.swing.JButton searchDocNumBtn;
    private javax.swing.JPanel tab2Panel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
